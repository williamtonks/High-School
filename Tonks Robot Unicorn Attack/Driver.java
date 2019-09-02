import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Driver extends JFrame implements Runnable, KeyListener
{
    Random ran = new Random();
    Dino Dino;
    Subs theSubs[] = new Subs[2];
    Ringos theRingos[] = new Ringos[2];
    Thread t= new Thread(this);
    Rectangle screen;
    long timer;
    int x = 0, y = 0, jumpFlag = 0, endFlag = 0;
    Image i;
    Image back, end;
    Clip TheEnd, Theme;
    public Driver()
    {
        try{
            URL url = this.getClass().getClassLoader().getResource("TheEnd.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            TheEnd = AudioSystem.getClip();
            TheEnd.open(audioIn);
            homerSing();
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        try{
            URL url = this.getClass().getClassLoader().getResource("get_smart_theme.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Theme = AudioSystem.getClip();
            Theme.open(audioIn);
            theme();
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        theme();
        theSubs[0] = new Subs(300, 420);
        theSubs[1] = new Subs(500, 425);
        theRingos[0] = new Ringos(375, 25);
        theRingos[1] = new Ringos(500, 25);
        Dino = new Dino(0, 425);
        addKeyListener (this);  
        back = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Revolver.gif"));
        back = back.getScaledInstance ( 500, 500 , 1);
        end = Toolkit.getDefaultToolkit().getImage(getClass().getResource("mushroom.gif"));
        end = end.getScaledInstance ( 500, 500 , 1);
        screen = new Rectangle(x,y,500, 500);
        t.start();
    }

    public void paint(Graphics g)
    {
        i=createImage(getSize().width, getSize().height);
        Graphics2D g2= (Graphics2D)i.getGraphics();
        g2.drawImage(back, x, y, this);
        for (int index = 0; index < theSubs.length; index++)
            theSubs[index].drawS(g2);
        for( int index = 0; index < theRingos.length; index++)
            theRingos[index].drawR(g2);      
        if (Dino.getY() >= 425) 
            Dino.drawDR(g2);
        else
            Dino.drawDJ(g2);
        if (endFlag == 1)            
            g2.drawImage(end, x, y, null);
        g2.dispose();
        g.drawImage(i, 0, 0 , null);
    }

    public void homerSing()
    {
        TheEnd.loop(1);
    }

    public void theme()
    {
        Theme.loop(1);
    }

    public void run()
    {
        try
        {
            while (true)
            {   
                Dino.move();
                for ( int index = 0; index < theSubs.length; index++)
                {
                    theSubs[index].moveS();
                    if (theSubs[index].getX() < 0)                    
                        theSubs[index].reset();                    
                    if(Dino.getRect().intersects(theSubs[index].getRect()))
                    {
                        endFlag = 1;
                        homerSing();
                    }
                }
                for ( int index = 0; index < theRingos.length; index++)
                {
                    theRingos[index].moveR();
                    if (theRingos[index].getX() < 0)                    
                        theRingos[index].reset();
                    if(Dino.getRect().intersects(theRingos[index].getRect()))
                    {
                        endFlag = 1;
                        homerSing();
                    }
                }
                if (jumpFlag == 1)
                {
                    Dino.startJump();
                    Dino.Jump();
                    Dino.makeSound();
                }
                jumpFlag = 0;
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

    public void keyPressed(KeyEvent e)
    {}

    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode()== 32)
        {
            jumpFlag = 1;
        }
    }

    public void keyTyped(KeyEvent e)
    {}

    public static void main(String[] args)
    {
        Driver driver = new Driver();
        driver.setSize(500, 500);
        driver.setVisible(true);
        driver.setResizable(false);
    }
}