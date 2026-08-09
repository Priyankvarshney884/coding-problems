package Numbers;
// this code we have write for to find the perfect number ( Perfect number is number where all the sums of divisible is equal to number itself)
import java.util.Scanner;

public class perfectNumber {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int countSum =0;

        for(int i = 1;i<n;i++)
        {
            if(n%i==0)
            {
                countSum+=i;

            }
        }
        if(countSum==n)
            System.out.println("Number is a perfect Number ");
        else
            System.out.println("Number is not the perfect Number");
    }
}
