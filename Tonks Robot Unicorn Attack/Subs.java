import java.awt.*;
import java.util.*;
public class Subs
{
    private int xPosition, yPosition, xVel; 
    Random ran = new Random();
    Rectangle rec;
    Image Subs;
    public Subs(int x, int y)
    {
        xVel= -10;
        xPosition = x;
        yPosition = y;
        rec = new Rectangle(x,y, 125, 80);
        Subs = Toolkit.getDefaultToolkit().getImage(getClass().getResource("yellowsubmarine.gif"));
        Subs= Subs.getScaledInstance ( 125, 80, 1);
    }

    public void drawS(Graphics2D g2)
    {
        g2.drawImage(Subs, xPosition, yPosition, null);
    }

    public void moveS()
    {
        xPosition+=xVel;
        rec.setLocation(xPosition, yPosition);
    }

    public Rectangle getRect()
    {
        return rec; 
    }

    public int getX()
    {
        return xPosition;
    }

    public void reset()
    {
        xPosition =ran.nextInt(250)+ 500;
    }

}