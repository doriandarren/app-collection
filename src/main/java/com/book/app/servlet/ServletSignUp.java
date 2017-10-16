package com.book.app.servlet;

import java.io.IOException;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.app.business.InfAppServices;

import entities.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/SignUp")
public class ServletSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/* referencia por inyección */
	//@EJB
	//private AppServices service; 
	
	
	@Inject
	private InfAppServices service;// */
	
    
    public ServletSignUp() {
        super();
        // TODO Auto-generated constructor stub
        
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
							throws ServletException, IOException {	
		
		String email = request.getParameter("email");
		String name = request.getParameter("name");	
		
		//checkParameter();
		
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		
		try {			
			service.signUpUser(user);
		} catch (EJBException e) {
			e.getCausedByException();
			if(e.getClass().isAssignableFrom(EntityExistsException.class)){
				//El usuario existe
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}else{
				//Error, comprueba los datos del formulario o inetente mas tarde
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
		}
		
		HttpHelper.saveSessionUser(request, user);
		response.sendRedirect("./home");
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
	
	
	private void checkParameter() {
		// TODO Auto-generated method stub
		
	}
	
}
