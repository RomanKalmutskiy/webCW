package ua.itea;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import db.conn.DBWorker;
import db.conn.MySQLConfig;
import db.conn.SQLDBCDriverConnection;

@Controller
@Scope("session")
public class AccessController {
	
	private List<String> areas = new ArrayList<String>();
	
	@RequestMapping(value="/lg",method=RequestMethod.GET)
	public String login(){
		return "loginForm";
		}
	
	@RequestMapping(value="/successView",method=RequestMethod.GET)
	public String success(){
		return "success";
	}
	
	@RequestMapping(value="/regView",method=RequestMethod.GET)
	public String registration(){
		return "registrationView";
	}
	
	@RequestMapping(value="/regSuccess",method=RequestMethod.GET)
	public String regSucces(){
		return "registrationSuccess";
	}
	
	
	@RequestMapping(value = "/lg", method ={RequestMethod.POST, RequestMethod.GET}, params = { "logout" })
	public String logout(@RequestParam("logout") String logoutVar, 
			HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		if (logoutVar != null) {
			session.invalidate();
		}
		return "loginForm";
	}
	
	@RequestMapping(value = "/prod", method ={RequestMethod.POST, RequestMethod.GET}, params = { "logout" })
	public String shopLogout(@RequestParam("logout") String logoutVar, 
			HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		if (logoutVar != null) {
			session.invalidate();
		}
		return "loginForm";
	}
	
	
	
	@RequestMapping(value = "/lg", method ={RequestMethod.POST, RequestMethod.GET}, params = { "login", "psw" })
	public String login(@RequestParam("login") String loginVar, 
			@RequestParam("psw") String pswVar, HttpServletRequest req) throws FileNotFoundException {
		HttpSession session = req.getSession(true);
		if (session.getAttribute("user")!=null){
			return "success";
			
		} else {
			if (loginVar!=null){
				boolean isLoggedIn = false;
				MySQLConfig conf = null;
				conf = MySQLConfig.getInstance();
				SQLDBCDriverConnection conn = new SQLDBCDriverConnection(conf);
				Connection c = conn.setConnect();
				if (c!=null){
					DBWorker worker = new DBWorker(c);
					isLoggedIn = worker.login(loginVar, pswVar);
					if (isLoggedIn){
						session.setAttribute("user", worker.getUser());
						return "success";
					} else {
						String accessError = "<ul>";
						accessError += "<li>Access denied</li>";
						session.setAttribute("accessError", accessError);
						return "loginForm";
					}
				}
			} else {
				System.out.println("Connection is not established");
			}
		
		
		return "success";
	}
	}
	
	@RequestMapping(value = "/prod", method ={RequestMethod.POST, RequestMethod.GET}, params = { "login", "psw" })
	public String loginProd(@RequestParam("login") String loginVar, 
			@RequestParam("psw") String pswVar, HttpServletRequest req) throws FileNotFoundException {
		HttpSession session = req.getSession(true);
		if (session.getAttribute("user")!=null){
			return "success";
			
		} else {
			if (loginVar!=null){
				boolean isLoggedIn = false;
				MySQLConfig conf = null;
				conf = MySQLConfig.getInstance();
				SQLDBCDriverConnection conn = new SQLDBCDriverConnection(conf);
				Connection c = conn.setConnect();
				if (c!=null){
					DBWorker worker = new DBWorker(c);
					isLoggedIn = worker.login(loginVar, pswVar);
					if (isLoggedIn){
						session.setAttribute("user", worker.getUser());
						return "success";
					} else {
						String accessError = "<ul>";
						accessError += "<li>Access denied</li>";
						session.setAttribute("accessError", accessError);
						return "loginForm";
					}
				}
			} else {
				System.out.println("Connection is not established");
			}
		
		
		return "success";
	}
	}
	
	
	
	@RequestMapping(value = "/regView", method ={RequestMethod.POST, RequestMethod.GET}, 
			params = { "login", "psw", "repsw", "name", "gender", "area", "comment", "agree" })
	public String registrationVerification (
			@RequestParam("login") String login, 
			@RequestParam("psw") String psw, 
			@RequestParam("repsw") String repsw, 
			@RequestParam("name") String name,
			@RequestParam("gender") String gender,
			@RequestParam("area") String areaRaw,
			@RequestParam("comment") String comment,
			@RequestParam("agree") String agree,
			HttpServletRequest req) throws FileNotFoundException{
		HttpSession session = req.getSession(true);
		areas.add("Donetsk");
		areas.add("Luhansk");
		areas.add("KrimNash");
		int areaIndex = Integer.parseInt(areaRaw);
		String area = areas.get(areaIndex);
		//int intAgree = 0;
		
		//if (agree != null) {
		//	intAgree = 1;
		//}
		
		if (login != null) {

			boolean error = false;
			String errorText = "<ul>";
			if (login.length() == 0) {
				error = true;
				errorText += "<li>Empty login</li>";
			}
			if (psw.length() == 0) {
				error = true;
				errorText += "<li>Empty password</li>";
			}
			if (gender == null) {
				error = true;
				errorText += "<li>Empty gender</li>";
			}
			errorText += "</ul>";
			if (error) {
				session.setAttribute("error", errorText);
				System.out.println(errorText);
				return "registrationView";
			} else {
				System.out.println("no error");
			}
			//Debug message attributes to console
		//	System.out.println(String.format(
			//		"Login: %s, Password: %s, Re-Password: %s, Name: %s, Gender: %s, Area: %s, Comment: %s, Agree: %d",
			//		login, psw, repsw, name, gender, area, comment, agree));
			
			if ((login != null) && (psw.equals(repsw)) && (name != null) && (agree != null)) {
				MySQLConfig conf = MySQLConfig.getInstance();
				SQLDBCDriverConnection conn = new SQLDBCDriverConnection(conf);
				Connection c = conn.setConnect();

				if (c != null) {
					DBWorker worker = new DBWorker(c);
					int isUnique = worker.verifyUnique(login, psw);
					if (isUnique == 0) {
						boolean isUserCreated = worker.createUser(login, psw, name, gender, area, comment, agree);
						if (isUserCreated){
							//Debug message to console
							System.out.println("User has been registered");
							return "registrationSuccess";
						}
						
					} else {
						String errorNonUniqueUser = "User is already registered";
						session.setAttribute("error", errorNonUniqueUser);
						System.out.println(errorNonUniqueUser);
						return "registrationView";
					}

				}
			}else{
				System.out.println("Registration: Mandatory field is not provided");
			}
		}else{
			System.out.println("Registration: Login is not provided");
		}
		
		return "registrationView";
	}
	
	
	
}
	


