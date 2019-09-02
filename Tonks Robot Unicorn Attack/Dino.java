import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Dino extends JFrame
{
    private int xPosition, yPosition, yVel; 
    private boolean bool = false;
    Random ran = new Random();
    Image DinoR, DinoJ;
    Rectangle rec;
    Clip Excuse;
    public Dino(int x, int y)
    {
        try{
            URL url = this.getClass().getClassLoader().getResource("excuseme.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Excuse = AudioSystem.getClip();
            Excuse.open(audioIn);
            makeSound();
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        yVel = -10; 
        xPosition = x;
        yPosition = y;
        rec = new Rectangle ( xPosition, yPosition, 10, 10);        
        DinoR = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Dino run.gif"));
        DinoR= DinoR.getScaledInstance ( 75, 75 , 1);
        DinoJ = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Dino jump.gif"));
        DinoJ= DinoJ.getScaledInstance ( 75, 75, 1);
    }

    public void makeSound()
    {
        Excuse.loop(1);
    }

    public void drawDR(Graphics2D g2)
    {
        g2.drawImage(DinoR, xPosition, yPosition, null);
    }

    public void drawDJ(Graphics2D g2)
    {
        g2.drawImage(DinoJ, xPosition, yPosition, null);
    }

    public void move()
    {
        yPosition+=yVel;
        rec.setLocation(xPosition, yPosition);
        if (bool)
        {
            yVel+=5;
        }
        if (yPosition > 425)
        {
            yVel = 0;
            bool = false;
            yPosition = 425;
        }
    }

    public void startJump()
    {
        bool = true;
        yVel= -25;
    }

    public void Jump()
    {
        yPosition+=yVel;
        rec.setLocation(xPosition, yPosition);
    }

    public Rectangle getRect()
    {
        return rec; 
    }

    public boolean getbool()
    {
        return bool;
    }

    public int getY()
    {
        return yPosition;
    }
}
