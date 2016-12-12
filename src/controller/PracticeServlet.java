package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionRadioDAO;
import model.Quiz;


/**
 * Servlet implementation class PracticeServlet
 */
@WebServlet("/PracticeServlet")
public class PracticeServlet extends HttpServlet {
	QuestionRadioDAO quizDAO = new QuestionRadioDAO();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PracticeServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String command = request.getParameter("command");
		String url = "";
		String errorStr="";
		Quiz quiz = new Quiz();
		HttpSession session = request.getSession();
		switch(command){
		case "thitracnghiem":
			quiz = quizDAO.getQuiz(request.getParameter("quiz_name"));
			if(quiz!=null){
				session.setAttribute("quiz", quiz);
				url = "LamBaiThi.jsp";
			}
			else{
				errorStr="lỗi "+ request.getParameter("quiz_name");
				url = "khoahoc2.jsp";
			}
			break;

		case "nopbaitap":
			/*users = usersDAO.login(request.getParameter("username"),request.getParameter("password") );
			if(users!=null){
				session.setAttribute("user", users);
				url = "index.jsp";
			}
			else{
				request.setAttribute("error","Error user name or password");
				url = "index.jsp";
			}*/
			break;
		}
		request.setAttribute("errorStr", errorStr);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
