import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class NorthAm implements Continent 
{
    private int BonusTroops = 5;
    private String name = "NorthAm";
    private Territory[] myTerritories = new Territory[9];
    public NorthAm()
    {
        myTerritories[0] = new Territory("Alaska",40, 100, "Northwest Territory", "Kamchatka", "Alberia", 3);
        myTerritories[1] =new Territory("Northwest Territory", 150, 100,"Alaska", "Alberia", "Ontario", "Greenland", 4);
        myTerritories[2] =new Territory("Greenland", 400 ,80,"Northwest Territory", "Ontario","Quebec", "Iceland", 4);
        myTerritories[3] = new Territory("Alberia", 140,180, "Alaska", "Northwest Territory", "Ontario", "West United States", 4);
        myTerritories[4] = new Territory("Ontario", 250, 180,"Alberia", "Northwest Territory", "West United States", "East United States", "Quebec", "Greenland",6);
        myTerritories[5] = new Territory("Quebec", 320, 180, "Ontario", "East United States", "Greenland",3);
        myTerritories[6] =new Territory("West United States", 150 ,250, "Alberia","Ontario","East United States","Central America",4);
        myTerritories[7] =new Territory("East United States", 275, 250,"Quebec", "Ontario","West United States", "Central America",4);
        myTerritories[8] =new Territory("Central America", 170, 310, "West United States", "East United States","Venezuela", 3);
    }
    public Territory[] getTerritories()
    {
        return myTerritories;
    }
     public int getNumTerritories()
    {
        return myTerritories.length;
    }
    public int getBonusTroops()
    
    {
        return BonusTroops;
    }
    public boolean controls(int player)
    {
        boolean fullControl = true;
        for(int x = 0; x < myTerritories.length; x++)
        {
            if(!(myTerritories[x].myController() == player))
            {
               fullControl = false;
            }
        }
        return fullControl;
    }
}