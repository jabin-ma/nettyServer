package com.majipeng.nettyServer.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
//服务器相关配置
public class ServerConfig extends Properties{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2407038810316399754L;
	private static  HashMap<String,ServerConfig> instance=new HashMap<>();
	
	private ServerConfig(String file) {
		try {
			FileInputStream fi=new FileInputStream(new File("conf/"+file));
			load(fi);
			fi.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ServerConfig getInstance(String confName){
		if(!instance.containsKey(confName))
		{
			instance.put(confName, new ServerConfig(confName));
		}
		return instance.get(confName);
	}
    public int getInt(String key,int def){
    	return Integer.parseInt(getProperty(key));
    }
    
    @SuppressWarnings("unchecked")
	public <T> T getClass(String key,Class<T> cls){
    	if(key==null){
    		return null;
    	}
    	if(!containsKey(key)){
    		return null;
    	}
    	try {
			return (T) Class.forName(getProperty(key)).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
    }
    
	public static final class Key{
		public static final String SERVER_PORT="server_port";
	}
	
	
	public static final class Name{
		public static final String CONF_NAME_MAIN="server.conf";
	}
}
