package leetcode150.F19Heap;

import java.util.PriorityQueue;

/**
 * Given an array arr[] and a number k . The array is sorted in a way that every
 * element is at max k distance away from it sorted position. It means if we
 * completely sort the array, then the index of the element can go from i - k to
 * i + k where i is index in the given array. Our task is to completely sort the
 * array.
 */
public class P06SortKSortedArray {

    // brute force is to sort everything

    // optimized is using Heap.
    public int[] ksortedArr(int[] arr, int k) {
        if (k == 0 || arr.length == 0)
            return arr;
        int index = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i < k; i++) {
            pq.add(arr[i]);
        }
        for (int i=k ; i < arr.length; i++) {
            arr[index++] = pq.poll();
            pq.add(arr[i]);
        }
        while (!pq.isEmpty()) {
            arr[index++] = pq.poll();
        }
        return arr;
    }
}