package core_java;

public class Factorial
{
    public static void main(String[] args)
    {
        int num =10;
        int fac =1;

        for (int i = 1; i <= num; i++)
        {
            fac = fac * i;

        }
        System.out.print(fac);
    }
}
