import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Color.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Territory 
{
    private String name;
    private int xPosition, yPosition;
    private Rectangle rec;
    private int width = 50, height = 10;
    private ArrayList<String> adjTerritories = new ArrayList<String>();
    private int troops = 1;
    private int numAdj;
    private int myController;
    private Color myControlColor = Color.white;
    public  Territory(String name, int x, int y, String adj1, String adj2, int numA)
    {
        xPosition =x;
        yPosition = y;
        this.name = name;
        adjTerritories.add(adj1);
        adjTerritories.add(adj2);
        numAdj = numA;
        rec = new Rectangle(xPosition, yPosition, 20, 10);
    }  

    public  Territory(String name, int x, int y,String adj1, String adj2, String adj3, int numA)
    {
        xPosition =x;
        yPosition = y;
        this.name = name;
        adjTerritories.add(adj1);
        adjTerritories.add(adj2);
        adjTerritories.add(adj3);
        numAdj = numA;
        rec = new Rectangle(xPosition, yPosition, 20, 10);
    }  

    public  Territory(String name, int x, int y, String adj1, String adj2 ,String adj3, String adj4, int numA)
    {
        xPosition =x;
        yPosition = y;
        this.name = name;
        adjTerritories.add(adj1);
        adjTerritories.add(adj2);
        adjTerritories.add(adj3);
        adjTerritories.add(adj4);
        numAdj = numA;
        rec = new Rectangle(xPosition, yPosition, 20, 10);
    }

    public  Territory(String name, int x, int y, String adj1, String adj2 ,String adj3, String adj4, String adj5 , int numA)
    {
        xPosition =x;
        yPosition = y;
        this.name = name;
        adjTerritories.add(adj1);
        adjTerritories.add(adj2);
        adjTerritories.add(adj3);
        adjTerritories.add(adj4);
        adjTerritories.add(adj5);
        numAdj = numA;
        rec = new Rectangle(xPosition, yPosition, 20, 10);
    }  

    public  Territory(String name, int x, int y, String adj1, String adj2 ,String adj3, String adj4, String adj5, String adj6, int numA)
    {
        xPosition =x;
        yPosition = y;
        this.name = name;
        adjTerritories.add(adj1);
        adjTerritories.add(adj2);
        adjTerritories.add(adj3);
        adjTerritories.add(adj4);
        adjTerritories.add(adj5);
        adjTerritories.add(adj6);
        numAdj = numA;
        rec = new Rectangle(xPosition, yPosition, 20, 10);
    }  

    public  Territory(String name, int x, int y, String adj1, String adj2 ,String adj3, String adj4, String adj5, String adj6 ,String adj7, int numA)
    {
        xPosition =x;
        yPosition = y;
        this.name = name;
        adjTerritories.add(adj1);
        adjTerritories.add(adj2);
        adjTerritories.add(adj3);
        adjTerritories.add(adj4);
        adjTerritories.add(adj5);
        adjTerritories.add(adj6);
        adjTerritories.add(adj7);
        numAdj = numA;
        rec = new Rectangle(xPosition, yPosition, 20, 10);
    } 
    
    public boolean isEmpty()
    {
        return troops <= 0;
    }
    
    public String getMyName()
    {
        return name;
    }
    
    public Rectangle getRect()
    {
        return rec;
    }
    
    public void addTroop()
    {
        troops++;
    }
    
    public void loseTroops( int x)
    {
        troops = troops - x;
    }
    
    public int myController()
    {
        return myController;
    }
    
    public void changeMyControl(Color color, int pNum)
    {
        myController = pNum;
        myControlColor = color;
    }

    public int xPosition()
    {
        return xPosition;
    }

    public int yPosition()
    {
        return yPosition;
    }
    
    public int myTroops()
    {
        return troops;
    }
    public ArrayList adjacentTerritories()
    {
        return adjTerritories;
    }

    public void draw(Graphics2D g2)
    {
        g2.setColor(myControlColor);
        g2.fillRect(xPosition, yPosition, 20, 10);
        g2.setColor(Color.black);
        g2.drawString(name+" "+troops, xPosition, yPosition);
    }
}
