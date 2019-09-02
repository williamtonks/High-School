import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Dots
{
    private int x, y; 
    private Color myColor;
    public Dots(int xP, int yP)
    {
        this.x = xP; 
        this.y = yP; 
        myColor = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
    }
    
    public void myPaints( Graphics2D g2)
    {
        g2.setColor(myColor);
        g2.fillOval(x, y, 15, 15);
    }
}
