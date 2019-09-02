import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Board 
{
    private int length, width, totalMines;
    Tile[][] theTiles; 
    public Board(int lengthd, int widthd)
    {
        length = lengthd;
        width = widthd;
        totalMines = (int)((length * width)/5);
        theTiles = new Tile[length][width];
        for (int d = 0; d < length; d++)
        {
            for (int t = 0; t < width; t++)
            {
                theTiles[d][t] = new Tile(d * 20 + 100, t * 20 +100); 
            }
        }
        int xr = 0;
        while(xr < totalMines)
        {
            int xer = (int)(Math.random()*length);
            int yer = (int)(Math.random()*width);
            if(theTiles[xer][yer].haveMine() == false)
            {
                theTiles[xer][yer].giveMine();
                xr++;
            }
        }
        //establishes how many mines are adjacent in center tiles
        for (int d = 1; d < (length-1); d++)
        {
            for (int t = 1; t < (width-1); t++)
            {
                if(theTiles[d][t].haveMine() == false)
                {
                    int tempor = 0;
                    for (int ted = (d-1); ted < (d+2); ted++)
                    {
                        for (int det = (t-1); det < (t+2); det++)
                        {
                            if(theTiles[ted][det].haveMine()==true)
                            {
                                tempor++;
                            }
                        }
                    }
                    theTiles[d][t].giveMeValue(tempor);
                }
            }
        }
        // edges will be done seperately to avoid error
        int tempor = 1;
        while(tempor < width-1)
        {
            if(theTiles[0][tempor].haveMine()== false)
            {
                int count = 0;
                for(int det = tempor -1; det < (tempor +2); det++)
                {
                    if(theTiles[0][det].haveMine() == true)
                        count++;
                    if(theTiles[1][det].haveMine() == true)
                        count++;
                }
                theTiles[0][tempor].giveMeValue(count);
            }
            tempor++;
        }
        tempor = 1;
        while(tempor < width-1)
        {
            if(theTiles[length-1][tempor].haveMine()== false)
            {
                int count = 0;
                for(int det = tempor -1; det < (tempor +2); det++)
                {
                    if(theTiles[length-1][det].haveMine() == true)
                        count++;
                    if(theTiles[length-2][det].haveMine() == true)
                        count++;
                }
                theTiles[length-1][tempor].giveMeValue(count);
            }
            tempor++;
        }
        tempor = 1;
        while(tempor < length-1)
        {
            if(theTiles[tempor][0].haveMine()== false)
            {
                int count = 0;
                for(int det = tempor -1; det < (tempor +2); det++)
                {
                    if(theTiles[det][0].haveMine() == true)
                        count++;
                    if(theTiles[det][1].haveMine() == true)
                        count++;
                }
                theTiles[tempor][0].giveMeValue(count);
            }
            tempor++;
        }
        tempor = 1;
        while(tempor < length-1)
        {
            if(theTiles[tempor][width-1].haveMine()== false)
            {
                int count = 0;
                for(int det = tempor -1; det < (tempor +2); det++)
                {
                    if(theTiles[det][width-1].haveMine() == true)
                        count++;
                    if(theTiles[det][width-2].haveMine() == true)
                        count++;
                }
                theTiles[tempor][width-1].giveMeValue(count);
            }
            tempor++;
        }
        // now each corner will be done
        tempor = 0;
        if(theTiles[0][0].haveMine()==false)
        {
            if(theTiles[0][1].haveMine()==true)
                tempor++;
            if(theTiles[1][0].haveMine()==true)
                tempor++;
            if(theTiles[1][1].haveMine()==true)
                tempor++;
            theTiles[0][0].giveMeValue(tempor);
        }
        tempor = 0;
        if(theTiles[0][width-1].haveMine()==false)
        {
            if(theTiles[0][width-2].haveMine()==true)
                tempor++;
            if(theTiles[1][width-2].haveMine()==true)
                tempor++;
            if(theTiles[1][width-1].haveMine()==true)
                tempor++;
            theTiles[0][width-1].giveMeValue(tempor);
        }
        tempor = 0;
        if(theTiles[length-1][width-1].haveMine()==false)
        {
            if(theTiles[length-1][width-2].haveMine()==true)
                tempor++;
            if(theTiles[length-2][width-2].haveMine()==true)
                tempor++;
            if(theTiles[length-2][width-1].haveMine()==true)
                tempor++;
            theTiles[length-1][width-1].giveMeValue(tempor);
        }
        tempor = 0;
        if(theTiles[length-1][0].haveMine()==false)
        {
            if(theTiles[length-1][1].haveMine()==true)
                tempor++;
            if(theTiles[length-2][0].haveMine()==true)
                tempor++;
            if(theTiles[length-2][1].haveMine()==true)
                tempor++;
            theTiles[length-1][0].giveMeValue(tempor);
        }
    } 
    
    public boolean gameWon()
    {
        boolean won = false;
        int minesFlagged = 0;
        int totalFlags = 0;
        for (int d = 0; d < length; d++)
        {
            for (int t = 0; t < width; t++)
            {
                if(theTiles[d][t].haveMine()==true && theTiles[d][t].isFlagged()==true)
                {
                    minesFlagged++;
                }
                if(theTiles[d][t].isFlagged()==true)
                {
                    totalFlags++;
                }
            }
        }
        if (minesFlagged == totalFlags && minesFlagged == totalMines)
            won = true;
        return won;
    }

    public void paint( Graphics g2)
    {
        for (int d = 0; d < length; d++)
        {
            for (int t = 0; t < width; t++)
            {
                theTiles[d][t].paint(g2);
            }
        }
    }

    public Tile getTile(int xw, int yw)
    {
        return theTiles[xw][yw];
    }
    
    public Rectangle getTileRect(int xw, int yw)
    {
        return theTiles[xw][yw].getRect();
    }

}
