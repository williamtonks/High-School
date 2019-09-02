import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Driver extends JFrame implements Runnable, MouseListener
{
    Container con = getContentPane();
    Board theBoard = new Board();
    Random ran = new Random();
    Player[] thePlayers = new Player[3];
    boolean placeTroops = true, attackPhase, troopMovements;
    Territory attackingTerritory, defendingTerritory;
    boolean initialPhase = true;
    boolean win1, win2, win3;
    int aX = 1500, aY = 2000, dX = 1500, dY = 1500;
    Rectangle nextRec;
    Player attackingPlayer;
    Thread t = new Thread(this);
    Clip song;
    Image theWin1, theWin2, theWin3;
    public Driver()
    {       
        con.setLayout(new FlowLayout());
        try{
            URL url = this.getClass().getClassLoader().getResource("Stucky.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            song = AudioSystem.getClip();
            song.open(audioIn);
            song.loop(1);
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        theWin1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("win1.png"));
        theWin1 = theWin1.getScaledInstance (1300,800 , 1);
        theWin2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("win2.png"));
        theWin2 = theWin2.getScaledInstance (1300,800 , 1);
        theWin2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("win3.png"));
        theWin2 = theWin2.getScaledInstance (1300,800 , 1);
        thePlayers[0] = new Player(0);
        thePlayers[1] = new Player(1);
        thePlayers[2] = new Player(2);
        attackingPlayer = thePlayers[0];
        nextRec = new Rectangle(1100, 350, 100, 100);
        addMouseListener(this);
        for( int x = 0; x < (theBoard.getContinents()).length; x++)
        {
            Continent temp = (theBoard.getContinents())[x];
            for( int y = 0; y < temp.getNumTerritories(); y++)
            {
                Territory tempo =  (temp.getTerritories())[y];
                int decider = ran.nextInt(3);
                if(thePlayers[0].getMyNumber() == decider)
                    thePlayers[0].takeTerritory(tempo);
                else if (thePlayers[1].getMyNumber() == decider)
                    thePlayers[1].takeTerritory(tempo);
                else
                    thePlayers[2].takeTerritory(tempo);
            }
        }
        for( int x = 0; x < thePlayers.length; x++)
        {
            thePlayers[x].initialTroops();
        }
        t.start();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void run()
    {
        try{
            while(true)
            {
                t.sleep(100);
                if(thePlayers[0].win() == true)
                    win1 = true;    
                if(thePlayers[1].win() == true)
                    win2 = true; 
                if(thePlayers[2].win() == true)
                    win3 = true; 
                if(attackPhase == true && !(attackingTerritory == null) && !(defendingTerritory == null))
                {
                    int[] aDies = new int[3];
                    aDies[0] = ran.nextInt(6) +1;
                    aDies[1] = ran.nextInt(6) +1;
                    aDies[2] = ran.nextInt(6) +1;
                    int[] dDies = new int[2];
                    dDies[0] = ran.nextInt(6) +1;
                    dDies[1] = ran.nextInt(6) +1;
                    int aLargest=0;
                    int bLargest=0;
                    for( int x = 0; x < aDies.length; x++)
                    {
                        if(aDies[x] > aLargest)
                        {
                            bLargest = aLargest;
                            aLargest = aDies[x];
                        }
                        else if(aDies[x] > bLargest)
                        {
                            bLargest = aDies[x];
                        }
                    }
                    if( dDies[1] > dDies[0])
                    {
                        int temp = dDies[0];
                        dDies[0] = dDies[1];
                        dDies[1] = temp;
                    }
                    int aTroopsLost = 0;
                    int dTroopsLost = 0;
                    for( int x = 0; x < 2; x++)
                    {
                        if ( aDies[x] > dDies[x])
                        {
                            dTroopsLost++;
                        }
                        else if( dDies[x] > aDies[x])
                        {
                            aTroopsLost++;
                        }
                        else
                        {
                            aTroopsLost ++;
                            dTroopsLost ++;
                        }
                    }
                    attackingTerritory.loseTroops(aTroopsLost);
                    defendingTerritory.loseTroops(dTroopsLost);
                    if (defendingTerritory.isEmpty() == true)
                    {
                        thePlayers[attackingTerritory.myController()].takeTerritory(defendingTerritory); 
                        thePlayers[defendingTerritory.myController()].loseTerritory(defendingTerritory);
                        while(attackingTerritory.myTroops() > 1)
                        {
                            defendingTerritory.addTroop();
                            attackingTerritory.loseTroops(1);
                        }
                    }
                    attackingTerritory = null;
                    defendingTerritory = null;
                    aX = 1500;
                    aY = 2000; 
                    dX = 1500; 
                    dY = 1500;
                }
                if(troopMovements == true && !(attackingTerritory == null) && !(defendingTerritory == null))
                {
                    while(attackingTerritory.myTroops() > 1)
                    {
                        defendingTerritory.addTroop();
                        attackingTerritory.loseTroops(1);
                    }
                    attackingTerritory = null;
                    defendingTerritory = null;
                    aX = 1500;
                    aY = 2000; 
                    dX = 1500; 
                    dY = 1500;
                }
                repaint();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void update(Graphics g)
    {
        paint(g);
    } 

    public void paint(Graphics gr)
    {
        Image i=createImage(getSize().width, getSize().height);
        Graphics2D g2 = (Graphics2D)i.getGraphics();
        theBoard.drawBoard(g2, attackingPlayer.myColor(), placeTroops, attackPhase, troopMovements, aX, aY, dX, dY);
        g2.drawString("Player "+attackingPlayer.getMyNumber()+" has "+attackingPlayer.myAvailableTroops()+" available Troops", 400, 630);
        if (win1 == true)           
        g2.drawImage(theWin1, 0, 0, null);
        else if (win2 == true)
        g2.drawImage(theWin2, 0, 0, null);
        else if( win3 == true)
        g2.drawImage(theWin3, 0, 0, null);
        g2.dispose();
        gr.drawImage(i, 0, 0, this);
    }

    public void mouseClicked(MouseEvent e)
    {
        Point currentPoint = e.getPoint();
        if (nextRec.contains(currentPoint))
        {
            if(initialPhase == true && placeTroops == true)
            {
                if(attackingPlayer.getMyNumber() == 2)
                {
                    initialPhase = false;
                    attackingPlayer = thePlayers[0];
                }
                else
                {  attackingPlayer = thePlayers[attackingPlayer.getMyNumber()+1];}
            }
            else if(placeTroops == true)
            {
                placeTroops = false;
                attackPhase = true;
            }
            else if(attackPhase == true)
            {
                attackPhase = false;
                troopMovements = true;
            }
            else if(troopMovements == true)
            {
                troopMovements = false;
                placeTroops = true; 
                if(attackingPlayer.getMyNumber() == 2)
                { 
                    attackingPlayer = thePlayers[0];
                    attackingTerritory = null;
                    defendingTerritory = null;
                    aX = 1500;
                    aY = 2000; 
                    dX = 1500; 
                    dY = 1500;
                    int number = 0;
                    for(int x = 0; x< (theBoard.getContinents()).length; x++)
                    {
                        if ((theBoard.getContinents())[x].controls(attackingPlayer.getMyNumber()))
                            number+= (theBoard.getContinents())[x].getBonusTroops();
                    }
                    attackingPlayer.gainTroops(number);
                }
                else
                {  
                    attackingPlayer = thePlayers[attackingPlayer.getMyNumber()+1];
                    attackingTerritory = null;
                    defendingTerritory = null;
                    aX = 1500;
                    aY = 2000; 
                    dX = 1500; 
                    dY = 1500;
                    int number = 0;
                    for(int x = 0; x< (theBoard.getContinents()).length; x++)
                    {
                        if ((theBoard.getContinents())[x].controls(attackingPlayer.getMyNumber()))
                            number+= (theBoard.getContinents())[x].getBonusTroops();
                    }
                    attackingPlayer.gainTroops(number);
                }
            }
        }
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {}

    public void mousePressed(MouseEvent e)
    {}

    public void mouseReleased(MouseEvent e)
    {
        Point currentPoint = e.getPoint();
        if(placeTroops == true && (attackingPlayer.myAvailableTroops()) > 0)
        {
            for( int x = 0; x < (theBoard.getContinents()).length; x++)
            {
                Continent temp = (theBoard.getContinents())[x];
                for( int y = 0; y < temp.getNumTerritories(); y++)
                {
                    if( (((temp.getTerritories())[y]).getRect()).contains(currentPoint) && 
                    (attackingPlayer.myTerritories()).contains(((temp.getTerritories())[y])))
                    {
                        ((temp.getTerritories())[y]).addTroop();
                        attackingPlayer.loseAvailableTroop();
                    }
                }
            }
        }
        else if(attackPhase== true && attackingTerritory== null)
        {
            for( int x = 0; x < (theBoard.getContinents()).length; x++)
            {
                Continent temp = (theBoard.getContinents())[x];
                for( int y = 0; y < temp.getNumTerritories(); y++)
                {
                    if( (((temp.getTerritories())[y]).getRect()).contains(currentPoint) && 
                    ((temp.getTerritories())[y]).myTroops() > 2)
                    {
                        attackingTerritory = ((temp.getTerritories())[y]);
                        aX = attackingTerritory.xPosition();
                        aY = attackingTerritory.yPosition();
                    }
                }
            }
        }
        else if(attackPhase == true && defendingTerritory == null)
        {
            for( int x = 0; x < (theBoard.getContinents()).length; x++)
            {
                Continent temp = (theBoard.getContinents())[x];
                for( int y = 0; y < temp.getNumTerritories(); y++)
                {
                    if( (((temp.getTerritories())[y]).getRect()).contains(currentPoint) && 
                    attackingTerritory.adjacentTerritories().contains(((temp.getTerritories())[y]).getMyName())
                    && !((attackingTerritory.myController()) == ((temp.getTerritories())[y]).myController()))
                    {
                        defendingTerritory = (temp.getTerritories())[y];
                        dX = defendingTerritory.xPosition();
                        dY = defendingTerritory.yPosition();
                    }
                }
            }
        }
        else if(troopMovements == true && attackingTerritory== null)
        {
            for( int x = 0; x < (theBoard.getContinents()).length; x++)
            {
                Continent temp = (theBoard.getContinents())[x];
                for( int y = 0; y < temp.getNumTerritories(); y++)
                {
                    if( (((temp.getTerritories())[y]).getRect()).contains(currentPoint) && 
                    ((temp.getTerritories())[y]).myTroops() > 1)
                    {
                        attackingTerritory = ((temp.getTerritories())[y]);
                        aX = attackingTerritory.xPosition();
                        aY = attackingTerritory.yPosition();
                    }
                }
            }
        }
        else if(troopMovements == true && defendingTerritory == null)
        {
            for( int x = 0; x < (theBoard.getContinents()).length; x++)
            {
                Continent temp = (theBoard.getContinents())[x];
                for( int y = 0; y < temp.getNumTerritories(); y++)
                {
                    if( (((temp.getTerritories())[y]).getRect()).contains(currentPoint) && 
                    attackingTerritory.adjacentTerritories().contains(((temp.getTerritories())[y]).getMyName())
                    && ((attackingTerritory.myController()) == ((temp.getTerritories())[y]).myController()))
                    {
                        defendingTerritory = (temp.getTerritories())[y];
                        dX = defendingTerritory.xPosition();
                        dY = defendingTerritory.yPosition();
                    }
                }
            }
        }
    }

    public static void main(String[] args)
    {
        Driver frame = new Driver();
        frame.setSize(1250, 750);
        frame.setVisible(true);
    }
}