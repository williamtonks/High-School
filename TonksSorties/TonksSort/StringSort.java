import java.util.*;
import javax.swing.*;
public class StringSort
{
    public static void main(String[] args)
    {
        String a[] = new String[20];
        Random ran = new Random();
        for ( int index = 0; index < 20; index++)
        {
            int ct = 0; 
            String s = "";
            while (ct < 5)
            {
                s+= (char)(ran.nextInt(26)+65);
                ct++;
            }
            a[index] = s;
            System.out.println(a[index]+" at index " + index);
        }
         for( int x = 0; x < 20; x++)
        {
            String smallest = "ZZZZZ";
             int loc = 21;
            for ( int y = x; y < 20; y++)
            {
                if ( a[y].compareTo(smallest) < 0)
                {
                    smallest = a[y];          
                    loc = y;
                }
            }
            String temp = a[x];                                                                  
            a[x] = a[loc];
            a[loc] = temp;
            smallest = "ZZZZZ";
            loc = 21;
        }
        for ( int x = 0; x < 20; x ++)
        {
            System.out.println(a[x]);
        }
    }
}
