package com.book.app.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class ServletUpSetCollection
 */
@WebServlet("/ServletUpSetCollection")
public class ServletUpSetCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/* referencia por inyección */
	@EJB
	private InfAppServices service; 
	
    public ServletUpSetCollection() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = HttpHelper.getSessionUser(request); 
		if(user==null){
			response.sendRedirect("signin.html"); 
			return; 
		}
		
		String option = request.getParameter("option"); 
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String pathURL = request.getContextPath();
        		
		
		if (option.equals("insert")) {			        
	        out.println(TemplateHtml.getHead("Insert", pathURL)); 
	        out.println(TemplateHtml.getMenu(pathURL));        
	        out.println(getForm("0","",""));        
	        out.println(TemplateHtml.getFooter(pathURL));
		} else if (option.equals("edit")) {
			
			String id = request.getParameter("id"); 						
			Collection colec = service.find(Collection.class, id);
			
			out.println(TemplateHtml.getHead("Edit", pathURL)); 
	        out.println(TemplateHtml.getMenu(pathURL));        
	        out.println(getForm(id,colec.getName(), colec.getDescription()));        
	        out.println(TemplateHtml.getFooter(pathURL));
		}	
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		User user = HttpHelper.getSessionUser(request); 
		if(user==null){
			response.sendRedirect("signin.html"); 
			return; 
		}
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
				
		if(id.equals("0")){	
			Collection colec = new Collection();
			colec.setName(name);
			colec.setDescription(description);			
			service.addCollection(user.getId(), colec);
			response.sendRedirect("./home"); 
		}else if(Integer.valueOf(id)>0){
			Collection colec = new Collection();
			colec.setId(id);
			colec.setName(name);
			colec.setDescription(description);
			colec.setUser(user);			
			service.updateCollection(colec);
			response.sendRedirect("./home");
		}
		
	}
	
	
	
	private String getForm(String id, String name, String description) {
		StringBuilder builder = new StringBuilder();		
		builder.append("<div class=\"container\">\r\n" + 
				"		<div class=\"row\">\r\n" + 
				"			<div class=\"col-xs-12 text-center\">\r\n" + 
				"				<h1>Form</h1>\r\n" + 
				"			</div>\r\n" + 
				"			<form action=\"./addcollection\" method=\"post\" class=\"form-horizontal\">\r\n" + 
				"				<input type=\"hidden\" name=\"id\" value=\""+id+"\">\r\n" + 
				"				<div class=\"form-group\">\r\n" + 
				"					<label for=\"name\" class=\"col-xs-4 control-label\">Name:\r\n" + 
				"					</label>\r\n" + 
				"					<div class=\"col-xs-6\">\r\n" + 
				"						<input type=\"text\" id=\"name\" class=\"form-control\" name=\"name\" value=\""+name+"\" autofocus>\r\n" + 
				"					</div>\r\n" + 
				"				</div>\r\n" + 
				"				<div class=\"form-group\">\r\n" + 
				"					<label for=\"description\" class=\"col-xs-4 control-label\">Description:\r\n" + 
				"					</label>\r\n" + 
				"					<div class=\"col-xs-6\">\r\n" + 
				"						<input type=\"text\" id=\"description\" class=\"form-control\" name=\"description\" value=\""+description+"\">\r\n" + 
				"					</div>\r\n" + 
				"				</div>\r\n" + 
				"				<div class=\"form-group\">\r\n" + 
				"					<div class=\"col-xs-6 col-xs-offset-4\">\r\n" + 
				"						<input type=\"submit\" class=\"btn btn-primary\" role=\"button\" value=\"Submit\" />\r\n" + 
				"					</div>\r\n" + 
				"				</div>\r\n" + 
				"			</form>\r\n" + 
				"		</div>\r\n" + 
				"	</div>");		
		return builder.toString();
	}
}
