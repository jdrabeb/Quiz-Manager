package com.epita.quizmanager.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Configuration
{
		private Properties properties;
		
		private static Configuration configuration;
		
		private Configuration ()
		{
			properties = new Properties ();
			try
			{
				InputStream input = new FileInputStream ("config.properties");
				properties.load (input);
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
		
		public static Configuration getInstance ()
		{
			if (configuration == null)
			{
				configuration = new Configuration ();
			} 
			return configuration;
		}
		
		public String getPropertyValue (String key)
		{
			return properties.getProperty (key);
		}
//		
//		public void setUserProperty (User user)
//		{
//			try {
//				FileOutputStream output = new FileOutputStream("config.properties", true);
//				properties.setProperty("user.username", user.getName());
//				properties.setProperty("user.password", user.getPassword());
//				properties.setProperty("user.id", user.getId());
//				properties.setProperty("user.isAdmin", Boolean.toString(user.isAdmin()));	    	
//
//				try {
//					properties.store(output, "");
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
}
