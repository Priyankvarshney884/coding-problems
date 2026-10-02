package HashSet;

import java.util.HashSet;

public class UniqueElement {
    public static void main(String[] args)
    {
        int[] ar = {1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,7,8,9};

        System.out.println(helperUnique(ar));
    }

    public static int helperUnique(int[] ar)
    {
        HashSet<Integer> set = new HashSet<>();

        for(int i : ar)
        {
            set.add(i);
        }

        return set.size();

    }
}
