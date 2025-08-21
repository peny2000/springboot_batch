package com.a3.lfs.support.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * <p>
 * 업무명 : 환경설정 정보 조회 
 * <p>
 * 업무구분 : 공통 > 환경설정 정보 조회
 * <p>
 * 설명 : 환경설정 정보를 조회한다.
 *
 * @author  A3 Security
 * @version 0.1
 */
public class ConfigSet {
	
	private static Properties props;
	
	public ConfigSet() throws Exception {
		if ( props == null ) {
			init();
		}
	}
	
	public void reload() throws Exception {
		try
		{
			init();
		}
		catch ( Exception e )
		{
			System.out.println("Can't read the properties file...");
		}
	}

	private void init() throws Exception {
		
		InputStream is = getClass().getResourceAsStream("/application.properties");
		props          = new Properties();
		
		try
		{
			props.load(is);
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			System.out.println("Cant't read the properties file...");
			return;
		}
	}
	
	public Properties getProperties() {
		return props;
	}

	public String getProperty(String s) throws Exception {
		
		String result = "";
		
		try
		{
			result = props.getProperty(s);
		}
		catch ( Exception ex )
		{
			throw new Exception("ConfigSet.getProperty(\"" + s + "\")\r\n" + ex.getMessage());
		}
		return result;
	}

}
