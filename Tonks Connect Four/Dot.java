import java.util.*;
import javax.swing.*;
import java.awt.*;
public class Dot
{
    private int x, y, rowNum, colNum, width, height;
    private Color color;
    private boolean visible;
    public Dot(int player, int xSpot, int ySpot)
    {
        color = Color.WHITE;
        visible = false;
        x = xSpot;
        y = ySpot;
        width = 50;
        height = 50;
    }

    public void draw(Graphics2D gt)
    {
        if(visible)
        {
            gt.setColor(color);
            gt.fillOval(x, y, width, height);
        }
    }
    //create a method that changes the
    //"visibility" of the Dot
    public void makeVisible (int p)
    {
        visible = true;
        if (p==1)
            color = Color.BLUE;
        else
            color = Color.RED;
    }
    
    public boolean Gravity()
    {
        return visible;
    }
}
