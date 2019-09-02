import javax.swing.*;
import java.util.*;
public class IntSort
{
    public static void main(String[] args)
    {
        int  a[] = new int[20];
        Random ran = new Random();
        for ( int i = 0; i < 20; i++)
        {
            a[i] = ran.nextInt(99);
        }
        for( int x = 0; x < 20; x++)
        {
            int temp = 0, smallest = 100 , loc = 21;
            for ( int y = x; y < 20; y++)
            {
                if ( a[y] < smallest)
                {
                    smallest = a[y];
                    loc = y;
                }
            }
            temp = a[x];
            a[x] = smallest;
            a[loc] = temp;
        }
        for ( int i = 0; i < 20; i++)
        {
            System.out.print(a[i]+ " ");
        }
    }
}