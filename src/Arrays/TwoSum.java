package Arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Two Sum: return the indexes of two distinct elements whose values add to the target.
 *
 * <p>Assumes exactly one valid pair exists. The returned indexes may be in either order.
 */
public class TwoSum {

    /**
     * Finds a pair in one pass using a map from value to its earlier index.
     *
     * <p>For each value, look for the complement among previously visited values,
     * then store the current value. Looking up before inserting prevents reusing
     * the same array element twice.
     *
     * <p>Expected time: O(n). Extra space: O(n).
     *
     * @param nums input values
     * @param target required sum
     * @return indexes of the two values
     * @throws NullPointerException if {@code nums} is null
     * @throws IllegalArgumentException if no pair sums to {@code target}
     */
    public static int[] twoSum(int[] nums, int target) {
        Objects.requireNonNull(nums, "nums must not be null");

        Map<Integer, Integer> indexByValue = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // Use long for the subtraction so extreme int inputs cannot overflow.
            long complement = (long) target - nums[i];
            if (complement >= Integer.MIN_VALUE && complement <= Integer.MAX_VALUE) {
                Integer complementIndex = indexByValue.get((int) complement);
                if (complementIndex != null) {
                    return new int[]{complementIndex, i};
                }
            }

            indexByValue.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two values sum to the target");
    }

    /**
     * Reference implementation that checks every pair.
     * Useful for comparing the straightforward O(n^2) approach with {@link #twoSum(int[], int)}.
     */
    public static int[] twoSumBruteForce(int[] nums, int target) {
        Objects.requireNonNull(nums, "nums must not be null");

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((long) nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        throw new IllegalArgumentException("No two values sum to the target");
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        System.out.println(Arrays.toString(twoSum(nums, target))); // [0, 1]
    }
}
