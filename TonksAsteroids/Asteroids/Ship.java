import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Ship
{
    private int x, y, xVel, yVel;
    private double rotation = 0;
    private int[] arX, arY; 
    Polygon poly1;
    public Ship(int x, int y)
    {
        this.x = x;
        this.y = y;
        arX = new int[] {x, x-10, x+10};
        arY = new int[] {y, y+30, y +30};
        poly1 = new Polygon(arX, arY, 3);
    }

    public void draw(Graphics2D g)
    {
        g.rotate(rotation, x, y+15);
        g.setColor(Color.red);
        g.fillPolygon(poly1);
        g.rotate((rotation*-1), x, y+15);
    }

    public void turn ( double amt)
    {
        rotation+= amt;
        if(rotation> 6.28)
            rotation -= 6.28;
        if(rotation<-6.28)
            rotation+=6.28;
    }

    public void goForward()
    {
        xVel = (10*(int)(Math.cos(Math.toRadians(rotation))));
        yVel = (10*(int)(Math.sin(Math.toRadians(rotation))));
    }

    public void go()
    {
        x+=xVel;
        y+=yVel;
        if (xVel > 0)
            xVel--;
        if(xVel<0)
            xVel++;
        if (yVel > 0)
            yVel--;
        if(yVel<0)
            yVel++;
        arX = new int[] {x, x-10, x+10};
        arY = new int[] {y, y+30, y +30};
        poly1 = new Polygon(arX, arY, 3);
    }
}