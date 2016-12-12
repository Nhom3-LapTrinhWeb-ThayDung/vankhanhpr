package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CourseWaiting;
import model.User_info;
import model.Course;
import dao.CourseWaitingDAO;


/**
 * Servlet implementation class CourseWaitingServlet
 */
@WebServlet("/CourseWaitingServlet")
public class CourseWaitingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseWaitingServlet() {
		super();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String command = request.getParameter("command");
		String url = "";
		CourseWaiting coursewaiting = new CourseWaiting();
		CourseWaitingDAO coursewaitingDAO = new CourseWaitingDAO();

		RequestDispatcher rd ;

		HttpSession session = request.getSession();
		User_info user_register= (User_info)session.getAttribute("user_info");
		/*Course course_register = (Course)session.getAttribute("course_register");*/

		switch(command){
		case "insert":
			long x = new java.util.Date().getTime();
			coursewaiting.setCourse_waiting_id(x);
			coursewaiting.setUser_id(user_register.getId());
			coursewaiting.setCourse_id(Long.parseLong(request.getParameter("course_id")));					
			coursewaiting.setTime_register(new Timestamp(new Date().getTime()));	
			
			boolean k = coursewaitingDAO.check_course_register(user_register.getId(), Long.parseLong(request.getParameter("course_id")));
			if(!k)
			{
				response.getWriter().write("Bạn đã đăng ký khóa học này !");
				break;
			}
			boolean f = coursewaitingDAO.insert(coursewaiting);
			if(f)
			{
				response.getWriter().write("Đăng ký khóa học thành công. Vui lòng chờ xác nhận !");
			}
			else
			{
				//session.removeAttribute("user");
				response.getWriter().write("Bạn đã đăng ký khóa học này rồi !");
			}
			/*RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);*/
			break;	
		case "delete":
			url="";

			String course_waiting_id = request.getParameter("course_waiting_id");
			String course_id = request.getParameter("course_id");
			f = coursewaitingDAO.delete(Long.parseLong(course_waiting_id));
			if(f)
				response.getWriter().write("thành công!");
				url="khoahoc2.jsp?course_id="+course_id;
			rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
			break;
		case "deletecoursewaiting":
			String course_waiting_id1 = request.getParameter("course_waiting_id");
			f = coursewaitingDAO.deleteWaiting(Long.parseLong(course_waiting_id1));
			if(f)
			{
				response.getWriter().write("Delete success!");
			}
			else
				response.getWriter().write("không thành công!");
			{
			//session.removeAttribute("user");
				response.getWriter().write("Delete unsuccessful!");
			}
			break;

			
		case "acceptcoursewaiting":
			String course_waiting_idacp = request.getParameter("course_waiting_id");
			String course_idacp = request.getParameter("course_id");
			String user_idacpc= request.getParameter("user_id");
			long coursewaitingid=Long.parseLong(course_waiting_idacp);
			long courseid =Long.parseLong(course_idacp);
			long userid =Long.parseLong(user_idacpc);
			
			f=coursewaitingDAO.insertCouser_user(coursewaitingid, courseid, userid);
			if(f)
			{
				response.getWriter().write("Accept success!");
			}
			else
			{
			//session.removeAttribute("user");
				response.getWriter().write("Accept unsuccessful!");
			}
			break;

		}
	}
}


