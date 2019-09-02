import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Africa implements Continent 
{
    private int BonusTroops = 3;
    private String name = "Africa";
    private Territory[] myTerritories = new Territory[6];;
    public Africa()
    {
        myTerritories[0] =new Territory("Egypt",650, 420, "South Europe", "Middle East","East Africa", "North Africa",4);
        myTerritories[1] =new Territory("North Africa", 540, 450, "West Europe", "Brazil","Central Africa","East Africa","Egypt",5);
        myTerritories[2] =new Territory("East Africa", 710 ,500,"Middle East", "Egypt", "North Africa", "Central Africa", "South Africa", "Madagascar",6);
        myTerritories[3] =new Territory("Central Africa", 650,550, "North Africa","East Africa","South Africa",3);
        myTerritories[4] =new Territory("South Africa", 680, 650,"Central Africa", "East Africa", "Madagascar",3);
        myTerritories[5] =new Territory("Madagascar", 780, 650, "South Africa", "East Africa",2);
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