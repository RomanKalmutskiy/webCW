package ua.itea;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.conn.DBWorker;
import db.conn.MySQLConfig;
import db.conn.SQLDBCDriverConnection;
import models.Product;



public class ProductController2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {
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
		HttpSession session=req.getSession();
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
		List<Product> products = null;
		if (req.getParameter("category") != null) {
			products = worker.getProducts(req.getParameter("category"));
		} else {
			products = worker.getProducts();
		}
		req.setAttribute("products", products);
		RequestDispatcher rd = req.getRequestDispatcher("/views/products2.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {
	doGet(req,resp);
	}
}
