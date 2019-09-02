import javax.swing.*;
import java.util.*;
public class CharIns
{
    public static void main(String[] args)
    {
        char  a[] = new char[20];
        Random ran = new Random();
        for ( int i = 0; i < 20; i++)
        {
            a[i] = (char) ( ran.nextInt(26)+65);
            System.out.println(a[i]+ " ");
        }
        for ( int x=1; x <a.length; x++)
        {
            char b = a[x];
            int y = x;
            while ( y > 0 && a[y-1] >b)
            {
            a[y] = a[y-1];
            y = y-1;
            }
            a[y] =b;
        }
        for ( int i = 0; i < 20; i++)
        {
            System.out.print(a[i]+ " ");
        }
    }
}