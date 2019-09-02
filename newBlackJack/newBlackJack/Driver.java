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
    Thread t = new Thread(this);
    Random ran = new Random();
    int currentTurn;
    Card[] theDeck, cardsInPlay;
    int[] playerTotals;
    int currentPV;
    boolean playing; 
    Rectangle nextTurnRect;
    Rectangle dealerShowRect;
    Image nextTurn, dealerShow;
    public Driver()
    {
        con.setLayout(new FlowLayout());
        JOptionPane.showMessageDialog(null, " Welcome to the 17 South Band Casino. There are four players needed to be at this table.");
        currentTurn =  -1;
        addMouseListener(this);
        Random ran = new Random();
        nextTurn = Toolkit.getDefaultToolkit().getImage(getClass().getResource("nextTurn.png"));
        nextTurn = nextTurn.getScaledInstance (80, 100, 1);
        nextTurnRect = new Rectangle(1000, 300, 80, 100);
        dealerShow = Toolkit.getDefaultToolkit().getImage(getClass().getResource("dealershow.png"));
        dealerShow = dealerShow.getScaledInstance (80, 100, 1);
        dealerShowRect = new Rectangle(1000, 150, 80, 100);
        playing = false;
        theDeck = new Card[52];
        playerTotals = new int[4];
        cardsInPlay = new Card[5];
        for (int xl = 0; xl < 4; xl++)
        {
            playerTotals[xl] = 500; 
        }
        /*
        All variables initialized here
        All objects instantiated here
        All Listeners added here
         */
        for (int l = 0; l < theDeck.length;l++)
        {
            theDeck[l] = new Card ( l% 13 , l % 4, 2000, 2000);
        }
        for ( int xl = 0; xl < 5; xl++)
        {
            int lol = (int) (Math.random() * 52);
            cardsInPlay[xl] = theDeck[lol];
            cardsInPlay[xl].changePosition( 400 + xl* 70, 400);
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        t.start();
    }

    public void run()
    {
        try{
            while(true)
            {
                t.sleep(67);//Smaller number == faster, larger == slower
                /*
                All motion and collision detection coded here
                 */
                repaint();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void paint(Graphics gr)
    {
        Image i=createImage(getSize().width, getSize().height);
        Graphics2D g = (Graphics2D)i.getGraphics();
        g.setColor(new Color(34,139,34));
        g.fillRect(0, 0, 1320, 768);
        g.drawImage(nextTurn, 1000, 300, null);
        g.drawImage(dealerShow, 1000, 150, null);

        for (int l = 0; l < theDeck.length;l++)
        {
            theDeck[l].paint(g);
        }
        for ( int xl = 0; xl < 5; xl++)
        {
            cardsInPlay[xl].paint(g);
        }
        g.drawString(" Your current total is " + currentPV, 400, 350);
        g.drawString(" Current Player Turn is " + currentTurn, 400, 300);
        for ( int dl = 0; dl < 4; dl++)
        {
            g.drawString(" Player " + dl + " has " + playerTotals[dl] + ".", 150, 300 + dl * 20);
        }
        g.dispose();
        gr.drawImage(i, 0, 0, this);
    }

    public void mouseClicked(MouseEvent e)
    {
        Point currentPoint = e.getPoint();
        if (playing == false && nextTurnRect.contains(currentPoint))
        {
            if(currentTurn >= 4)
            {
                currentTurn = 0;
            }
            else
            {
                currentTurn++;
            }
            Card[] temp = new Card[5];
            int count = 0;
            while (count < 5)
            {
                int lol = ran.nextInt(52);
                boolean alreadyPlayed = false;
                for ( int xd = 0; xd < 5; xd++)
                {
                    if (theDeck[lol].equals(temp[xd]))
                    {
                        alreadyPlayed = true;
                    }
                }
                if (alreadyPlayed == false)
                {
                    temp[count] = theDeck[lol];
                    count++;
                }
            }
            cardsInPlay = temp;
            for ( int xd = 0; xd < 5; xd++)
            {
                cardsInPlay[xd].changePosition(400 + xd* 70, 400);
            }
            playing = true;
            currentPV = 0;
            placeBet(currentTurn);
        }
        if(playing == true)
        {
            for(int xd = 0; xd < 5; xd++)
            {
                if(cardsInPlay[xd].getRect().contains(currentPoint) && cardsInPlay[xd].isBlank() == true)
                {
                    currentPV += cardsInPlay[xd].show();
                }
            }
            if (currentPV > 21)
            {
                JOptionPane.showMessageDialog(null, " You bust");
                playing = false;
                for (int xd = 0; xd < 5; xd++)
                {
                    cardsInPlay[xd].changePosition( 2000 , 2000);
                }
            }
        }
        if( playing == true && dealerShowRect.contains(currentPoint))
        {
            boolean done = false;
            int dealerPV = 0;
            // this game is stacked in the dealer's favour. He only has a 1 / 10 probability of getting a 10 vs a 4/13
            while(done == false)
            {
                dealerPV += ran.nextInt(9) + 1; 
                if ( ran.nextInt(dealerPV) +4 < 10)
                {
                    done = false;
                }
                else
                {
                    done = true;
                }
            }
            if ( currentPV > dealerPV || dealerPV > 21)
            {
                winBet(currentTurn);
                JOptionPane.showMessageDialog(null, " You Win. You had" + currentPV+" and the dealer had" +dealerPV+".");
            }
            else
            {
                 JOptionPane.showMessageDialog(null, " You LOSE. Dealer had " +dealerPV);
            }
            playing = false;
            for (int xd = 0; xd < 5; xd++)
            {
                cardsInPlay[xd].changePosition( 2000 , 2000);
            }
        }
    }

    public void placeBet( int x)
    {
        playerTotals[x] -= 100;
    }

    public void winBet( int x)
    {
        playerTotals[x] += 200;
    }

    public void mouseExited(MouseEvent e)
    {}

    public void mouseEntered(MouseEvent e)
    {}

    public void mouseReleased(MouseEvent e)
    {}

    public void mousePressed(MouseEvent e)
    {}

    public static void main(String[] args)
    {
        Driver frame = new Driver();
        frame.setSize(1320, 768);//determines size of screen
        frame.setVisible(true);
    }

    public void update(Graphics g)
    {
        paint(g);
    } 
}
