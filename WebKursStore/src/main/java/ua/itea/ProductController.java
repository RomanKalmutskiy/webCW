package ua.itea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import models.Product;

@Controller
@Scope("session")
public class ProductController {
	
	private List<String> areas = new ArrayList<String>();
	DBWorker worker = null;
	
	@RequestMapping(value="/shop",method=RequestMethod.GET)
	public String shopView(){
		return "index";
		}
	
	@RequestMapping(value="/prod",method ={RequestMethod.POST, RequestMethod.GET})
	public String getProd(HttpServletRequest req) throws FileNotFoundException{
HttpSession session = req.getSession(true);
		
		MySQLConfig conf = null;
		DBWorker worker=null;
		conf = MySQLConfig.getInstance();
		SQLDBCDriverConnection conn = new SQLDBCDriverConnection(conf);
		Connection c = conn.setConnect();
		if (c!=null){
			worker = new DBWorker(c);}
		else {
			System.out.println("Connection is not established");
		}

		Map<Product, Integer> cart=null;
		
		if(session.getAttribute("cart")==null){
			cart = new HashMap <Product, Integer>();
			session.setAttribute("cart", cart);

		}else{
			cart = (Map) session.getAttribute("cart");
			
		}
		if (req.getParameter("buy") != null) {
			Product product=null;
			product = worker.getProduct(Integer.parseInt(req.getParameter("id")));
			if(cart.containsKey(product)){
				Integer i = cart.get(product);
				i++;
				cart.put(product, i);
				
			}else{
			cart.put(product, 1);
			}
		}
		
		if (req.getParameter("DEL") != null) {
			Product product=null;
			product = worker.getProduct(Integer.parseInt(req.getParameter("id")));
			if(cart.containsKey(product)){
				Integer i = cart.get(product);
				i--;
				if(i>0){
					cart.remove(product);
					cart.put(product, i);
					}else
						cart.remove(product);
				
			}
			
		}
		
		List<Product> products = null;
		if (req.getParameter("category") != null) {
			products = worker.getProducts(req.getParameter("category"));
		} else {
			products = worker.getProducts();
		}
		req.setAttribute("products", products);
	
		
		if (session.getAttribute("user")!=null){
			return "products";
			
		} else {
		
		return "loginForm";}
		}
	
	@RequestMapping(value = "/prod", method ={RequestMethod.POST, RequestMethod.GET}, params = { "buy", "id", "category", "DEL" })
	public String getProd2(@RequestParam("buy") String buyVar, 
			@RequestParam("id") String idVar, @RequestParam("category") String categoryVar, @RequestParam("DEL") String delVar,  HttpServletRequest req) throws NumberFormatException, IOException {
		HttpSession session = req.getSession(true);
		
		MySQLConfig conf = null;
		DBWorker worker=null;
		conf = MySQLConfig.getInstance();
		SQLDBCDriverConnection conn = new SQLDBCDriverConnection(conf);
		Connection c = conn.setConnect();
		if (c!=null){
			worker = new DBWorker(c);}
		else {
			System.out.println("Connection is not established");
		}

		Map<Product, Integer> cart=null;
		
		if(session.getAttribute("cart")==null){
			cart = new HashMap <Product, Integer>();
			session.setAttribute("cart", cart);

		}else{
			cart = (Map) session.getAttribute("cart");
			
		}
		if (req.getParameter("buy") != null) {
			Product product=null;
			product = worker.getProduct(Integer.parseInt(req.getParameter("id")));
			if(cart.containsKey(product)){
				Integer i = cart.get(product);
				i++;
				cart.put(product, i);
				
			}else{
			cart.put(product, 1);
			}
		}
		
		if (req.getParameter("DEL") != null) {
			Product product=null;
			product = worker.getProduct(Integer.parseInt(req.getParameter("id")));
			if(cart.containsKey(product)){
				Integer i = cart.get(product);
				i--;
				if(i>0){
				cart.remove(product);
				cart.put(product, i);
				}else
					cart.remove(product);
				
			}
			
		}
		
		
		List<Product> products = null;
		if (req.getParameter("category") != null) {
			products = worker.getProducts(req.getParameter("category"));
		} else {
			products = worker.getProducts();
		}
		req.setAttribute("products", products);
	
		return "products";
	}
	
	


@RequestMapping(value = "/shop", method ={RequestMethod.POST, RequestMethod.GET}, params = { "buy", "id", "category", "DEL" })
public String getProdShop(@RequestParam("buy") String buyVar, 
		@RequestParam("id") String idVar, @RequestParam("category") String categoryVar, @RequestParam("DEL") String delVar,  HttpServletRequest req) throws NumberFormatException, IOException {
	HttpSession session = req.getSession(true);
	
	MySQLConfig conf = null;
	DBWorker worker=null;
	conf = MySQLConfig.getInstance();
	SQLDBCDriverConnection conn = new SQLDBCDriverConnection(conf);
	Connection c = conn.setConnect();
	if (c!=null){
		worker = new DBWorker(c);}
	else {
		System.out.println("Connection is not established");
	}

	Map<Product, Integer> cart=null;
	
	if(session.getAttribute("cart")==null){
		cart = new HashMap <Product, Integer>();
		session.setAttribute("cart", cart);

	}else{
		cart = (Map) session.getAttribute("cart");
		
	}
	if (req.getParameter("buy") != null) {
		Product product=null;
		product = worker.getProduct(Integer.parseInt(req.getParameter("id")));
		if(cart.containsKey(product)){
			Integer i = cart.get(product);
			i++;
			cart.put(product, i);
			
		}else{
		cart.put(product, 1);
		}
	}
	
	if (req.getParameter("DEL") != null) {
		Product product=null;
		product = worker.getProduct(Integer.parseInt(req.getParameter("id")));
		if(cart.containsKey(product)){
			Integer i = cart.get(product);
			i--;
			if(i>0){
			cart.remove(product);
			cart.put(product, i);
			}else
				cart.remove(product);
			
		}
		
	}
	
	
	List<Product> products = null;
	if (req.getParameter("category") != null) {
		products = worker.getProducts(req.getParameter("category"));
	} else {
		products = worker.getProducts();
	}
	req.setAttribute("products", products);

	return "index";
}


}
	


