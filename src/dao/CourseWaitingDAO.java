package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connect.DBConnect;
import model.Course;
import model.CourseWaiting;
import model.User_info;;

public class CourseWaitingDAO {

	private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PreparedStatement ps;
		CourseWaitingDAO cwDAO = new CourseWaitingDAO();
		CourseWaiting cw = new CourseWaiting();
		
	/*	cw.setCourse_id(Long.parseLong("234234"));
		cw.setUser_id(2);
		cw.setCourse_waiting_id(new Date().getTime());
		cw.setTime_register(new Timestamp(new Date().getTime()));
		boolean f = cwDAO.insert(cw);
		System.out.println(cw.getCourse_waiting_id()+"-"+cw.getCourse_id()+"-"+cw.getUser_id()+"-"+cw.getTime_register().toString());
	*/
		Connection conn = DBConnect.getConnecttion();
		
		
		String sql = "select * from course where course.course_id ='1479732694852' "
				+ "and course.course_id not in (select course_user.course_id from course_user where course_user.course_id='1479732694852' and course_user.id ='2' )"
				+ "and course.course_id not in (select course_waiting.course_id from course_waiting where course_waiting.course_id='1479732694852' and course_waiting.user_id ='2' );";
		
		
		String sql2 = "select * from course_user"
				+ "where '2' not in (select course_user.id"
				+ "				from  course_user"
				+ "				where course_user.id ='2' "
				+ "					and course_user.course_id = '1479799303858')";
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				conn.close();
				System.out.println("Tìm thấy");
			}
			else
			{
				System.out.println("Không tìm thấy");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
            
		
	}
	
	public boolean insert(CourseWaiting cw)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "insert into course_waiting values(?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, cw.getCourse_waiting_id());
			ps.setLong(2, cw.getUser_id());
			ps.setLong(3, cw.getCourse_id());
			ps.setTimestamp(4, cw.getTime_register());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	 //Kiểm tra xem khóa học đó học viên đã đăng ký chưa
    public boolean check_course_register (long user_id,long course_id)
	{
		Connection conn = DBConnect.getConnecttion();
		String sql = "select * from course where course.course_id ='"+course_id+"' "
				+ "and course.course_id not in (select course_user.course_id from course_user where course_user.course_id='"+course_id+"' and course_user.id ='"+user_id+"' )"
				+ "and course.course_id not in (select course_waiting.course_id from course_waiting where course_waiting.course_id='"+course_id+"' and course_waiting.user_id ='"+user_id+"' );";
		
		PreparedStatement ps;
		try
		{
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				conn.close();
				return true;
			}
            
		}
		catch (Exception e) 
		{
            e.printStackTrace();           
        }
		 return false;
	}
	public boolean delete(long course_waiting_id)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "delete from course_waiting where course_waiting_id=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, course_waiting_id);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteWaiting(long course_waiting_id)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "DELETE FROM course_waiting WHERE course_waiting_id ='"+course_waiting_id+"'";
		PreparedStatement ps;
		try{
			ps = (PreparedStatement) con.prepareCall(sql);
			
			ps.executeUpdate();
			
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean insertCouser_user(long course_waiting_id,long course_id ,long user_id)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "insert into course_user values(?,?)";
		String sql1 = "DELETE FROM course_waiting WHERE course_waiting_id ='"+course_waiting_id+"'";
		PreparedStatement ps;
		PreparedStatement ps1;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, user_id);
			ps.setLong(2, course_id);
			ps.executeUpdate();
			
			ps1 = (PreparedStatement) con.prepareCall(sql1);
			ps1.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//Lấy teacher
	public User_info getteacher(long course_id) {
        try {
        	Connection conn = DBConnect.getConnecttion();
            String sql = "select * from user_info, course_user where user_info.id = course_user.id and quyen=1 and course_id='"+course_id+"'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            User_info u = new User_info();
            while (rs.next()) {
            	u.setId(rs.getLong(1));
            	u.setTen(rs.getString(2));
            	u.setSodienthoai(rs.getString(3));
            	u.setGioitinh(rs.getInt(4));
            	u.setNgaysinh(rs.getString(5));
            	u.setEmail(rs.getString(6));
            	
            	u.setDiachi(rs.getString(8));
            	u.setQuyen(rs.getInt(9));            	
            }
            conn.close();
            return u;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	//Lấy danh sách khóa học đang chờ xác nhận theo từng học viên
	public List<Course> getListCourseWaiting(long user_id) {
        try {
        	Connection conn = DBConnect.getConnecttion();
            String sql = "select * from course_waiting, course where course_waiting.course_id = course.course_id and user_id='"+user_id+"'";
            
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
             
            List<Course> listCourseWaiting = new ArrayList<Course>();
            
            while (rs.next()) {
            	Course cw = new Course();
            	
            	cw.setCourse_id(rs.getLong(3));
            	cw.setCourse_name(rs.getString(6));
            	cw.setCourse_startdate(rs.getString(7));
            	cw.setCourse_enddate(rs.getString(8));
            	cw.setCourse_schedulingday(rs.getInt(9));
            	cw.setCourse_startlession(rs.getInt(10));
            	cw.setCourse_endlession(rs.getInt(11));
            	cw.setCourse_place(rs.getString(12));
            	cw.setCourse_description(rs.getString(13));
            	
            	listCourseWaiting.add(cw);
            }
            conn.close();
            return listCourseWaiting;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	public CourseWaiting getCourseWaiting(long user_id,long course_id) {
        try {
        	Connection conn = DBConnect.getConnecttion();
            String sql = "select * from  course, course_waiting where course.course_id=course_waiting.course_id and course_waiting.user_id='"+user_id+"' and course.course_id='"+course_id+"'";
            
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
  
            CourseWaiting cw = new CourseWaiting();
            while (rs.next()) {
            	cw.setCourse_waiting_id(rs.getLong("course_waiting_id"));
            	cw.setCourse_id(rs.getLong("course_id"));
            	cw.setTime_register(rs.getTimestamp("time_register"));
            	cw.setUser_id(rs.getLong("user_id"));
            }
            conn.close();
            return cw;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
