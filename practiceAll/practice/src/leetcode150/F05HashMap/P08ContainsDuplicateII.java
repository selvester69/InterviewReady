package leetcode150.F05HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return true if there are two
 * distinct indices i and j such that nums[i] == nums[j] and abs(i - j) <= k.
 * 
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * =============================================
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * =============================================
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 * 
 * Constraints
 * 
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 0 <= k <= 10^5
 */
public class P08ContainsDuplicateII {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])&& i-map.get(nums[i])<=k){
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}