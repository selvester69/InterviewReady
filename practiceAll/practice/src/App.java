import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {

        // Return false
        System.out.println(rateLimit("device_info", 30, 3));
        System.out.println(rateLimit("device_info", 30, 3));
        System.out.println(rateLimit("device_info", 30, 3));

        // Return true
        System.out.println(rateLimit("device_info", 30, 3));
    }

    static class Pair {
        long second;
        int requests;

        Pair(long second, int requests) {
            this.second = second;
            this.requests = requests;
        }
    }

    static Map<String, Pair> cache = new HashMap<>();

    public static boolean rateLimit(String key, int intervalInSecs, int maxLimit) {
        // Return "true" if no more requests are allowed, and "false" otherwise
        long currTimeSec = System.currentTimeMillis() / 1000;
        long scaleSec = currTimeSec / intervalInSecs;
        if (cache.containsKey(key)) {
            Pair p = cache.get(key);
            if (p.second == scaleSec) {
                if (p.requests >= maxLimit) {
                    return true;
                }
                p.requests++;
                return false;
            } else {
                p = new Pair(scaleSec, 1);
                cache.put(key, p);
                return false;
            }
        } else {
            Pair p = new Pair(scaleSec, 1);
            cache.put(key, p);
            return false;
        }
    }
}
