package Arrays;

import java.util.Scanner;

public class RunningSum {
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] ar = new int[n];
        for(int i=0;i<n;i++)
        {
            ar[i]= sc.nextInt();
        }
        helperRunningSum(ar);

        for(int i : ar)
            System.out.print(i+" ");
    }
    public static int[] helperRunningSum(int[] ar)
    {
        for(int i=0;i<ar.length;i++)
        {
            if(i>0)
            {
                ar[i]= ar[i-1]+ar[i];
            }
        }
        return ar;
    }
}
