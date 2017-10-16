package com.book.app.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.app.servlet.util.TemplateHtml;

/**
 * Servlet implementation class ServletUpSetCollection
 */
@WebServlet("/ServletUpSetCollection")
public class ServletUpSetCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUpSetCollection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String option = request.getParameter("option"); 
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String pathURL = request.getContextPath();
        		
		
		if (option.equals("insert")) {	
			        
	        out.println(TemplateHtml.getHead("Insert", pathURL)); 
	        out.println(TemplateHtml.getMenu(pathURL));        
	        out.println(getForm("0"));        
	        out.println(TemplateHtml.getFooter(pathURL));
		} else if (option.equals("edit")) {
			
			String id = request.getParameter("id"); 			
			out.println(TemplateHtml.getHead("Edit", pathURL)); 
	        out.println(TemplateHtml.getMenu(pathURL));        
	        out.println(getForm(id));        
	        out.println(TemplateHtml.getFooter(pathURL));
		}
		
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
				
		if(id.equals("0")){
			
		}
		
		
		
	}
	
	
	
	private String getForm(String id) {
		StringBuilder builder = new StringBuilder();		
		builder.append("<div class=\"container\">\r\n" + 
				"		<div class=\"row\">\r\n" + 
				"			<div class=\"col-xs-12 text-center\">\r\n" + 
				"				<h1>Form</h1>\r\n" + 
				"			</div>\r\n" + 
				"			<form action=\"./servlet/signup\" method=\"post\" class=\"form-horizontal\">\r\n" + 
				"				<input type=\"hidden\" name=\"id\" value=\""+id+"\">\r\n" + 
				"				<div class=\"form-group\">\r\n" + 
				"					<label for=\"name\" class=\"col-xs-4 control-label\">Name:\r\n" + 
				"					</label>\r\n" + 
				"					<div class=\"col-xs-6\">\r\n" + 
				"						<input type=\"text\" id=\"name\" class=\"form-control\" name=\"name\" value=\"\" autofocus>\r\n" + 
				"					</div>\r\n" + 
				"				</div>\r\n" + 
				"				<div class=\"form-group\">\r\n" + 
				"					<label for=\"description\" class=\"col-xs-4 control-label\">Description:\r\n" + 
				"					</label>\r\n" + 
				"					<div class=\"col-xs-6\">\r\n" + 
				"						<input type=\"text\" id=\"description\" class=\"form-control\" name=\"description\" value=\"\">\r\n" + 
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
