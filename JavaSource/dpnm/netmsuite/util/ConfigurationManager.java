package dpnm.netmsuite.util;

import java.io.*;

public class ConfigurationManager {
	
	public ConfigurationManager(){
		
		try{
			File configFile = new File("./config");
			FileReader fr = new FileReader(configFile);
			BufferedReader br = new BufferedReader(fr);
			
			DB_DRIVER = br.readLine();
			DB_URI = br.readLine();
			URL_FOR_RMI = br.readLine();
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
	
	
	private static ConfigurationManager configMgr = new ConfigurationManager();
	
	
	public static ConfigurationManager getInstance(){
		return configMgr;
	}
	
	private String DB_DRIVER;
	private String DB_URI;
	private String URL_FOR_RMI;
	
	public static void main(String argc[]){
		
		ConfigurationManager mgr = ConfigurationManager.getInstance();
		System.out.println(mgr.URL_FOR_RMI);
	}

	public void setDB_DRIVER(String dB_DRIVER) {
		DB_DRIVER = dB_DRIVER;
	}

	public String getDB_DRIVER() {
		return DB_DRIVER;
	}

	public void setDB_URI(String dB_URI) {
		DB_URI = dB_URI;
	}

	public String getDB_URI() {
		return DB_URI;
	}

	public void setURL_FOR_RMI(String uRL_FOR_RMI) {
		URL_FOR_RMI = uRL_FOR_RMI;
	}

	public String getURL_FOR_RMI() {
		return URL_FOR_RMI;
	}
	
}
