package com.yecai.garden.soil.system.common.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * DES加密类
 * 
 * @author yecai
 * @date 2018-01-10
 */
@SuppressWarnings("restriction")
public class DataEncryptionStandardUtils {
	// 密码，长度要是8的倍数
	private final static String PASSWORD = "huangyecai";
	private final static String ENCODE = "ISO-8859-1";

	/**
	 * 加密
	 * 
	 * @param datasource
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 */
	private static byte[] encrypt(byte[] datasource) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(PASSWORD.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加密
	 * 
	 * @param str
	 *            明文
	 * @return 字符串密文
	 * @throws Exception
	 */
	public static String encrypt(String str) throws Exception {
		return new String(encrypt(str.getBytes(ENCODE)), ENCODE);
	}

	/**
	 * 加密
	 * 
	 * @param str明文
	 * @return BASE64密文
	 * @throws Exception
	 */
	@SuppressWarnings("restriction")
	public static String encrypt1(String str) throws Exception {
		String strs = new BASE64Encoder().encode(encrypt(str.getBytes(ENCODE)));
		return strs;
	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] src) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(PASSWORD.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
	}

	/**
	 * 解密
	 * 
	 * @param str密文
	 * @return 明文
	 * @throws Exception
	 */
	public static String decrypt(String str) throws Exception {
		return new String(decrypt(str.getBytes(ENCODE)), ENCODE);
	}

	/**
	 * 解密
	 * 
	 * @param str密文
	 * @return 明文
	 * @throws Exception
	 */
	@SuppressWarnings("restriction")
	public static String decrypt1(String str) throws Exception {
		if (str == null || str.equals("")) {
			return null;
		}

		BASE64Decoder decoder = new BASE64Decoder();
		byte[] buf = decoder.decodeBuffer(str);
		byte[] bt = decrypt(buf);
		return new String(bt, ENCODE);
	}
}
