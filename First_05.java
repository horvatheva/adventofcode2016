package day05;

import java.io.UnsupportedEncodingException;
import java.security.*;

public class First_05 {
	
	private static String getMD5Hash(String input) {
		String result = "";
		byte[] bytesOfMessage;
		try {
			bytesOfMessage = input.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < thedigest.length; i++) {
				sb.append(Integer.toString((thedigest[i] & 0xff) + 0x100, 16).substring(1));
			}
			result = sb.toString();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		String init = "abbhdwsy";
		int number = 1;
		int ready = 0;
		String password = "";
		String hash = "";
		
		while(ready < 8){
			hash = getMD5Hash(init + number);
			if(hash.startsWith("00000")) {
				password += hash.charAt(5);
				ready++;
				System.out.println("Digest: " + hash);
			}
			number++;
		}
		System.out.println("Password: " + password);
	}
}
