import java.awt.*;
import java.util.*;
public class Ringos
{
    private int xPosition, yPosition, xVel; 
    Random ran = new Random();
    Rectangle rec;
    Image Ringo;
    public Ringos(int x, int y)
    {
        xVel= -10;
        xPosition = x;
        yPosition = y;
        rec = new Rectangle(x,y, 125,100);
        Ringo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Rinog.gif"));
        Ringo= Ringo.getScaledInstance ( 125, 100, 1);
    }

    public void drawR(Graphics2D g2)
    {
        g2.drawImage(Ringo, xPosition, yPosition, null);
    }

    public void moveR()
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
        xPosition =ran.nextInt(200)+ 500;
    }
}