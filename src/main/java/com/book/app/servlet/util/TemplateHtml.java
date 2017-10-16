package com.book.app.servlet.util;

public class TemplateHtml {
	
	/*private static String pathURL="";
	
	public static String getPathURL() {
		return pathURL;
	}

	public static void setPathURL(String pathURL) {
		TemplateHtml.pathURL = pathURL;
	}*/

	
	public static String getHead(String title, String pathURL) {
		
		String str =
		         "<!doctype html public \"-//w3c//dtd html 4.0 " +
		         "transitional//en\">\n"
		        +"<html>\n" +
				        "<head><title>" + title + "</title></head>\n" +
				        "<!-- Bootstrap -->\r\n" + 
				        "<link href=\""+ pathURL +"/css/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" >" +	            	      
				        "<body>\n";
		return str;
	}
	
	public static String getMenu(String pathURL) {		
		String strMenu= "<nav class=\"navbar navbar-default\">\r\n" + 
				"	<div class=\"container-fluid\">\r\n" + 
				"		<!-- Brand and toggle get grouped for better mobile display -->\r\n" + 
				"		<div class=\"navbar-header\">\r\n" + 
				"			<button type=\"button\" class=\"navbar-toggle collapsed\"\r\n" + 
				"				data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\"\r\n" + 
				"				aria-expanded=\"false\">\r\n" + 
				"				<span class=\"sr-only\">Toggle navigation</span> <span\r\n" + 
				"					class=\"icon-bar\"></span> <span class=\"icon-bar\"></span> <span\r\n" + 
				"					class=\"icon-bar\"></span>\r\n" + 
				"			</button>\r\n" + 
				"			<a class=\"navbar-brand\" href=\""+ pathURL +"/servlet/home\">Home</a>\r\n" + 
				"		</div>\r\n" + 
				"\r\n" + 
				"		<!-- Collect the nav links, forms, and other content for toggling -->\r\n" + 
				"		<div class=\"collapse navbar-collapse\"\r\n" + 
				"			id=\"bs-example-navbar-collapse-1\">\r\n" + 
				"			<ul class=\"nav navbar-nav\">\r\n" + 
				"				<li class=\"dropdown\">\r\n" + 
				"		        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Colections <span class=\"caret\"></span></a>\r\n" + 
				"		        <ul class=\"dropdown-menu\">\r\n" + 
				"		            <li><a href=\""+ pathURL +"/servlet/addcollection?option=insert&id=0\">Add</a></li>\r\n" + 
				"		          </ul>\r\n" + 
				"		        </li>\r\n" + 
				"				\r\n" + 
				"				<li class=\"dropdown\">\r\n" + 
				"		        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Items <span class=\"caret\"></span></a>\r\n" + 
				"		        <ul class=\"dropdown-menu\">\r\n" + 
				"		            <li><a href=\""+  pathURL  +"/servlet/additem\">Add</a></li>\r\n" + 
				"		            <li><a href=\""+  pathURL  +"/servlet/updateitem\">Update</a></li>\r\n" +
				"                   <li><a href=\""+  pathURL  +"/servlet/removeitem\">Remove</a></li>\r\n" +
				"		          </ul>\r\n" + 
				"		        </li>\r\n" + 
				"		        \r\n" +
				"				<li><a href=\""+  pathURL  +"/insertimage.html\">Image</a></li>\r\n" +				
				"			</ul>\r\n" + 
				"			<ul class=\"nav navbar-nav navbar-right\">\r\n" + 
				"      			<li><a href=\""+  pathURL  +"/servlet/logout\"><span class=\"glyphicon glyphicon-user\"></span> Sign Up</a></li>\r\n" + 
				"    		</ul>"+				
				"		</div>\r\n" + 
				"		<!-- /.navbar-collapse -->\r\n" + 
				"	</div>\r\n" + 
				"	<!-- /.container-fluid --> </nav>";						
		return strMenu;		
	}	
	
	public static String getFooter(String pathURL) {
		String str = "<script type=\"text/javascript\" src=\""+ pathURL +"/js/jquery/jquery-3.2.1.min.js\"></script>\r\n" + 
	               "	<script src=\""+ pathURL +"/css/bootstrap/js/bootstrap.min.js\"></script>"   +               
	            "</body>"+
	         "</html>";
		return str;
	}
	
	public static String getFooter(String[] strJS, String pathURL) {
		StringBuilder str = new StringBuilder();
		str.append("<script type=\"text/javascript\" src=\""+ pathURL +"/js/jquery/jquery-3.2.1.min.js\"></script>\r\n" + 
	               "	<script src=\""+ pathURL +"/css/bootstrap/js/bootstrap.min.js\"></script>");
		
		for(int i=0; i<strJS.length;i++) {
			str.append("<script type=\"text/javascript\" src=\""+ pathURL +"/js/"+ strJS[i] +"\"></script>\r\n");
		}
		
	    str.append("</body>\r\n</html>");
		return str.toString();
	}
	
}
