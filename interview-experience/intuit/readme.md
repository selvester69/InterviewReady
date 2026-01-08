// Problem Statement: You are building a log aggregation system. You receive 2 streams of logs from different servers.
// Each stream is sorted by timestamp. However, due to network latency (clock skew), a stream might occasionally receive a
// log that is slightly out of order locally (e.g., a log from time 100 arrives after 102), but only within a small window W.
// if W=5 seconds, a log from time 100 will never arrive after time 105
//W = 5
// A = [1, 4, 7]
// B = [2, 3, 8]

// Expected = [1, 2, 3, 4, 7, 8]
// 105 -> 101,102,
        //103,104

/*
W = 5
A = [10, 15, 13]
B = [11, 14, 12]
Expected = [10, 11, 12, 13, 14, 15]
*/

/*
W = 5
A = [101, 103, 105, 105]
B = [102, 104, 105, 100]
Expected = [100, 101, 102, 103, 104, 105, 105, 105]
*/

// merge logs from both servers

import java.util.*;
class Solution{

    public static List<Integer> mergeLogsStream(int w, int[] t1,int[] t2){
        
        // both have same length
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue(); 
        int index1=0;
        int index2=0;
        while(index1<t1.length && index2<t2.length){// index out of bound 
            //  if(t1[index1]<t2[index2]){
            //     pq.add(t1[index1++]);
            // }else {
            //     pq.add(t2[index2++]);
            // }
            pq.add(t1[index1++]);
            pq.add(t2[index2++]);
            //once the queue is full 
            if(pq.size()>w){// can be t1.length+t2.length
                // 
                res.add(pq.poll());
            }
        }// all streams are complete 
        // remaining element
        while(pq.size()>0){
            res.add(pq.poll());
        }
        return res;
    }
    
    /*
        while(i1<t1.length && i2<t2.lgnth){
            if(t1[i1]<t2[i2]){
                pq.add(t1[i1++])
            }else {
                pq.add(t2[i2++]);
            }
                
            }
        }
    */
   
    
    public static void main(String[] args){
        List<Integer> res = mergeLogsStream(5, new int[]{10, 15, 13}, new int[]{11, 14, 12});
        System.out.println(res);
        List<Integer> res2 = mergeLogsStream(5, new int[]{1, 4, 7}, new int[]{2, 3, 8});
        System.out.println(res2);
        List<Integer> res3 = mergeLogsStream(5, new int[]{101, 103, 105, 105, 106}, new int[]{102,103, 104, 105, 100});
        System.out.println(res3);
    }
    
}

// some questions about operational excellence and some other questions aboud AI usage.
