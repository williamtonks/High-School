import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Tonks extends JFrame implements Runnable, MouseMotionListener, KeyListener
{
    Container con = getContentPane();
    Random rananko = new Random ();
    Thread t = new Thread(this);
    int [] ar = new int[250];
    int [] ar2 = new int[250];
    int flag = 0, ManX = 365, ManY = 270; 
    int PepperX = 100, PepperY = 100, PepperVelX = 5, PepperVelY = 4, MissileX = 10000, MissileY = 10000, MissileVelX = 10, MissileVelY = -13 ;
    Rectangle MrPibb, DrPepper;
    Image space, Pepper, Missile, Man;
    public Tonks()
    {
        con.setLayout(new FlowLayout());
        addMouseMotionListener(this);
        addKeyListener(this);
        space = Toolkit.getDefaultToolkit().getImage(getClass().getResource("space.gif"));
        Pepper = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Pepper.gif"));
        Missile = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Missile.gif"));
        Man = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Man.gif"));
        MrPibb = new Rectangle ( MissileX, MissileY, 120, 120);
        DrPepper = new Rectangle (PepperX, PepperY, 50, 50);
        space = space.getScaledInstance ( 500, 500, 1);
        Pepper = Pepper.getScaledInstance ( 50, 50, 1);
        Missile = Missile.getScaledInstance ( 100, 100, 1);
        Man = Man.getScaledInstance ( 120, 120, 1);
        int x = 0;
        while (x<250)
        {
            ar[x] = rananko.nextInt(500);
            ar2[x] = rananko.nextInt(500);
            x++;
        }
        t.start();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void run()
    {
        try{
            while(true)
            {
                PepperX+=PepperVelX;
                PepperY+=PepperVelY;
                DrPepper.setLocation ( PepperX, PepperY);
                if ( PepperX >= 450)
                {
                    PepperVelX*=-1;
                }
                if ( PepperY >= 450)
                {
                    PepperVelY*=-1;
                }
                if ( PepperX <= 0)
                {
                    PepperVelX*=-1;
                }
                if ( PepperY <= 0)
                {
                    PepperVelY*=-1;
                }
                MissileX+=MissileVelX;
                MissileY+=MissileVelY;
                MrPibb.setLocation ( MissileX, MissileY);
                if ( MissileX >= 500)
                {
                    MissileX = 1000;
                    MissileY = 1000;
                }
                if ( MissileY <= 0)
                {
                    MissileX = 1000;
                    MissileY = 1000;
                }
                if (MrPibb.intersects(DrPepper))
                {                    
                    Pepper = Toolkit.getDefaultToolkit().getImage(getClass().getResource("explosion.gif"));
                    Pepper = Pepper.getScaledInstance (50, 50, 1);
                    flag = 1;
                }
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
        g2.fillRect(0,0, 500, 500);
        g2.setColor(Color.white);
        int x = 0;
        if ( flag == 1)
        {
            g2.drawImage( Man , ManX, ManY, this);
        }
        while ( x<250)
        {
            g2.fillOval( ar[x], ar2[x], 2,2);
            x++;
            g2.fillOval(ar[x], ar2[x],4,4);
            x++;
        }
        g2.drawImage(Pepper, PepperX, PepperY, this);
        g2.drawImage(Missile, MissileX , MissileY, this);
        g2.drawImage(space, 0, 0, this);
        g2.dispose();
        gr.drawImage(i, 0, 0, this);
    }

    public static void main(String[] args)
    {
        Tonks frame = new Tonks();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public void mouseMoved(MouseEvent me)
    {
        int x = 0, xChange, yChange;
        xChange = me.getX() - 250;
        yChange = me.getY() - 250;
        xChange %= 5;
        yChange %= 5;
        while ( x< 250)
        {
            ar[x]+=xChange;
            ar2[x]+=yChange;
            if(ar[x] > 500)
            {
                ar[x] = 0; 
            }
            if(ar2[x] > 500)
            {
                ar2[x] = 0;
            }
            if(ar[x] < 0)
            {
                ar[x] = 500;
            }
            if(ar2[x] < 0)
            {
                ar2[x] = 500;
            }
            x++;
        }
    }

    public void mouseDragged(MouseEvent me)
    {
    }

    public void keyReleased(KeyEvent k)
    {
        if (k.getKeyCode() == 32)
        {
            MissileX = 0;
            MissileY = 500;          
        }
    }

    public void keyPressed(KeyEvent k)
    {

    }

    public void keyTyped(KeyEvent k)
    {

    }
}

