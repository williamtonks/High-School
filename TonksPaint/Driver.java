
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Driver extends JFrame implements Runnable, MouseMotionListener
{
    Container con = getContentPane();
    Thread t = new Thread(this);
    Random ran = new Random();
    ArrayList<Dots> myDots = new ArrayList<Dots>();
    // All variables and objects declared here.
    public Driver()
    {
        con.setLayout(new FlowLayout());
        addMouseMotionListener(this);
        /*
        All variables initialized here
        All objects instantiated here
        All Listeners added here
         */
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        t.start();
    }

    public void run()
    {
        try{
            while(true)
            {
                t.sleep(67);//Smaller number == faster, larger == slower
                /*
                All motion and collision detection coded here
                 */
                repaint();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void paint(Graphics gr)
    {
        Image i=createImage(getSize().width, getSize().height);
        Graphics2D g = (Graphics2D)i.getGraphics();
        for( int x = 0; x< myDots.size(); x++)
        {
            myDots.get(x).myPaints(g);
        }
        g.dispose();
        gr.drawImage(i, 0, 0, this);
    }

    public static void main(String[] args)
    {
        Driver frame = new Driver();
        frame.setSize(1320, 768);//determines size of screen
        frame.setVisible(true);
    }
    
    public void mouseDragged(MouseEvent e)
    {
        myDots.add( new Dots(e.getX(), e.getY()));
    }

    public void mouseMoved(MouseEvent e)
    {
    }
    public void update(Graphics g)
    {
        paint(g);
    } 
}