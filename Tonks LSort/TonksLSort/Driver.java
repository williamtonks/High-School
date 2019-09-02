import javax.swing.*;
import java.util.*;
public class Driver
{
    public static void main(String[] args)
    {
        Random r = new Random();
        A collection[] = new A[20];
        int aCount = 0, bCount = 0, cCount = 0, dCount = 0;
        for (int x = 0; x < collection.length; x++)
        {
            int y = r.nextInt(4);
            if (y == 0)
            {
                aCount++;
                collection[x] = new A();
            }
            if (y == 1)
            {
                bCount++;
                collection[x] = new B();
            }
            if (y == 2)
            {
                cCount++;
                collection[x] = new C();
            }
            if (y == 3)
            {
                dCount++;
                collection[x] = new D();
            }
        }
        
        for (int x = 0; x < collection.length; x++)
        {
           System.out.println(collection[x]);
        }
        System.out.println("Put sorting here.");
        for ( int x = 0; x < collection.length;x++)
        {
            for ( int y = x; y <collection.length;y++)
            {
            if( collection[y] instanceof D)
            {
                A temp = collection[y];
                collection[y] = collection[x];
                collection[x] = temp;
                y = collection.length;
            }
        }}
        for ( int x = dCount; x < collection.length;x++)
        {
            for ( int y = x; y <collection.length;y++)
            {
            if( collection[y] instanceof C)
            {
                A temp = collection[y];
                collection[y] = collection[x];
                collection[x] = temp;
                y = collection.length;
            }
        }}
        for ( int x = cCount; x < collection.length;x++)
        {
            for ( int y = x; y <collection.length;y++)
            {
            if( collection[y] instanceof B)
            {
                A temp = collection[y];
                collection[y] = collection[x];
                collection[x] = temp;
                y = collection.length;
            }
        }}
        for(int x = collection.length-1; x> 0; x--)
        {
            System.out.println(collection[x]);
        }
    }
}