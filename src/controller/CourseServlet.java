package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CourseDAO;
import dao.QuestionRadioDAO;
import model.Course;
import model.QuestionQuiz;
import model.Quiz;

/**
 * Servlet implementation class CourseServlet
 */
@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
	CourseDAO courseDAO = new CourseDAO();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		HttpSession session = request.getSession();
		
		Course course = new Course();
		boolean f;
		switch(command){
		case "insert":
			long x = new java.util.Date().getTime();
			course.setCourse_id(x);
			course.setCourse_description(request.getParameter("course_description"));
			course.setCourse_startdate(request.getParameter("startdate_nam")
				+"-"+ request.getParameter("startdate_thang")
				+"-"+ request.getParameter("startdate_ngay"));
			course.setCourse_enddate(request.getParameter("enddate_nam")
				+"-"+ request.getParameter("enddate_thang")
				+"-"+ request.getParameter("enddate_ngay"));
			course.setCourse_name(request.getParameter("course_name"));
			course.setCourse_startlession(Integer.parseInt(request.getParameter("startlession")));
			course.setCourse_endlession(Integer.parseInt(request.getParameter("endlession")));
			course.setCourse_place(request.getParameter("course_place"));
			course.setCourse_schedulingday(Integer.parseInt(request.getParameter("schedulingday")));
			
			/*course = new Course(x,request.getParameter("course_name"),request.getParameter("startdate_nam")
					+"-"+ request.getParameter("startdate_thang")
					+"-"+ request.getParameter("startdate_ngay"),request.getParameter("enddate_nam")
					+"-"+ request.getParameter("enddate_thang")
					+"-"+ request.getParameter("enddate_ngay"),Integer.parseInt(request.getParameter("schedulingday")),Integer.parseInt(request.getParameter("startlession")),Integer.parseInt(request.getParameter("endlession")),request.getParameter("course_place"),request.getParameter("course_description"));*/
			f = courseDAO.insert(course);
			if(f)
			{
					f= courseDAO.insertCourse_User(x,Long.parseLong(request.getParameter("idgiangvien")));
					if(f)
					{
						session.setAttribute("course", course);
						response.getWriter().write("khoahoc2.jsp?course_id="+x);
						response.getWriter().write("");
						
					}
					else
					{
						//session.removeAttribute("user");
						//url="canhangiangvien.jsp";
						response.getWriter().write("Mở khóa học không thành công!");
					}
			}
			else
			{
				//session.removeAttribute("user");
				//url="canhangiangvien.jsp";
				response.getWriter().write("Mở khóa học không thành công!");
			}
			
			break;
		case "deletecourse":
			long CourseDAO;
			CourseDAO=Long.parseLong(request.getParameter("courseID_delete"));
			f = courseDAO.deleteCourse(CourseDAO);
			if(f)
			{
				
				response.getWriter().write("Delete success!");
			}
			else
			{
			//session.removeAttribute("user");
				response.getWriter().write("Delete unsuccessful!");
			}
			break;		
		case "update":
			course.setCourse_id(Long.parseLong(request.getParameter("course_id")));
			course.setCourse_description(request.getParameter("course_description"));
			course.setCourse_startdate(request.getParameter("course_startdate"));
			course.setCourse_enddate(request.getParameter("course_enddate"));
			course.setCourse_name(request.getParameter("course_name"));
			course.setCourse_startlession(Integer.parseInt(request.getParameter("course_startlession")));
			course.setCourse_endlession(Integer.parseInt(request.getParameter("course_endlession")));
			course.setCourse_place(request.getParameter("course_place"));
			course.setCourse_schedulingday(Integer.parseInt(request.getParameter("course_schedulingday")));
			
			/*course = new Course(x,request.getParameter("course_name"),request.getParameter("startdate_nam")
					+"-"+ request.getParameter("startdate_thang")
					+"-"+ request.getParameter("startdate_ngay"),request.getParameter("enddate_nam")
					+"-"+ request.getParameter("enddate_thang")
					+"-"+ request.getParameter("enddate_ngay"),Integer.parseInt(request.getParameter("schedulingday")),Integer.parseInt(request.getParameter("startlession")),Integer.parseInt(request.getParameter("endlession")),request.getParameter("course_place"),request.getParameter("course_description"));*/
			f = courseDAO.update(course);
			if(f)
			{
				url="khoahoc2.jsp?course_id="+course.getCourse_id();
			}
			else
			{
				url="edit-course.jsp?course_id="+course.getCourse_id();
			}
			response.sendRedirect(url);
			/*RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);*/
			break;
		case "getlistcourseofuser":
			/*int userid=Integer.parseInt(request.getParameter("userid"));
			//String y = request.getParameter("userid");
			List<Course> listCourse = new CourseDAO().getListCourse(userid);
	        request.setAttribute("listCourse", y);
	        //if(listCourse==null)
	        	response.getWriter().write(y);
	       // else
	        	//response.getWriter().write("load ds khóa học thành công!");
			//break;
*/		}
	
	}

}
