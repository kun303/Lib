package com.android.ethan.api.utils;


import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class DESHelper {
	
	/**
	 * 根据参数生成KEY
	 */
	public static Key getKey(String strKey) {
		try {
			DESKeySpec dks = new DESKeySpec(strKey.getBytes("utf-8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(dks);
			return securekey;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 加密String明文输入,String密文输出
	 */
	public static String desEncrypt(String strMing, Key key) {
		try{
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptedData = cipher.doFinal(strMing.getBytes("utf-8"));

			return Base64.encodeBytes(encryptedData);
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}

	/**
	 * 解密 以String密文输入,String明文输出
	 * 
	 * @param strMi
	 * @return
	 */
	public static String desDecrypt(String strMi, Key key) {
		try {
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decryptDate = Base64.decode(strMi);
			byte decryptedData[] = cipher.doFinal(decryptDate);

			return new String(decryptedData,"UTF-8");
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}

}