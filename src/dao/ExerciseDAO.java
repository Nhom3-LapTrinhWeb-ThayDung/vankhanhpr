package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.DBConnect;
import model.Course;
import model.Exercise;

public class ExerciseDAO {
	private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
	public boolean insert(Exercise ex)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "insert into exercise values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, ex.getExercise_id());
			ps.setString(2, ex.getExercise_name());
			ps.setString(3, ex.getExersice_content());
			ps.setString(4,ex.getExercise_startdate());
			ps.setString(5, ex.getExercise_starttime());
			ps.setString(6, ex.getExercise_enddate());
			ps.setString(7, ex.getExercise_endtime());
			ps.setLong(8, ex.getSection_id());

			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean update(Exercise ex)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "update exercise set exercise_name=?,exersice_content=?,exercise_startdate=?,exercise_starttime=?,exercise_enddate=?,exercise_endtime=? where exercise_id=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			
			ps.setString(1, ex.getExercise_name());
			ps.setString(2, ex.getExersice_content());
			ps.setString(3,ex.getExercise_startdate());
			ps.setString(4, ex.getExercise_starttime());
			ps.setString(5, ex.getExercise_enddate());
			ps.setString(6, ex.getExercise_endtime());
			ps.setLong(7, ex.getExercise_id());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(long id)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "delete from exercise where exercise_id=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	 public List<Exercise> getListExercise(long section_id) {
	        try {
	        	Connection conn = DBConnect.getConnecttion();
	            String sql = "select * from exercise where  section_id='"+section_id+"'";
	            ps = conn.prepareStatement(sql);
	            rs = ps.executeQuery();
	             
	            List<Exercise> listExercise = new ArrayList<Exercise>();
	            while (rs.next()) {
	            	Exercise ex = new Exercise();
	            	ex.setExercise_id(rs.getLong(1));
	            	ex.setExercise_name(rs.getString(2));
	            	ex.setExersice_content(rs.getString(3));
	            	ex.setExercise_startdate(rs.getString(4));
	            	ex.setExercise_starttime(rs.getString(5));
	            	ex.setExercise_enddate(rs.getString(6));
	            	ex.setExercise_endtime(rs.getString(7));
	            	ex.setSection_id(rs.getLong(8));

	            	listExercise.add(ex);
	            }
	            conn.close();
	            return listExercise;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	
	 public Exercise getExercise(long exercise_id) {
	        try {
	        	Connection conn = DBConnect.getConnecttion();
	            String sql = "select * from exercise where  exercise_id='"+exercise_id+"'";
	            ps = conn.prepareStatement(sql);
	            rs = ps.executeQuery();
	             
	            Exercise ex = new Exercise();
	            while (rs.next()) {            	ex.setExercise_id(rs.getLong(1));
	            	ex.setExercise_name(rs.getString(2));
	            	ex.setExersice_content(rs.getString(3));
	            	ex.setExercise_startdate(rs.getString(4));
	            	ex.setExercise_starttime(rs.getString(5));
	            	ex.setExercise_enddate(rs.getString(6));
	            	ex.setExercise_endtime(rs.getString(7));
	            	ex.setSection_id(rs.getLong(8));

	            }
	            conn.close();
	            return ex;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	
	 
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExerciseDAO dao = new ExerciseDAO();
    	/*for (Exercise ex : dao.findListExercise("bai")) {
    		System.out.println(ex.getExercise_id() +"~"+ ex.getExercise_name()  +"~"+ 
    				ex.getExersice_content() +"~"+ ex.getExercise_startdate()
					 +"~"+ ex.getExercise_starttime() +"~"+ ex.getExercise_enddate() +"~"+ ex.getExercise_endtime()
					 +"~"+ ex.getSection_id());
		}*/
	}	

}
