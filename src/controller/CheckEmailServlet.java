package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDAO;

/**
 * Servlet implementation class CheckEmailServlet
 */
@WebServlet("/CheckEmailServlet")
public class CheckEmailServlet extends HttpServlet {
	UsersDAO usersDAO = new UsersDAO();
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CheckEmailServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//String checkemail="";
		 if (usersDAO.checkEmail(request.getParameter("email-register"))) {
			 //checkemail="Email đã được sử dụng! ";
	          //response.getWriter().write("<img src=\"Images/not-available.png\"/>");
			 response.getWriter().write("\nEmail đã được sử dụng! ");
	     } else {
	          //response.getWriter().write("<img src=\"Images/available.png\"/>");
	    	 response.getWriter().write("");
	     }
	}

}
