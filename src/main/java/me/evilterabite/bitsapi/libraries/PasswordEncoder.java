package me.evilterabite.bitsapi.libraries;

import com.lambdaworks.crypto.SCryptUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {

    public static String encodeMD5(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] passwordBytes = password.getBytes();
            messageDigest.reset();
            byte[] digested = messageDigest.digest(passwordBytes);
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digested) {
                stringBuilder.append(Integer.toHexString(0xff & b));
            }

            return stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String encodeSCrypt(String password) {
        return SCryptUtil.scrypt(password, 65536, 7, 5);
    }

    public static boolean checkSCrypt(String enteredPassword, String hashed) {
        return SCryptUtil.check(enteredPassword, hashed);
    }
}
