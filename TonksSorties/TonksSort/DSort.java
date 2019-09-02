import java.util.*;
public class DSort
{
    public static void main (String[] args)
    {
        int array[][] = new int[5][5];
        int xLoc = 0, yLoc = 0;
        Random ran = new Random();
        for ( int x = 0; x < 5; x++)
        {
            for ( int y = 0; y< 5; y++)
            {
                array[x][y] = ran.nextInt(99);
                System.out.print(array[x][y]+" ");
            }
        }
        System.out.println(" ");
        for ( int x = 0; x < 5; x++)
        {
            for ( int y = 0; y< 5; y++)
            {
                int smallest = 100;
                for ( int z = x; z<5; z++)
                {
                    for (int a = y; a<5; a++)
                    {
                        if ( array[z][a] < smallest)
                        {
                            smallest = array[z][a];
                            xLoc = z;
                            yLoc = a;
                        }
                    }
                }
                int temp = array[xLoc][yLoc];
                array[xLoc][yLoc] = array[x][y];
                array[x][y] = temp;
            }
        }
        for ( int x = 0; x < 5; x++)
        {
            for ( int y = 0; y< 5; y++)
            {
                System.out.print(array[x][y]+" ");
            }
        }
    }
}
