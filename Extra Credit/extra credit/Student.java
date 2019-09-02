
public class Student extends Person
{
    
    public Student()
    {
    }

    public void print()
    {
        System.out.println("student");
    }
    public static void printAll(Person[] list)
    {
        for (Person p : list)
            p.print();
    }
}
