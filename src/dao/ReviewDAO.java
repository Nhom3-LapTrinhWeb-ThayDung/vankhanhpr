package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;

import connect.DBConnect;
import model.AnswerUser;
import model.Course;
import model.Review_Question;

public class ReviewDAO {
	private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
	public List<Review_Question> getListReview_Question() {
        try {
        	Connection conn = DBConnect.getConnecttion();
            String sql = "select * from review_question";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
             
            List<Review_Question> listq = new ArrayList<Review_Question>();
            while (rs.next()) {
            	Review_Question q = new Review_Question();
            	q.setNumber(rs.getInt(1));
            	q.setQuestion(rs.getString(2));
            	listq.add(q);
            }
            conn.close();
            return listq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	public int getCountRow() {
        int countRow = 0;
        try {
            conn = DBConnect.getConnecttion();
            String sql = "select count(*) from review_question";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
             
            if (rs.next())
                countRow = rs.getInt(1);
             
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return countRow;
    }
	
	public boolean insertReview(long review_id, long student_id,long course_id, long teacher_id, String opinion)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "insert into review values(?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, review_id);
			ps.setLong(2, student_id);
			ps.setLong(3, course_id);
			ps.setLong(4, teacher_id);
			ps.setString(5, opinion);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean insertReview_Answer(long review_id, int review_option, int number)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "insert into review_answer values(?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, review_id);
			ps.setInt(2, review_option);
			ps.setInt(3, number);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) throws SQLException{
    	ReviewDAO rDAO = new ReviewDAO();
    	long review_id= Long.parseLong("1481378654016");
    	long student_id = 2;
    	long course_id= Long.parseLong("1481372019619");
    	long teacher_id = 1;
    	String opinion ="ádsadsad";
    	boolean f = rDAO.insertReview_Answer(review_id, 2, 1);
    	System.out.println(f);
    }
}
