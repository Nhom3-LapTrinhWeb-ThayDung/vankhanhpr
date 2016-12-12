package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connect.DBConnect;
import model.Exercise_User;
import model.Quiz_User;

public class Quiz_UserDAO {
	private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quiz_UserDAO quDAO = new Quiz_UserDAO();
		for(Quiz_User qu : quDAO.getListQuiz_User(Long.parseLong("1479799303858")))
		{
			System.out.println(qu.getResult_id());
		}
	}
	public List<Quiz_User> getListQuiz_User(long course_id) {
        try {
        	Connection conn = DBConnect.getConnecttion();
            String sql = "select * from quiz,quiz_result,section,user_info where section.course_id='"+course_id+"' "
            		+ "and quiz.id=quiz_result.quiz_id and quiz.section_id=section.section_id and quiz_result.user_id=user_info.id";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
             
            List<Quiz_User> listQuiz_User = new ArrayList<Quiz_User>();
            while (rs.next()) {
            	Quiz_User q = new Quiz_User();
            	q.setCourse_id(rs.getLong("course_id"));
            	q.setQuiz_id(rs.getLong("quiz_id"));
            	q.setQuiz_name(rs.getString("quiz_name"));
            	q.setResult_id(rs.getLong("result_id"));
            	q.setScores(rs.getDouble("scores"));
            	q.setSection_name(rs.getString("section_name"));
            	q.setSocaudung(rs.getInt("socaudung"));
            	q.setTimesubmit(rs.getTimestamp("timesubmit"));
            	q.setTimeswork(rs.getString("timework"));
            	q.setTongsocau(rs.getInt("tongsocau"));
            	q.setUser_id(rs.getLong("user_id"));
            	q.setUser_name(rs.getString("ten"));
            	listQuiz_User.add(q);
            }
            conn.close();
            return listQuiz_User;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
