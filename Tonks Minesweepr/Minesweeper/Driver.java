import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Driver extends JFrame implements Runnable, MouseListener
{
    Container con = getContentPane();
    Thread t = new Thread(this);
    private static int length, width; 
    private static long starttime;
    private int finaltime;
    boolean myTrue;
    boolean gameIsWon;
    Board theBoard; 
    Image title;
    public Driver()
    {
        con.setLayout(new FlowLayout());
        /*
        All variables initialized here
        All objects instantiated here
        All Listeners added here
         */
        finaltime = 0;
        starttime = System.currentTimeMillis();
        myTrue = true;
        addMouseListener(this);
        length = Integer.parseInt(JOptionPane.showInputDialog(null, " What is the size of board you would like by length?"));
        width = Integer.parseInt(JOptionPane.showInputDialog(null, " What is the size of board you would like by width?"));
        theBoard = new Board(length,width);
        gameIsWon = false;
        title = Toolkit.getDefaultToolkit().getImage(getClass().getResource("title.png"));
        title = title.getScaledInstance (100,200, 1);
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
                if(theBoard.gameWon()== true)
                {
                    if(myTrue == true)
                    {
                        gameIsWon = true;
                        long timeMillis = System.currentTimeMillis()-starttime;
                        finaltime = (int)(timeMillis/1000);
                    }
                    myTrue = false;
                }
                else
                {
                    gameIsWon = false;
                }
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
        long timeMillis = System.currentTimeMillis()-starttime;
        long timeSeconds = (long)(timeMillis/1000);
        String timeSecsString = Long.toString(timeSeconds);
        g.drawString(timeSecsString,50,50);
        theBoard.paint(g);
        g.drawImage(title, length * 10 + 50, 0, null);
        if(gameIsWon == true)
        {
            g.setColor(Color.RED);
            g.fillRect( 10, 60, 150, 30);
            g.setColor(Color.BLACK);
            g.drawString("You won in " + finaltime+ " seconds.", 10 , 75);
        }
        g.dispose();
        gr.drawImage(i, 0, 0, this);
    }

    public void mouseClicked(MouseEvent e)
    {
        Point currentPoint = e.getPoint();
        if(SwingUtilities.isRightMouseButton(e))
        {
            for (int d =0 ; d < (length); d++)
            {
                for (int t =0; t < (width); t++)
                {
                    if((theBoard.getTileRect(d,t)).contains(currentPoint))
                    {
                        theBoard.getTile(d,t).rightClicked();
                    }
                }
            }
        }
        if(SwingUtilities.isLeftMouseButton(e))
        {
            for (int d =0 ; d < (length); d++)
            {
                for (int t =0; t < (width); t++)
                {
                    if((theBoard.getTileRect(d,t)).contains(currentPoint))
                    {
                        theBoard.getTile(d,t).leftClicked();
                    }
                }
            }
        }
    }

    public void mouseExited(MouseEvent e)
    {}

    public void mouseEntered(MouseEvent e)
    {}

    public void mouseReleased(MouseEvent e)
    {}

    public void mousePressed(MouseEvent e)
    {}

    public static void main(String[] args)
    {
        Driver frame = new Driver();
        frame.setSize(length * 20 + 200, width * 20 + 200);//determines size of screen
        frame.setVisible(true);
    }

    public void update(Graphics g)
    {
        paint(g);
    } 
}
