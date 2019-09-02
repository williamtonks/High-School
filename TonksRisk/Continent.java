import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public interface Continent
{
    Territory[] getTerritories();
    int getNumTerritories();
    int getBonusTroops();
    boolean controls(int player);
}
