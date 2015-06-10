package com.epam.labs.utils;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class for work with hash algorithms
 */
public class HashUtil {
    final Logger log = Logger.getLogger(getClass().getName());

    /**
     * Method for getting hash for inputted message
     *
     * @param message Message for which hash must be generated
     * @return Hash value of inputted message
     */
    public String getHash(String message) {
        log.info("Getting hash for password");
        String msg = "";
        String tmp = generateSHA256(message);
        tmp = generateMD5(tmp);
        msg = generateSHA1(tmp);
        return msg;
    }


    /**
     * Method for getting MD5 hash value
     *
     * @param message Message which must be hashed
     * @return Hash for inputted message
     */
    private String generateMD5(String message) {
        return hashString(message, "MD5");
    }

    /**
     * Method for getting SHA-1 hash value
     *
     * @param message Message which must be hashed
     * @return Hash for inputted message
     */
    private String generateSHA1(String message) {
        return hashString(message, "SHA-1");
    }

    /**
     * Method for getting SHA-256 hash value
     *
     * @param message Message which must be hashed
     * @return Hash for inputted message
     */
    private String generateSHA256(String message) {
        return hashString(message, "SHA-256");
    }

    /**
     * Method for generating hash by specified algorithm for inputted message
     *
     * @param message   Inputted message
     * @param algorithm Hash algorithm
     * @return Hash value of inputted message
     */
    private String hashString(String message, String algorithm) {

        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));
            return convertByteArrayToHexString(hashedBytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            log.info("Could not generate hash from String", ex);
        }
        return message;
    }

    /**
     * Method for converting Byte array to string
     *
     * @param arrayBytes
     * @return
     */
    private String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }
}