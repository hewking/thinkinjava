package crypt;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;



public class AESUtils {
	private static final String KEY_ALGORITHM = "AES";
	//private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	//private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/NoPadding";

	//AES IV
	public static final String ENCRYPTION_IV = "pytIa738ewdljkdF";
	//Default AES Key
	public static final String AES_KEY = "*>df6Xdf;aPoew3f";

	static String ENCODING = "UTF-8";


	/**
	 * AES 加密操作
	 *
	 * @param content
	 *            待加密内容
	 * @param password
	 *            加密密码
	 * @return 返回Base64转码后的加密数据
	 */
	public static String encrypt(String content, String password) {
		try {
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, makeKey(password), new IvParameterSpec(
					new byte[16]));
			//cipher.init(Cipher.ENCRYPT_MODE, makeKey(password), makeIv());
			return StringUtils.bytes2HexString(cipher.doFinal(content.getBytes(ENCODING)));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * AES 解密操作
	 *
	 * @param content
	 * @param password
	 * @return
	 */
	public static String decrypt(String content, String password) {

		String decrypted = "";
		try {
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, makeKey(password), new IvParameterSpec(new byte[16]));
			//cipher.init(Cipher.DECRYPT_MODE, makeKey(password), makeIv());
			//cipher.init(Cipher.DECRYPT_MODE, makeKey(password));
			decrypted = new String(cipher.doFinal(StringUtils.hexString2Bytes(content)), ENCODING);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return decrypted;
	}

	static AlgorithmParameterSpec makeIv() {
		try {
			return new IvParameterSpec(ENCRYPTION_IV.getBytes(ENCODING));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	static Key makeKey(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] key = md.digest(password.getBytes(ENCODING));
			return new SecretKeySpec(key, KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String generateRandomKey() {
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {

	    /*MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] key = md.digest(Constants.AES_KEY.getBytes(ENCODING));
            System.out.println(StringUtils.bytes2HexString(key));
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/


//		String en = AESUtils.encrypt("hello", Constants.AES_KEY);
//		System.out.println("encrypt:"+en);
//		String decrypt = AESUtils.decrypt(en, Constants.AES_KEY);
//		System.out.println("decrypt:"+decrypt);
		//  System.out.println(AESUtils.decrypt("B4A79BF8E7FE2C4876E0F09611E1334D", Constants.AES_KEY));
	}
}
