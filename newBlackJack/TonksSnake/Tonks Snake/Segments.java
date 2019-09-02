import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Segments
{
    private int x, y;
    private int yVel, xVel;
    public Segments()
    {
        x = 200;
        y = 200;
        yVel = 0;
        xVel = 0;
    }
    public Segments( int it1, int it2, int it3, int it4)
    {
        x = it1;
        y = it2;
        xVel = it3;
        yVel = it4;       
    }
    public void move()
    {
        x+=xVel;
        y+=yVel;
    }
    public void paint(Graphics2D g2)
    {
        g2.drawRect(x, y, 25, 25);
    }
    public void turn(int a)
    {
        if ( a == 0)
        {
            xVel = 2;
            yVel = 0;
        }
        else if ( a == 1)
        {
            xVel = -2;
            yVel = 0;
        }
        else if ( a == 2)
        {
            xVel = 0;
            yVel = 2;
        }
        else
        {
            xVel = 0;
            yVel = -2;
        }
    }
    public int aTurn(int a )
    {
        return 1;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getxVel()
    {
        return xVel;
    }
    public int getyVel()
    {
        return yVel;
    }
}
