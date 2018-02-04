package db.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDBCDriverConnection {
	MySQLConfig conf;
	public SQLDBCDriverConnection(MySQLConfig conf){
		this.conf=conf;	
	}
	public SQLDBCDriverConnection(){
	
	}
	public Connection setConnect() {
		Connection conn = null;	
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://"+conf.getHost()+"/"+conf.getDb();
				String login = conf.getUser();
				String password =conf.getPsw();
			conn = DriverManager.getConnection(url,login,password);
		//	System.out.println("Connection to SQL has been established.");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return conn;
	}


}