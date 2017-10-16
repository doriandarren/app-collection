package com.book.app.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.EntityNotFoundException;
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
@WebServlet("/ServletSignIn")
public class ServletSignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/* referencia por inyección */
	@EJB
	private InfAppServices service; 
	
	
    public ServletSignIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
						throws ServletException, IOException {
		
		String emailUser = request.getParameter("email"); 
		   
		checkParameters(); 
		
		User user; 
		
		try{			
			user = service.signInUser(emailUser); 			
	    }catch(EJBException e){	    	
	    	e.getCausedByException(); 
	    	if(e.getClass().isAssignableFrom(EntityNotFoundException.class)){
	    		//El usuario no existe, responder adecuadamente y/o renviar 
	    		//al signUp
	    		//TODO 
	    	}else {
	    		//Error, compruebe los datos del formulario o intentelo mas tarde
	    	}	    	
	    	return; 
	    }
						   
		HttpHelper.saveSessionUser(request, user);
		response.sendRedirect("./home");
		
	}

	private void checkParameters() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
