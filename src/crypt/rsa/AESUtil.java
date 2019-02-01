package crypt.rsa;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
	private final static String HEX = "0123456789ABCDEF";
	private final static int JELLY_BEAN_4_2 = 17;



	/**
	 * ����
	 * 
	 * @param key
	 *            ��Կ
	 * @param src
	 *            �����ı�
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String key, String src) throws Exception {
		byte[] rawKey = getRawKey(key.getBytes());
		byte[] result = encrypt(rawKey, src.getBytes());
		return toHex(result);
	}

	/**
	 * ����
	 * 
	 * @param key
	 *            ��Կ
	 * @param encrypted
	 *            �������ı�
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String key, String encrypted) throws Exception {
		byte[] rawKey = getRawKey(key.getBytes());
		byte[] enc = toByte(encrypted);
		byte[] result = decrypt(rawKey, enc);
		return new String(result);
	}

	/**
	 * ��ȡ256λ�ļ�����Կ
	 * 
	 * @param seed
	 * @return
	 * @throws Exception
	 */
	private static byte[] getRawKey(byte[] seed) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom sr = null;
//		// ��4.2���ϰ汾�У�SecureRandom��ȡ��ʽ�����˸ı�
//		if (android.os.Build.VERSION.SDK_INT >= JELLY_BEAN_4_2) {
//			sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
//		} else {
			sr = SecureRandom.getInstance("SHA1PRNG");
//		}
		sr.setSeed(seed);
		// 256 bits or 128 bits,192bits
		kgen.init(256, sr);
		SecretKey skey = kgen.generateKey();
		byte[] raw = skey.getEncoded();
		return raw;
	}

	/**
	 * �����ļ��ܹ���
	 * 
	 * @param key
	 * @param src
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] key, byte[] src) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(src);
		return encrypted;
	}

	/**
	 * �����Ľ��ܹ���
	 * 
	 * @param key
	 * @param encrypted
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] key, byte[] encrypted)
			throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] decrypted = cipher.doFinal(encrypted);
		return decrypted;
	}

	public static String toHex(String txt) {
		return toHex(txt.getBytes());
	}

	public static String fromHex(String hex) {
		return new String(toByte(hex));
	}

	public static byte[] toByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++)
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
					16).byteValue();
		return result;
	}

	public static String toHex(byte[] buf) {
		if (buf == null)
			return "";
		StringBuffer result = new StringBuffer(2 * buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}

	private static void appendHex(StringBuffer sb, byte b) {
		sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
	}

	public static void main(String[] args) {
		try {
			String s = decrypt("v2ex.com","U2FsdGVkX1//EARjvXk1rMk3ob2BE58XPg+ns6wkjfc=");
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
