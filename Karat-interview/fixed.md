

---
## 🧩 Problem Statement: Ad Conversion Rate Analysis

You are given two files (or lists of records):

1. **User Events:** A list of records, each containing `[user_id, event_type, timestamp]`. Relevant event types are typically **'view'** (ad view) and **'purchase'** (conversion).
2. **User Sign-Ups:** A list of records, each containing `[user_id, sign_up_date]`.

The task is to: **Calculate the overall conversion rate for a specific segment of users, where the conversion rate is defined as $\frac{\text{Number of Unique Users who Purchased}}{\text{Number of Unique Users who Viewed an Ad}}$.**

### **Specific Requirement (Karat Focus)**

Calculate the conversion rate **only for users who signed up before a given cut-off date**.

```java
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class AdConversionAnalyzer {

    // Simulates a date parser/comparator. For simplicity, we use integers for dates 
    // where a smaller number means an earlier date.
    private static final int TARGET_DATE_CUTOFF = 20240101; 

    /**
     * Calculates the conversion rate for users who signed up before the target date.
     *
     * @param events List of [user_id, event_type, timestamp]
     * @param signUps List of [user_id, sign_up_date]
     * @return The calculated conversion rate (double).
     */
    public double calculateSegmentConversionRate(
        List<String[]> events, 
        List<String[]> signUps) 
    {
        // 1. PRE-PROCESS: Build a map to link User ID to Sign-Up Date.
        // Map<UserID, Sign_Up_Date>
        Map<String, Integer> userSignUpDates = new HashMap<>();
        for (String[] record : signUps) {
            String userId = record[0];
            // Assuming the date string can be safely converted to a comparable Integer/Date object
            int signUpDate = Integer.parseInt(record[1]); 
            userSignUpDates.put(userId, signUpDate);
        }

        // 2. AGGREGATION: Collect unique users who viewed and purchased.
        Set<String> viewers = new HashSet<>();
        Set<String> purchasers = new HashSet<>();

        for (String[] event : events) {
            String userId = event[0];
            String eventType = event[1];

            // Check if the user exists and meets the sign-up date criteria
            if (userSignUpDates.containsKey(userId)) {
                int signUpDate = userSignUpDates.get(userId);
                
                // Only proceed if the user signed up *before* the cutoff date
                if (signUpDate < TARGET_DATE_CUTOFF) {
                    
                    if (eventType.equals("view")) {
                        viewers.add(userId);
                    } else if (eventType.equals("purchase")) {
                        purchasers.add(userId);
                    }
                }
            }
        }

        // 3. CALCULATION: Find the intersection and calculate the rate.
        
        // We need users who PURCHASED AND were part of the VIEWERS group.
        // Since the prompt defines the rate as: (Unique Purchasers) / (Unique Viewers),
        // we first filter the purchasers set to only include those who also viewed.
        
        Set<String> convertedUsers = new HashSet<>(purchasers);
        // Retain only those users in 'purchasers' who are also in 'viewers'.
        convertedUsers.retainAll(viewers);
        
        int numerator = convertedUsers.size();
        int denominator = viewers.size();

        if (denominator == 0) {
            // Cannot divide by zero. Return 0.0 if no targeted users viewed the ad.
            return 0.0; 
        }

        return (double) numerator / denominator;
    }
}
```

Optimized Solution on readability


```java
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class AdConversionAnalyzer {

    private static final int TARGET_DATE_CUTOFF = 20240101;

    public double calculateSegmentConversionRate(
        List<String[]> events,
        List<String[]> signUps)
    {
        Set<String> targetUserIds = signUps.stream()
            .filter(record -> Integer.parseInt(record[1]) < TARGET_DATE_CUTOFF)
            .map(record -> record[0])
            .collect(Collectors.toSet());

        if (targetUserIds.isEmpty()) {
            return 0.0;
        }

        Set<String> viewers = new HashSet<>();
        Set<String> purchasers = new HashSet<>();

        for (String[] event : events) {
            String userId = event[0];

            if (targetUserIds.contains(userId)) {
                String eventType = event[1];

                if ("view".equals(eventType)) {
                    viewers.add(userId);
                } else if ("purchase".equals(eventType)) {
                    purchasers.add(userId);
                }
            }
        }

        Set<String> convertedUsers = new HashSet<>(purchasers);
        convertedUsers.retainAll(viewers);

        int numerator = convertedUsers.size();
        int denominator = viewers.size();

        return denominator == 0 ? 0.0 : (double) numerator / denominator;
    }
}
```