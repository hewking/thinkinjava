package com.hewking.crypt;


import java.io.UnsupportedEncodingException;

public class PassUtils {
    /**
     * 用指定掩码对密码处理后，再用指定策略对处理后的密码加密
     * 从掩码中截取密码长度的子串，再和密码做异或，然后再加密
     *
     * @param password 密码
     * @return 加密后的密码
     */
    public static String encryptPassword(String password) {
        if (StringUtils.isNullOrEmpty(password)) {
            return password;
        }

        String maskedPassword = Constants.PASS_MASK.substring(0, password.length());
        String encryptedPassword = password;

        try {
            byte[] pwdBytes = password.getBytes(Constants.ENCODING);
            byte[] keyBytes = maskedPassword.getBytes(Constants.ENCODING);

            for (int i = 0; i < pwdBytes.length; i++) {
                pwdBytes[i] ^= keyBytes[i];
            }
            //mask
            encryptedPassword = StringUtils.bytes2HexString(pwdBytes);

            //1st md5
            encryptedPassword = MD5Utils.md5Summary(encryptedPassword);

            //append noise
            encryptedPassword += encryptedPassword + Constants.PASS_NOISE;

            //2nd md5
            encryptedPassword = MD5Utils.md5Summary(encryptedPassword);
        } catch (UnsupportedEncodingException e) {
        }

        return encryptedPassword;
    }

}
