package mymediaMain;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modell.PostDataBase;
import modell.UserDataBase;
import modell.User;


@WebServlet(urlPatterns = "/login")
public class WelcomeServlet extends HttpServlet {
	private static final UserDataBase UserDB = UserDataBase.getDataBase();
	@Override
	public void init() {
		   
		   try {
	            UserDB.connectDB();
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
		   User user = UserDB.getUser(request.getParameter("username"));
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
           UserDB.disconnectDB();
	   }

}
