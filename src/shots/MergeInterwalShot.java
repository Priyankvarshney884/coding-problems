package shots;

import java.util.ArrayList;
import java.util.List;

// this code we have write to merge the interval and remove the overlapping interval which is not sulitable
public class MergeInterwalShot {
    public static void main(String[] args) {
        int[][] intervals = {{-1,-3},{2,6},{8,10},{15,18}};
        // sort the array
        // merge if needed
        int[] newInterval = {2, 10};

        int[][] result = insert(intervals, newInterval);

        for(int[] interval : result)
        {
            System.out.println("["+interval[0]+","+interval[1]+"]");;
        }

    }
    public static int[][] insert(int[][] ar , int[] newAr)
    {
        List<int[]> list = new ArrayList<>();
        int i =0;

        // add the interval before newInterval addes
        while(i< ar.length && ar[i][1] < newAr[0])
        {
            list.add(ar[i]);
            i++;
        }

        // Merge the overlapping intervals
        while(i<ar.length && ar[i][0] <= newAr[1])
        {
            newAr[0]= Math.min(newAr[0], ar[i][0]);
            newAr[1]= Math.max(newAr[1], ar[i][1]);
            i++;
        }

        //add remainig intervals
        list.add(newAr);

        while( i<ar.length)
        {
            list.add(ar[i]);
            i++;
        }

        return list.toArray(new int[list.size()][]);
    }
}
