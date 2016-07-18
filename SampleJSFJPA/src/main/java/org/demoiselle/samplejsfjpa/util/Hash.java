package org.demoiselle.samplejsfjpa.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public static String SHA1 = "SHA-1";
    public static String SHA256 = "SHA-256";
    public static String MD5 = "MD5";

    private static String bytesToString(byte[] input) {
        StringBuilder result = new StringBuilder();
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (int idx = 0; idx < input.length; ++idx) {
            byte b = input[idx];
            result.append(digits[(b & 0xf0) >> 4]);
            result.append(digits[b & 0x0f]);
        }
        return result.toString();
    }

    public static String fromString(String value, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(value.getBytes(Charset.forName("UTF-8")));
            return Hash.bytesToString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

}
