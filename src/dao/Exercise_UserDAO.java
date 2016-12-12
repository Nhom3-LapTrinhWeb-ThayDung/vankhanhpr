package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.DBConnect;
import model.Exercise;
import model.Exercise_User;
import model.Quiz;
import model.QuizResult;
import model.Section;
import model.Users;

public class Exercise_UserDAO {
	private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
	
	public List<Exercise_User> getListExercise_User(long course_id) {
        try {
        	Connection conn = DBConnect.getConnecttion();
            String sql = "select * from exercise_user where course_id='"+course_id+"'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
             
            List<Exercise_User> listExerciseUser = new ArrayList<Exercise_User>();
            while (rs.next()) {
            	Exercise_User ex = new Exercise_User();
            	ex.setResult_id(rs.getLong(1));
            	ex.setExercise_id(rs.getLong(2));
            	ex.setUser_id(rs.getLong(3));
            	ex.setUser_name(rs.getString(4));
            	ex.setCourse_id(rs.getLong(5));
            	ex.setSection_name(rs.getString(6));
            	ex.setExercise_name(rs.getString(7));
            	ex.setFilesubmit(rs.getString(8));
            	ex.setTimesubmit(rs.getTimestamp(9));
            	ex.setDescription(rs.getString(10));
            	ex.setScore(rs.getDouble(11));
            	ex.setReview(rs.getString(12));
            	listExerciseUser.add(ex);
            }
            conn.close();
            return listExerciseUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public boolean chamdiem(long result_id, double score, String review )
    {
    	Connection con = DBConnect.getConnecttion();
		String sql = "update exercise_user set score=?,review=? where result_id=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setDouble(1, score);
			ps.setString(2, review);
			ps.setLong(3, result_id);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }
	
	public Exercise_User timbaidanop(long exercise_id, long user_id ){
		
		Connection con = DBConnect.getConnecttion();
		String sql = "select * from exercise_user where exercise_id='"+exercise_id+"' and user_id='"+user_id+"'";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Exercise_User exu = new Exercise_User();
				exu.setResult_id(rs.getLong(1));
				exu.setExercise_id(rs.getLong(2));
				exu.setUser_id(rs.getLong(3));
				exu.setUser_name(rs.getString(4));
				exu.setCourse_id(rs.getLong(5));
				exu.setSection_name(rs.getString(6));
				exu.setExercise_name(rs.getString(7));
				exu.setFilesubmit(rs.getString(8));
				exu.setTimesubmit(rs.getTimestamp(9));
				exu.setDescription(rs.getString(10));
				exu.setScore(rs.getDouble(11));
				exu.setReview(rs.getString(12));
				con.close();
				return exu;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Section getSection(long exercise_id) {
        try {
        	Connection conn = DBConnect.getConnecttion();
            String sql = "select * from exercise,section where exercise.section_id = section.section_id and exercise_id='"+exercise_id+"'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
             
            Section s = new Section();
            while (rs.next()) {
            	s.setSection_id(rs.getLong("section_id"));
            	s.setSection_name(rs.getString("section_name"));
            	s.setSection_content(rs.getString("section_content"));
            	s.setCourse_id(rs.getLong("course_id"));
            }
            conn.close();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public boolean nopbai(Exercise_User exu )
    {
    	Connection con = DBConnect.getConnecttion();
		String sql = "insert into exercise_user values(?,?,?,?,?,?,?,?,?,?,'0',' ')";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, exu.getResult_id());
			ps.setLong(2, exu.getExercise_id());
			ps.setLong(3, exu.getUser_id());
			ps.setString(4, exu.getUser_name());
			ps.setLong(5, exu.getCourse_id());
			ps.setString(6, exu.getSection_name());
			ps.setString(7, exu.getExercise_name());
			ps.setString(8, exu.getFilesubmit());
			ps.setTimestamp(9, exu.getTimesubmit());
			ps.setString(10, exu.getDescription());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }
	
	public boolean suabai(Exercise_User exu )
    {
    	Connection con = DBConnect.getConnecttion();
		String sql = "update exercise_user set filesubmit=?,timesubmit=?,description=? where result_id=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setString(1, exu.getFilesubmit());
			ps.setTimestamp(2, exu.getTimesubmit());
			ps.setString(3, exu.getDescription());
			ps.setLong(4, exu.getResult_id());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }
	
	public List<Quiz> findListQuiz(String str) {
        try {
            conn = DBConnect.getConnecttion();
            String sql = "select * from quiz where quiz_name like '%"+str+"%' or start_date like '%"+str+"%' or ecd_date like '%"+str+"%'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
             
            List<Quiz> listQuiz = new ArrayList<Quiz>();
            while (rs.next()) {
            	Quiz q = new Quiz();
				q.setId(rs.getLong("id"));
				q.setQuiz_name(rs.getString("quiz_name"));
				q.setStart_date(rs.getString("start_date"));
				q.setEnd_date(rs.getString("end_date"));
				q.setTime(rs.getString("time"));
				q.setCount(rs.getInt("count"));
				q.setDescription(rs.getString("description"));
				q.setSection_id(rs.getLong("section_id"));
				listQuiz.add(q);
            }
            conn.close();
            return listQuiz;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }
	
	public static void main(String[] args) {
	// TODO Auto-generated method stub
		Exercise_UserDAO dao = new Exercise_UserDAO();
    	/*
    	
    	for (Exercise_User ex : dao.findListExercise_User("2016")) {
    		System.out.println(ex.getResult_id() +"-"+ex.getSection_name() + "-" + ex.getExercise_id()
    		 + "-" + ex.getExercise_name()  + "-" + ex.getFilesubmit()
    		 + "-" + ex.getReview() + "-" + ex.getScore()
    		 + "-" + ex.getUser_id() + "-" + ex.getUser_name() + "-" + ex.getTimesubmit());
		}*/
	}

}
