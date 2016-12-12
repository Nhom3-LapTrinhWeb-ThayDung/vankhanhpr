package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MessageDAO;
import model.Message;
import model.User_info;



/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	MessageDAO messageDAO = new MessageDAO();
	
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String command = request.getParameter("command");
		String url = "";
		Message message = new Message();
		
		HttpSession session = request.getSession();
		User_info usersend= (User_info)session.getAttribute("user_info");
		switch(command){
		case "insert":
			long x = new java.util.Date().getTime();
			message.setId_message(x);
			message.setNoidung(request.getParameter("noidung_message"));
			
			message.setId_nguoigui(usersend.getId());
			message.setId_nguoinhan(Integer.parseInt(request.getParameter("id_nguoinhan")));
		
			
			message.setThoigian(new Timestamp(new Date().getTime()));
			
			message.setDaxem(false);
			
			boolean f = messageDAO.insert(message);
			if(f)
			{

				response.getWriter().write("send success!");
				
			}
			else
			{
				//session.removeAttribute("user");
				response.getWriter().write("send unsuccessfull!");
			}
			/*RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);*/
			break;

		
		}
		
	}

}
