
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Driver extends JFrame implements Runnable, KeyListener
{
    Container con = getContentPane();
    Thread t = new Thread(this);
    ArrayList<Segments> theSnake = new ArrayList<Segments>();
    long passinMe;
    public Driver()
    {
        passinMe = System.currentTimeMillis()+6000;
        addKeyListener(this);
        theSnake.add(new Segments());
        con.setLayout(new FlowLayout());
        t.start();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void run()
    {
        try{
            while(true)
            {
                t.sleep(100);
                if ( passinMe < System.currentTimeMillis())
                {                    
                    int it1 = theSnake.get(theSnake.size()-1).getX();
                    int it2 = theSnake.get(theSnake.size()-1).getY();
                    int it3 = theSnake.get(theSnake.size()-1).getxVel();
                    int it4 = theSnake.get(theSnake.size()-1).getyVel();
                    theSnake.add(new Segments(it1 , it2 + 25, it3, it4));
                    passinMe = System.currentTimeMillis()+6000;
                }
                for ( Segments them: theSnake)
                {
                    them.move();
                }
                repaint();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void update(Graphics g)
    {
        paint(g);
    } 

    public void paint(Graphics gr)
    {
        Image i=createImage(getSize().width, getSize().height);
        Graphics2D g2 = (Graphics2D)i.getGraphics();
        for ( Segments them: theSnake)
        {
            them.paint(g2);
        }
        g2.dispose();
        gr.drawImage(i, 0, 0, this);
    }

    public void keyPressed( KeyEvent e)
    {}

    public void keyTyped( KeyEvent e)
    {}

    public void keyReleased( KeyEvent e)
    {
            if ( e.getKeyCode() == 40)
                theSnake.get(0).turn(2);
            else if ( e.getKeyCode() == 37)
                theSnake.get(0).turn(1);
            else if ( e.getKeyCode() == 39)
                theSnake.get(0).turn(0);
            else 
                theSnake.get(0).turn(3);
            
    }

    public static void main(String[] args)
    {
        Driver frame = new Driver();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}