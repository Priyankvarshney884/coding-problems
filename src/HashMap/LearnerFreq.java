package HashMap;
//SCALER organizes a series of contests aimed at helping learners enhance their coding skills. Each learner can participate in multiple contests, and their participation is represented by integers in an array. The goal is to identify how frequently each learner has participated in these contests. This information will help SCALER determine which learners are participating the least, allowing them to provide targeted support and encouragement.
//
//
//Given an array A that represents the participants of various contests, where each integer corresponds to a specific learner, and an array B containing the learners for whom you want to check participation frequency, your task is to find the frequency of each learner from array B in the array A and return a list containing all these frequencies

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Arrays;

public class LearnerFreq {
    public static void main(String[] args)
    {
        int[] A = {2, 5, 9, 2, 8};
        int[] B= {3,2};
        int[] C= freq(A,B);
        System.out.println(Arrays.toString(C));
    }

    public static int[] freq(int[] a, int[] b)
    {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int j : a) {
            map.put(j, map.getOrDefault(j, 0) + 1);
        }

        int firstLear = map.get(b[0]);
        int secondLear = map.get(b[1]);

        return new int[]{firstLear, secondLear};

    }
}
