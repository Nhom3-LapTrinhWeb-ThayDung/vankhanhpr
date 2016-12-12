package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionRadioDAO;
import model.AnswerUser;
import model.QuestionQuiz;
import model.Quiz;
/**
 * Servlet implementation class SetListQuestionServlet
 */
@WebServlet("/SetListQuestionServlet")
public class SetListQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetListQuestionServlet() {
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
		String errorStr = "";
		String command = request.getParameter("command");
		long course_id;
		Quiz quiz = new Quiz();
		QuestionRadioDAO questionRadioDAO = new QuestionRadioDAO();
		long quiz_id ;
		String quiz_name;
		String start_date;
		String end_date;
		String time;
		String description;
		int count;
		long section_id;
		String url="";
		String question,option1,option2,option3,option4,answer;
		RequestDispatcher dispatcher;
		List<QuestionQuiz> listQuestion =  new ArrayList<QuestionQuiz>();;
		switch (command) {
		case "insert":
			//insert quiz
			course_id = Long.parseLong(request.getParameter("course_id"));
			quiz = new Quiz();
			quiz_id =  new java.util.Date().getTime();
			quiz_name = request.getParameter("quiz_name");
			start_date = request.getParameter("start_date") ;
			end_date = request.getParameter("end_date");
			time = request.getParameter("times[hour]")
					+":"+ request.getParameter("times[minute]"); 
			count= Integer.parseInt( request.getParameter("question_count"));
			
			description = request.getParameter("description");
			section_id = Long.parseLong(request.getParameter("section_id"));
			if(quiz_name=="" ||start_date==null ||end_date==null ||time==null ||count==0)	
			{
				errorStr = "Chưa nhập đủ thông tin1!"+quiz_name+start_date+end_date+time+count;
			}
			else
			{
				errorStr="";
				quiz = new Quiz(quiz_id,quiz_name,start_date,end_date,time,count,description,section_id);
			}


			// insert cĂ¡c cĂ¢u há»�i (question)
			for (int i = 1; i <= count; i++) {
				//String answerUser = request.getParameter("ans[" + i + "]");
				long id =  new java.util.Date().getTime()+i;
				
				question = request.getParameter("question["+i+"]");
				option1 = request.getParameter("txtoptionA["+i+"]");
				option2 = request.getParameter("txtoptionB["+i+"]");
				option3 = request.getParameter("txtoptionC["+i+"]");
				option4 = request.getParameter("txtoptionD["+i+"]");
				answer = request.getParameter("ans["+i+"]");
				if (question == null ||  option1==null || option2==null ||option3==null ||option4==null || answer==null || quiz_name=="")
				{
					errorStr = "Chưa nhập đủ thông tin2!"+question+option1+option2+option3+option4+answer+quiz_name;
				} else {
					errorStr="";
					QuestionQuiz q = new QuestionQuiz(id,i,question,option1,option2,option3,option4,answer,quiz_id );
					listQuestion.add(q);
				}
			}

			if (!errorStr.isEmpty()) {

			} else {
				questionRadioDAO = new QuestionRadioDAO();

				boolean f1 = questionRadioDAO.insertQuiz(quiz);
				if(f1)
				{
					
				
					for (QuestionQuiz questionQuiz : listQuestion) {
						boolean f = questionRadioDAO.insertQuestion(questionQuiz);
						if(f)
						{
							errorStr="";
						}
						else
						{
							errorStr="Thêm bài thi trắc nghiệm không thành công1!";
						}
					}
				}
				else
				{
					errorStr="Thêm bài thi trắc nghiệm không thành công2!";
				}
			}
			if(errorStr.isEmpty())
				url = "khoahoc2.jsp?course_id="+course_id;
			else
				url = "create-quiz.jsp?course_id="+course_id+"$section_id="+section_id;
			/*response.getWriter().write(errorStr);*/
			request.setAttribute("errorStr", errorStr);
			response.sendRedirect(url);
			break;
		case "update":
			//update quiz
			course_id = Long.parseLong(request.getParameter("course_id"));
			quiz = new Quiz();
			quiz_id = Long.parseLong(request.getParameter("quiz_id"));
			quiz_name = request.getParameter("quiz_name");
			start_date = request.getParameter("start_date") ;
			end_date = request.getParameter("end_date");
			time = request.getParameter("times[hour]")
					+":"+ request.getParameter("times[minute]"); 
			count= Integer.parseInt( request.getParameter("question_count"));
			
			description = request.getParameter("description");
			section_id = Long.parseLong(request.getParameter("section_id"));
			if(quiz_name=="" ||start_date==null ||end_date==null ||time==null ||count==0)	
			{
				errorStr = "Chưa nhập đủ thông tin1!"+quiz_name+start_date+end_date+time+count;
			}
			else
			{
				errorStr="";
				quiz = new Quiz(quiz_id,quiz_name,start_date,end_date,time,count,description,section_id);
			}


			// insert cĂ¡c cĂ¢u há»�i (question)
			
			for (int i = 1; i <= count; i++) {
				//String answerUser = request.getParameter("ans[" + i + "]");
				long id =  Long.parseLong(request.getParameter("questionid["+i+"]"));
				
				question = request.getParameter("question["+i+"]");
				option1 = request.getParameter("txtoptionA["+i+"]");
				option2 = request.getParameter("txtoptionB["+i+"]");
				option3 = request.getParameter("txtoptionC["+i+"]");
				option4 = request.getParameter("txtoptionD["+i+"]");
				answer = request.getParameter("ans["+i+"]");
				if (question == null ||  option1==null || option2==null ||option3==null ||option4==null || answer==null || quiz_name=="")
				{
					errorStr = "Chưa nhập đủ thông tin2!"+question+option1+option2+option3+option4+answer+quiz_name;
				} else {
					errorStr="";
					QuestionQuiz q = new QuestionQuiz(id,i,question,option1,option2,option3,option4,answer,quiz_id );
					listQuestion.add(q);
				}
			}

			if (!errorStr.isEmpty()) {

			} else {
				questionRadioDAO = new QuestionRadioDAO();

				boolean f1 = questionRadioDAO.update(quiz);
				if(f1)
				{
				
					for (QuestionQuiz questionQuiz : listQuestion) {
						boolean f = questionRadioDAO.updateQuestion(questionQuiz);
						if(f)
						{
							errorStr="";
						}
						else
						{
							errorStr="Sửa bài thi trắc nghiệm không thành công1!";
						}
					}
				}
				else
				{
					errorStr="Sửa bài thi trắc nghiệm không thành công2!";
				}
			}
			if(errorStr.isEmpty())
				url = "khoahoc2.jsp?course_id="+course_id;
			else
				url = "edit-quiz.jsp?course_id="+course_id+"$section_id="+section_id;
			//response.getWriter().write(errorStr);
			/*request.setAttribute("errorStr", errorStr);
			dispatcher = this.getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);*/
			response.sendRedirect(url);
			break;
		case "checkquestionresult":
			boolean check = questionRadioDAO.checkQuizResult(Long.parseLong(request.getParameter("quiz_id")));
			if(check)
				response.getWriter().write("true");
			else
				response.getWriter().write("false");
			break;
		case "delete":
			quiz_id = Long.parseLong(request.getParameter("quiz_id"));
			count = questionRadioDAO.getCountRow(quiz_id);
			boolean f ;
			boolean f1;
			boolean f2;
			f1 = questionRadioDAO.deleteQuizResult(quiz_id);
			if(f1)
			{
				f2=questionRadioDAO.deleteQuestion(quiz_id);
				if(f2)
				{
					f= questionRadioDAO.deleteQuiz(quiz_id);
					if(f)
						response.getWriter().write("X�a b�i test th�nh c�ng!");
					else
						response.getWriter().write("X�a b�i test kh�ng th�nh c�ng!");
				}
				else
					response.getWriter().write("X�a b�i test kh�ng th�nh c�ng!");
			}
			else
				response.getWriter().write("X�a b�i test kh�ng th�nh c�ng!");
			break;
		default:
			break;
		}
		
	}

}
