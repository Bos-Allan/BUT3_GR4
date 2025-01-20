package com.iut.banque.use;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class encryptMethode {
    /**
     * @return hashed password
     */
    public static String encrypt(String userPwd) {
        String hashedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] encryptPass = md.digest(userPwd.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for(int i=0; i< encryptPass.length ;i++){
                sb.append(Integer.toString((encryptPass[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hashedPassword;
    }
}
