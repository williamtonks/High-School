import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Asia implements Continent
{
    private int BonusTroops = 7;
    private String name = "Asia";
    private Territory[] myTerritories = new Territory[12];
    public Asia()
    {
        myTerritories[0] = new Territory("Siberia",900, 100, "Yakutsk","Irkustk","Mongolia","China","Ural",5);
        myTerritories[1]=new Territory("Ural", 820, 160, "Russia", "Afghanistan", "China", "Siberia",4);
        myTerritories[2]=new Territory("Afghanistan", 800, 270,"Russia","Middle East", "India", "China","Ural",5);
        myTerritories[3]=new Territory("Middle East",730,375, "East Africa", "Egypt", "Southern Europe","Russia", "Afghanistan", "India",6);
        myTerritories[4]= new Territory("India", 900, 380,"Middle East", "Afghanistan", "China","Siam", 4);
        myTerritories[5] =new Territory("China", 940, 320, "Mongolia", "Siberia", "Ural", "Afghanistan", "India", "Siam",6);
        myTerritories[6] =new Territory("Siam", 980, 400, "India", "China","Indonesia",3);
        myTerritories[7]=new Territory("Mongolia", 970, 250, "China", "Siberia", "Irkutsk", "Kamchatka","Japan",5);
        myTerritories[8] =new Territory("Irkutsk", 970, 170, "Mongolia","Siberia","Yakutsk","Kamchatka",4);
        myTerritories[9] =new Territory("Yakutsk", 980, 80, "Siberia", "Kamchatka", "Irkutsk",3);
        myTerritories[10] = new Territory("Kamchatka", 1080, 80, "Yakutsk", "Irkutsk","Mongolia", "Alaska", "Japan",5);
        myTerritories[11] = new Territory("Japan", 1110, 250, "Mongolia", "Kamchatka",2);
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
