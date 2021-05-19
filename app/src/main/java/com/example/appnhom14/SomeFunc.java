package com.example.appnhom14;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class SomeFunc {
    public String hashSHA256(String pw){
        try {
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }
            digest.reset();
            return bin2Hex(digest.digest(pw.getBytes()));
        } catch (Exception ignored){
            return null;
        }
    }

    private static String bin2Hex(byte[] data) {
        StringBuilder hex = new StringBuilder(data.length * 2);
        for (byte b: data){
            hex.append(String.format("%02x", b & 0xFF));
        }
        return hex.toString();
    }
}

