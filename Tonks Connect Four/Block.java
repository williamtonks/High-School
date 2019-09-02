import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Block
{
    private int x, y, rowNum, colNum, width, height;
    private Color color;
    private Dot myDot;
    private Rectangle r;
    public Block(int xDimension, int yDimension)
    {
        width = 50;
        height = width;
        x = xDimension*width;
        y = yDimension*height;
        rowNum=xDimension;
        colNum = yDimension;
        color = Color.BLACK;
        myDot = new Dot(0, x, y);
        r = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics2D gt)
    {
        gt.setColor(color);
        gt.drawRect(x, y, width, height);
        myDot.draw(gt);
    }
    //create a method that changes the visibility of the dot
    //if this Block is clicked on.
    //The color of the dot depends on the player.
    public void makeVisible(int player)
    {
        myDot.makeVisible(player);
    }

    public boolean isClicked(Point p)
    {
        return (r.contains(p));
    }
    
    public boolean Gravity()
    {
        return myDot.Gravity();
    }

}
