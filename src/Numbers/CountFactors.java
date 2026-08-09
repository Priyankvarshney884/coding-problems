package Numbers;


import java.util.Scanner;

// this code is written for find the number of count of a number
public class CountFactors {
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int n1= (int)Math.sqrt(n);
        int count=0;
        for(int i = 1; i<=n; i++)
        {
            if(n%i==0)
            {
                count++;
            }
        }
        System.out.println(count);
    }
}
