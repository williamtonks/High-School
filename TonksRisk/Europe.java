import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Europe implements Continent
{
    private int BonusTroops = 5;
    private String name = "Europe";
    private Territory[] myTerritories = new Territory[7];
    public Europe()
    {
        myTerritories[0] = new Territory("West Europe",500, 340, "North Africa", "South Europe", "North Europe",3);
        myTerritories[1] = new Territory("North Europe", 590, 230, "South Africa", "West Europe","Russia", "Scandinavia","Great Britain",5);
        myTerritories[2] = new Territory("South Europe ", 590, 310, "Middle East", "Egypt", "West Europe", "North Europe","Russia",5);
        myTerritories[3] = new Territory("Russia", 700, 220, "Scandinavia","North Europe","South Europe","Middle East","Afganistan","Ural", 6);
        myTerritories[4] = new Territory("Scandinavia", 590, 125, " Russia", "North Europe", "Great Britain", "Iceland",4);
        myTerritories[5] = new Territory("Iceland", 500, 130, "Greenland", "Great Britain", "Scandinavia",3);
        myTerritories[6] = new Territory("Great Britain", 480, 220, "North Europe", "Scandinavia","Iceland",3);
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
