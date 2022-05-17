package com.parshin.task_4.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    private static final Logger logger = LogManager.getLogger();
    private static final String ENCODING_ALGORITHM = "SHA3-256";
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static String getHashedPassword(String password){
        String result = null;
        try{
            MessageDigest messageDigest = MessageDigest.getInstance(ENCODING_ALGORITHM);
            messageDigest.update(password.getBytes(UTF_8));
            byte[] digest = messageDigest.digest();
            result = bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.WARN,"encryption exception can't be reached");
        }
        return result;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(hash.length * 2);
        for (byte h : hash) {
            String hex = Integer.toHexString(0xff & h);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
