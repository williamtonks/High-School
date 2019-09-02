import java.util.*;
public class CharSort
{
    public static void main(String[] args)
    {
        char b[] = new char[20];
        Random ran = new Random();
        for ( int a = 0; a < 20; a++)
        {
            b[a] = (char)(ran.nextInt(90));
        }
        for ( int a = 0; a < 20; a++)
        {
            char smallest = (char)(ran.nextInt(2) + 91);
            int loc = 21;
            for ( int c = a; c < 20; c++)
            {
                if ( b[c] < smallest)
                {
                    smallest = b[c];
                    loc = c;
                }
            }
            char temp = b[a];
            b[a] = b[loc];
            b[loc] = temp;
        }
        for ( int a = 0; a < 20; a++)
        {
            System.out.print(b[a]+ " ");
        }
    }
}
