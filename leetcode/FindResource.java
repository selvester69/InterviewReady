package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Suppose we have an unsorted log file of accesses to web resources. Each log
 * entry consists of an access time, the ID of the user making the access, and
 * the resource ID.
 * 
 * The access time is represented as seconds since 00:00:00, and all times are
 * assumed to be in the same day.
 * 
 * Write a function that takes the logs and returns the resource with the
 * highest number of accesses in any 5 minute window, together with how many
 * accesses it saw.
 * 
 * Expected Output:
 * most_requested_resource(logs1) # => ('resource_1', 3) [resource_1 is accessed
 * at 416, 620, 620]
 * most_requested_resource(logs2) # => ('resource_1', 2) [resource_1 is accessed
 * at 1060, 1262]
 * most_requested_resource(logs3) # => ('resource_5', 1) [resource_5 is accessed
 * at 300]
 * most_requested_resource(logs4) # => ('resource_5', 4) [resource_5 is accessed
 * at 1, 1, 301, 301]
 * most_requested_resource(logs5) # => ('resource_3', 4) [resource_3 is accessed
 * at 1199, 1200, 1201, and 1202]
 * 
 */
public class FindResource {

    public static String[] findResource(String[][] logs) {
        Map<String,List<Integer>> hm = new HashMap<>();
        for(String[] res: resources){
          hm.putIfAbsent(res[2],new ArrayList<>());
          hm.get(res[2]).add(Integer.parseInt(res[0]));
        }
        String[]ans = new String[2];
        int maxCount=0;
        for(Map.Entry<String,List<Integer>> entry: hm.entrySet()){
          int resourceCount = validateWindow(entry.getValue());
          if(resourceCount>maxCount){
            ans[0] = entry.getKey();
            ans[1] = String.valueOf(resourceCount);
            maxCount = resourceCount;
          }
        }
    return ans;
    }

    public static int getmaxIn5minWindow(List<Integer> arr) {
        Collections.sort(arr);
        int i=0,j=0;
        int maxCount = 0;
        while(j<arr.size()){
          if(arr.get(j)-arr.get(i) <= INTERVAL){
            maxCount = Math.max(maxCount,j-i+1);
            j++;
          }
          else {
              i++;
          }
        }
        return maxCount;
    }

    public static void main(String[] argv) {
        String[][] logs1 = {
                { "200", "user_1", "resource_5" },
                { "3", "user_1", "resource_1" },
                { "620", "user_1", "resource_1" },
                { "620", "user_3", "resource_1" },
                { "34", "user_6", "resource_2" },
                { "95", "user_9", "resource_1" },
                { "416", "user_6", "resource_1" },
                { "58523", "user_3", "resource_1" },
                { "53760", "user_3", "resource_3" },
                { "58522", "user_22", "resource_1" },
                { "100", "user_3", "resource_6" },
                { "400", "user_6", "resource_2" },
        };

        String[][] logs2 = {
                { "357", "user", "resource_2" },
                { "1262", "user", "resource_1" },
                { "1462", "user", "resource_2" },
                { "1060", "user", "resource_1" },
                { "756", "user", "resource_3" },
                { "1090", "user", "resource_3" },
        };

        String[][] logs3 = {
                { "300", "user_10", "resource_5" },
        };

        String[][] logs4 = {
                { "1", "user_96", "resource_5" },
                { "1", "user_10", "resource_5" },
                { "301", "user_11", "resource_5" },
                { "301", "user_12", "resource_5" },
                { "603", "user_12", "resource_5" },
                { "1603", "user_12", "resource_7" },
        };

        String[][] logs5 = {
                { "300", "user_1", "resource_3" },
                { "599", "user_1", "resource_3" },
                { "900", "user_1", "resource_3" },
                { "1199", "user_1", "resource_3" },
                { "1200", "user_1", "resource_3" },
                { "1201", "user_1", "resource_3" },
                { "1202", "user_1", "resource_3" },
        };
        System.out.println(Arrays.toString(findResource(logs1)));
        System.out.println(Arrays.toString(findResource(logs2)));
        System.out.println(Arrays.toString(findResource(logs3)));
        System.out.println(Arrays.toString(findResource(logs4)));
        System.out.println(Arrays.toString(findResource(logs5)));
    }

}
