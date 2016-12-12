package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CourseDAO;
import dao.ReviewDAO;
import model.AnswerUser;
import model.User_info;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewServlet() {
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
		String errorStr="";
		String url="";
		CourseDAO courseDAO = new CourseDAO();
		ReviewDAO reviewDAO = new ReviewDAO();
		long course_id=Long.parseLong(request.getParameter("course_id"));
		HttpSession session = request.getSession();
		User_info student = (User_info) session.getAttribute("user_info");
		User_info teacher = courseDAO.getteacher(course_id);
		String opinion = request.getParameter("opinion");
		long review_id= new Date().getTime();
		List<AnswerUser> listChoosenUsers = new ArrayList<AnswerUser>();
		int countRow = reviewDAO.getCountRow();
		for (int i = 1; i <= countRow; i++) {
            String answerUser = request.getParameter("ans[" + i + "]");
                AnswerUser au = new AnswerUser(i, answerUser);
                listChoosenUsers.add(au);
            }
		boolean f = reviewDAO.insertReview(review_id, student.getId(), course_id, teacher.getId(), opinion);
        if(f)
        {
    		for (AnswerUser as: listChoosenUsers) {
    			f = reviewDAO.insertReview_Answer(review_id, Integer.parseInt(as.getAnswer()), as.getNumber());
    			if(f)
				{
					errorStr="";
				}
				else
				{
					errorStr="Ðánh giá không thành công!";
				}
    		}
        }
        else
		{
			errorStr="Ðánh giá không thành công!";
		}
        if(errorStr.isEmpty())
			url = "khoahoc2.jsp?course_id="+course_id;
		else
			url = "Review.jsp?course_id="+course_id;
        request.setAttribute("errorStr", errorStr);
        response.sendRedirect(url);
        /*RequestDispatcher dispatcher;
		dispatcher = this.getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);*/
	}
}
