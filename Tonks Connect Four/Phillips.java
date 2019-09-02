import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Phillips extends JFrame implements Runnable, MouseListener
{
    //The 10X10 grid contains empty Black boxes.
    //Objective: Place colored dots in columns
    // by the use of mouse clicks.
    //you need to add mouselistener interface, and an player int.
    int player = 1;
    Container con = getContentPane();
    Thread t = new Thread(this);
    Block theGrid[][] = new Block[10][10];
    public Phillips()
    {
        addMouseListener(this);
        con.setLayout(new FlowLayout());
        for (int x = 0; x < theGrid.length; x++)
            for (int y = 0; y < theGrid[x].length; y++)
            {
                theGrid[x][y] = new Block(x, y);
        }
        t.start();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void run()
    {
        try{
            while(true)
            {
                t.sleep(100);
                repaint();
            }
        }
        catch(Exception e){}
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void paint(Graphics gr)
    {
        Image i=createImage(getSize().width, getSize().height);
        Graphics2D g2 = (Graphics2D)i.getGraphics();
        for (int x = 0; x < theGrid.length; x++)
            for (int y = 0; y < theGrid[x].length; y++)
            {
                theGrid[x][y].draw(g2);
        }
        g2.dispose();
        gr.drawImage(i, 0, 0, this);
    }

    public void mousePressed(MouseEvent m){}

    public void mouseClicked(MouseEvent m){}

    public void mouseReleased(MouseEvent m)
    { 
        Point z = m.getPoint();
        for (int x = 0; x < theGrid.length; x++)
            for (int y = 0; y < theGrid[x].length; y++)
            {
                if (theGrid[x][y].isClicked(z))
                {
                    for (int index = 9; index > theGrid.length -11; index--)
                        if(!theGrid[x][index].Gravity())
                        {
                            theGrid[x][index].makeVisible(player);
                            index = -1;
                    }
                }
        }
        if (player == 1)
            player = 2;
        else
            player = 1;
    }

    public void mouseExited(MouseEvent m){}

    public void mouseEntered(MouseEvent m){}

    public static void main(String[] args)
    {
        Phillips frame = new Phillips();
        frame.setSize(560, 560);
        frame.setVisible(true);
    }
}