import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.awt.Color;
public class Crayons
{
    Random ran = new Random();
    private int size, xPosition, yPosition; 
    private Color color;
    public Crayons(int x, int y)
    {
        size = ran.nextInt(200)+150;
        xPosition = x * 50;
        yPosition = 500 - size;
        color = new Color(ran.nextInt(256),ran.nextInt(256),ran.nextInt(256)); 
    }
    
    public void draw(Graphics2D g2)
    {
        g2.setColor(color);
        g2.fillRect(xPosition, yPosition, 50, size);
    }
}
