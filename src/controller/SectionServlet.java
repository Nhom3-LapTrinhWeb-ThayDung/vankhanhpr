package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExerciseDAO;
import dao.SectionDAO;
import model.Section;

/**
 * Servlet implementation class SectionServlet
 */
@WebServlet("/SectionServlet")
public class SectionServlet extends HttpServlet {
	SectionDAO sectionDAO = new SectionDAO();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SectionServlet() {
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
		ExerciseDAO exerciseDAO = new ExerciseDAO();
		Section section = new Section();
		boolean f;
		String url="";
		String section_id = "";
		String course_id = "";
		switch(command){
		case "insert":
			section.setSection_id(new java.util.Date().getTime());
			section.setSection_name(request.getParameter("section_name"));
			section.setSection_content(request.getParameter("section_content"));
			section.setCourse_id(Long.parseLong(request.getParameter("course_id")));
			
			f = sectionDAO.insert(section);
			if(f)
				response.getWriter().write("Thêm section thành công!");
			else
				/*response.getWriter().write(exercise.getExercise_id() +"~"+ exercise.getExercise_name()  +"~"+ 
						 exercise.getExersice_content() +"~"+ exercise.getExercise_startdate()
						 +"~"+ exercise.getExercise_starttime() +"~"+ exercise.getExercise_enddate() +"~"+ exercise.getExercise_endtime()
						 +"~"+ exercise.getSection_id());*/
				response.getWriter().write("Thêm section không thành công!");
			
			break;
		case "update":
			url="";
			section.setSection_id(Long.parseLong(request.getParameter("section_id")));
			section.setSection_name(request.getParameter("section_name"));
			section.setSection_content(request.getParameter("section_content"));
			section.setCourse_id(Long.parseLong(request.getParameter("course_id")));
			
			f = sectionDAO.update(section);
			if(f)
				url="khoahoc2.jsp?course_id="+section.getCourse_id();
			else
				url="edit-section.jsp?course_id="+section.getCourse_id()+"&section_id="+section.getSection_id();
			response.sendRedirect(url);
			break;
		case "delete":
			url="";
			
			section_id = request.getParameter("section_id");
			course_id = request.getParameter("course_id");
			f = sectionDAO.delete(Long.parseLong(section_id));
			if(f)
				url="khoahoc2.jsp?course_id="+course_id;
			else
				url="edit-section.jsp?course_id="+course_id+"&section_id="+section_id;
			response.sendRedirect(url);
			break;
		case "deleteresources":
			url="";
			f = sectionDAO.deleteresources(Long.parseLong(request.getParameter("resources_id")));
			if(f)
				response.getWriter().write("Xóa file thành công!");
			else
				response.getWriter().write("Xóa file không thành công!");
			break;
		
		}
		
	}

}
