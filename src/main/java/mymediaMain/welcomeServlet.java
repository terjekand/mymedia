package mymediaMain;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modell.POST_DataBase;
import Modell.USER_DataBase;
import Modell.User;


@WebServlet(urlPatterns = "/login")
public class welcomeServlet extends HttpServlet {
	private static final USER_DataBase USER_DB = USER_DataBase.getDataBase();
	@Override
	public void init() {
		   
		   try {
	            USER_DB.connectDB();
	            System.out.println("Connect to database");
		       

	        } catch (Exception e) {
	        	System.err.println(e);
	        }
	}
		@Override
		public void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException{
			
		request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
		
	   }
	
	   @Override
	   protected void doPost(HttpServletRequest request, HttpServletResponse response)
			      throws ServletException, IOException {
		    
		   System.out.println(request.getParameter("username"));
		   System.out.println(request.getParameter("password"));
		   User user = USER_DB.getUser(request.getParameter("username"));
		   if(user == null) {
			   request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
		   }
		   if(user != null && user.getPassword().equals(request.getParameter("password"))) {
			   System.out.println("USER INFO");
			   System.out.println(user.getUsername());
			   System.out.println(user.getPassword());
			   request.setAttribute("username", request.getParameter("username"));
			   request.setAttribute("password", request.getParameter("password"));
			   request.getRequestDispatcher("WEB-INF/views/welcome.jsp").forward(request, response);
		   }
		    
		   
	   }
	   
	   @Override
	   public void destroy() {
           USER_DB.disconnectDB();
	   }

}
