import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Board
{
    private Image theBoard, theKey, theLogo, troopPlace, attackPhase, troopMovements, nextPhase, redStuff, purpleStuff;
    private int tY = 400 , aY = 450, tMY = 500, playPhase;
    private Color playerColor;
    Continent[] theContinents = new Continent[6];
    public Board()
    {
        theBoard = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Risk Board.png"));
        theBoard = theBoard.getScaledInstance (1250,750 , 1);
        theKey = Toolkit.getDefaultToolkit().getImage(getClass().getResource("TroopKey.png"));
        theKey = theKey.getScaledInstance (200, 200 , 1);
        theLogo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Risk Logo.png"));
        theLogo = theLogo.getScaledInstance (225, 60, 1);
        troopPlace = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Place Troops.png"));
        troopPlace = troopPlace.getScaledInstance (100, 50, 1);
        attackPhase = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Attack Phase.png"));
        attackPhase = attackPhase.getScaledInstance (100, 50, 1);
        troopMovements = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Troop Movements.png"));
        troopMovements = troopMovements.getScaledInstance (100, 50, 1);
        nextPhase = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Next Phase.png"));
        nextPhase = nextPhase.getScaledInstance (100, 100, 1);
        redStuff = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Red Stuff.png"));
        redStuff = redStuff.getScaledInstance (30, 20, 1);
        purpleStuff = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Purple Stuff.png"));
        purpleStuff = purpleStuff.getScaledInstance (30, 20, 1);
        theContinents[0] = new Australia();
        theContinents[1] = new Europe();
        theContinents[2] = new Asia();
        theContinents[3]= new Africa();
        theContinents[4]= new NorthAm();
        theContinents[5]= new SouthAm();        
    }
    public void drawBoard(Graphics2D g2, Color color, boolean p, boolean a, boolean t, int aX1, int aY1, int dX, int dY)
    {
        g2.drawImage(theBoard, 0, 0, null);
        playerColor = color;
        if(p == true)
        playPhase = tY;
        else if( a == true)
        playPhase = aY;
        else if (t == true)
        playPhase = tMY;
        g2.setColor(playerColor);
        g2.fillRect(10, playPhase, 100, 50);
        g2.drawImage(nextPhase, 1100, 350, null);
        g2.drawImage(theKey, 0, 550, null);
        g2.drawImage(theLogo, 500, 30, null);
        g2.drawImage(attackPhase, 10, aY, null);
        g2.drawImage(troopPlace, 10,tY , null);
        g2.drawImage(troopMovements, 10, tMY, null);
        g2.drawImage(redStuff, aX1, aY1, null);
        g2.drawImage(purpleStuff, dX, dY, null);
        for( int x = 0; x < theContinents.length; x++)
        {
           for( int y = 0; y < theContinents[x].getNumTerritories(); y++)
           {
               Territory[] temp = theContinents[x].getTerritories();
               temp[y].draw(g2);
            }
        }
    }
    public Continent[] getContinents()
    {
        return theContinents;
    }
}
