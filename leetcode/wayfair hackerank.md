# wayfair

```java
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'getMinTime' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY taskMemory
     *  2. INTEGER_ARRAY taskType
     *  3. INTEGER maxMemory
     */

    public static int getMinTime(List<Integer> taskMemory, List<Integer> taskType, int maxMemory) {
    // Write your code here
        Map<Integer,List<Integer>> typeToMemory = new HashMap<>();
        for(int i=0;i<taskMemory.size();i++){
            int type = taskType.get(i);
            int memory = taskMemory.get(i);
            typeToMemory.computeIfAbsent(type, k-> new ArrayList<>()).add(memory);
        }
        int totalTime = 0;
        for(List<Integer> memories: typeToMemory.values()){
            Collections.sort(memories, Collections.reverseOrder());
            int left =0,right=memories.size()-1;
            while(left<=right){
                if(left==right){
                    totalTime++;
                    left++;
                }else {
                    if(memories.get(left)+memories.get(right)<=maxMemory){
                        totalTime++;
                        left++;
                        right--;
                    }else {
                        totalTime++;
                        left++;
                    }
                }
            }
        }
        return totalTime;
    }
}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int taskMemoryCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> taskMemory = IntStream.range(0, taskMemoryCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int taskTypeCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> taskType = IntStream.range(0, taskTypeCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int maxMemory = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.getMinTime(taskMemory, taskType, maxMemory);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
```

========

```java

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'getRegions' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY ip_addresses as parameter.
     */

    public static List<Integer> getRegions(List<String> ip_addresses) {
    // Write your code here
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<ip_addresses.size();i++){
            String ip = ip_addresses.get(i);
            if(isValidIP(ip)){
                System.out.println("valid "+i);
                int region = getValidRegion(ip);
                result.add(region);
            }else {
                result.add(-1);
            }
        }
        return result;
    }
    public static boolean isValidIP(String ipAddress){
        if(ipAddress==null|| "".equals(ipAddress)){
            return false;
        }
        String[]arr = ipAddress.split("\\.");
        System.out.println(arr.length);
        if(arr.length!=4) return false;
        for(String item:arr){
            int valid = Integer.valueOf(item);
            if(valid<0||valid>255)
                return false;
        }
        return true;
    }
    public static int getValidRegion(String ip){
        int firstOctect = Integer.valueOf(ip.split("\\.")[0]);
        if(firstOctect>=0 && firstOctect<=127){
            return 1;
        }else if(firstOctect>=128 && firstOctect<=191){
            return 2;
        }else if(firstOctect>=192 && firstOctect<=223){
            return 3;
        }else if(firstOctect>=224 && firstOctect<=239){
            return 4;
        }else if(firstOctect>=240 && firstOctect<=255){
            return 5;
        }else {
            return -1;
        }
        
    }

}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int ip_addressesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> ip_addresses = IntStream.range(0, ip_addressesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<Integer> result = Result.getRegions(ip_addresses);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
```
