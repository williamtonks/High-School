import java.awt.*;
import java.util.*;
public class Beads
{
    private int  x, y, xPosition, yPosition; 
    Random ran = new Random();
    private Rectangle rec;
    private Image Beads;
    private boolean visible;
    public Beads(int x, int y)
    {
        xPosition = x;
        yPosition = y;
        Beads = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Beads.gif"));
        Beads= Beads.getScaledInstance ( 50, 50, 1);
    }

    public void drawB(Graphics2D g2)
    {
        g2.drawImage(Beads, xPosition, yPosition, null);
    }
}