package day05;

import java.io.UnsupportedEncodingException;
import java.security.*;

public class Second_05 {

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

	private static String charToString(char c) {
		return "" + c;
	}

	public static void main(String[] args) {
		String init = "abbhdwsy";
		int number = 1;
		int ready = 0;
		String[] password = new String[8];
		String hash = "";

		while (ready < 8) {
			hash = getMD5Hash(init + number);
			if (hash.startsWith("00000")) {
				String pos = charToString(hash.charAt(5));
				// System.out.println("Position char is: " + pos);
				if (pos.matches("[0-7]")) {
					// System.out.println("Digest: " + hash);
					if (password[Integer.parseInt(pos)] == null) {
						password[Integer.parseInt(pos)] = charToString(hash.charAt(6));
						// System.out.println("In the password: " + password[Integer.parseInt(pos)]);
						ready++;
					}
				}
			}
			number++;
		}
		String pw = "";
		for (String s : password) {
			pw += s;
		}
		System.out.println("Password: " + pw);
	}

}
