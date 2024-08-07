package com.dit.chatapp.utils;

import java.util.ResourceBundle;

public class ConfigReader {
	public ConfigReader() {
		// TODO Auto-generated constructor stub
	}
	private static ResourceBundle rb= ResourceBundle.getBundle("config");
	public static String getValue(String key) {
		return rb.getString(key);
	}
	
}
