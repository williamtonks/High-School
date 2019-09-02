import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Tile
{
    private int x, y, minesAdjacent;
    private String minesAdjacentS;
    private Color myColor;
    private Rectangle rect;
    private Image flagI, exploder;
    private boolean mine, clickedOn, flagged, alreadyClicked, exploded;
    public Tile(int xd, int yd)
    {
        x = xd;
        y = yd;
        rect = new Rectangle(x,y,20,20);
        minesAdjacentS = "";
        mine = false;
        clickedOn = false;
        flagged = false;
        alreadyClicked = false;
        myColor = new Color(210, 180, 140);
        flagI = Toolkit.getDefaultToolkit().getImage(getClass().getResource("theFlag.png"));
        flagI = flagI.getScaledInstance (20,20, 1);
        exploder = Toolkit.getDefaultToolkit().getImage(getClass().getResource("explosion.png"));
        exploder = exploder.getScaledInstance (20,20, 1);
    } 

    public void paint( Graphics g)
    {
        g.setColor(myColor);
        g.fillRect(x,y,20,20);
        if(mine == true)
        {
            g.setColor(Color.RED);
            g.fillRect(x,y,20,20);
        }
        if(flagged == true)
            g.drawImage(flagI,x,y,null);
        if(exploded == true)
            g.drawImage(exploder,x,y,null);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,20,20);
        if(clickedOn == true)
            g.drawString(minesAdjacentS,x+7,y+15);
    }

    public void giveMine()
    {
        mine = true;
    }

    public boolean haveMine()
    {
        return mine;
    }
    
    public boolean isFlagged()
    {
        return flagged;
    }

    public Rectangle getRect()
    {
        return rect;
    }

    public void rightClicked()
    {
        if(alreadyClicked == false)
            flagged = !flagged;
    }

    public void leftClicked()
    {
        if(flagged==false)
        {
            clickedOn = true;
            alreadyClicked = true;
            if(mine == true)
                exploded = true;
        }
    }

    public void giveMeValue(int minesNext)
    {
        minesAdjacent += minesNext;
        minesAdjacentS = Integer.toString(minesAdjacent);
    }
}
