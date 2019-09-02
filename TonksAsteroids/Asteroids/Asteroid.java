import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Asteroid
{
    Random ran = new Random();
    private int x, y, xVel, yVel;
    private int[] arX, arY;
    private Polygon poly1;
    private Color color;
    public Asteroid(int x1, int y1)
    {
        x = x1;
        y = y1;
        xVel = ran.nextInt(15);
        yVel = ran.nextInt(15);
        arX = new int[]{x, x-20, x - 15, x -25, x, x+5, x +15, x +23};
        arY = new int[]{y, y+15, y+23, y + 29, y +35, y +27, y + 21, y +11 };
        poly1 = new Polygon(arX, arY, 8);
    }
    
    public void move()
    {
        x+= xVel;
        y+= yVel;
        if ( x < 0)
        {x = 475; 
        } 
        if( x > 475)
        {x = 0;
        }
        if ( y < 0 )
        {y = 465;
        }
        if (y > 465)
        {y = 0;
        }
        arX = new int[]{x, x-20, x - 15, x -25, x, x+5, x +15, x +23};
        arY = new int[]{y, y+15, y+23, y + 29, y +35, y +27, y + 21, y +11 };
        poly1 = new Polygon(arX, arY, 8);
    }

    public void drawA (Graphics2D gr)
    {
        color = Color.gray.darker();
        gr.setColor(color);
        gr.fillPolygon(poly1);
    }
}
