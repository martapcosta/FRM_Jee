package com.marta.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {

	public static String encrypt(String value) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] valueBytes = value.getBytes();
			md.reset();
			byte[] digested = md.digest(valueBytes);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < digested.length; i++) {
				sb.append(Integer.toHexString(0xff & digested[i]));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			System.out.println("Not able to get MD5 instance: " + ex.getMessage());
		}
		return value;
	}
}
