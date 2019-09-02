import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Driver extends JFrame implements Runnable
{
    Container con = getContentPane();
    Thread t = new Thread(this);
    int x = 0, y = 0;
    Crayons Crayonaray [] = new Crayons[5];
    public Driver()
    {
        con.setLayout(new FlowLayout());
        for ( x = 0; x < Crayonaray.length; x++)
        Crayonaray[x] = new Crayons(x ,y);
        t.start();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void run()
    {
        try{
            while(true)
            {
                t.sleep(10001);
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
        for ( int x = 0; x < Crayonaray.length; x++)
        Crayonaray[x].draw(g2);
        g2.dispose();
        gr.drawImage(i, 0, 0, this);
    }

    public static void main(String[] args)
    {
        Driver frame = new Driver();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
