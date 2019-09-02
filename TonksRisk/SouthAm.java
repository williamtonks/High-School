import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class SouthAm implements Continent
{
    private int BonusTroops = 2;
    private String name = "SouthAm";
    private Territory[] myTerritories = new Territory[4];
    public SouthAm()
    {
        myTerritories[0] =new Territory("Venezuela", 280, 410, "Central America", "Brazil", "Peru", 3);
        myTerritories[1] = new Territory("Peru", 275, 500, "Venezuela", "Brazil", "Argentina", 3);
        myTerritories[2] = new Territory("Brazil", 375, 500, "North Africa", "Venezuela", "Peru", "Argentina",4);
        myTerritories[3] = new Territory("Argentina", 300, 600, "Peru", "Brazil", 2);
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
