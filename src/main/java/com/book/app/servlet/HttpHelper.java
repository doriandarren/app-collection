package com.book.app.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import entities.User;

public class HttpHelper {

	
	private static final String USER_ID = "user_id";
	private static final String USER_EMAIL = "user_email";
	private static final String USER_NAME = "user_name";	
	
	public static String getStyleTable(){
		return "<style>" 
		+"table {" 
		+"    font-family: arial, sans-serif;"
		+"    border-collapse: collapse;"
		+"    width: 100%;"
		+"}"

		+"td, th {"
		+"    border: 1px solid #dddddd;"
		+ "   text-align: left;"
		+ "   padding: 8px;"
		+"}"

		+"tr:nth-child(even) {"
		+"    background-color: #dddddd;"
		+"}"
		+"</style>"; 
	}
	
	public static void deleteSessionUser(HttpServletRequest request){	
		HttpSession sesion = request.getSession();
		sesion.setAttribute(USER_ID, null);
		sesion.setAttribute(USER_EMAIL, null);
		sesion.setAttribute(USER_NAME, null);
		
	}
	
	
	public static void saveSessionUser(HttpServletRequest request, User user){		
		HttpSession sesion = request.getSession(true);
		sesion.setAttribute(USER_ID, user.getId());
		sesion.setAttribute(USER_EMAIL, user.getEmail());
		sesion.setAttribute(USER_NAME, user.getName());
	}
	
	public static User getSessionUser(HttpServletRequest request){		
		HttpSession sesion = request.getSession(true);		
		String id = (String) sesion.getAttribute(USER_ID);
		String email = (String) sesion.getAttribute(USER_EMAIL);
		String name = (String) sesion.getAttribute(USER_NAME);
		User userLogin = null;
		if(name!=null && id!=null && email!=null){
			userLogin = new User();		
			userLogin.setId(id);
			userLogin.setEmail(email);
			userLogin.setName(name);			
		}				
		return userLogin;
	}
	
	//TODO blanquear la sesion
	
}
