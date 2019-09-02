import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Card
{
    private int myNumber, mySuit, x, y;
    private boolean blank;
    private Rectangle rect;
    private Image king, queen, jack, heart, spade, club, diamond;
    public Card(int num, int suit, int xl, int yl)
    {
        x= xl;
        y = yl;
        myNumber = num;
        mySuit = suit;
        blank = true;
        Rectangle rect = new Rectangle (x, y, 60, 100);
        king = Toolkit.getDefaultToolkit().getImage(getClass().getResource("king.png"));
        king = king.getScaledInstance (40,40, 1);
        queen = Toolkit.getDefaultToolkit().getImage(getClass().getResource("queen.png"));
        queen = queen.getScaledInstance (40,40, 1);
        jack = Toolkit.getDefaultToolkit().getImage(getClass().getResource("lumberjack.jpg"));
        jack = jack.getScaledInstance (40,40, 1);
        heart = Toolkit.getDefaultToolkit().getImage(getClass().getResource("heart.png"));
        heart = heart.getScaledInstance (40,40, 1);
        diamond = Toolkit.getDefaultToolkit().getImage(getClass().getResource("diamond.png"));
        diamond = diamond.getScaledInstance (40,40, 1);
        spade = Toolkit.getDefaultToolkit().getImage(getClass().getResource("spade.png"));
        spade = spade.getScaledInstance (40,40, 1);
        club = Toolkit.getDefaultToolkit().getImage(getClass().getResource("club.png"));
        club = club.getScaledInstance (40,40, 1);
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawRect(x,y, 60, 100);
        if(blank == false)
        {
            if(myNumber == 0)
                g.drawImage(king, x + 5, y + 40, null);
            else if(myNumber == 12)
                g.drawImage(queen, x +5, y +40, null);
            else if(myNumber == 11)
                g.drawImage(jack, x + 5, y +40, null);
            else
                g.drawString(Integer.toString(myNumber),x + 5 ,y+40);
            if(mySuit == 0)
                g.drawImage(spade, x + 15, y, null);
            else if(mySuit == 1)
                g.drawImage(club, x + 15, y, null);
            else if(mySuit == 2)
                g.drawImage(heart, x + 15, y, null);
            else
                g.drawImage(diamond, x + 15, y, null);
        }
    }
    
    public void changePosition( int xm, int ym)
    {
        x = xm;
        y = ym; 
        rect = new Rectangle(x, y, 60, 100);
    }
    
    public void goBlank()
    {
        blank = true;
    }
    
    public boolean isBlank()
    {
        return blank;
    }
    
    public int show()
    {
        blank = false;
        if ( myNumber == 11 || myNumber == 12 || myNumber == 0 )
        {
            return 10;
        }
        else
        {
            return myNumber;
        }
    }
    
    public Rectangle getRect()
    {
        return rect;
    }
}
