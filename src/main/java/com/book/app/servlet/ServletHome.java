package com.book.app.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.app.business.InfAppServices;
import com.book.app.servlet.util.TemplateHtml;

import entities.Collection;
import entities.User;

/**
 * Servlet implementation class ServletHome
 */
@WebServlet("/ServletHome")
public class ServletHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/* referencia por inyección */
	@EJB
	private InfAppServices service; 
	
    public ServletHome() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
						throws ServletException, IOException {
		
		
		User user = HttpHelper.getSessionUser(request); 
		if(user==null){
			response.sendRedirect("signin.html"); 
			return; 
		}
		
		
		User userFind = service.find(User.class, user.getId());
		Set<Collection> listCol = userFind.getCollections();
		
		
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("<div class=\"container\">\r\n" + 
				"		<div class=\"row\">\r\n" + 
				"			<div class=\"col-xs-12 text-center\">\r\n" + 
				"				<h1>Welcome Session</h1>\r\n" + 
				"				<p>Hola! " + request.getSession().getAttribute("user_name") + "</p>\r\n" +
				"			</div>" +
				"     </div>\r\n" + 
				"</div>");		
		
		
		
		builder.append("<div class=\"container\">\r\n" + 
				"		<div class=\"row\">\r\n" + 
				"			<div class=\"col-xs-12 text-center\">\r\n" + 
				"				<h1>Collections</h1>\r\n"+ 
				"		    </div>");
		
		
		builder.append("<table class=\"table\">" +
	    "<tr>" +
		"    <th>#</th>" + 
		"    <th>Name</th>" + 
		"    <th>Action</th>" + 
	    "</tr>");
		
		for(int i=0; i<listCol.size(); i++ ){			
			builder.append("<tr>" + 
				    "	<td>"+ (i+1) +"</td>" + 
					"    <td>"+ listCol.iterator().next().getName() +"</td>" + 
					"    <td><a href=\"./addcollection?option=edit&id="+ listCol.iterator().next().getId() +"\">Edit</a></td>" +
					"    <td><a href=\"./removecollection?id="+ listCol.iterator().next().getId() +"\">Remove</a></td>" +
				    "</tr>");
		}
		builder.append("</table>");	
		
		builder.append("     </div>\r\n" + 
				"</div>");
			
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String pathURL = request.getContextPath();
        
        out.println(TemplateHtml.getHead("Home", pathURL)); 
        out.println(TemplateHtml.getMenu(pathURL));        
        out.println(builder);        
        out.println(TemplateHtml.getFooter(pathURL));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
							throws ServletException, IOException {
		doGet(request, response);
	}

}
