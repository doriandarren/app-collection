package com.book.app.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.EntityExistsException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.app.business.AppServices;

import entities.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class ServletSignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/* referencia por inyección */
	@EJB
	private AppServices service; 
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSignIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		User user = null;
		try {
			
			
			user = service.signInUser(email);
			
			
		} catch (EJBException e) {
			e.getCausedByException();
			if(e.getClass().isAssignableFrom(EntityExistsException.class)){
				//El usurio no existe, responde adecuadamente
				//TODO
			}else{
				//Error, comprueba los datos del formulario o inetente mas tarde
			}
		}
		
		HttpHelper.saveSesionUser(request, user);
		response.sendRedirect("/ServletHome");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
