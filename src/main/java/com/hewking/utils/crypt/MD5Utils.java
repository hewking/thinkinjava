package com.hewking.utils.crypt;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    /**
     * md5算法名称
     */
    private static final String MD5 = "MD5";

    /**
     * MD5
     *
     * @param str
     * @return
     */
    public static String md5Summary(String str) {

        if (str == null) {
            return null;
        }

        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance(MD5);
            messageDigest.reset();
            messageDigest.update(str.getBytes(Constants.ENCODING));
        } catch (NoSuchAlgorithmException e) {
            return str;
        } catch (UnsupportedEncodingException e) {
            return str;
        }

        byte[] byteArray = messageDigest.digest();

        return StringUtils.bytes2HexString(byteArray).toUpperCase();
    }

}
