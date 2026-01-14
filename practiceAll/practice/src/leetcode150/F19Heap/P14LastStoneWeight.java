package leetcode150.F19Heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class P14LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i : stones) {
            pq.add(i);
        }
        while (pq.size() > 1) {
            int item1 = pq.poll();
            int item2 = pq.poll();
            pq.add(Math.abs(item1 - item2));
        }
        return pq.size() > 0 ? pq.poll() : 0;
    }
}