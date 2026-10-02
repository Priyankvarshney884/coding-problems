package HashMap;

import java.util.HashMap;
import java.util.Scanner;

// You are given an array A of N integers. Return the count of elements with frequncy 1 in the given array.
public class FrequencyCount {
    public static void main(String[] args)
    {
        int[] ar = {3,4,3,6,7,8};

        System.out.println(freq(ar));
    }

    public static int freq(int[] ar)
    {
        int oneFreq=0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int j : ar) {
            map.put(j, map.getOrDefault(j, 0) + 1);
        }

        for(int key:map.keySet())
        {
            if(map.get(key)==1)
                oneFreq++;
        }

        return oneFreq;
    }
}
