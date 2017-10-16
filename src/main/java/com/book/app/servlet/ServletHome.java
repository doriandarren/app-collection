package com.book.app.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.app.servlet.util.TemplateHtml;

import entities.User;

/**
 * Servlet implementation class ServletHome
 */
@WebServlet("/ServletHome")
public class ServletHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
						throws ServletException, IOException {
		
		
		User user = HttpHelper.getSessionUser(request); 
		if(user==null){
			response.sendRedirect("signin.html"); 
			return; 
		}
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("<div class=\"container\">\r\n" + 
				"		<div class=\"row\">\r\n" + 
				"			<div class=\"col-xs-12 text-center\">\r\n" + 
				"				<h1>Welcome Session</h1>\r\n" + 
				"				<p>Hola! " + request.getSession().getAttribute("user_name") + "</p>\r\n" +
				"			</div>" +
				"     </div>\r\n" + 
				"</div>");		
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String pathURL = request.getContextPath();
        
        out.println(TemplateHtml.getHead("Image", pathURL)); 
        out.println(TemplateHtml.getMenu(pathURL));        
        out.println(builder);        
        out.println(TemplateHtml.getFooter(pathURL));
						
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
							throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
