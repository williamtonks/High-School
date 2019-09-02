import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Pits
{
    private int xPosition, yPosition; 
    Random ran = new Random();
    private Rectangle rec;
    private Beads myBead;
    private int beadValue = 4;
    private Image Pits;
    private Clip Take;
    public Pits(int x, int y)
    {   
        try{
            URL url = this.getClass().getClassLoader().getResource("Take.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Take = AudioSystem.getClip();
            Take.open(audioIn);
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        xPosition = x;
        yPosition = y;
        rec = new Rectangle(x, y, 75, 100);
        myBead = new Beads( x + 10, y + 20);
        Pits = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Pit.gif"));
        Pits= Pits.getScaledInstance ( 75, 100, 1);
    }

    public void drawP(Graphics2D g2)
    {        
        g2.drawImage(Pits, xPosition, yPosition, null);
        if(beadValue > 0)
        myBead.drawB(g2);
        g2.drawString(""+beadValue, xPosition, yPosition);
    }
    
    public void Sing()
    {
        Take.loop(1);
    }
     
    public boolean isClicked(Point p)
    {
        return (rec.contains(p));
    }
    
    public int beadValue()
    {
        return beadValue;
    }
    
    public void emptyPocket()
    {
        beadValue = 0;
    }
    
    public void increaseBead()
    {
        beadValue++;
    }
}