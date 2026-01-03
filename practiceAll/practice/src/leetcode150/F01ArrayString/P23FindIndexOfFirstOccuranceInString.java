package leetcode150.F01ArrayString;

public class P23FindIndexOfFirstOccuranceInString {

    public static int findIndex(String needle, String hayStack) {
        int needleIndex = 0;
        int hayStackIndex = 0;

        while (hayStackIndex < hayStack.length()) {
            if (needle.charAt(needleIndex) == hayStack.charAt(hayStackIndex)) {
                // handle single index value
                if (needle.length() == 1) {
                    return hayStackIndex;
                }
                needleIndex++;
                hayStackIndex++;
            } else {
                // if index do not match increment hayStackIndex;
                // move hayStackIndex to original +1;
                hayStackIndex = hayStackIndex - needleIndex + 1;
                needleIndex = 0;
            }
            // break point of loop since needle is found no need to loop further.
            if (needleIndex == needle.length()) {
                return hayStackIndex - needleIndex;
            }
        }
        // it means needle is not found
        return -1;
    }

    public static void main(String[] args) {
        int res = findIndex("sad", "sadbutsad");
        System.out.println(res);
    }

}
