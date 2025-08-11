# Top 150 interview questions

## Questions

### Question 1: Merge Sorted Array

You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

Examples

```text
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
=============================================
Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].
=============================================
Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
```

Constraints:

```text
nums1.length == m + n
nums2.length == n
0 <= m, n <= 200
1 <= m + n <= 200
-109 <= nums1[i], nums2[j] <= 109
```

### Solution

```java
public class SolutionMergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int i = m + n - 1;

        while (p2 >= 0) {
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[i--] = nums1[p1--];
            } else {
                nums1[i--] = nums2[p2--];
            }
        }
    }
}
```

### Question 2: Remove Element

Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.Return k.

Examples

```text
Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2. It does not matter what you leave beyond the returned k (hence they are underscores).
=============================================
Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4. Note that the five elements can be returned in any order. It does not matter what you leave beyond the returned k (hence they are underscores).
```

Constraints

```text
0 <= nums.length <= 100
0 <= nums[i] <= 50
0 <= val <= 100
```

Solution

```java
public class SolutionRemoveElement {
    public int removeElement(int[] nums, int val) {
        // 'k' will track the position for the next element that is not equal to 'val'.
        int k = 0; 

        // Iterate through the array with a fast pointer 'i'.
        for (int i = 0; i < nums.length; i++) {
            // If the current element is not the value to be removed,
            // move it to the 'k' position and increment 'k'.
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        // 'k' now represents the count of elements not equal to 'val',
        // which is the new length of the array.
        return k;
    }
}
```

### Question 3: Remove Duplicates from Sorted Array

Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.Return k after placing the final result in the first k slots of nums.Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

Examples

```text
Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k.
=============================================
Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k.
```

Constraints

```text
1 <= nums.length <= 3 * 10^4
-100 <= nums[i] <= 100
nums is sorted in non-decreasing order.
```

Solution

```java
public class SolutionRemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // 'i' is the slow-pointer, indicating the position for the next unique element.
        // 'j' is the fast-pointer, iterating through the array.
        int i = 0; 
        for (int j = 1; j < nums.length; j++) {
            // If the element at the fast-pointer is different from the element
            // at the slow-pointer, it means we found a new unique element.
            if (nums[j] != nums[i]) {
                // Increment the slow-pointer, then place the unique element there.
                i++;
                nums[i] = nums[j];
            }
        }
        // 'i + 1' gives the count of unique elements (k), as 'i' is 0-indexed.
        return i + 1; 
    }
}
```

### Question 4: Remove Duplicates from Sorted Array II

Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.Return k after placing the final result in the first k slots of nums.Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

Examples

```text
Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
=============================================
Input: nums = [0,0,1,1,1,1,2,3,3]
Output: 7, nums = [0,0,1,1,2,3,3,_,_]
Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
```

Constraints

```text
1 <= nums.length <= 3 * 10^4
-10^4 <= nums[i] <= 10^4
nums is sorted in non-decreasing order.
```

Solution

```java
public class SolutionRemoveDuplicatesII {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }

        // 'i' is the slow-pointer, indicating the position for the next element to be placed.
        int i = 2; 

        // 'j' is the fast-pointer, iterating through the array.
        for (int j = 2; j < nums.length; j++) {
            // If the current element (nums[j]) is different from the element two positions
            // behind the slow-pointer (nums[i-2]), it means this element is valid.
            if (nums[j] != nums[i - 2]) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}
```

### Question 5: Majority Element

Given an array nums of size n, return the majority element.The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
Examples

```text
Input: nums = [3,2,3]
Output: 3
=============================================
Input: nums = [2,2,1,1,1,2,2]
Output: 2
```

Constraints

```text
n == nums.length
1 <= n <= 5 * 10^4
-10^9 <= nums[i] <= 10^9
```

Solution

```java
public class SolutionMajorityElement {
    public int majorityElement(int[] nums) {
        // Boyer-Moore Majority Vote Algorithm
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }
}
```

### Question 6: Rotate Array

Given an array, rotate the array to the right by k steps, where k is non-negative.

Examples

```text
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
=============================================
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
```

Constraints

```text
1 <= nums.length <= 10^5
-2^31 <= nums[i] <= 2^31 - 1
0 <= k <= 10^5
```

Solution

```java
public class SolutionRotateArray {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}
```

### Question 7: Best Time to Buy and Sell Stock

You are given an array prices where prices[i] is the price of a given stock on the ith day. You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Examples

```text
Input: prices = [7,1,5,3,6,4]
Output: 5
=============================================
Input: prices = [7,6,4,3,1]
Output: 0
```

Constraints

```text
1 <= prices.length <= 10^5
0 <= prices[i] <= 10^4
```

Solution

```java
public class SolutionBestTimeToBuySellStock {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE, maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) minPrice = price;
            else if (price - minPrice > maxProfit) maxProfit = price - minPrice;
        }
        return maxProfit;
    }
}
```

### Question 8: Best Time to Buy and Sell Stock II

You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Examples

```text
Input: prices = [7,1,5,3,6,4]
Output: 7
=============================================
Input: prices = [1,2,3,4,5]
Output: 4
```

Constraints

```text
1 <= prices.length <= 3 * 10^4
0 <= prices[i] <= 10^4
```

Solution

```java
public class SolutionBestTimeToBuySellStockII {
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}
```

### Question 9: Jump Game

Given an array of non-negative integers nums, you are initially positioned at the first index of the array. Each element in the array represents your maximum jump length at that position. Determine if you are able to reach the last index.

Examples

```text
Input: nums = [2,3,1,1,4]
Output: true
=============================================
Input: nums = [3,2,1,0,4]
Output: false
```

Constraints

```text
1 <= nums.length <= 10^4
0 <= nums[i] <= 10^5
```

Solution

```java
public class SolutionJumpGame {
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        return true;
    }
}
```

### Question 10: Jump Game II

Given an array of non-negative integers nums, you are initially positioned at the first index of the array. Each element in the array represents your maximum jump length at that position. Return the minimum number of jumps to reach the last index.

Examples

```text
Input: nums = [2,3,1,1,4]
Output: 2
=============================================
Input: nums = [2,3,0,1,4]
Output: 2
```

Constraints

```text
1 <= nums.length <= 10^4
0 <= nums[i] <= 1000
```

Solution

```java
public class SolutionJumpGameII {
    public int jump(int[] nums) {
        int jumps = 0, end = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == end) {
                jumps++;
                end = farthest;
            }
        }
        return jumps;
    }
}
```

### Question 11: H-Index

Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper, return the researcher's h-index.

Examples

```
Input: citations = [3,0,6,1,5]
Output: 3
=============================================
Input: citations = [1,3,1]
Output: 1
```

Constraints

```
n == citations.length
1 <= n <= 5000
0 <= citations[i] <= 1000
```

Solution

```java
import java.util.Arrays;
public class SolutionHIndex {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        for (int i = 0; i < n; i++) {
            if (citations[i] >= n - i) {
                return n - i;
            }
        }
        return 0;
    }
}
```

### Question 12: Insert Delete GetRandom O(1)

Implement the RandomizedSet class with insert, remove, and getRandom methods, all with average O(1) time complexity.

Examples

```
Input
["RandomizedSet","insert","remove","insert","getRandom","remove","insert","getRandom"]
[[],[1],[2],[2],[],[1],[2],[]]
Output
[null,true,false,true,2,true,false,2]
```

Constraints

```
-2^31 <= val <= 2^31 - 1
At most 2 * 10^5 calls will be made to insert, remove, and getRandom.
```

Solution

```java
import java.util.*;
public class RandomizedSet {
    private List<Integer> list;
    private Map<Integer, Integer> map;
    private Random rand;

    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int idx = map.get(val);
        int last = list.get(list.size() - 1);
        list.set(idx, last);
        map.put(last, idx);
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
```

### Question 13: Product of Array Except Self

Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i]. The solution must be O(n) and without using division.

Examples

```
Input: nums = [1,2,3,4]
Output: [24,12,8,6]
=============================================
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
```

Constraints

```
2 <= nums.length <= 10^5
-30 <= nums[i] <= 30
```

Solution

```java
public class SolutionProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int left = 1;
        for (int i = 0; i < n; i++) {
            res[i] = left;
            left *= nums[i];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
}
```

### Question 14: Gas Station

There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i], and the cost to travel to the next station is cost[i]. Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.

Examples

```
Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
Output: 3
=============================================
Input: gas = [2,3,4], cost = [3,4,3]
Output: -1
```

Constraints

```
n == gas.length == cost.length
1 <= n <= 10^5
0 <= gas[i], cost[i] <= 10^4
```

Solution

```java
public class SolutionGasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0, curr = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            total += gas[i] - cost[i];
            curr += gas[i] - cost[i];
            if (curr < 0) {
                start = i + 1;
                curr = 0;
            }
        }
        return total >= 0 ? start : -1;
    }
}
```

### Question 15: Candy

There are n children standing in a line. Each child is assigned a rating value. You are giving candies to these children subjected to the following requirements: Each child must have at least one candy. Children with a higher rating get more candies than their neighbors. Return the minimum number of candies you need to distribute.

Examples

```
Input: ratings = [1,0,2]
Output: 5
=============================================
Input: ratings = [1,2,2]
Output: 4
```

Constraints

```
n == ratings.length
1 <= n <= 2 * 10^4
0 <= ratings[i] <= 2 * 10^4
```

Solution

```java
public class SolutionCandy {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) candies[i] = candies[i - 1] + 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) candies[i] = Math.max(candies[i], candies[i + 1] + 1);
        }
        int sum = 0;
        for (int c : candies) sum += c;
        return sum;
    }
}
```

### Question 16: Trapping Rain Water

Given n non-negative integers representing an elevation map, compute how much water it can trap after raining.

Examples

```
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
=============================================
Input: height = [4,2,0,3,2,5]
Output: 9
```

Constraints

```
n == height.length
1 <= n <= 2 * 10^4
0 <= height[i] <= 10^5
```

Solution

```java
public class SolutionTrappingRainWater {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1, leftMax = 0, rightMax = 0, res = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) leftMax = height[left];
                else res += leftMax - height[left];
                left++;
            } else {
                if (height[right] >= rightMax) rightMax = height[right];
                else res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
}
```

### Question 17: Roman to Integer

Convert a Roman numeral to an integer.

Examples

```
Input: s = "III"
Output: 3
=============================================
Input: s = "LVIII"
Output: 58
=============================================
Input: s = "MCMXCIV"
Output: 1994
```

Constraints

```
1 <= s.length <= 15
s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
```

Solution

```java
import java.util.Map;
public class SolutionRomanToInteger {
    public int romanToInt(String s) {
        Map<Character, Integer> map = Map.of(
            'I', 1, 'V', 5, 'X', 10, 'L', 50,
            'C', 100, 'D', 500, 'M', 1000
        );
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = map.get(s.charAt(i));
            if (i + 1 < s.length() && val < map.get(s.charAt(i + 1))) {
                res -= val;
            } else {
                res += val;
            }
        }
        return res;
    }
}
```

### Question 18: Integer to Roman

Convert an integer to a Roman numeral.

Examples

```
Input: num = 3
Output: "III"
=============================================
Input: num = 58
Output: "LVIII"
=============================================
Input: num = 1994
Output: "MCMXCIV"
```

Constraints

```
1 <= num <= 3999
```

Solution

```java
public class SolutionIntegerToRoman {
    public String intToRoman(int num) {
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romans = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(romans[i]);
            }
        }
        return sb.toString();
    }
}
```

### Question 19: Length of Last Word

Given a string s consisting of words and spaces, return the length of the last word in the string.

Examples

```
Input: s = "Hello World"
Output: 5
=============================================
Input: s = "   fly me   to   the moon  "
Output: 4
```

Constraints

```
1 <= s.length <= 10^4
s consists of only English letters and spaces ' '.
```

Solution

```java
public class SolutionLengthOfLastWord {
    public int lengthOfLastWord(String s) {
        int len = 0, i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == ' ') i--;
        while (i >= 0 && s.charAt(i) != ' ') {
            len++;
            i--;
        }
        return len;
    }
}
```

### Question 20: Longest Common Prefix

Write a function to find the longest common prefix string amongst an array of strings.

Examples

```
Input: strs = ["flower","flow","flight"]
Output: "fl"
=============================================
Input: strs = ["dog","racecar","car"]
Output: ""
```

Constraints

```
1 <= strs.length <= 200
0 <= strs[i].length <= 200
```

Solution

```java
public class SolutionLongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}
```

### Question 21: Reverse Words in a String

Given an input string s, reverse the order of the words.

Examples

```
Input: s = "the sky is blue"
Output: "blue is sky the"
=============================================
Input: s = "  hello world  "
Output: "world hello"
```

Constraints

```
1 <= s.length <= 10^4
s contains English letters (upper-case and lower-case), digits, and spaces ' '.
```

Solution

```java
public class SolutionReverseWords {
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i != 0) sb.append(" ");
        }
        return sb.toString();
    }
}
```

### Question 22: Zigzag Conversion

Convert a string to a zigzag pattern on a given number of rows.

Examples

```
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
=============================================
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
```

Constraints

```
1 <= s.length <= 1000
1 <= numRows <= 1000
```

Solution

```java
public class SolutionZigzagConversion {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) rows[i] = new StringBuilder();
        int curRow = 0, dir = -1;
        for (char c : s.toCharArray()) {
            rows[curRow].append(c);
            if (curRow == 0 || curRow == numRows - 1) dir *= -1;
            curRow += dir;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) res.append(row);
        return res.toString();
    }
}
```

### Question 23: Find the Index of the First Occurrence in a String

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Examples

```
Input: haystack = "sadbutsad", needle = "sad"
Output: 0
=============================================
Input: haystack = "leetcode", needle = "leeto"
Output: -1
```

Constraints

```
1 <= haystack.length, needle.length <= 10^4
```

Solution

```java
public class SolutionStrStr {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
```

### Question 24: Text Justification

Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

Examples

```
Input: words = ["This","is","an","example","of","text","justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
```

Constraints

```
1 <= words.length <= 300
1 <= words[i].length <= 20
words[i] consists of only English letters and symbols.
1 <= maxWidth <= 100
```

Solution

```java
import java.util.*;
public class SolutionTextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int i = 0, n = words.length;
        while (i < n) {
            int j = i, len = 0;
            while (j < n && len + words[j].length() + (j - i) <= maxWidth) {
                len += words[j++].length();
            }
            StringBuilder sb = new StringBuilder();
            int spaces = maxWidth - len;
            int gaps = j - i - 1;
            if (j == n || gaps == 0) {
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) sb.append(" ");
                }
                for (int k = sb.length(); k < maxWidth; k++) sb.append(" ");
            } else {
                int space = spaces / gaps, extra = spaces % gaps;
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        for (int s = 0; s < space + (k - i < extra ? 1 : 0); s++) sb.append(" ");
                    }
                }
            }
            res.add(sb.toString());
            i = j;
        }
        return res;
    }
}
```

## 2 Pointers

### Question 25: Valid Palindrome

Given a string s, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Examples

```
Input: s = "A man, a plan, a canal: Panama"
Output: true
=============================================
Input: s = "race a car"
Output: false
```

Constraints

```
1 <= s.length <= 2 * 10^5
```

Solution

```java
public class SolutionValidPalindrome {
    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) return false;
            l++; r--;
        }
        return true;
    }
}
```

### Question 26: Is Subsequence

Given two strings s and t, return true if s is a subsequence of t.

Examples

```
Input: s = "abc", t = "ahbgdc"
Output: true
=============================================
Input: s = "axc", t = "ahbgdc"
Output: false
```

Constraints

```
0 <= s.length <= 100
0 <= t.length <= 10^4
```

Solution

```java
public class SolutionIsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) i++;
            j++;
        }
        return i == s.length();
    }
}
```

### Question 27: Two Sum II - Input Array Is Sorted

Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number.

Examples

```
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
=============================================
Input: numbers = [2,3,4], target = 6
Output: [1,3]
```

Constraints

```
2 <= numbers.length <= 3 * 10^4
-1000 <= numbers[i] <= 1000
numbers is sorted in non-decreasing order.
```

Solution

```java
public class SolutionTwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) return new int[]{l + 1, r + 1};
            else if (sum < target) l++;
            else r--;
        }
        return new int[0];
    }
}
```

### Question 28: Container With Most Water

Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai), n vertical lines are drawn. Find two lines that together with the x-axis form a container, such that the container contains the most water.

Examples

```
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
=============================================
Input: height = [1,1]
Output: 1
```

Constraints

```
2 <= height.length <= 10^5
0 <= height[i] <= 10^4
```

Solution

```java
public class SolutionContainerWithMostWater {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, max = 0;
        while (l < r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) l++;
            else r--;
        }
        return max;
    }
}
```

### Question 29: 3Sum

Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j != k and nums[i] + nums[j] + nums[k] == 0.

Examples

```
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
=============================================
Input: nums = []
Output: []
```

Constraints

```
0 <= nums.length <= 3000
-10^5 <= nums[i] <= 10^5
```

Solution

```java
import java.util.*;
public class SolutionThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++; r--;
                } else if (sum < 0) l++;
                else r--;
            }
        }
        return res;
    }
}
```

## Sliding Window

### Question 30: Minimum Size Subarray Sum

Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray of which the sum is greater than or equal to target. If there is no such subarray, return 0.

Examples

```
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
=============================================
Input: target = 4, nums = [1,4,4]
Output: 1
```

Constraints

```
1 <= target <= 10^9
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^4
```

Solution

```java
public class SolutionMinSubArrayLen {
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0, sum = 0, minLen = Integer.MAX_VALUE;
        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];
            while (sum >= target) {
                minLen = Math.min(minLen, r - l + 1);
                sum -= nums[l++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
```

### Question 31: Longest Substring Without Repeating Characters

Given a string s, find the length of the longest substring without repeating characters.

Examples

```
Input: s = "abcabcbb"
Output: 3
=============================================
Input: s = "bbbbb"
Output: 1
```

Constraints

```
0 <= s.length <= 5 * 10^4
```

Solution

```java
import java.util.*;
public class SolutionLongestSubstringNoRepeat {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0, l = 0;
        for (int r = 0; r < s.length(); r++) {
            if (map.containsKey(s.charAt(r))) {
                l = Math.max(l, map.get(s.charAt(r)) + 1);
            }
            map.put(s.charAt(r), r);
            maxLen = Math.max(maxLen, r - l + 1);
        }
        return maxLen;
    }
}
```

another Solution

```java
public int lengthOfLongestSubstring(String s) {
        Set<Character> hs = new HashSet<>();
        int longest = 0;
        int i = 0, j = 0;
        // use sliding window
        while (j < s.length()) {
            // calculations
            char c = s.charAt(j);
            if (!hs.contains(c)) {
                hs.add(c);
                longest = Math.max(longest, j - i + 1);
                j++;
            } else if (hs.contains(c)) {
                // ans from calcualtions
                while (hs.contains(c) && hs.size()>0) {
                    hs.remove(s.charAt(i));
                    i++;
                }
            }
        }
        return longest;
    }
```

### Question 32: Substring with Concatenation of All Words

Given a string s and a list of words, find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

Examples

```
Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
=============================================
Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []
```

Constraints

```
1 <= s.length <= 10^4
1 <= words.length <= 5000
1 <= words[i].length <= 30
```

Solution

```java
import java.util.*;
public class SolutionSubstringWithConcatWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words.length == 0) return res;
        int wordLen = words[0].length(), totalLen = wordLen * words.length;
        Map<String, Integer> wordCount = new HashMap<>();
        for (String w : words) wordCount.put(w, wordCount.getOrDefault(w, 0) + 1);
        for (int i = 0; i <= s.length() - totalLen; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while (j < words.length) {
                String sub = s.substring(i + j * wordLen, i + (j + 1) * wordLen);
                if (!wordCount.containsKey(sub)) break;
                seen.put(sub, seen.getOrDefault(sub, 0) + 1);
                if (seen.get(sub) > wordCount.get(sub)) break;
                j++;
            }
            if (j == words.length) res.add(i);
        }
        return res;
    }
}
```

### Question 33: Minimum Window Substring

Given two strings s and t, return the minimum window in s which will contain all the characters in t.

Examples

```
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
=============================================
Input: s = "a", t = "a"
Output: "a"
```

Constraints

```
1 <= s.length, t.length <= 10^5
```

Solution

```java
import java.util.*;
public class SolutionMinWindowSubstring {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
        int l = 0, r = 0, minLen = Integer.MAX_VALUE, start = 0, count = t.length();
        while (r < s.length()) {
            char c = s.charAt(r++);
            if (map.containsKey(c)) {
                if (map.get(c) > 0) count--;
                map.put(c, map.get(c) - 1);
            }
            while (count == 0) {
                if (r - l < minLen) {
                    minLen = r - l;
                    start = l;
                }
                char left = s.charAt(l++);
                if (map.containsKey(left)) {
                    map.put(left, map.get(left) + 1);
                    if (map.get(left) > 0) count++;
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
```

## Matrix

### Question 34: Valid Sudoku

Determine if a 9 x 9 Sudoku board is valid.

Examples

```
Input: board = 
[["5","3",".",".","7",".",".",".","."],
 ["6",".",".","1","9","5",".",".","."],
 [".","9","8",".",".",".",".","6","."],
 ["8",".",".",".","6",".",".",".","3"],
 ["4",".",".","8",".","3",".",".","1"],
 ["7",".",".",".","2",".",".",".","6"],
 [".","6",".",".",".",".","2","8","."],
 [".",".",".","4","1","9",".",".","5"],
 [".",".",".",".","8",".",".","7","9"]]
Output: true
```

Constraints

```
board.length == 9
board[i].length == 9
```

Solution

```java
import java.util.*;
public class SolutionValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                if (!seen.add(c + " in row " + i) ||
                    !seen.add(c + " in col " + j) ||
                    !seen.add(c + " in block " + i / 3 + "-" + j / 3)) return false;
            }
        }
        return true;
    }
}
```

### Question 35: Spiral Matrix

Given an m x n matrix, return all elements of the matrix in spiral order.

Examples

```
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
=============================================
Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
```

Constraints

```
m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
```

Solution

```java
import java.util.*;
public class SolutionSpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) res.add(matrix[top][i]);
            top++;
            for (int i = top; i <= bottom; i++) res.add(matrix[i][right]);
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) res.add(matrix[bottom][i]);
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) res.add(matrix[i][left]);
                left++;
            }
        }
        return res;
    }
}
```

### Question 36: Rotate Image

You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise) in-place.

Examples

```
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
```

Constraints

```
n == matrix.length == matrix[i].length
1 <= n <= 20
```

Solution

```java
public class SolutionRotateImage {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = tmp;
            }
        }
    }
}
```

### Question 37: Set Matrix Zeroes

Given an m x n integer matrix, if an element is 0, set its entire row and column to 0 in-place.

Examples

```
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
=============================================
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
```

Constraints

```
m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
```

Solution

```java
public class SolutionSetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean firstRow = false, firstCol = false;
        for (int i = 0; i < m; i++) if (matrix[i][0] == 0) firstCol = true;
        for (int j = 0; j < n; j++) if (matrix[0][j] == 0) firstRow = true;
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
        if (firstRow) for (int j = 0; j < n; j++) matrix[0][j] = 0;
        if (firstCol) for (int i = 0; i < m; i++) matrix[i][0] = 0;
    }
}
```

### Question 38: Game of Life

According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population.
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
The next state of the board is determined by applying the above rules simultaneously to every cell in the current state of the m x n grid board. In this process, births and deaths occur simultaneously.

Given the current state of the board, update the board to reflect its next state.

Note that you do not need to return anything.

Examples

```text
Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
```

Constraints

```text
m == board.length
n == board[0].length
1 <= m, n <= 25
```

Solution

```java
public class Solution {
    public void gameOfLife(int[][] board) {
        // Get the dimensions of the board.
        int m = board.length;
        if (m == 0) return;
        int n = board[0].length;

        // An array to easily iterate through all 8 neighbor positions.
        // We use relative coordinates (dx, dy) from the current cell.
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},          {0, 1},
            {1, -1}, {1, 0}, {1, 1}
        };

        // First pass: iterate through each cell and calculate its next state.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbors = 0;

                // Check all 8 neighbors.
                for (int[] dir : directions) {
                    int x = i + dir[0];
                    int y = j + dir[1];

                    // Check if the neighbor is within the board boundaries.
                    // The bitwise AND with 1 (board[x][y] & 1) retrieves the *current* state.
                    if (x >= 0 && x < m && y >= 0 && y < n && (board[x][y] & 1) == 1) {
                        liveNeighbors++;
                    }
                }

                // Apply the Game of Life rules to determine the next state.
                // The current state is retrieved using board[i][j] & 1.
                // The next state is stored in the second least significant bit.
                
                // Rule 1 & 3: A live cell (1) with 2 or 3 live neighbors lives on.
                if ((board[i][j] & 1) == 1) {
                    if (liveNeighbors == 2 || liveNeighbors == 3) {
                        // Set the second bit to 1, indicating the cell will be live.
                        board[i][j] |= 2; // '2' is 10 in binary, which sets the second bit.
                    }
                    // For other cases (liveNeighbors < 2 or > 3), the second bit remains 0, meaning it dies.
                } 
                // Rule 4: A dead cell (0) with exactly 3 live neighbors becomes a live cell.
                else {
                    if (liveNeighbors == 3) {
                        // Set the second bit to 1, indicating the cell will become live.
                        board[i][j] |= 2;
                    }
                }
            }
        }

        // Second pass: Update the board to the new state.
        // We shift all bits to the right by 1 (>>= 1).
        // This moves the second bit (the new state) to the first bit,
        // and discards the original state.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }
    }
}
```

## HashMap

### Question 39: Ransom Note

Given two strings ransomNote and magazine, return true if ransomNote can be constructed from magazine.

Examples

```text
Input: ransomNote = "a", magazine = "b"
Output: false
=============================================
Input: ransomNote = "aa", magazine = "aab"
Output: true

Constraints

1 <= ransomNote.length, magazine.length <= 10^5
```

Solution

```java
public class SolutionRansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];
        for (char c : magazine.toCharArray()) count[c - 'a']++;
        for (char c : ransomNote.toCharArray()) {
            if (--count[c - 'a'] < 0) return false;
        }
        return true;
    }
}
```

Another Solution:

```java
public boolean canConstruct(String ransomNote, String magazine) {
        // Create a HashMap to store the character counts of the magazine.
        HashMap<Character, Integer> charCounts = new HashMap<>();

        // Populate the HashMap with characters from the magazine.
        // The key is the character, and the value is its frequency.
        for (char c : magazine.toCharArray()) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }

        // Iterate through the characters of the ransom note.
        for (char c : ransomNote.toCharArray()) {
            // Check if the character is in the HashMap and its count is greater than 0.
            // If the character is not a key or its count is 0, we can't form the note.
            if (charCounts.getOrDefault(c, 0) > 0) {
                // If the character is available, decrement its count in the HashMap.
                charCounts.put(c, charCounts.get(c) - 1);
            } else {
                // Not enough of this character in the magazine.
                return false;
            }
        }

        // If we successfully iterated through the entire ransom note,
        // it means we have all the required characters.
        return true;
    }
```

### Question 40: Isomorphic Strings

Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

```text

Example 1:

Input: s = "egg", t = "add"

Output: true

Explanation:

The strings s and t can be made identical by:

Mapping 'e' to 'a'.
Mapping 'g' to 'd'.
Example 2:

Input: s = "foo", t = "bar"

Output: false

Explanation:

The strings s and t can not be made identical as 'o' needs to be mapped to both 'a' and 'r'.

Example 3:

Input: s = "paper", t = "title"

Output: true

Constraints:

1 <= s.length <= 5 * 104
t.length == s.length
s and t consist of any valid ascii character.
```

Solution

```java
import java.util.*;
public class SolutionIsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> mapST = new HashMap<>();
        Map<Character, Character> mapTS = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            if ((mapST.containsKey(c1) && mapST.get(c1) != c2) ||
                (mapTS.containsKey(c2) && mapTS.get(c2) != c1)) return false;
            mapST.put(c1, c2);
            mapTS.put(c2, c1);
        }
        return true;
    }
}
```

### Question 41: Word Pattern

Given a pattern and a string s, find if s follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s. Specifically:

Each letter in pattern maps to exactly one unique word in s.
Each unique word in s maps to exactly one letter in pattern.
No two letters map to the same word, and no two words map to the same letter.

```text
Example 1:
Input: pattern = "abba", s = "dog cat cat dog"
Output: true

Explanation:
The bijection can be established as:
'a' maps to "dog".
'b' maps to "cat".
Example 2:

Input: pattern = "abba", s = "dog cat cat fish"

Output: false

Example 3:

Input: pattern = "aaaa", s = "dog cat cat dog"

Output: false

Constraints:

1 <= pattern.length <= 300
pattern contains only lower-case English letters.
1 <= s.length <= 3000
s contains only lowercase English letters and spaces ' '.
s does not contain any leading or trailing spaces.
All the words in s are separated by a single space.
```

Solution

```java
import java.util.*;
public class SolutionWordPattern {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) return false;
        Map<Character, String> map = new HashMap<>();
        Map<String, Character> revMap = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String w = words[i];
            if ((map.containsKey(c) && !map.get(c).equals(w)) ||
                (revMap.containsKey(w) && revMap.get(w) != c)) return false;
            map.put(c, w);
            revMap.put(w, c);
        }
        return true;
    }
}
```

### Question 42: Valid Anagram

Given two strings s and t, return true if t is an anagram of s, and false otherwise.

```text
Example 1:

Input: s = "anagram", t = "nagaram"

Output: true

Example 2:

Input: s = "rat", t = "car"

Output: false

Constraints:

1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.

Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
```

Solution

```java
public class SolutionValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        for (char c : t.toCharArray()) {
            if (--count[c - 'a'] < 0) return false;
        }
        return true;
    }
}
```

### Question 43: Group Anagrams

Given an array of strings strs, group the anagrams together. You can return the answer in any order.

```text
Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Explanation:

There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
Example 2:

Input: strs = [""]
Output: [[""]]

Example 3:

Input: strs = ["a"]
Output: [["a"]]

Constraints:

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
```

Solution

```java
import java.util.*;
public class SolutionGroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
```

### Question 44: Two Sum

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

```text
Examples

Input: nums = [2,7,11,15], target = 9
Output: [0,1]

Constraints

2 <= nums.length <= 10^4
-10^9 <= nums[i] <= 10^9
```

Solution

```java
import java.util.*;
public class SolutionTwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) return new int[]{map.get(complement), i};
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
```

### Question 45: Happy Number

Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.

```text
Example 1:

Input: n = 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
Example 2:

Input: n = 2
Output: false

Constraints:

1 <= n <= 231 - 1
```

Solution

```java
import java.util.*;
public class SolutionHappyNumber {
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = sumOfSquares(n);
        }
        return n == 1;
    }
    private int sumOfSquares(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            sum += d * d;
            n /= 10;
        }
        return sum;
    }
}
```

### Question 46: Contains Duplicate II

Given an integer array nums and an integer k, return true if there are two distinct indices i and j such that nums[i] == nums[j] and abs(i - j) <= k.

Examples

```
Input: nums = [1,2,3,1], k = 3
Output: true
=============================================
Input: nums = [1,0,1,1], k = 1
Output: true
=============================================
Input: nums = [1,2,3,1,2,3], k = 2
Output: false
```

Constraints

```
1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
0 <= k <= 10^5
```

Solution

```java
import java.util.*;
public class SolutionContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) return true;
            map.put(nums[i], i);
        }
        return false;
    }
}
```

### Question 47: Longest Consecutive Sequence

Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

Examples

```
Input: nums = [100,4,200,1,3,2]
Output: 4
=============================================
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
```

Constraints

```
0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
```

Solution

```java
import java.util.*;
public class SolutionLongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) set.add(n);
        int maxLen = 0;
        for (int n : set) {
            if (!set.contains(n - 1)) {
                int curr = n, len = 1;
                while (set.contains(curr + 1)) {
                    curr++;
                    len++;
                }
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }
}
```

## Intervals

### Question 48: Summary Ranges

You are given a sorted unique integer array nums.

A range [a,b] is the set of all integers from a to b (inclusive).

Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.

Each range [a,b] in the list should be output as:

"a->b" if a != b
"a" if a == b

```text
Example 1:

Input: nums = [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: The ranges are:
[0,2] --> "0->2"
[4,5] --> "4->5"
[7,7] --> "7"
Example 2:

Input: nums = [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: The ranges are:
[0,0] --> "0"
[2,4] --> "2->4"
[6,6] --> "6"
[8,9] --> "8->9"

Constraints:

0 <= nums.length <= 20
-231 <= nums[i] <= 231 - 1
All the values of nums are unique.
nums is sorted in ascending order.
```

Solution

```java
import java.util.*;
public class SolutionSummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < nums.length) {
            int start = i;
            while (i + 1 < nums.length && nums[i + 1] == nums[i] + 1) i++;
            if (start == i) res.add(String.valueOf(nums[i]));
            else res.add(nums[start] + "->" + nums[i]);
            i++;
        }
        return res;
    }
}
```

### Question 49: Merge Intervals

Given an array of intervals, merge all overlapping intervals.

Examples

```
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
=============================================
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
```

Constraints

```
1 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= intervals[i][0] <= intervals[i][1] <= 10^4
```

Solution

```java
import java.util.*;
public class SolutionMergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        int[] curr = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (curr[1] >= intervals[i][0]) {
                curr[1] = Math.max(curr[1], intervals[i][1]);
            } else {
                res.add(curr);
                curr = intervals[i];
            }
        }
        res.add(curr);
        return res.toArray(new int[res.size()][]);
    }
}
```

### Question 50: Insert Interval

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

Examples

```
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
=============================================
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
```

Constraints

```
0 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= intervals[i][0] <= intervals[i][1] <= 10^5
```

Solution

```java
import java.util.*;
public class SolutionInsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int i = 0, n = intervals.length;
        while (i < n && intervals[i][1] < newInterval[0]) res.add(intervals[i++]);
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval);
        while (i < n) res.add(intervals[i++]);
        return res.toArray(new int[res.size()][]);
    }
}
```

### Question 51: Minimum Number of Arrows to Burst Balloons

Given a list of intervals representing balloons, find the minimum number of arrows to burst all balloons.

Examples

```
Input: points = [[10,16],[2,8],[1,6],[7,12]]
Output: 2
=============================================
Input: points = [[1,2],[3,4],[5,6],[7,8]]
Output: 4
```

Constraints

```
1 <= points.length <= 10^5
points[i].length == 2
```

Solution

```java
import java.util.*;
public class SolutionMinArrowsBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
        int arrows = 1, end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > end) {
                arrows++;
                end = points[i][1];
            }
        }
        return arrows;
    }
}
```

## Stack

### Question 52: Valid Parentheses

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

Examples

```text
Input: s = "()[]{}"
Output: true
=============================================
Input: s = "(]"
Output: false

Constraints
1 <= s.length <= 10^4
```

Solution

```java
import java.util.*;
public class SolutionValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = Map.of(')', '(', '}', '{', ']', '[');
        for (char c : s.toCharArray()) {
            if (map.containsValue(c)) stack.push(c);
            else if (stack.isEmpty() || stack.pop() != map.get(c)) return false;
        }
        return stack.isEmpty();
    }
}
```

### Question 53: Simplify Path

Given an absolute path for a file (Unix-style), simplify it.

Examples

```text
Input: path = "/home/"
Output: "/home"
=============================================
Input: path = "/../"
Output: "/"

Constraints

1 <= path.length <= 3000
```

Solution

```java
import java.util.*;
public class SolutionSimplifyPath {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        for (String part : path.split("/")) {
            if (part.equals("") || part.equals(".")) continue;
            if (part.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else {
                stack.push(part);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String dir : stack) sb.insert(0, "/" + dir);
        return sb.length() == 0 ? "/" : sb.toString();
    }
}
```

### Question 54: Min Stack

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Examples

```text
Input: ["MinStack","push","push","push","getMin","pop","top","getMin"]
       [[],[-2],[0],[-3],[],[],[],[]]
Output: [null,null,null,null,-3,null,0,-2]

Constraints

-2^31 <= val <= 2^31 - 1
```

Solution

```java
import java.util.*;
public class MinStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();
    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) minStack.push(val);
    }
    public void pop() {
        if (stack.pop().equals(minStack.peek())) minStack.pop();
    }
    public int top() {
        return stack.peek();
    }
    public int getMin() {
        return minStack.peek();
    }
}
```

### Question 55: Evaluate Reverse Polish Notation

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Examples

```
Input: tokens = ["2","1","+","3","*"]
Output: 9
=============================================
Input: tokens = ["4","13","5","/","+"]
Output: 6
```

Constraints

```
1 <= tokens.length <= 10^4
```

Solution

```java
import java.util.*;
public class SolutionEvalRPN {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String t : tokens) {
            if ("+-*/".contains(t)) {
                int b = stack.pop(), a = stack.pop();
                switch (t) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": stack.push(a / b); break;
                }
            } else {
                stack.push(Integer.parseInt(t));
            }
        }
        return stack.pop();
    }
}
```

### Question 56: Basic Calculator

Implement a basic calculator to evaluate a simple expression string.

Examples

```
Input: s = "1 + 1"
Output: 2
=============================================
Input: s = " 2-1 + 2 "
Output: 3
```

Constraints

```
1 <= s.length <= 3 * 10^5
```

Solution

```java
import java.util.*;
public class SolutionBasicCalculator {
    public int calculate(String s) {
        int res = 0, sign = 1, n = s.length();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = c - '0';
                while (i + 1 < n && Character.isDigit(s.charAt(i + 1)))
                    num = num * 10 + (s.charAt(++i) - '0');
                res += sign * num;
            } else if (c == '+') sign = 1;
            else if (c == '-') sign = -1;
            else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (c == ')') {
                res = stack.pop() * res + stack.pop();
            }
        }
        return res;
    }
}
```

## Linked List

### Question 57: Linked List Cycle

Given a linked list, determine if it has a cycle.

Examples

```
Input: head = [3,2,0,-4], pos = 1
Output: true
=============================================
Input: head = [1,2], pos = 0
Output: true
```

Constraints

```
The number of nodes in the list is in the range [0, 10^4].
```

Solution

```java
public class SolutionLinkedListCycle {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
```

### Question 58: Add Two Numbers

Add two numbers represented by linked lists.

Examples

```
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
```

Constraints

```
The number of nodes in each list is in the range [1, 100].
```

Solution

```java
public class SolutionAddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) { sum += l1.val; l1 = l1.next; }
            if (l2 != null) { sum += l2.val; l2 = l2.next; }
            curr.next = new ListNode(sum % 10);
            carry = sum / 10;
            curr = curr.next;
        }
        return dummy.next;
    }
}
```

### Question 59: Merge Two Sorted Lists

Merge two sorted linked lists and return it as a new sorted list.

Examples

```
Input: l1 = [1,2,4], l2 = [1,3,4]
Output: [1,1,2,3,4,4]
```

Constraints

```
The number of nodes in both lists is in the range [0, 50].
```

Solution

```java
public class SolutionMergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) { curr.next = l1; l1 = l1.next; }
            else { curr.next = l2; l2 = l2.next; }
            curr = curr.next;
        }
        curr.next = l1 != null ? l1 : l2;
        return dummy.next;
    }
}
```

### Question 60: Copy List with Random Pointer

Copy a linked list with next and random pointer.

Examples

```
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
```

Constraints

```
0 <= Node.val <= 100
```

Solution

```java
import java.util.*;
public class SolutionCopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Map<Node, Node> map = new HashMap<>();
        Node curr = head;
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }
        return map.get(head);
    }
}
```

### Question 61: Reverse Linked List II

Reverse a linked list from position left to right.

Examples

```
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
```

Constraints

```
1 <= left <= right <= n
```

Solution

```java
public class SolutionReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 1; i < left; i++) prev = prev.next;
        ListNode curr = prev.next;
        for (int i = 0; i < right - left; i++) {
            ListNode tmp = curr.next;
            curr.next = tmp.next;
            tmp.next = prev.next;
            prev.next = tmp;
        }
        return dummy.next;
    }
}
```

### Question 62: Reverse Nodes in k-Group

Reverse nodes of a linked list k at a time.

Examples

```
Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]
=============================================
Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]
```

Constraints

```
1 <= k <= n
```

Solution

```java
public class SolutionReverseNodesKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }
        if (count < k) return head;
        curr = head;
        ListNode prev = null, next = null;
        for (int i = 0; i < k; i++) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head.next = reverseKGroup(curr, k);
        return prev;
    }
}
```

### Question 63: Remove Nth Node From End of List

Remove the nth node from the end of a linked list.

Examples

```
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
```

Constraints

```
1 <= n <= size of list
```

Solution

```java
public class SolutionRemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy, slow = dummy;
        for (int i = 0; i <= n; i++) fast = fast.next;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
```

### Question 64: Remove Duplicates from Sorted List II

Remove all nodes that have duplicate numbers from a sorted linked list.

Examples

```
Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]
=============================================
Input: head = [1,1,1,2,3]
Output: [2,3]
```

Constraints

```
The number of nodes in the list is in the range [0, 300].
```

Solution

```java
public class SolutionRemoveDuplicatesSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0, head), prev = dummy;
        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val) head = head.next;
                prev.next = head.next;
            } else {
                prev = prev.next;
            }
            head = head.next;
        }
        return dummy.next;
    }
}
```

### Question 65: Rotate List

Rotate a linked list to the right by k places.

Examples

```
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
```

Constraints

```
0 <= k <= 2 * 10^9
```

Solution

```java
public class SolutionRotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        int len = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }
        k = k % len;
        if (k == 0) return head;
        tail.next = head;
        for (int i = 0; i < len - k; i++) tail = tail.next;
        ListNode newHead = tail.next;
        tail.next = null;
        return newHead;
    }
}
```

### Question 66: Partition List

Partition a linked list around a value x.

Examples

```
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
```

Constraints

```
The number of nodes in the list is in the range [0, 200].
```

Solution

```java
public class SolutionPartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode before = new ListNode(0), after = new ListNode(0);
        ListNode b = before, a = after;
        while (head != null) {
            if (head.val < x) { b.next = head; b = b.next; }
            else { a.next = head; a = a.next; }
            head = head.next;
        }
        a.next = null;
        b.next = after.next;
        return before.next;
    }
}
```

### Question 67: LRU Cache

Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Examples

```
Input: ["LRUCache","put","put","get","put","get","put","get","get","get"]
       [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
Output: [null,null,null,1,null,-1,null,-1,3,4]
```

Constraints

```
1 <= capacity <= 3000
```

Solution

```java
import java.util.*;
public class LRUCache {
    private final int capacity;
    private final LinkedHashMap<Integer, Integer> map;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap<>(capacity, 0.75f, true);
    }
    public int get(int key) {
        return map.getOrDefault(key, -1);
    }
    public void put(int key, int value) {
        map.put(key, value);
        if (map.size() > capacity) {
            map.remove(map.keySet().iterator().next());
        }
    }
}
```

## Binary Tree General

### Question 68: Maximum Depth of Binary Tree

Given a binary tree, find its maximum depth.

Examples

```
Input: root = [3,9,20,null,null,15,7]
Output: 3
```

Constraints

```
The number of nodes in the tree is in the range [0, 10^4].
```

Solution

```java
public class SolutionMaxDepthBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
```

### Question 69: Same Tree

Given two binary trees, check if they are the same.

Examples

```
Input: p = [1,2,3], q = [1,2,3]
Output: true
=============================================
Input: p = [1,2], q = [1,null,2]
Output: false
```

Constraints

```
The number of nodes in both trees is in the range [0, 100].
```

Solution

```java
public class SolutionSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
```

### Question 70: Invert Binary Tree

Invert a binary tree.

Examples

```
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]
```

Constraints

```
The number of nodes in the tree is in the range [0, 100].
```

Solution

```java
public class SolutionInvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode tmp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tmp);
        return root;
    }
}
```

### Question 71: Symmetric Tree

Check if a binary tree is symmetric.

Examples

```
Input: root = [1,2,2,3,4,4,3]
Output: true
=============================================
Input: root = [1,2,2,null,3,null,3]
Output: false
```

Constraints

```
The number of nodes in the tree is in the range [1, 1000].
```

Solution

```java
public class SolutionSymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }
    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return t1.val == t2.val &&
            isMirror(t1.left, t2.right) &&
            isMirror(t1.right, t2.left);
    }
}
```

### Question 72: Construct Binary Tree from Preorder and Inorder Traversal

Construct a binary tree from preorder and inorder traversal.

Examples

```
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
```

Constraints

```
1 <= preorder.length <= 3000
```

Solution

```java
public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        int[] preIndex = { 0 };
        return helper(preorder, 0, n - 1, preIndex, map);
    }

    private TreeNode helper(int[] preorder, int inS, int inE, int[] preIndex, Map<Integer, Integer> map) {
        if (inS > inE) {
            return null;
        }
        int rootData = preorder[preIndex[0]];
        int rootIndex = map.get(rootData);// either get from map or loop and get from inorder[]

        preIndex[0]++;

        TreeNode root = new TreeNode(rootData);
        root.left = helper(preorder, inS, rootIndex - 1, preIndex, map);
        root.right = helper(preorder, rootIndex + 1, inE, preIndex, map);
        return root;
    }
```

### Question 73: Construct Binary Tree from Inorder and Postorder Traversal

Construct a binary tree from inorder and postorder traversal.

Examples

```text
Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]

Constraints

1 <= inorder.length <= 3000
```

Solution

```java
import java.util.*;
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        int[] postIndex = new int[] { n - 1 };
       return helper(postorder, map, postIndex, 0, n - 1);
    }

    public TreeNode helper(int[] postorder, Map<Integer, Integer> map, int[] postIndex,
            int inS, int inE) {
        if (inS > inE)
            return null;
        int rootData = postorder[postIndex[0]];
        int index = map.get(rootData);
        TreeNode root = new TreeNode(rootData);
        postIndex[0]--;
        root.right = helper(postorder, map, postIndex, index + 1, inE);
        root.left = helper(postorder, map, postIndex, inS, index - 1);
        return root;
    }
}
```

### Question 74: Populating Next Right Pointers in Each Node II

Populate each next pointer to point to its next right node.

Examples

```text
Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]

Constraints

The number of nodes in the tree is in the range [0, 6000].
```

Solution

```java
public class SolutionConnectNextRightII {
    public Node connect(Node root) {
        Node head = root;
        while (head != null) {
            Node dummy = new Node(0), curr = dummy;
            while (head != null) {
                if (head.left != null) { curr.next = head.left; curr = curr.next; }
                if (head.right != null) { curr.next = head.right; curr = curr.next; }
                head = head.next;
            }
            head = dummy.next;
        }
        return root;
    }
}
```

### Question 75: Flatten Binary Tree to Linked List

Flatten a binary tree to a linked list in-place.

Examples

```text
Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]

Constraints

The number of nodes in the tree is in the range [0, 2000].
```

Solution inplace with O(1) space

```java
public class SolutionFlattenBinaryTree {
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                TreeNode rightmost = root.left;
                while (rightmost.right != null) rightmost = rightmost.right;
                rightmost.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }
}
```

Another Solution Using BFS O(n) extra space

```java
class Solution {
    public void flatten(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        insert(root, q);
        q.poll(); // skip the original root since we'll relink from it
        while (!q.isEmpty()) {
            root.right = q.poll(); // connect to next node
            root.left = null;      // left should be null in linked list
            root = root.right;
        }
    }

    // Preorder traversal to collect nodes
    void insert(TreeNode node, Queue<TreeNode> queue) {
        if (node == null) return;
        queue.add(node);
        insert(node.left, queue);
        insert(node.right, queue);
    }
}
```

### Question 76: Path Sum

Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values equals the sum.

Examples

```
Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], sum = 22
Output: true
```

Constraints

```
The number of nodes in the tree is in the range [0, 5000].
```

Solution

```java
public class SolutionPathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return sum == root.val;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
```

### Question 77: Sum Root to Leaf Numbers

Given a binary tree, return the sum of all root-to-leaf numbers.

Examples

```
Input: root = [1,2,3]
Output: 25
```

Constraints

```
The number of nodes in the tree is in the range [0, 1000].
```

Solution

```java
public class SolutionSumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }
    private int dfs(TreeNode node, int curr) {
        if (node == null) return 0;
        curr = curr * 10 + node.val;
        if (node.left == null && node.right == null) return curr;
        return dfs(node.left, curr) + dfs(node.right, curr);
    }
}
```

### Question 78: Binary Tree Maximum Path Sum

Find the maximum path sum in a binary tree.

Examples

```
Input: root = [1,2,3]
Output: 6
=============================================
Input: root = [-10,9,20,null,null,15,7]
Output: 42
```

Constraints

```
The number of nodes in the tree is in the range [1, 3 * 10^4].
```

Solution

```java
public class SolutionBinaryTreeMaxPathSum {
    private int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }
    private int maxGain(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxGain(node.left));
        int right = Math.max(0, maxGain(node.right));
        maxSum = Math.max(maxSum, node.val + left + right);
        return node.val + Math.max(left, right);
    }
}
```

### Question 79: Binary Search Tree Iterator

Implement an iterator over a binary search tree (BST).

Examples

```
Input: root = [7,3,15,null,null,9,20]
Output: [3,7,9,15,20]
```

Constraints

```
The number of nodes in the tree is in the range [1, 10^5].
```

Solution

```java
import java.util.*;
public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        pushLeft(root);
    }
    private void pushLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
    public int next() {
        TreeNode node = stack.pop();
        pushLeft(node.right);
        return node.val;
    }
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
```

### Question 80: Count Complete Tree Nodes

Count the number of nodes in a complete binary tree.

Examples

```
Input: root = [1,2,3,4,5,6]
Output: 6
```

Constraints

```
The number of nodes in the tree is in the range [0, 5 * 10^4].
```

Solution

```java
public class SolutionCountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int left = leftDepth(root), right = rightDepth(root);
        if (left == right) return (1 << left) - 1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    private int leftDepth(TreeNode node) {
        int d = 0;
        while (node != null) { d++; node = node.left; }
        return d;
    }
    private int rightDepth(TreeNode node) {
        int d = 0;
        while (node != null) { d++; node = node.right; }
        return d;
    }
}
```

### Question 81: Lowest Common Ancestor of a Binary Tree

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

Examples

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
=============================================
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
```

Constraints

```
The number of nodes in the tree is in the range [2, 10^5].
```

Solution

```java
public class SolutionLowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }
}
```

## Binary Tree BFS

### Question 82: Binary Tree Right Side View

Return the values of the nodes you can see ordered from top to bottom when looking at a binary tree from the right side.

Examples

```
Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]
=============================================
Input: root = [1,null,3]
Output: [1,3]
```

Constraints

```
The number of nodes in the tree is in the range [0, 100].
```

Solution

```java
import java.util.*;
public class SolutionRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (i == size - 1) res.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
        }
        return res;
    }
}
```

### Question 83: Average of Levels in Binary Tree

Return the average value of the nodes on each level in the form of an array.

Examples

```
Input: root = [3,9,20,null,null,15,7]
Output: [3.00000,14.50000,11.00000]
```

Constraints

```
The number of nodes in the tree is in the range [1, 10^4].
```

Solution

```java
import java.util.*;
public class SolutionAverageOfLevels {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                sum += node.val;
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            res.add(sum / size);
        }
        return res;
    }
}
```

### Question 84: Binary Tree Level Order Traversal

Return the level order traversal of a binary tree's nodes' values.

Examples

```
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
```

Constraints

```
The number of nodes in the tree is in the range [0, 2000].
```

Solution

```java
import java.util.*;
public class SolutionLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            res.add(level);
        }
        return res;
    }
}
```

### Question 85: Binary Tree Zigzag Level Order Traversal

Return the zigzag level order traversal of a binary tree's nodes' values.

Examples

```
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
```

Constraints

```
The number of nodes in the tree is in the range [0, 2000].
```

Solution

```java
import java.util.*;
public class SolutionZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean leftToRight = true;
        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (leftToRight) level.addLast(node.val);
                else level.addFirst(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            res.add(level);
            leftToRight = !leftToRight;
        }
        return res;
    }
}
```

## Bianry Search Tree

### Question 86: Minimum Absolute Difference in BST

Given a BST, return the minimum absolute difference between values of any two nodes.

Examples

```
Input: root = [4,2,6,1,3]
Output: 1
```

Constraints

```
The number of nodes in the tree is in the range [2, 10^4].
```

Solution

```java
public class SolutionMinAbsDiffBST {
    private Integer prev = null;
    private int min = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return min;
    }
    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        if (prev != null) min = Math.min(min, node.val - prev);
        prev = node.val;
        inorder(node.right);
    }
}
```

### Question 87: Kth Smallest Element in a BST

Find the kth smallest element in a BST.

Examples

```
Input: root = [3,1,4,null,2], k = 1
Output: 1
```

Constraints

```
The number of nodes in the tree is in the range [1, 10^4].
```

Solution

```java
public class SolutionKthSmallestBST {
    private int count = 0, res = 0;
    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return res;
    }
    private void inorder(TreeNode node, int k) {
        if (node == null) return;
        inorder(node.left, k);
        count++;
        if (count == k) res = node.val;
        inorder(node.right, k);
    }
}
```

### Question 88: Validate Binary Search Tree

Determine if a binary tree is a valid BST.

Examples

```
Input: root = [2,1,3]
Output: true
=============================================
Input: root = [5,1,4,null,null,3,6]
Output: false
```

Constraints

```
The number of nodes in the tree is in the range [1, 10^4].
```

Solution

```java
public class SolutionValidateBST {
    public boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }
    private boolean isValid(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;
        if ((min != null && node.val <= min) || (max != null && node.val >= max)) return false;
        return isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
    }
}
```

## Graph General

### Question 89: Number of Islands

Given a 2D grid map of '1's (land) and '0's (water), count the number of islands.

Examples

```
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
```

Constraints

```
m == grid.length
n == grid[i].length
1 <= m, n <= 300
```

Solution

```java
public class SolutionNumberOfIslands {
    public int numIslands(char[][] grid) {
        int count = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
        return count;
    }
    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') return;
        grid[i][j] = '0';
        dfs(grid, i + 1, j); dfs(grid, i - 1, j);
        dfs(grid, i, j + 1); dfs(grid, i, j - 1);
    }
}
```

### Question 90: Surrounded Regions

Capture all regions surrounded by 'X' on a 2D board.

Examples

```
Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
```

Constraints

```
m == board.length
n == board[i].length
1 <= m, n <= 200
```

Solution

```java
public class SolutionSurroundedRegions {
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0); dfs(board, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            dfs(board, 0, j); dfs(board, m - 1, j);
        }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == '#') board[i][j] = 'O';
    }
    private void dfs(char[][] board, int i, int j) {
        int m = board.length, n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') return;
        board[i][j] = '#';
        dfs(board, i + 1, j); dfs(board, i - 1, j);
        dfs(board, i, j + 1); dfs(board, i, j - 1);
    }
}
```

### Question 91: Clone Graph

Clone an undirected graph. Each node contains a value and a list of its neighbors.

Examples

```
Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
```

Constraints

```
The number of nodes in the graph is in the range [0, 100].
```

Solution

```java
import java.util.*;
public class SolutionCloneGraph {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Map<Node, Node> map = new HashMap<>();
        return clone(node, map);
    }
    private Node clone(Node node, Map<Node, Node> map) {
        if (map.containsKey(node)) return map.get(node);
        Node copy = new Node(node.val);
        map.put(node, copy);
        for (Node neighbor : node.neighbors) {
            copy.neighbors.add(clone(neighbor, map));
        }
        return copy;
    }
}
```

### Question 92: Evaluate Division

Given equations like a / b = 2.0, evaluate queries.

Examples

```
Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.0,0.5,-1.0,1.0,-1.0]
```

Constraints

```
1 <= equations.length <= 20
```

Solution

```java
import java.util.*;
public class SolutionEvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0), b = equations.get(i).get(1);
            graph.computeIfAbsent(a, k -> new HashMap<>()).put(b, values[i]);
            graph.computeIfAbsent(b, k -> new HashMap<>()).put(a, 1.0 / values[i]);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            res[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), graph, new HashSet<>());
        }
        return res;
    }
    private double dfs(String a, String b, Map<String, Map<String, Double>> graph, Set<String> visited) {
        if (!graph.containsKey(a) || !graph.containsKey(b)) return -1.0;
        if (a.equals(b)) return 1.0;
        visited.add(a);
        for (String nei : graph.get(a).keySet()) {
            if (visited.contains(nei)) continue;
            double d = dfs(nei, b, graph, visited);
            if (d != -1.0) return graph.get(a).get(nei) * d;
        }
        return -1.0;
    }
}
```

### Question 93: Course Schedule

Determine if you can finish all courses given prerequisites.

Examples

```
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
=============================================
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
```

Constraints

```
1 <= numCourses <= 2000
```

Solution

```java
import java.util.*;
public class SolutionCourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) graph[i] = new ArrayList<>();
        for (int[] p : prerequisites) graph[p[1]].add(p[0]);
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++)
            if (!dfs(graph, visited, i)) return false;
        return true;
    }
    private boolean dfs(List<Integer>[] graph, int[] visited, int i) {
        if (visited[i] == 1) return false;
        if (visited[i] == 2) return true;
        visited[i] = 1;
        for (int nei : graph[i])
            if (!dfs(graph, visited, nei)) return false;
        visited[i] = 2;
        return true;
    }
}
```

### Question 94: Course Schedule II

Return the order of courses you should take to finish all courses.

Examples

```
Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
=============================================
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: []
```

Constraints

```
1 <= numCourses <= 2000
```

Solution

```java
import java.util.*;
public class SolutionCourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) graph[i] = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int[] p : prerequisites) {
            graph[p[1]].add(p[0]);
            indegree[p[0]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (indegree[i] == 0) q.offer(i);
        int[] res = new int[numCourses];
        int idx = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            res[idx++] = curr;
            for (int nei : graph[curr])
                if (--indegree[nei] == 0) q.offer(nei);
        }
        return idx == numCourses ? res : new int[0];
    }
}
```

## Graph BFS

### Question 95: Snakes and Ladders

Find the minimum number of moves to reach the last square on a snakes and ladders board.

Examples

```
Input: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
Output: 4
```

Constraints

```
2 <= board.length == board[0].length <= 20
```

Solution

```java
import java.util.*;
public class SolutionSnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = true;
        int moves = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                if (curr == n * n) return moves;
                for (int d = 1; d <= 6; d++) {
                    int next = curr + d;
                    if (next > n * n) continue;
                    int[] pos = getPos(next, n);
                    int val = board[pos[0]][pos[1]];
                    if (val != -1) next = val;
                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }
            moves++;
        }
        return -1;
    }
    private int[] getPos(int num, int n) {
        int r = (num - 1) / n, c = (num - 1) % n;
        if (r % 2 == 1) c = n - 1 - c;
        return new int[]{n - 1 - r, c};
    }
}
```

### Question 96: Minimum Genetic Mutation

Return the minimum number of mutations to mutate from start to end using valid genes.

Examples

```
Input: start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
Output: 1
=============================================
Input: start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
Output: 2
```

Constraints

```
start.length == end.length == 8
```

Solution

```java
import java.util.*;
public class SolutionMinGeneticMutation {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> dict = new HashSet<>(Arrays.asList(bank));
        if (!dict.contains(end)) return -1;
        char[] genes = {'A','C','G','T'};
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                if (curr.equals(end)) return steps;
                char[] arr = curr.toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    char old = arr[j];
                    for (char g : genes) {
                        arr[j] = g;
                        String next = new String(arr);
                        if (dict.remove(next)) q.offer(next);
                    }
                    arr[j] = old;
                }
            }
            steps++;
        }
        return -1;
    }
}
```

### Question 97: Word Ladder

Return the length of the shortest transformation sequence from beginWord to endWord.

Examples

```
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
```

Constraints

```
1 <= beginWord.length <= 10
```

Solution

```java
import java.util.*;
public class SolutionWordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int steps = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                if (curr.equals(endWord)) return steps;
                char[] arr = curr.toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    char old = arr[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        arr[j] = c;
                        String next = new String(arr);
                        if (dict.remove(next)) q.offer(next);
                    }
                    arr[j] = old;
                }
            }
            steps++;
        }
        return 0;
    }
}
```

## Trie

### Question 98: Implement Trie (Prefix Tree)

Implement a trie with insert, search, and startsWith methods.

Examples

```
Input: ["Trie","insert","search","search","startsWith","insert","search"]
       [[],["apple"],["apple"],["app"],["app"],["app"],["app"]]
Output: [null,null,true,false,true,null,true]
```

Constraints

```
1 <= word.length, prefix.length <= 2000
```

Solution

```java
public class Trie {
    private Trie[] children = new Trie[26];
    private boolean isEnd = false;
    public void insert(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) node.children[idx] = new Trie();
            node = node.children[idx];
        }
        node.isEnd = true;
    }
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }
    private Trie searchPrefix(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) return null;
            node = node.children[idx];
        }
        return node;
    }
}
```

### Question 99: Design Add and Search Words Data Structure

Implement a data structure that supports adding new words and searching with '.' as a wildcard.

Examples

```
Input: ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
       [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output: [null,null,null,null,false,true,true,true]
```

Constraints

```
1 <= word.length <= 25
```

Solution

```java
public class WordDictionary {
    private WordDictionary[] children = new WordDictionary[26];
    private boolean isEnd = false;
    public WordDictionary() {}
    public void addWord(String word) {
        WordDictionary node = this;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) node.children[idx] = new WordDictionary();
            node = node.children[idx];
        }
        node.isEnd = true;
    }
    public boolean search(String word) {
        return search(word, 0, this);
    }
    private boolean search(String word, int i, WordDictionary node) {
        if (i == word.length()) return node.isEnd;
        char c = word.charAt(i);
        if (c == '.') {
            for (WordDictionary child : node.children)
                if (child != null && search(word, i + 1, child)) return true;
            return false;
        } else {
            int idx = c - 'a';
            return node.children[idx] != null && search(word, i + 1, node.children[idx]);
        }
    }
}
```

### Question 100: Word Search II

Given a 2D board and a list of words, return all words present in the board.

Examples

```
Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
```

Constraints

```
1 <= board.length, board[i].length <= 12
```

Solution

```java
import java.util.*;
public class SolutionWordSearchII {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                dfs(board, i, j, root, res);
        return res;
    }
    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> res) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        char c = board[i][j];
        if (c == '#' || node.children[c - 'a'] == null) return;
        node = node.children[c - 'a'];
        if (node.word != null) {
            res.add(node.word);
            node.word = null;
        }
        board[i][j] = '#';
        dfs(board, i + 1, j, node, res);
        dfs(board, i - 1, j, node, res);
        dfs(board, i, j + 1, node, res);
        dfs(board, i, j - 1, node, res);
        board[i][j] = c;
    }
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode node = root;
            for (char c : w.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) node.children[idx] = new TrieNode();
                node = node.children[idx];
            }
            node.word = w;
        }
        return root;
    }
}
```

## BackTrack

### Question 101: Letter Combinations of a Phone Number

Return all possible letter combinations that the number could represent.

Examples

```
Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
```

Constraints

```
0 <= digits.length <= 4
```

Solution

```java
import java.util.*;
public class SolutionLetterCombinations {
    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.isEmpty()) return res;
        backtrack(digits, 0, new StringBuilder(), res);
        return res;
    }
    private void backtrack(String digits, int idx, StringBuilder sb, List<String> res) {
        if (idx == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String letters = KEYS[digits.charAt(idx) - '0'];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            backtrack(digits, idx + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
```

### Question 102: Combinations

Return all possible combinations of k numbers out of 1 ... n.

Examples

```
Input: n = 4, k = 2
Output: [[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
```

Constraints

```
1 <= n <= 20
```

Solution

```java
import java.util.*;
public class SolutionCombinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), res);
        return res;
    }
    private void backtrack(int start, int n, int k, List<Integer> curr, List<List<Integer>> res) {
        if (curr.size() == k) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = start; i <= n; i++) {
            curr.add(i);
            backtrack(i + 1, n, k, curr, res);
            curr.remove(curr.size() - 1);
        }
    }
}
```

### Question 103: Permutations

Return all possible permutations of a list of numbers.

Examples

```
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```

Constraints

```
1 <= nums.length <= 6
```

Solution

```java
import java.util.*;
public class SolutionPermutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), res);
        return res;
    }
    private void backtrack(int[] nums, boolean[] used, List<Integer> curr, List<List<Integer>> res) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            curr.add(nums[i]);
            backtrack(nums, used, curr, res);
            curr.remove(curr.size() - 1);
            used[i] = false;
        }
    }
}
```

### Question 104: Combination Sum

Return all unique combinations where the candidate numbers sum to target.

Examples

```
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
```

Constraints

```
1 <= candidates.length <= 30
```

Solution

```java
import java.util.*;
public class SolutionCombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }
    private void backtrack(int[] candidates, int target, int start, List<Integer> curr, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) continue;
            curr.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, curr, res);
            curr.remove(curr.size() - 1);
        }
    }
}
```

### Question 105: N-Queens II

Return the number of distinct solutions to the n-queens puzzle.

Examples

```
Input: n = 4
Output: 2
```

Constraints

```
1 <= n <= 9
```

Solution

```java
public class SolutionNQueensII {
    private int count = 0;
    public int totalNQueens(int n) {
        backtrack(0, n, new boolean[n], new boolean[2 * n], new boolean[2 * n]);
        return count;
    }
    private void backtrack(int row, int n, boolean[] cols, boolean[] d1, boolean[] d2) {
        if (row == n) {
            count++;
            return;
        }
        for (int col = 0; col < n; col++) {
            int id1 = col - row + n, id2 = col + row;
            if (cols[col] || d1[id1] || d2[id2]) continue;
            cols[col] = d1[id1] = d2[id2] = true;
            backtrack(row + 1, n, cols, d1, d2);
            cols[col] = d1[id1] = d2[id2] = false;
        }
    }
}
```

### Question 106: Generate Parentheses

Return all combinations of well-formed parentheses.

Examples

```
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
```

Constraints

```
1 <= n <= 8
```

Solution

```java
import java.util.*;
public class SolutionGenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(n, n, "", res);
        return res;
    }
    private void backtrack(int left, int right, String curr, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(curr);
            return;
        }
        if (left > 0) backtrack(left - 1, right, curr + "(", res);
        if (right > left) backtrack(left, right - 1, curr + ")", res);
    }
}
```

### Question 107: Word Search

Given a 2D board and a word, return true if the word exists in the grid.

Examples

```
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
```

Constraints

```
1 <= board.length, board[i].length <= 6
```

Solution

```java
public class SolutionWordSearch {
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (dfs(board, word, i, j, 0)) return true;
        return false;
    }
    private boolean dfs(char[][] board, String word, int i, int j, int idx) {
        if (idx == word.length()) return true;
        int m = board.length, n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(idx)) return false;
        char tmp = board[i][j];
        board[i][j] = '#';
        boolean found = dfs(board, word, i + 1, j, idx + 1) ||
                        dfs(board, word, i - 1, j, idx + 1) ||
                        dfs(board, word, i, j + 1, idx + 1) ||
                        dfs(board, word, i, j - 1, idx + 1);
        board[i][j] = tmp;
        return found;
    }
}
```

## Divide and Conquer

### Question 108: Convert Sorted Array to Binary Search Tree

Convert a sorted array to a height-balanced BST.

Examples

```
Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
```

Constraints

```
1 <= nums.length <= 10^4
```

Solution

```java
public class SolutionSortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }
    private TreeNode build(int[] nums, int l, int r) {
        if (l > r) return null;
        int m = l + (r - l) / 2;
        TreeNode node = new TreeNode(nums[m]);
        node.left = build(nums, l, m - 1);
        node.right = build(nums, m + 1, r);
        return node;
    }
}
```

### Question 109: Sort List

Sort a linked list in O(n log n) time and constant space complexity.

Examples

```
Input: head = [4,2,1,3]
Output: [1,2,3,4]
```

Constraints

```
The number of nodes in the list is in the range [0, 5 * 10^4].
```

Solution

```java
public class SolutionSortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        return merge(l1, l2);
    }
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) { curr.next = l1; l1 = l1.next; }
            else { curr.next = l2; l2 = l2.next; }
            curr = curr.next;
        }
        curr.next = l1 != null ? l1 : l2;
        return dummy.next;
    }
}
```

### Question 110: Construct Quad Tree

Construct a Quad-Tree from a 2D grid.

Examples

```
Input: grid = [[0,1],[1,0]]
Output: [[0,1],[1,0]]
```

Constraints

```
n == grid.length == grid[i].length
n == 2^x where 0 <= x <= 6
```

Solution

```java
public class SolutionConstructQuadTree {
    public Node construct(int[][] grid) {
        return build(grid, 0, 0, grid.length);
    }
    private Node build(int[][] grid, int x, int y, int len) {
        if (len == 1) return new Node(grid[x][y] == 1, true);
        int half = len / 2;
        Node tl = build(grid, x, y, half);
        Node tr = build(grid, x, y + half, half);
        Node bl = build(grid, x + half, y, half);
        Node br = build(grid, x + half, y + half, half);
        if (tl.isLeaf && tr.isLeaf && bl.isLeaf && br.isLeaf &&
            tl.val == tr.val && tr.val == bl.val && bl.val == br.val)
            return new Node(tl.val, true);
        return new Node(true, false, tl, tr, bl, br);
    }
}
```

### Question 111: Merge k Sorted Lists

Merge k sorted linked lists and return it as one sorted list.

Examples

```
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
```

Constraints

```
k == lists.length
0 <= k <= 10^4
```

Solution

```java
import java.util.*;
public class SolutionMergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) if (node != null) pq.offer(node);
        ListNode dummy = new ListNode(0), curr = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            curr.next = node;
            curr = curr.next;
            if (node.next != null) pq.offer(node.next);
        }
        return dummy.next;
    }
}
```

## Kadane's Algorithm

### Question 112: Maximum Subarray

Find the contiguous subarray with the largest sum.

Examples

```
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
```

Constraints

```
1 <= nums.length <= 10^5
```

Solution

```java
public class SolutionMaximumSubarray {
    public int maxSubArray(int[] nums) {
        int max = nums[0], curr = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(nums[i], curr + nums[i]);
            max = Math.max(max, curr);
        }
        return max;
    }
}
```

### Question 113: Maximum Sum Circular Subarray

Find the maximum sum of a non-empty subarray in a circular array.

Examples

```
Input: nums = [1,-2,3,-2]
Output: 3
=============================================
Input: nums = [5,-3,5]
Output: 10
```

Constraints

```
1 <= nums.length <= 3 * 10^4
```

Solution

```java
public class SolutionMaxSumCircularSubarray {
    public int maxSubarraySumCircular(int[] nums) {
        int total = 0, maxSum = nums[0], curMax = 0, minSum = nums[0], curMin = 0;
        for (int n : nums) {
            curMax = Math.max(curMax + n, n);
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.min(curMin + n, n);
            minSum = Math.min(minSum, curMin);
            total += n;
        }
        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }
}
```

## Binary Search

### Question 114: Search Insert Position

Return the index if the target is found, or the index where it would be if inserted in order.

Examples

```
Input: nums = [1,3,5,6], target = 5
Output: 2
=============================================
Input: nums = [1,3,5,6], target = 2
Output: 1
```

Constraints

```
1 <= nums.length <= 10^4
```

Solution

```java
public class SolutionSearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) return m;
            else if (nums[m] < target) l = m + 1;
            else r = m - 1;
        }
        return l;
    }
}
```

### Question 115: Search a 2D Matrix

Write an efficient algorithm that searches for a value in an m x n matrix.

Examples

```
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
=============================================
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false
```

Constraints

```
m == matrix.length
n == matrix[i].length
```

Solution

```java
public class SolutionSearch2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m * n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int val = matrix[mid / n][mid % n];
            if (val == target) return true;
            else if (val < target) l = mid + 1;
            else r = mid - 1;
        }
        return false;
    }
}
```

### Question 116: Find Peak Element

Find a peak element in an array.

Examples

```
Input: nums = [1,2,3,1]
Output: 2
=============================================
Input: nums = [1,2,1,3,5,6,4]
Output: 5
```

Constraints

```
1 <= nums.length <= 1000
```

Solution

```java
public class SolutionFindPeakElement {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] < nums[m + 1]) l = m + 1;
            else r = m;
        }
        return l;
    }
}
```

### Question 117: Search in Rotated Sorted Array

Search for a target value in a rotated sorted array.

Examples

```
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
=============================================
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
```

Constraints

```
1 <= nums.length <= 5000
```

Solution

```java
public class SolutionSearchRotatedSortedArray {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) return m;
            if (nums[l] <= nums[m]) {
                if (nums[l] <= target && target < nums[m]) r = m - 1;
                else l = m + 1;
            } else {
                if (nums[m] < target && target <= nums[r]) l = m + 1;
                else r = m - 1;
            }
        }
        return -1;
    }
}
```

### Question 118: Find First and Last Position of Element in Sorted Array

Find the starting and ending position of a given target value.

Examples

```
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
=============================================
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
```

Constraints

```
0 <= nums.length <= 10^5
```

Solution

```java
public class SolutionFindFirstLastPosition {
    public int[] searchRange(int[] nums, int target) {
        int left = find(nums, target, true);
        int right = find(nums, target, false);
        return new int[]{left, right};
    }
    private int find(int[] nums, int target, boolean left) {
        int l = 0, r = nums.length - 1, res = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                res = m;
                if (left) r = m - 1;
                else l = m + 1;
            } else if (nums[m] < target) l = m + 1;
            else r = m - 1;
        }
        return res;
    }
}
```

### Question 119: Find Minimum in Rotated Sorted Array

Find the minimum element in a rotated sorted array.

Examples

```
Input: nums = [3,4,5,1,2]
Output: 1
=============================================
Input: nums = [4,5,6,7,0,1,2]
Output: 0
```

Constraints

```
1 <= nums.length <= 5000
```

Solution

```java
public class SolutionFindMinRotatedSortedArray {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] > nums[r]) l = m + 1;
            else r = m;
        }
        return nums[l];
    }
}
```

### Question 120: Median of Two Sorted Arrays

Find the median of two sorted arrays.

Examples

```
Input: nums1 = [1,3], nums2 = [2]
Output: 2.0
=============================================
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.5
```

Constraints

```
0 <= m, n <= 1000
```

Solution

```java
public class SolutionMedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
        int m = nums1.length, n = nums2.length, half = (m + n + 1) / 2;
        int l = 0, r = m;
        while (l <= r) {
            int i = l + (r - l) / 2;
            int j = half - i;
            int maxLeftA = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int minRightA = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int maxLeftB = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int minRightB = (j == n) ? Integer.MAX_VALUE : nums2[j];
            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                if ((m + n) % 2 == 0)
                    return (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2.0;
                else
                    return Math.max(maxLeftA, maxLeftB);
            } else if (maxLeftA > minRightB) r = i - 1;
            else l = i + 1;
        }
        return 0.0;
    }
}
```

### Question 121: Kth Largest Element in an Array

Find the kth largest element in an unsorted array.

Examples

```
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
=============================================
Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
```

Constraints

```
1 <= k <= nums.length <= 30000
```

Solution

```java
import java.util.*;
public class SolutionKthLargestElement {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) pq.poll();
        }
        return pq.peek();
    }
}
```

### Question 122: IPO

Given the initial capital and profits of projects, find the maximum capital after k projects.
Examples

```
Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,2]
Output: 4
=============================================
Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
Output: 6
```

Constraints

```
1 <= k <= 10^4
0 <= w <= 10^9
1 <= profits.length, capital.length <= 10^4
```

```java
import java.util.*;
public class SolutionIPO {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }
        Arrays.sort(projects, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int i = 0;
        for (int j = 0; j < k; j++) {
            while (i < n && projects[i][0] <= w) {
                pq.offer(projects[i][1]);
                i++;
            }
            if (pq.isEmpty()) break;
            w += pq.poll();
        }
        return w;
    }
}
```

### Question 123: Find K Pairs with Smallest Sums

Find the k pairs with the smallest sums from two sorted arrays.
Examples

```
Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
=============================================
Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [[1,1],[1,1]]
```

Constraints

```
1 <= nums1.length, nums2.length <= 10^5
0 <= nums1[i], nums2[i] <= 10^9
1 <= k <= 10^4
```

```java
import java.util.*;
public class SolutionKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k <= 0) return res;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            pq.offer(new int[]{nums1[i], nums2[0], 0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] pair = pq.poll();
            res.add(Arrays.asList(pair[0], pair[1]));
            if (pair[2] + 1 < nums2.length) {
                pq.offer(new int[]{pair[0], nums2[pair[2] + 1], pair[2] + 1});
            }
        }
        return res;
    }
}
```

### Question 124: Find Median from Data Stream

Design a data structure that supports adding numbers and finding the median efficiently.
Examples

```Input: ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
       [[],[1],[2],[],[3],[]]
Output: [null,null,null,1.5,null,2.0]
```

Constraints

```
1 <= num <= 10^5
There will be at most 10^5 calls to addNum and findMedian.
```

```java
import java.util.*;
public class MedianFinder {
    private PriorityQueue<Integer> maxHeap; // lower half
    private PriorityQueue<Integer> minHeap; // upper half
    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }
    }
    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }
}
```

## Bit Manipulation

### Question 125: Add Binary

Add two binary strings and return their sum as a binary string.
Examples

```Input: a = "11", b = "1"
Output: "100"
=============================================
Input: a = "1010", b = "1011"
Output: "10101"
```

Constraints

```
1 <= a.length, b.length <= 10^4
a and b consist only of '0' or '1'.
```

```java
public class SolutionAddBinary {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        return sb.reverse().toString();
    }
}
```

### Question 126: Reverse Bits

Reverse the bits of a given 32-bit unsigned integer.
Examples

```Input: n = 43261596
Output: 964176192
=============================================
Input: n = 4294967293
Output: 3221225471
```

Constraints

```
The input must be a 32-bit unsigned integer.
```

```java
public class SolutionReverseBits {
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1; // Shift result left by 1
            result |= (n & 1); // Add the last bit of n to result
            n >>= 1; // Shift n right by 1
        }
        return result;
    }
}
```

### Question 127: Number of 1 Bits

Count the number of '1' bits in a given integer.
Examples

```Input: n = 11
Output: 3
=============================================
Input: n = 128
Output: 1
```

Constraints

```
0 <= n <= 2^31 - 1
```

```java
public class SolutionNumberOf1Bits {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1; // Check the last bit
            n >>>= 1; // Shift right by 1 (unsigned)
        }
        return count;
    }
}
```

### Question 128: Single Number

Find the single number in an array where every other number appears twice.
Examples

```Input: nums = [2,2,1]
Output: 1
=============================================
Input: nums = [4,1,2,1,2]
Output: 4
```

Constraints

```
1 <= nums.length <= 3 * 10^4
-3 * 10^4 <= nums[i] <= 3 * 10^4
Each element in the array appears twice except for one element.
```

```java
public class SolutionSingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num; // XOR operation
        }
        return result;
    }
}
```

### Question 129: Single Number II

Find the single number in an array where every other number appears three times.
Examples

```Input: nums = [2,2,3,2]
Output: 3
=============================================
Input: nums = [0,1,0,1,0,1,99]
Output: 99
```

Constraints

```
1 <= nums.length <= 3 * 10^4
-2^31 <= nums[i] <= 2^31 - 1
Each element in the array appears three times except for one element.
```

```java
public class SolutionSingleNumberII {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = (ones ^ num) & ~twos; // Add to ones if not in twos
            twos = (twos ^ num) & ~ones; // Add to twos if not in ones
        }
        return ones; // The single number will be in ones
    }
}
```

### Question 130: Bitwise AND of Numbers Range

Find the bitwise AND of all numbers in a given range [m, n].
Examples

```Input: m = 5, n = 7
Output: 4
=============================================
Input: m = 0, n = 1
Output: 0
```

Constraints

```
0 <= m <= n <= 2^31 - 1
```

```java
public class SolutionBitwiseANDRange {
    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        while (m < n) {
            m >>= 1;
            n >>= 1;
            shift++;
        }
        return m << shift; // Shift back to the left by the number of shifts
    }
}
```

## Math

### Question 131: Palindrome Number

Determine whether an integer is a palindrome without converting it to a string.
Examples

```Input: x = 121
Output: true
=============================================
Input: x = -121
Output: false
```

Constraints

```
-2^31 <= x <= 2^31 - 1
```

```java
public class SolutionPalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false; // Negative numbers and multiples of 10 (except 0) are not palindromes
        }
        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10; // Build the reversed number
            x /= 10; // Remove the last digit from x
        }
        return x == reversed || x == reversed / 10; // Check if the original number is equal to the reversed number or the reversed number without its last digit
    }
}
```

### Question 132: Plus One

Given a non-negative integer represented as an array of digits, increment the number by one.
Examples

```Input: digits = [1,2,3]
Output: [1,2,4]
=============================================
Input: digits = [9,9,9]
Output: [1,0,0,0]
```

Constraints

```
1 <= digits.length <= 100
0 <= digits[i] <= 9
The digits do not contain any leading zeros except for the number 0 itself.
```

```java
import java.util.*;
public class SolutionPlusOne {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits; // No carry needed, just increment and return
            }
            digits[i] = 0; // Set current digit to 0 and carry over
        }
        // If we reach here, it means we had a carry from the most significant digit
        int[] result = new int[n + 1];
        result[0] = 1; // Set the first digit to 1
        return result; // The rest are already initialized to 0
    }
}
```

### Question 133: Factorial Trailing Zeroes

Count the number of trailing zeroes in the factorial of a given number.
Examples

```Input: n = 5
Output: 1
=============================================
Input: n = 10
Output: 2
```

Constraints

```
0 <= n <= 10^4
```

```java
public class SolutionFactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        int count = 0;
        while (n >= 5) {
            n /= 5; // Count how many times 5 is a factor
            count += n; // Add to the count
        }
        return count; // The count of trailing zeroes is the number of times 5 is a factor in the numbers from 1 to n
    }
}
```

### Question 134: Sqrt(x)

Implement the sqrt function to compute the square root of a non-negative integer x.
Examples

```Input: x = 4
Output: 2
=============================================
Input: x = 8
Output: 2
```

Constraints

```
0 <= x <= 2^31 - 1
```

```java
public class SolutionSqrt {
    public int mySqrt(int x) {
        if (x < 2) return x; // Handle cases for 0 and 1 directly
        int left = 2, right = x / 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid; // Use long to prevent overflow
            if (square == x) {
                return mid; // Found the exact square root
            } else if (square < x) {
                left = mid + 1; // Move to the right half
            } else {
                right = mid - 1; // Move to the left half
            }
        }
        return right; // The right pointer will be at the largest integer whose square is less than or equal to x
    }
}
```

### Question 135: Pow(x, n)

Implement the pow function to compute x raised to the power n.
Examples

```Input: x = 2.00000, n = 10
Output: 1024.00000
=============================================
Input: x = 2.10000, n = 3
Output: 9.26100
```

Constraints

```
-100.0 < x < 100.0
-2^31 <= n <= 2^31 - 1
-10^4 <= x^n <= 10^4
```

```java
public class SolutionPow {
    public double myPow(double x, int n) {
        if (n == 0) return 1; // x^0 = 1
        if (n < 0) {
            x = 1 / x; // If n is negative, take the reciprocal of x
            n = -n; // Make n positive
        }
        double result = 1;
        while (n > 0) {
            if ((n & 1) == 1) { // If n is odd
                result *= x; // Multiply the result by x
            }
            x *= x; // Square x
            n >>= 1; // Divide n by 2
        }
        return result; // Return the final result
    }
}
```

### Question 136: Max Points on a Line

Given an array of points on a 2D plane, find the maximum number of points that lie on the same straight line.
Examples

```Input: points = [[1,1],[2,2],[3,3]]
Output: 3
=============================================
Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
```

Constraints

```
1 <= points.length <= 300
points[i].length == 2
-10^4 <= points[i][0], points[i][1] <= 10^4
```

```java
import java.util.*;
public class SolutionMaxPointsOnLine {
    public int maxPoints(int[][] points) {
        if (points.length < 2) return points.length; // If less than 2 points, return the count
        int maxPoints = 1;
        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> slopeCount = new HashMap<>();
            int duplicate = 0, vertical = 0;
            for (int j = i + 1; j < points.length; j++) {
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    duplicate++; // Count duplicates
                } else if (points[i][0] == points[j][0]) {
                    vertical++; // Count vertical lines
                } else {
                    int dy = points[j][1] - points[i][1];
                    int dx = points[j][0] - points[i][0];
                    int gcd = gcd(dy, dx); // Get the greatest common divisor
                    dy /= gcd; // Reduce dy
                    dx /= gcd; // Reduce dx
                    String slope = dy + "/" + dx; // Create a unique slope representation
                    slopeCount.put(slope, slopeCount.getOrDefault(slope, 0) + 1); // Count the slope
                }
            }
            maxPoints = Math.max(maxPoints, vertical + duplicate + 1); // Update max
            for (int count : slopeCount.values()) {
                maxPoints = Math.max(maxPoints, count + duplicate + 1); // Update max
            }
        }
        return maxPoints; // Return the maximum points on a line
    }
}
private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a; // Return the greatest common divisor
    }
}
```

### Question 136: Max Points on a Line

Given an array of points on a 2D plane, find the maximum number of points that lie on the same straight line.
Examples

```Input: points = [[1,1],[2,2],[3,3]]
Output: 3
=============================================
Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
```

Constraints

```
1 <= points.length <= 300
points[i].length == 2
-10^4 <= points[i][0], points[i][1] <= 10^4
```

```java
import java.util.*;
public class SolutionMaxPointsOnLine {
    public int maxPoints(int[][] points) {
        if (points.length < 2) return points.length; // If less than 2 points, return the count
        int maxPoints = 1;
        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> slopeCount = new HashMap<>();
            int duplicate = 0, vertical = 0;
            for (int j = i + 1; j < points.length; j++) {
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    duplicate++; // Count duplicates
                } else if (points[i][0] == points[j][0]) {
                    vertical++; // Count vertical lines
                } else {
                    int dy = points[j][1] - points[i][1];
                    int dx = points[j][0] - points[i][0];
                    int gcd = gcd(dy, dx); // Get the greatest common divisor
                    dy /= gcd; // Reduce dy
                    dx /= gcd; // Reduce dx
                    String slope = dy + "/" + dx; // Create a unique slope representation
                    slopeCount.put(slope, slopeCount.getOrDefault(slope, 0) + 1); // Count the slope
                }
            }
            maxPoints = Math.max(maxPoints, vertical + duplicate + 1); // Update max
            for (int count : slopeCount.values()) {
                maxPoints = Math.max(maxPoints, count + duplicate + 1); // Update max
            }
        }
        return maxPoints; // Return the maximum points on a line
    }
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a; // Return the greatest common divisor
    }
}
```

## 1-D DP

### Question 137: Climbing Stairs

You are climbing a staircase with n steps. Each time you can either climb 1 or 2 steps. Find the number of distinct ways to reach the top.
Examples

```Input: n = 2
Output: 2
=============================================
Input: n = 3
Output: 3
```

Constraints

```
1 <= n <= 45
```

```java
public class SolutionClimbingStairs {
    public int climbStairs(int n) {
        if (n <= 2) return n; // Base cases: 1 step or 2 steps
        int first = 1, second = 2;
        for (int i = 3; i <= n; i++) {
            int temp = second; // Store the previous second value
            second = first + second; // Update second to the new value
            first = temp; // Update first to the previous second value
        }
        return second; // The second variable holds the number of ways to reach the nth step
    }
}
```

### Question 138: House Robber

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, but adjacent houses have security systems connected that will automatically contact the police if two adjacent houses are robbed on the same night.
Examples

```Input: nums = [1,2,3,1]
Output: 4
=============================================
Input: nums = [2,7,9,3,1]
Output: 12
```

Constraints

```
1 <= nums.length <= 100
0 <= nums[i] <= 400
```

```java
public class SolutionHouseRobber {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0; // No houses to rob
        if (nums.length == 1) return nums[0]; // Only one house to rob
        int prev1 = 0, prev2 = 0; // Initialize previous two
        for (int num : nums) {
            int temp = prev1; // Store the previous max
            prev1 = Math.max(prev1, prev2 + num); // Max of robbing current house or not
            prev2 = temp; // Update prev2 to the previous max
        }
        return prev1; // The last computed value is the maximum amount that can be robbed
    }
}
```

### Question 139: Word Break

Given a string s and a dictionary of strings wordDict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
Examples

```Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
=============================================
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
```

Constraints

```
1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
The input string s and the words in wordDict consist of only lowercase English letters.
The wordDict does not contain duplicate words.
```

```java
import java.util.*;
public class SolutionWordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict); // Convert list to set
        boolean[] dp = new boolean[s.length() + 1]; // DP array to track
        dp[0] = true; // Base case: empty string can always be segmented
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true; // If substring s[j:i] is in wordDict and dp[j] is true, set dp[i] to true
                    break; // No need to check further
                }
            }
        }
        return dp[s.length()]; // Return if the whole string can be segmented
    }
}
```

### Question 140: Coin Change

Given an amount and a list of coin denominations, find the minimum number of coins needed to make that amount.
Examples

```Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
=============================================
Input: coins = [2], amount = 3
Output: -1
Explanation: It is not possible to make amount 3 with coin 2.
```

Constraints

```
1 <= coins.length <= 12
1 <= coins[i] <= 2^31 - 1
0 <= amount <= 10^4
```

```java
import java.util.*;
public class SolutionCoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE); // Initialize with max value
        dp[0] = 0; // Base case: 0 coins needed for amount 0
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
```

### Question 141: Longest Increasing Subsequence

Find the length of the longest increasing subsequence in an array of integers.
Examples

```Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], so the length is 4.
=============================================
Input: nums = [0,1,0,3,2,3]
Output: 4
Explanation: The longest increasing subsequence is [0,1,2,3], so the length is 4.
```

Constraints

```
1 <= nums.length <= 2500
-10^4 <= nums[i] <= 10^4
```

```java
import java.util.*;
public class SolutionLongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0; // If the array is empty
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1); // Initialize dp array with 1
        int maxLength = 1; // At least one element is always an increasing subsequence
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); // Update dp[i] if a longer subsequence is found
                }
            }
            maxLength = Math.max(maxLength, dp[i]); // Update the maximum length found so far
        }
        return maxLength; // Return the maximum length of increasing subsequence
    }
}
```

## Multi Dimesional DP

### Question 142: Triangle

Find the minimum path sum from the top to the bottom of a triangle.
Examples

```Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The minimum path sum is 2 + 3 + 5 + 1 = 11.
=============================================
Input: triangle = [[-10]]
Output: -10
```

Constraints

```
1 <= triangle.length <= 200
1 <= triangle[i].length <= i + 1
-10^4 <= triangle[i][j] <= 10^4
```

```java
import java.util.*;
public class SolutionTriangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n]; // DP array to store minimum path sums
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i); // Initialize with the last row of the triangle
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]); // Update dp[j] with the minimum path sum
            }
        }
        return dp[0]; // The top element will have the minimum path sum
    }
}
```

### Question 143: Minimum Path Sum

Find the minimum path sum from the top left to the bottom right of a grid.
Examples

```Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: The path 1 → 3 → 1 → 1 → 1 has the minimum sum = 7.
=============================================
Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
Output: 21
```

Constraints

```
m == grid.length
n == grid[0].length
1 <= m, n <= 200
0 <= grid[i][j] <= 100
```

```java
import java.util.*;
public class SolutionMinimumPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n]; // DP array to store minimum path sums
        dp[0][0] = grid[0][0]; // Start at the top
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0]; // Fill the first column
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid  [0][j]; // Fill the first row
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]); // Update dp[i][j]
            }
        }
        return dp[m - 1][n - 1]; // Return the minimum path sum
    }
}
```

### Question 144: Unique Paths II

Find the number of unique paths from the top left to the bottom right of a grid with obstacles.
Examples

```Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There are two unique paths to the bottom right corner.
=============================================
Input: obstacleGrid = [[0,1],[0,0]]
Output: 1
```

Constraints

```
m == obstacleGrid.length
n == obstacleGrid[0].length
1 <= m, n <= 100
obstacleGrid[i][j] is 0 or 1
```

```java
import java.util.*;
public class SolutionUniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0; // If start or end is blocked, return 0
        }
        int[][] dp = new int[m][n]; // DP array to store unique paths
        dp[0][0] = 1; // Start at the top left corner
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0; // If there's an obstacle, no paths through
                } else {
                    if (i > 0) dp[i][j] += dp[i - 1][j]; // Add paths from the top
                    if (j > 0) dp[i][j] += dp[i][j - 1]; // Add paths from the left
                }
            }
        }
        return dp[m - 1][n - 1]; // Return the number of unique paths to the bottom right corner
    }
}
```

### Question 145: Longest Palindromic Substring

Find the longest palindromic substring in a given string.
Examples

```Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
=============================================
Input: s = "cbbd"
Output: "bb"
```

Constraints

```
1 <= s.length <= 1000
s consist of only digits and English letters (lowercase and/or uppercase).
```

```java
public class SolutionLongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return ""; // Handle edge cases
        int start = 0, end = 0; // Initialize start and end indices
        for (int i = 0; i < s.length(); i++) {    
            int len1 = expandAroundCenter(s, i, i); // Odd length palindrome
            int len2 = expandAroundCenter(s, i, i + 1); // Even length palindrome
            int len = Math.max(len1, len2); // Get the maximum length
            if (len > end - start) { // Update start and end indices if a longer palindrome is found
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1); // Return the longest palindromic substring
    }
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--; // Expand to the left
            right++; // Expand to the right
        }
        return right - left - 1; // Return the length of the palindrome found
    }
}
```

### Question 146: Interleaving String

Determine if a string s3 is formed by interleaving two other strings s1 and s2.
Examples

```Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbca"
Output: true
=============================================
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbac"
Output: false
```

Constraints

```
1 <= s1.length, s2.length <= 100
0 <= s3.length <= s1.length + s2.length
s1, s2, and s3 consist of lowercase English letters.
```

```java
import java.util.*;
public class SolutionInterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false; // Check length constraint
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1]; // DP array to track interleaving
        dp[0][0] = true; // Base case: empty strings interleave to form an empty string
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i > 0) {
                    dp[i][j] |= dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1); // Check if s1 can contribute to s3
                }
                if (j > 0) {
                    dp[i][j] |= dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1); // Check if s2 can contribute to s3
                }
            }
        }
        return dp[s1.length()][s2.length()]; // Return if the entire s3 can be formed by interleaving s1 and s2
    }
}
```

### Question 147: Edit Distance

Find the minimum number of operations required to convert one string into another.
Examples

```Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: horse → rorse (replace 'h' with 'r') → rose (remove 'r') → ros (remove 'e')
=============================================
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: intention → inention (remove 't') → enention (replace 'i' with 'e') → exention (replace 'n' with 'x') → exection (replace 'n' with 'c') → execution (add 'u')
```

Constraints

```1 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
```

```java
import java.util.*;
public class SolutionEditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1]; // DP array to store edit distances
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j; // If word1 is empty, we need j insertions
                } else if (j == 0) {
                    dp[i][j] = i; // If word2 is empty, we need i deletions
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // If characters match, no operation needed
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], // Deletion
                                           Math.min(dp[i][j - 1], // Insertion
                                                    dp[i - 1][j - 1])); // Replacement
                }
            }
        }
        return dp[m][n]; // Return the minimum edit distance
    }
}
```

### Question 148: Best Time to Buy and Sell Stock III

Find the maximum profit you can achieve by making at most two transactions.
Examples

```Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 3.
=============================================
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 4.
```

Constraints

```1 <= prices.length <= 10^5
0 <= prices[i] <= 10^5
```

```java
import java.util.*;
public class SolutionBestTimeToBuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0; // If less than 2 prices, no profit can be made
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;  // Initialize variables for first and second transactions
        for (int price : prices) {
            firstBuy = Math.max(firstBuy, -price); // Update firstBuy
            firstSell = Math.max(firstSell, firstBuy + price); // Update firstSell
            secondBuy = Math.max(secondBuy, firstSell - price); // Update secondBuy
            secondSell = Math.max(secondSell, secondBuy + price); // Update secondSell
        }
        return secondSell; // Return the maximum profit
    }
}
```

### Question 149: Best Time to Buy and Sell Stock IV

Find the maximum profit you can achieve by making at most k transactions.
Examples

```Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 2.
=============================================
Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 4.
Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3.
```

Constraints

```1 <= k <= 100
1 <= prices.length <= 1000
0 <= prices[i] <= 1000
```

```java
import java.util.*;
public class SolutionBestTimeToBuyAndSellStockIV {
    public int maxProfit(int k, int[] prices) {
        if (prices.length < 2 || k == 0) return 0; // If less than 2 prices or no transactions, no profit can be made
        if (k >= prices.length / 2) {
            int maxProfit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1]; // If the price increases
                }
            }
            return maxProfit; // Return the total profit for unlimited transactions
        }
        int[][] dp = new int[k + 1][prices.length]; // DP array to store profits
        for (int i = 1; i <= k; i++) {
            int maxDiff = -prices[0]; // Initialize maxDiff for the first transaction
            for (int j = 1; j < prices.length; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxDiff); // Update the profit for the current transaction
                maxDiff = Math.max(maxDiff, dp[i - 1][j] - prices[j]); // Update maxDiff for the next transaction
            }
        }
        return dp[k][prices.length - 1]; // Return the maximum profit after k transactions
    }
}
```

### Question 150: Maximal Square

Find the largest square containing only 1's in a binary matrix and return its area.
Examples

```Input: matrix = [["1","0","1","0","0"],["1","1","1","1","0"],["1","0","0","1","0"],["0","1","1","1","0"],["1","0","1","0","1]]
Output: 4
Explanation: The largest square has a side length of 2, so its area is 4.
=============================================
Input: matrix = [["0","1"],["1","0"]]
Output: 1
Explanation: The largest square has a side length of 1, so its area is 1.
```

Constraints

```
1 <= matrix.length, matrix[i].length <= 300
matrix[i][j] is '0' or '1'.
```

```java
import java.util.*;
public class SolutionMaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0; // Handle edge cases
        int maxSide = 0; // Variable to track the maximum side length of the square
        int[][] dp = new int[matrix.length][matrix[0].length]; // DP array to store the side lengths of squares
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0)
                        dp[i][j] = 1; // If it's the first row or column, set the side length to 1
                    else
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1; // Update the side length based on the minimum of the three neighboring squares
                    maxSide = Math.max(maxSide, dp[i][j]); // Update the maximum side length found so   far
                }
            }
        }
        return maxSide * maxSide; // Return the area of the largest square found
    }
}
```

completed 150 questions in leetcode/leetcode.md
