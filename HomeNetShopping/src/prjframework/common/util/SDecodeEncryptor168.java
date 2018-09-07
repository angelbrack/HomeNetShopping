package prjframework.common.util;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;

/**
 * 경력개발센터 복호화 소스 (배포용)
 * @author 고려대학교 전산개발부
 */
public class SDecodeEncryptor168 {
	
	/**
	 * 경력개발센터 프로그램에 해당하는 KEY
	 * 암호화 할때와 복호화 할때 해당 KEY 값이 일치해야 한다.
	 */
	private final static String KEY = "career.korea.ac.kr";
		
	public static byte[] decode(String encrypted) throws Exception {
		return new BASE64Decoder().decodeBuffer(encrypted);
	}
	
	public static String decodeRandomKey(String randomKey) {
		String ciphers = "aBcDeFgHiJ";
		String key = "";
		
		for(int i = 0; i < randomKey.length(); i++) {
			int index = ciphers.indexOf(randomKey.substring(i, i+1));
			if(index < 0)
				key = key.concat(".");
			else
				key = key.concat(String.valueOf(index));
		}
		
		return key;
	}
	
	public static String findRandomKey(String encrypted) {
		return encrypted.substring(encrypted.lastIndexOf(".")-1);
	}
	
	public static String findOriginData(String encrypted) {
		return encrypted.substring(0, encrypted.lastIndexOf(".")-1);
	}
	
	public static String decrypt(byte[] keydata, byte[] data) {
		try {
	        DESedeKeySpec keySpec = new DESedeKeySpec(keydata);
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
	        SecretKey desKey = keyFactory.generateSecret(keySpec);

	        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
	        cipher.init(Cipher.DECRYPT_MODE, desKey);

	        byte [] decryptedText = cipher.doFinal(data);
	        String output =  new String(decryptedText, "UTF8");
	        
	        return output;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 암호화된 값을 복호화 한다.
	 * @param encrypted 암호화로 전달된 파라미터
	 * @return String
	 */
	public static String getDecryptedValue(String encrypted) {
		
		String decrypted = "";
		
		try {
			String customKey = KEY + decodeRandomKey(findRandomKey(encrypted));
			
			decrypted = decrypt(customKey.getBytes(), decode(findOriginData(encrypted)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return decrypted;
	}

	public static void main(String[] args) {
		
		DecodeEncryptor168 encryptor168 = new DecodeEncryptor168();
		
		//테스트로 암호화 한 값
		String encrypted = "IOJrGDGHemMUJWKoDgxQCRRpbs8mo6MuyFnrO7iNrbOeHEXXvln4dQ20LLUP/eYMLIgm7m0pd0haHjFh4tdEqaUzo3MQsYajStzvya6OHY69v20iCRPbmQ==a.iFcDaacHigaBeHiJ";
		
		String decrypted = encryptor168.getDecryptedValue(encrypted);
		
		System.out.println("decrypt data : " + decrypted);
		
	}
}

