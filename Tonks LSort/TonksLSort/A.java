import javax.swing.*;
public class A
{
        private int num;
    protected String output;
    public A()
    {
     num = 1;
     output = "I am an A.";
    }

    public void print()
    {
    System.out.println(output+", "+num);
    }
    public void add(int y)
    {
        num += y;
    }
    public String toString()
    {
        return output+","+num;
    }
    public int num()
    {
        return num;
    }
}
