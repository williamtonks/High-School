import java.util.*;
public class tonksBsearch
{
    public tonksBsearch()
    {
        int ar[] = new int[1000];
        Random ran = new Random();
        for(int x = 0; x <ar.length; x++)
        {
            ar[x] =ran.nextInt(1000);
            System.out.print(ar[x]+" ");
        }
        System.out.println();
        Arrays.sort(ar);
        for(int x = 0; x <ar.length; x++)
        {
            System.out.print(ar[x]+" ");
        }
        System.out.println(" Located at"+ binarySort( ar, ran.nextInt(1000), 0, ar.length));        
    }

    public int binarySort (int[] arra, int num, int low, int high)
    {
        int middle = (low+arra.length)/2;
        System.out.println( low+" "+high+ " "+ middle);
        if ( low < high)
        {
            if ( num >= arra[middle])
            {
                 middle = binarySort( arra,  num,  middle, high);
            }
            else if ( arra[middle] <= num)
            {
                middle = binarySort(arra, num, low, middle);
            }
            else 
            {
                middle = -1;
            }
        }
        return middle;
    }
}
