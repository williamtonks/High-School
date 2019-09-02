import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Driver extends JFrame implements Runnable, MouseListener
{
    Random ran = new Random();
    Thread t= new Thread(this);
    Image  i, board, back, p1, p2; 
    Image iTurn, wTurn;
    int player = 1, currentBead = 0, player1Score = 0, player2Score = 0, end = 0 , placeholder = 0;
    Pits thePits[] = new Pits[14];
    Clip P1, P2;
    public Driver()
    {
        try{
            URL url = this.getClass().getClassLoader().getResource("P1Win.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            P1 = AudioSystem.getClip();
            P1.open(audioIn);
            url = this.getClass().getClassLoader().getResource("P2Win.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            P2 = AudioSystem.getClip();
            P2.open(audioIn);
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        back = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Tree.jpg"));
        back = back.getScaledInstance ( 750, 600 , 1);
        board = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Board.gif"));
        board = board.getScaledInstance ( 675, 350 , 1);
        p1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Player1.jpg"));
        p1 = p1.getScaledInstance ( 750, 600, 1);
        p2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Player2.jpg"));
        p2 = p2.getScaledInstance ( 750, 600 , 1);
        iTurn = Toolkit.getDefaultToolkit().getImage(getClass().getResource("1Turn.gif"));
        iTurn = iTurn.getScaledInstance ( 100, 50, 1);
        wTurn = Toolkit.getDefaultToolkit().getImage(getClass().getResource("2Turn.gif"));
        wTurn = wTurn.getScaledInstance ( 100, 50 , 1);
        addMouseListener(this);
        thePits[0] = new Pits( 75, 275);
        thePits[1] = new Pits( 150, 175);
        thePits[2] = new Pits(225, 175);
        thePits[3] = new Pits(300, 175);
        thePits[4] = new Pits(375, 175);
        thePits[5] = new Pits(450, 175);
        thePits[6] = new Pits(525, 175);
        thePits[7] = new Pits( 150, 360);
        thePits[8] = new Pits( 225, 360);
        thePits[9] = new Pits(300, 360);
        thePits[10] = new Pits(375, 360);
        thePits[11] = new Pits( 450, 360);
        thePits[12] = new Pits(525, 360);
        thePits[13] = new Pits(600, 275);
        t.start();
    }

    public void paint(Graphics g)
    {
        i=createImage(getSize().width, getSize().height);
        Graphics2D g2= (Graphics2D)i.getGraphics();
        g2.drawImage( back, 0, 0, this);
        g2.drawImage( board, 50, 150, this);
        for ( int x = 0; x < thePits.length; x++)
        {
            thePits[x].drawP(g2);
        }
        g2.drawString("Player 1 has "+player1Score+" beads", 500, 50);
        g2.drawString("Player 2 has "+player2Score+" beads", 25, 50);
        if (player ==1)
            g2.drawImage(iTurn, 200, 25, this);
        else
            g2.drawImage(wTurn, 200, 25, this);
        if ( end == 1)
        { 
            g2.drawImage( p1, 0, 0, this);
            Singer();
        }
        if (end == 2)
        { 
            g2.drawImage( p2, 0, 0, this);
            Singer();
        }
        g2.dispose();
        g.drawImage(i, 0, 0 , null);
    }

    public void Singer()
    {
        if ( end == 1)
            P1.loop(1);
        if ( end == 2)
            P2.loop(1);
    }    

    public void run()
    {
        try
        {
            while (true)
            {   
                t.sleep(100);
                repaint();
            }
        }
        catch(Exception e){}
    }

    public void mouseExited(MouseEvent m){}

    public void mouseEntered(MouseEvent m){} 

    public void mousePressed(MouseEvent m){}

    public void mouseClicked(MouseEvent m)
    {
        Point z = m.getPoint();
        for (int x = 0; x < thePits.length; x++)
        {
            if( thePits[x].isClicked(z) && ! thePits[0].isClicked(z) && ! thePits[13].isClicked(z))
            {
                thePits[x].Sing();
                currentBead = thePits[x].beadValue();
                thePits[x].emptyPocket();
                for ( int b = x+1; b < thePits.length; b++)
                {
                    if (currentBead > 0)
                    {
                        thePits[b].increaseBead();
                        currentBead--;
                    }
                }
                if (currentBead > 0)
                {
                    for ( int y = 0; y < thePits.length; y++)
                    {
                        if( currentBead > 0)
                        {
                            thePits[y].increaseBead();
                            currentBead--;
                        }
                    }
                }
            }
        } 
        player1Score = thePits[0].beadValue();
        player2Score = thePits[13].beadValue();
        placeholder = player2Score;
        player2Score += 8;
        if ( player1Score > player2Score)
            end = 1;
        player2Score = placeholder;
        placeholder = player1Score;
        player1Score+= 8;
        if (player2Score > player1Score)
            end = 2;
        player1Score = placeholder;
        placeholder = 0;
        if (player == 1)
            player = 2;
        else
            player = 1;
    }

    public void mouseReleased(MouseEvent m)    {}

    public void update(Graphics g)
    {
        paint(g);
    }

    public static void main(String[] args)
    {
        Driver driver = new Driver();
        driver.setSize(750, 600);
        driver.setVisible(true);
        driver.setResizable(false);
    }
}

