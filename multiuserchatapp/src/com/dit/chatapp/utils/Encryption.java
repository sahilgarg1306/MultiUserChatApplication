package com.dit.chatapp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Encryption {
	public static String passwordEncrypter(String plainpassword) throws NoSuchAlgorithmException {
		String encryptedpassword= null;
		MessageDigest messageDigest=MessageDigest.getInstance("MD5");
		messageDigest.update(plainpassword.getBytes());
		byte [] encrypt=messageDigest.digest();
		StringBuffer sb= new StringBuffer();
		for(byte b : encrypt) {
			sb.append(b);
		}
		encryptedpassword = sb.toString();
		//System.out.println("Encrypted Password"+encryptedpassword);
		return encryptedpassword;
	}
}
