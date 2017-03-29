package com.absoft.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Diego Arantes
 * @description Algoritmo para converter Strings para SHA1.
 */
public class ConverterSHA1 {

    public static String cipher(String value) {
        byte[] buffer = value.getBytes();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(buffer);
            byte[] digest = md.digest();
            String hexValue = "";
            for (int i = 0; i < digest.length; i++) {
                int b = digest[i] & 0xff;
                if (Integer.toHexString(b).length() == 1) {
                    hexValue = hexValue + "0";
                }
                hexValue = hexValue + Integer.toHexString(b);
            }
            return hexValue;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ConverterSHA1.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
