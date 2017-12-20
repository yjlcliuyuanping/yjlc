package com.yjlcmsc.util;


import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

	private static Properties properties = new Properties();
	static{
		try {
			properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public static String getProperties(String key){
		return properties.getProperty(key, null);
	}
	
	
}
