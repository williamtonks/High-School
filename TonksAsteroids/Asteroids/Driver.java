import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Driver extends JFrame implements Runnable, KeyListener
{
    Container con = getContentPane();
    Ship ship;
    Missile missileA[] = new Missile[5];
    ArrayList<Asteroid> asteroidA  = new ArrayList <Asteroid>();
    Thread t = new Thread(this);
    public Driver()
    {
        con.setLayout(new FlowLayout());
        addKeyListener(this);
        for ( int x = 0; x < missileA.length; x++)
        {
            missileA[x] = new Missile(300 + (x*20), 300);
        }
        for ( int x = 0; x < 3; x++)
        {
            asteroidA.add( new Asteroid(100 + (x*50), 100));
        }
        ship = new Ship(250,250);
        t.start();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void run()
    {
        try{
            while(true)
            {
                t.sleep(100);
                for ( int x = 0; x < asteroidA.size(); x++)
                {
                    asteroidA.get(x).move();
                }
                ship.go();
                repaint();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void keyReleased(KeyEvent e)
    {}
    
    public void keyPressed(KeyEvent e)
    {
         if (e.getKeyCode()== 37)
         {
             ship.turn(-.2);
         }
         if(e.getKeyCode() == 39)
         {
             ship.turn(.2);
         }
         if(e.getKeyCode() == 38)
         {
             ship.goForward();
         }
    }
    
    public void keyTyped(KeyEvent e)
    {}
    
    public void update(Graphics g)
    {
        paint(g);
    } 

    public void paint(Graphics gr)
    {
        Image i=createImage(getSize().width, getSize().height);
        Graphics2D g2 = (Graphics2D)i.getGraphics();
        Graphics2D g3 = (Graphics2D)i.getGraphics();
        ship.draw(g2);
        for ( int x = 0; x<missileA.length; x++)
        {
            missileA[x].drawM(g2);
        }
        for ( int x = 0; x<asteroidA.size(); x++)
        {
            asteroidA.get(x).drawA(g3);
        }
        g2.dispose();
        g3.dispose();
        gr.drawImage(i, 0, 0, this);
    }

    public static void main(String[] args)
    {
        Driver frame = new Driver();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}