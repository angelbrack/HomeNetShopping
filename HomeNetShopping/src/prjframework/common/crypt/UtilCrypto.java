package prjframework.common.crypt;

import java.io.IOException;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.Key;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.SecretKeyFactory;

public class UtilCrypto {
	
	public native static String des(String msg, String type);
	public native static String getKey(String type);
	
	private static Key keyMulticampus = null; 
	
	private static Cipher cipher = null;
	private static DESedeKeySpec kspec = null;
	private static SecretKeyFactory skf= null;
		
	
	private static String seedMulticampus = "7CD9F0A8E5446FBF";
	
	
	static {	
		System.loadLibrary("des");
		try {
			cipher = Cipher.getInstance("DESede");
			skf= SecretKeyFactory.getInstance("DESede");
			setKey();
		} catch (Exception e) {
			//System.out.println(e.toString());
		} 	
	}
	
	/************************ START HASH  ******************************/	 
	 
	public static String encryptSHA(String inputValue) throws Exception {
		if( inputValue == null ) 
			throw new Exception("Can't conver to Message Digest 5 String value!!");
			
		MessageDigest md = MessageDigest.getInstance("SHA"); //step 2
		//md.update(inputValue.getBytes("UTF-8")); //step 3
		//byte raw[] = md.digest();
		
		byte raw[] = md.digest(inputValue.getBytes()); //step 4
		return (new BASE64Encoder()).encode(raw); //step 5
	}
  
	public static String encryptMD5(String inputValue) throws Exception {
		if( inputValue == null ) 
			throw new Exception("Can't conver to Message Digest 5 String value!!");			
		
		if (inputValue.length() < 1) {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			byte[] raw =  md.digest(inputValue.getBytes());	
			return (new BASE64Encoder()).encode(raw); 
		}
		else {
			cipher.init(Cipher.ENCRYPT_MODE, keyMulticampus);
			return (new BASE64Encoder()).encode(cipher.doFinal(inputValue.getBytes()));
		}
	}
	
	/************************ END HASH  *******************************/




	/************************ START 3DES  ******************************/	
		
	/*
	public static String getKey() {
		return String.valueOf(key.getEncoded());
	}
	*/
	
	public static String encrypt3DES(String input, String systemType)	
		throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		
		if (systemType.equals("multicampus"))
			cipher.init(Cipher.ENCRYPT_MODE, keyMulticampus);
		/*
		else if (systemType.equals("wiseup"))
			cipher.init(Cipher.ENCRYPT_MODE, keyWiseUp);
		else if (systemType.equals("wisehome"))
			cipher.init(Cipher.ENCRYPT_MODE, keyWiseHome);
		*/
		return (new BASE64Encoder()).encode(cipher.doFinal(input.getBytes()));
	}
	/* ��ȣȭŰ ������� �߰� (20090310) �����
	 * �Ϲ������� systemType="multicampus" ������
	 * Ư�� ��ȣȭŰ�� ����ϰ��� �Ҷ� systemType�� �־��ش�.
	 */
	public static String decrypt3DES(String input, String systemType)
		throws InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, IOException {
		
		byte[] encryptionBytes = new sun.misc.BASE64Decoder().decodeBuffer(input);

		if (systemType.equals("multicampus"))
			cipher.init(Cipher.DECRYPT_MODE, keyMulticampus);
		else
			cipher.init(Cipher.DECRYPT_MODE, skf.generateSecret(new DESedeKeySpec(systemType.getBytes())));

		/*
		else if (systemType.equals("wiseup"))
			cipher.init(Cipher.DECRYPT_MODE, keyWiseUp);
		else if (systemType.equals("wisehome"))
			cipher.init(Cipher.DECRYPT_MODE, keyWiseHome);
		*/
		return 	new String(cipher.doFinal(encryptionBytes));
	}

    public static void setKey() throws InvalidKeyException, InvalidKeySpecException{
    	
    	String tmp = null;
		tmp = des(seedMulticampus, "Enc");
		kspec = new DESedeKeySpec(tmp.getBytes());
		keyMulticampus = skf.generateSecret(kspec);
	}

	/* TEST */	
    public static void main(String args[]) throws Exception {
    	 String strResult = null;
    	 
    	 /* ���� �׽�Ʈ*/
    	 /*
    	 Calendar c = Calendar.getInstance();
    	 //System.out.println("start time : " +c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + ":" + c.get(Calendar.MILLISECOND) );
    	 for (int i=0; i < 1000 ; i++) {
	    	 	strResult = UtilCrypto.encrypt3DES("abcdefefghijklmnop"+String.valueOf(i), "multicampus");
	    	 	strResult = UtilCrypto.decrypt3DES(strResult, "multicampus");	    
	    	 ////System.out.println(i + " : " + strResult);	 
	     }	     
    	 	
         c = Calendar.getInstance();
    	 //System.out.println("end   time : " +c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + ":" + c.get(Calendar.MILLISECOND) );        
	     */
	     if (args[0].equals("Enc")) {
	     	//System.out.println("Encode : " + UtilCrypto.encrypt3DES(args[1],"multicampus"));
	     	//System.out.println("Encode : " + UtilCrypto.encryptMD5(args[1]));
	     }
	     else {
	     	//System.out.println("Decode : " + UtilCrypto.decrypt3DES(args[1],"multicampus"));
	     }
    }
    
}
