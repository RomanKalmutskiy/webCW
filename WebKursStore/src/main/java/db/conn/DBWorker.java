package db.conn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Product;
import models.User;

public class DBWorker {
	
	private User user = null;
	
	private Connection conn=null;
	public DBWorker(Connection conn){
		this.conn=conn;
	}

	

	public boolean login(String login, String psw){
		String name=null;
		//String sql="SELECT name FROM users WHERE email='"+login+"' AND password='"+psw+"'";
		String sql="SELECT login, password, name, gender, area, comments, amigo FROM users2 WHERE login='"+login+"' AND password='"+psw+"'";
		
		Statement st=null;
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
		}
		ResultSet rs=null;
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
		}
		try {
			if(rs.next()){
				name=rs.getString("name");
				user = new User (rs.getString("login"), rs.getString("password"), rs.getString("name"), rs.getString("gender"), rs.getString("area"), rs.getString("comments"), rs.getString("amigo"));
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return false;
		
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	public boolean createUser(String login, String psw, String name, String gender, String area, String comment,
			String amigo) {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO users2 (login, password, name, gender, area, comments, amigo) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			stmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("Statement is not prepared");
		}
		try {
			stmt.setString(1, login);
			stmt.setString(2, psw);
			stmt.setString(3, name);
			stmt.setString(4, gender);
			stmt.setString(5, area);
			stmt.setString(6, comment);
			stmt.setString(7, amigo);
		} catch (SQLException e) {
			System.out.println("some values is not set");
		}
		try {
			stmt.execute();
			System.out.println("Query executed SUCCESSFULLY");
			return true;
		} catch (SQLException e) {
			System.out.println("Query is not executed");
		}
		return false;

	}

	public int verifyUnique(String login, String psw) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "SELECT COUNT(id) FROM users2 WHERE login=? AND password=?";
		try {
			stmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("Statement is not prepared");
		}
		try {
			stmt.setString(1, login);
		} catch (SQLException e1) {
			System.out.println("Login is not set");
		}
		try {
			stmt.setString(2, psw);
		} catch (SQLException e1) {
			System.out.println("Password is not set");
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
			System.out.println("Query executed SUCCESSFULLY");
		} catch (SQLException e) {
			System.out.println("Result set is not obtained");
		}
		try {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("SQL statement is not executed");
		}
		return count;
	}
	
	/*.. рабочая версия
	public List<Product> getProducts(){
		String query="SELECT * FROM products";
		Statement st=null;
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Product> products=new ArrayList<>();
		try {
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next()){
				products.add(new Product(rs.getInt("prod_id"),rs.getString("prod_descript"),Integer.parseInt(rs.getString("prod_price"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	*/
	
	public List<Product> getProducts(){
		String query="SELECT * FROM products";
		Statement st=null;
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Product> products=new ArrayList<>();
		try {
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next()){
				products.add(new Product(rs.getInt("prod_id"),rs.getString("prod_descript"),Integer.parseInt(rs.getString("prod_price")), rs.getString("prod_category")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	
	
	
	
	
	
	
	// рабочая версия
	public List<Product> getProducts(String category){
		String query="SELECT * FROM products WHERE prod_category='"+category+"'";
		Statement st=null;
		File image = null;
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Product> products=new ArrayList<>();
		try {
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next()){
			products.add(new Product(rs.getInt("prod_id"),rs.getString("prod_descript"),Integer.parseInt(rs.getString("prod_price")), rs.getString("prod_category")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	
	
	public Product getProduct(int id){
		String query="SELECT * FROM products WHERE prod_id='"+id+"'";
		Statement st=null;
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Product product=null;
		try {
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next()){
				product=new Product(rs.getInt("prod_id"),rs.getString("prod_descript"),Integer.parseInt(rs.getString("prod_price")), rs.getString("prod_category"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}
	
	
	
	/*
	
	public Product getProduct(int id) throws IOException{
		String query="SELECT * FROM products WHERE prod_id ='"+id+"'";
		Statement st=null;
		File image = null;
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	Product product=null;
		try {
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next()){
				image = new File("WebKurs2/views/FOTO/"+rs.getInt("prod_id")+".jpg");
				FileOutputStream fos = new FileOutputStream(image);

				byte[] buffer = new byte[1];
				InputStream is = rs.getBinaryStream(2);
				while (is.read(buffer) > 0) {
					fos.write(buffer);
				}
				
				product=new Product(rs.getInt("prod_id"),rs.getString("prod_descript"), Integer.parseInt(rs.getString("price")), rs.getString("prod_category"), image);
				fos.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}
	
	*/
	

	
	/*
	public void sqlReg(NewUser newUser) throws SQLException{
		String query = "INSERT INTO users (login, password, name, gender, area, comments, amigo) values (?,?,?,?,?,?,?)";
		PreparedStatement regBS = conn.prepareStatement(query);
		
		regBS.setString(1, newUser.getLogin());
		regBS.setString(2, newUser.getPassword());
		regBS.setString(3, newUser.getName());
		regBS.setString(4, newUser.getGender());
		regBS.setString(5, newUser.getArea());
		regBS.setString(6, newUser.getComment());
		regBS.setString(7, newUser.getAmigo());
		regBS.execute();	
	}
	*/
}
