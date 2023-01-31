package com.liceu.objects.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Utilities {
    public static String getSHA512(String input){
        return getSHA512(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String getSHA512(byte[] input) {
        String toReturn = null;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input);
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }
}
