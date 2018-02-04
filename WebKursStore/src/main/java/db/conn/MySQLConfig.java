package db.conn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class MySQLConfig {
	private String host;
	private String db;
	private String user;
	private String psw;
	private static MySQLConfig conf;
	MySQLConfig(){
		//System.out.println(config);
	}
	
	
	public static MySQLConfig getInstance() throws FileNotFoundException {
		FileInputStream fis=null;
		
		if(conf==null){
			XStream reader=new XStream(new DomDriver());
			
			
				System.out.println(MySQLConfig.class.getClassLoader().getResource(""));
				fis = new FileInputStream(MySQLConfig.class.getClassLoader().getResource("mysql_config.xml").getFile());
			
			conf=(MySQLConfig) reader.fromXML(fis);
			System.out.println(conf);
		}
		return conf;
		
	}

	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	@Override
	public String toString() {
		return "MySQLConfig [host=" + host + ", db=" + db + ", user=" + user + ", psw=" + psw + "]";
	}
}
