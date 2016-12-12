package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import dao.QuestionRadioDAO;
import model.QuestionQuiz;
import model.Quiz;
import model.QuizResult;
import model.User_info;
import model.AnswerUser;
/**
 * Servlet implementation class DoQuestionListServlet
 */
@WebServlet("/DoQuestionListServlet")
public class DoQuestionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoQuestionListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		long quiz_id=Long.parseLong(request.getParameter("quiz_id"));
		String command = request.getParameter("command");
		String url = "";
		QuestionRadioDAO questionRadioDAO = new QuestionRadioDAO();
		int countRow = questionRadioDAO.getCountRow(quiz_id);
		int hourssubmit = Integer.parseInt(request.getParameter("hourssubmit"));
		int minutesubmit = Integer.parseInt(request.getParameter("minutesubmit"));
		int secondsubmit = Integer.parseInt(request.getParameter("secondsubmit"));
		HttpSession session = request.getSession();
		User_info user_info = (User_info) session.getAttribute("user_info");
		int socaudung=0;
		String errorStr = "";
            switch(command){
    		case "nopbai":
    			
    	        List<QuestionQuiz> listQuestionRadios = questionRadioDAO.getListQuestionRadios(quiz_id);
    	        List<AnswerUser> listAnswerUsers = new ArrayList<AnswerUser>();
    	         
    	        
    	         
    	        for (int i = 1; i <= countRow; i++) {
    	            String answerUser = request.getParameter("ans[" + i + "]");
    	             
    	            if (answerUser == null) {
    	            	 AnswerUser au = new AnswerUser(i, "E");
     	                listAnswerUsers.add(au);
    	            } else {
    	                AnswerUser au = new AnswerUser(i, answerUser);
    	                listAnswerUsers.add(au);
    	            }
    	        }
    	        /*request.setAttribute("errorStr", errorStr);*/
    	       /* request.setAttribute("errorStr", errorStr);
                request.setAttribute("listQuestionRadios", listQuestionRadios);
                request.setAttribute("listAnswerUsers", listAnswerUsers);
                request.setAttribute("hourssubmit", hourssubmit);
                request.setAttribute("minutesubmit", minutesubmit);
                request.setAttribute("secondsubmit", secondsubmit);*/
                //url="/BaiTestSo1.result.jsp?quiz_id="+quiz_id;
    	        Quiz quiz = new Quiz();
    			quiz = questionRadioDAO.getQuiz(quiz_id);
    			int hours =Integer.parseInt(quiz.getTime().substring(0, 2)) ;
				int minute = Integer.parseInt(quiz.getTime().substring(3, 5));
				int second = Integer.parseInt(quiz.getTime().substring(6, 8));
				int secondwork = 60-secondsubmit;
				int minutework = minute -1 - minutesubmit;
				int hourswork = hours-hourssubmit;
				if(minutework<0)
				{
					minutework+=60;
					hourswork-=1;
				}
    			double diem;
    			for(QuestionQuiz q: listQuestionRadios)
    			{
    				for(AnswerUser a: listAnswerUsers)
    				{
    					if(q.getNumber() ==  a.getNumber())
    					{
    						if(q.getAnswer().equals(a.getAnswer()))
    						{
    							socaudung++;
    						}
    					}
    				}
    			}
    			int tongsocau = countRow;
    			//Time thoigianlambai = Time.parse("thoigianlambai");
    			diem = (10.0/tongsocau)* socaudung;
    			QuizResult qr = new QuizResult();
    			long result_id=new java.util.Date().getTime();
    			qr.setResult_id(result_id);
    			qr.setScores(diem);
    			qr.setQuiz_id(quiz_id);
    			qr.setSocaudung(socaudung);
    			qr.setTongsocau(tongsocau);
    			qr.setTimesubmit(new java.sql.Timestamp(new java.util.Date().getTime()));
    			//qr.setTimework(hourswork +":"+minutework +":"+secondwork);
    			qr.setTimework(hourswork +":"+minutework +":"+secondwork);
    			qr.setUser_id(user_info.getId());
    			boolean f = questionRadioDAO.insertQuizResult(qr);
    			if(f)
    			{
    				url="NopBai.jsp?quiz_id="+quiz_id+"&result_id="+result_id;
    			}
    			else
    			{
    				/*errorStr= qr.getResult_id()+"|"+qr.getSocaudung()+"|"+qr.getTongsocau()+"|"+qr.getScores()+"|"+qr.getTimework()+"|"+qr.getTimesubmit()+"|"+qr.getQuiz_id()+"|"+qr.getUser_id();*/
    				url="ThiTracNghiem.jsp?quiz_id="+quiz_id;
    			}
    			break;
    		
    		}
            /*request.setAttribute("errorStr", errorStr);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);*/
            response.sendRedirect(url);
            }

}
