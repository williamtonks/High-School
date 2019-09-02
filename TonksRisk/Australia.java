import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Australia implements Continent

{
    private int BonusTroops = 2;
    private int myController = -1;
    private String name = "Australia";
    private Territory[] myTerritories = new Territory[4];
    public Australia()
    {
        myTerritories[0] = new Territory("Indonesia", 1000, 540, "Siam", "New Guinea", "West Australia",4);
        myTerritories[1] = new Territory("New Guinea", 1110,500, "Indonesia", "East Australia",2);
        myTerritories[2] = new Territory("West Australia", 1080, 650, " East Australia", "Indonesia",2);
        myTerritories[3] = new Territory("East Australia", 1140, 600, " West Australia", "East Australia",2);
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
