import java.util.Random;

public class Util {
    // Method to generate a random MAC address
    public static String generateRandomMacAddress() {
        Random random = new Random();
        StringBuilder macAddressBuilder = new StringBuilder();

        // Generate six random hexadecimal digits separated by colons
        for (int i = 0; i < 6; i++) {
            // Generate a random hexadecimal digit (0-15) and convert it to a string
            String hexDigit = Integer.toHexString(random.nextInt(16));
            // Ensure that the hex digit is represented by two characters (e.g., "0F" instead of "F")
            if (hexDigit.length() == 1) {
                hexDigit = "0" + hexDigit;
            }
            // Append the hex digit to the MAC address builder
            macAddressBuilder.append(hexDigit);
            // Add a colon after every two digits except for the last one
            if (i < 5) {
                macAddressBuilder.append(":");
            }
        }

        return macAddressBuilder.toString();
    }
}
