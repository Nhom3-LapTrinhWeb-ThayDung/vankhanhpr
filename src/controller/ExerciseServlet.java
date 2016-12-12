package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExerciseDAO;
import dao.Exercise_UserDAO;
import model.Exercise;

/**
 * Servlet implementation class ExerciseServlet
 */
@WebServlet("/ExerciseServlet")
public class ExerciseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExerciseServlet() {
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
		ExerciseDAO exerciseDAO = new ExerciseDAO();
		boolean f;
		Exercise exercise = new Exercise();
		switch(command){
		case "insert":
			
			exercise.setExercise_id(new java.util.Date().getTime());
			exercise.setExercise_name(request.getParameter("exercise_name"));
			exercise.setExercise_startdate(request.getParameter("exercise_startdate"));
			exercise.setExercise_starttime(request.getParameter("exercise_starttime"));
			exercise.setExercise_enddate(request.getParameter("exercise_enddate"));
			exercise.setExercise_endtime(request.getParameter("exercise_endtime"));
			exercise.setSection_id(Long.parseLong(request.getParameter("section_id")));
			exercise.setExersice_content(request.getParameter("exercise_content"));
			f = exerciseDAO.insert(exercise);
			if(f)
				response.getWriter().write("Thêm bài tập thành công!");
			else
				/*response.getWriter().write(exercise.getExercise_id() +"~"+ exercise.getExercise_name()  +"~"+ 
						 exercise.getExersice_content() +"~"+ exercise.getExercise_startdate()
						 +"~"+ exercise.getExercise_starttime() +"~"+ exercise.getExercise_enddate() +"~"+ exercise.getExercise_endtime()
						 +"~"+ exercise.getSection_id());*/
				response.getWriter().write("Thêm bài tập không thành công!");
			break;
		case "update":
			exercise.setExercise_id(Long.parseLong(request.getParameter("exercise_id")));
			exercise.setExercise_name(request.getParameter("exercise_name"));
			exercise.setExercise_startdate(request.getParameter("exercise_startdate"));
			exercise.setExercise_starttime(request.getParameter("exercise_starttime"));
			exercise.setExercise_enddate(request.getParameter("exercise_enddate"));
			exercise.setExercise_endtime(request.getParameter("exercise_endtime"));
			exercise.setSection_id(Long.parseLong(request.getParameter("section_id")));
			exercise.setExersice_content(request.getParameter("exercise_content"));
			f = exerciseDAO.update(exercise);
			if(f)
				response.getWriter().write("Cập nhật bài tập thành công!");
			else
				/*response.getWriter().write(exercise.getExercise_id() +"~"+ exercise.getExercise_name()  +"~"+ 
						 exercise.getExersice_content() +"~"+ exercise.getExercise_startdate()
						 +"~"+ exercise.getExercise_starttime() +"~"+ exercise.getExercise_enddate() +"~"+ exercise.getExercise_endtime()
						 +"~"+ exercise.getSection_id());*/
				response.getWriter().write("Cập nhật bài tập không thành công!");
			break;
		case "delete":
			f = exerciseDAO.delete(Long.parseLong(request.getParameter("exercise_id")));
			if(f)
				response.getWriter().write("Xóa bài tập thành công!");
			else
				response.getWriter().write("Xóa bài tập không thành công!");
			break;
		case "chamdiem":
			
			Exercise_UserDAO e_uDAO = new Exercise_UserDAO();
			f = e_uDAO.chamdiem(Long.parseLong(request.getParameter("result_id")), Double.parseDouble(request.getParameter("score")), request.getParameter("review"));
			if(f)
				response.getWriter().write("Chấm điểm thành công!");
			else
				response.getWriter().write("Chấm điểm không thành công!");
			break;
		}
	}

}
