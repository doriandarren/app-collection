package com.book.app.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.app.business.InfAppServices;

import entities.Collection;
import entities.User;

/**
 * Servlet implementation class ServletRemoveCollection
 */
@WebServlet("/ServletRemoveCollection")
public class ServletRemoveCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	@Inject
	private InfAppServices service;
	
    public ServletRemoveCollection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = HttpHelper.getSessionUser(request); 
		if(user==null){
			response.sendRedirect("signin.html"); 
			return; 
		}
		
		String id = request.getParameter("id"); 						
		service.removeCollection(id);
		response.sendRedirect("./home"); 	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
