package other;

public class ValidateIPAddress {
    public static String validIPAddress(String queryIP) {
        if (queryIP.contains(".")) {
            String[] parts = queryIP.split("\\.", -1);
            if (parts.length != 4)
                return "Neither";
            for (String part : parts) {
                if (part.length() == 0 || part.length() > 3)
                    return "Neither";
                if (part.charAt(0) == '0' && part.length() != 1)
                    return "Neither";
                for (char c : part.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        return "Neither";
                    }
                }
                if (Integer.parseInt(part) > 255) {
                    return "Neither";
                }
            }
            return "IPv4";
        } else if (queryIP.contains(":")) {
            String[] parts = queryIP.split("\\:", -1);
            if (parts.length != 8)
                return "Neither";
            for (String part : parts) {
                if (part.length() == 0 || part.length() > 4)
                    return "Neither";
                for (char c : part.toUpperCase().toCharArray()) {
                    if ((c < '0' || c > '9') && (c < 'A' || c > 'F')) {
                        return "Neither";
                    }
                }
            }
            return "IPv6";
        } else {
            return "Neither";
        }
    }

    // formatted code
    public static String validIPAddressFormatted(String queryIP) {
        if (queryIP.contains(".")) {
            return validIP4Address(queryIP);
        } else if (queryIP.contains(":")) {
            return validIP6Address(queryIP);
        } else {
            return "Neither";
        }
    }

    public static String validIP4Address(String queryIP) {
        String[] parts = queryIP.split("\\.", -1);
        if (parts.length != 4)
            return "Neither";
        for (String part : parts) {
            if (part.length() == 0 || part.length() > 3)
                return "Neither";
            if (part.charAt(0) == '0' && part.length() != 1)
                return "Neither";
            for (char c : part.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return "Neither";
                }
            }
            if (Integer.parseInt(part) > 255) {
                return "Neither";
            }
        }
        return "IPv4";

    }

    public static String validIP6Address(String queryIP) {

        String[] parts = queryIP.split("\\:", -1);
        if (parts.length != 8)
            return "Neither";
        for (String part : parts) {
            if (part.length() == 0 || part.length() > 4)
                return "Neither";
            for (char c : part.toUpperCase().toCharArray()) {
                if ((c < '0' || c > '9') && (c < 'A' || c > 'F')) {
                    return "Neither";
                }
            }
        }
        return "IPv6";

    }

    public static void main(String[] args) {
        System.out.println(validIPAddress("172.16.254.1"));
        System.out.println(validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:"));
        System.out.println(validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
        System.out.println(validIPAddress("256.256.256.256"));
        System.out.println("==================");
        System.out.println(validIPAddressFormatted("172.16.254.1"));
        System.out.println(validIPAddressFormatted("2001:0db8:85a3:0:0:8A2E:0370:"));
        System.out.println(validIPAddressFormatted("2001:0db8:85a3:0:0:8A2E:0370:7334"));
        System.out.println(validIPAddressFormatted("256.256.256.256"));
    }
}
