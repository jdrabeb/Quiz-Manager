package com.epita.quizmanager.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Singleton of configuration get the properties of the database
 * from a config.properties file.
 * @author rabeb
 *
 */
public class Configuration
{
		private Properties properties;
		
		private static Configuration configuration;
		
		/**
		 * Private constructor of configuration.
		 * loads the config.properties file.
		 */
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
		
		/**
		 * Getter of the instance of the configuration.
		 * @return the instance of the configuration.
		 */
		public static Configuration getInstance ()
		{
			if (configuration == null)
			{
				configuration = new Configuration ();
			} 
			return configuration;
		}
		
		/**
		 * Reads and returns the properties for the config.properties file.
		 * @param key - The key to grab the value of property of.
		 * @return the property at key.
		 */
		public String getPropertyValue (String key)
		{
			return properties.getProperty (key);
		}
}
