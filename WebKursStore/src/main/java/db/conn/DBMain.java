package db.conn;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class DBMain {

	public static void main(String[] args) throws IOException {
		/*
		MySQLConfig config=new MySQLConfig();
		XStream writer=new XStream(new StaxDriver());
		FileOutputStream fos=new FileOutputStream("src/main/resources/mysql_config1.xml");
		writer.toXML(config, fos);
		fos.close();
		*/
		
		MySQLConfig conf = null;
		conf = MySQLConfig.getInstance();
		SQLDBCDriverConnection conn = new SQLDBCDriverConnection(conf);
		Connection c = conn.setConnect();

		
		DBWorker dbworker = new DBWorker(c);
		String login = "roma@mail.ua";
		String psw = "1245";
		String name="Toto";
		String gender = "male";
		String area= "3";
		String comment="qwe123";
		String amigo="download";
		dbworker.createUser(login, psw, name, gender, area, comment, amigo);
		
		
		
	}

}
