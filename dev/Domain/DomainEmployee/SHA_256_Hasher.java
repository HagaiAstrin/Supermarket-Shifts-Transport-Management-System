package Domain.DomainEmployee;

import com.google.gson.JsonObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA_256_Hasher {
    public SHA_256_Hasher() {}

    public boolean Checker(String[] j, String path){
        String user_name = j[0];
        String password = j[1]; // Assuming password is a string
        String id = j[2];

        // Convert password to SHA-256 hash
        String hashedPassword = hashPassword(password);


        // Perform authentication or validation logic using the hashed password
        // For example, compare it with a stored hashed password

        return IO_Data.Authentication(user_name, hashedPassword, id, path);
    }

    public String hashPassword(String password) {
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Apply SHA-256 hash function to the password
            byte[] hashBytes = digest.digest(password.getBytes());

            // Convert byte array to a hexadecimal string representation
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle NoSuchAlgorithmException (e.g., if SHA-256 algorithm is not available)
            e.printStackTrace();
            return null;
        }
    }
}
