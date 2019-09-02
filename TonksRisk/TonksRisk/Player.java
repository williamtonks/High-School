import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Player
{
    private Color myColor;
    private int myNumber;
    private int placeTroops;
    private ArrayList<Territory> myTerritories = new ArrayList<Territory>();
    private ArrayList<Continent> myContinents = new ArrayList<Continent>();
    public Player(int myNumber)
    {
        myColor = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
        this.myNumber = myNumber;
    }

    public void takeTerritory(Territory terr)
    {
        terr.changeMyControl(this.myColor, this.myNumber);
        myTerritories.add(terr);
    }

    public void loseTerritory(Territory terr)
    {
        myTerritories.remove(terr);
    }
    
    public void loseAvailableTroop()
    {
        placeTroops--;
    }

    public int getMyNumber()
    {
        return myNumber;
    }

    public int myAvailableTroops()
    {
        return placeTroops;
    }

    public Color myColor()
    {
        return myColor;
    }

    public void initialTroops()
    {
        placeTroops = 30 - (myTerritories.size());
    }

    public ArrayList myTerritories()
    {
        return myTerritories;        
    }

    public void gainTroops(int con)
    {
        placeTroops = 0;
        placeTroops+= (myTerritories.size()) /3;    
        placeTroops+= con;
    }
    
    public boolean win()
    {
        return myTerritories.size() > 41;
    }
    }
