package com.project.market.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Util {
    public static String randomString(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz@#$%^&ABCDEFGHIJKLMNOP1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);

            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;
    }

    public static String randomNumber(int length) {
        String alphabet = "1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);

            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;
    }

    public static boolean checkPassphrases(String phrases, String pass)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        boolean status = true;

        String passwordRequest = hashSha256(pass);
        if (!passwordRequest.equals(phrases)) {
            status = false;
        }
        return status;
    }

    public static String hashSha256(String msg)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(msg.getBytes("UTF-8")); // Change this to "UTF-16" if needed
        byte[] digest = md.digest();
        return String.format("%064x", new java.math.BigInteger(1, digest));
    }
}
