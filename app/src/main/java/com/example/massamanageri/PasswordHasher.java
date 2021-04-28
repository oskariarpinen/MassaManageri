package com.example.massamanageri;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordHasher {
    byte[] hash, salt;
    String storedhash;

    public String createHashWithSalt(String string) { // Function to create hashed passwords
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(string.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {                 // Converts bytes to string of equal length every time
                sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            storedhash = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return storedhash;
    }
}

