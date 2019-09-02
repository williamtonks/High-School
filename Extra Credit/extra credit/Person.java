import java.util.*;
public class Person
{   
    public Person()
    {

    }

    public void print()
    {
        System.out.println("person");
    }

    public static void printAll(Person[] list)
    {
        for (Person p : list)
            p.print();
    }
}
