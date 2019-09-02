import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Missile
{
    private int x, y;
    private int[] arX, arY;
    private Polygon poly1;
    private Color color;
    public Missile(int x1, int y1)
    {
        x = x1;
        y = y1;
        arX = new int[]{x, x, x + 5, x+5};
        arY = new int[]{y, y+15, y+15, y};
        poly1 = new Polygon(arX, arY, 4);
    }

    public void drawM (Graphics2D gr)
    {
        color = Color.blue.darker();
        gr.setColor(color);
        gr.fillPolygon(poly1);
    }
}
