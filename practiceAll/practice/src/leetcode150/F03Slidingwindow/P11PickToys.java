package leetcode150.F03Slidingwindow;

import java.util.HashMap;
import java.util.Map;

// as per question we can pick only 2 unique toys at a time but we can pick any number of toys
public class P11PickToys {

    // applying sliding window.
    public int pickToys(int[] nums) {
        int maxLen = 0;
        int i = 0, j = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (j < nums.length) {
            // do calcualtion
            map.put(nums[j], map.getOrDefault(map.get(nums[j]), 0) + 1);
            if (map.size() < 2) {
                j++;
            } else if (map.size() == 2) {
                // get ans from calc
                maxLen = Math.max(maxLen, j - i + 1);
                j++;
            } else if (map.size() > 2) {
                map.put(nums[i], map.get(nums[i]) - 1);
                if (map.get(nums[i]) == 0) {
                    map.remove(nums[i]);
                }
                i++;
            }
        }
        return maxLen;
    }
}