package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.DBConnect;
import model.Infotaikhoan;
import model.User_info;
import model.Users;

public class User_infoDAO {
	private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
	//login lấy thông tin cá nhân
	public User_info login(String username, String password, int quyen){
		Connection con = DBConnect.getConnecttion();
		String sql = "select * from user_info,user where user_name ='"+username+"' and user_pass='"+password+"' and user_id = id and quyen = '"+quyen+"'";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User_info u = new User_info();
				u.setId(rs.getLong("id"));
				u.setAnhdaidien(rs.getString("anhdaidien"));
				u.setDiachi(rs.getString("diachi"));
				u.setEmail(rs.getString("email"));
				u.setGioitinh(rs.getInt("gioitinh"));
				u.setNgaysinh(rs.getString("ngaysinh"));
				u.setQuyen(rs.getInt("quyen"));
				u.setSodienthoai(rs.getString("sodienthoai"));
				u.setTen(rs.getString("ten"));
				con.close();
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//them user
	public boolean insertUser_info(User_info u)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "insert into user_info values(?,?,?,?,?,?,NULL,NULL,2)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, u.getId());
			ps.setString(2, u.getTen());
			ps.setString(3, u.getSodienthoai());
			ps.setInt(4,u.getGioitinh());
			ps.setString(5, u.getNgaysinh());
			ps.setString(6, u.getEmail());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateUser_info(User_info u)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "update user_info set ten=?,sodienthoai=?,gioitinh=?,ngaysinh=?,email=?,anhdaidien=?,diachi=? where id=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setString(1, u.getTen());
			ps.setString(2, u.getSodienthoai());
			ps.setInt(3,u.getGioitinh());
			ps.setString(4, u.getNgaysinh());
			ps.setString(5, u.getEmail());
			ps.setString(6, u.getAnhdaidien());
			ps.setString(7, u.getDiachi());
			ps.setLong(8, u.getId());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public User_info getUser_info(long user_id)
	{
		try {
        	Connection conn = DBConnect.getConnecttion();
            String sql = "select * from user_info where id='"+user_id+"'";
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
            	u.setAnhdaidien(rs.getString(7));
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

	public List<User_info> getallteacher() {
        try {
        	Connection conn = DBConnect.getConnecttion();
            String sql = "select * from user_info where quyen=1";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
             
            List<User_info> listStudent = new ArrayList<User_info>();
            while (rs.next()) {
            	User_info u = new User_info();
            	u.setId(rs.getLong(1));
            	u.setTen(rs.getString(2));
            	u.setSodienthoai(rs.getString(3));
            	u.setGioitinh(rs.getInt(4));
            	u.setNgaysinh(rs.getString(5));
            	u.setEmail(rs.getString(6));
            	u.setAnhdaidien(rs.getString(7));
            	u.setDiachi(rs.getString(8));
            	u.setQuyen(rs.getInt(9));
            	listStudent.add(u);
            }
            conn.close();
            return listStudent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	public List<User_info> getallstudent() {
        try {
        	Connection conn = DBConnect.getConnecttion();
            String sql = "select * from user_info where quyen=2";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
             
            List<User_info> listStudent = new ArrayList<User_info>();
            while (rs.next()) {
            	User_info u = new User_info();
            	u.setId(rs.getLong(1));
            	u.setTen(rs.getString(2));
            	u.setSodienthoai(rs.getString(3));
            	u.setGioitinh(rs.getInt(4));
            	u.setNgaysinh(rs.getString(5));
            	u.setEmail(rs.getString(6));
            	u.setAnhdaidien(rs.getString(7));
            	u.setDiachi(rs.getString(8));
            	u.setQuyen(rs.getInt(9));
            	listStudent.add(u);
            }
            conn.close();
            return listStudent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	public boolean deleteUser_info(long userID)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "DELETE FROM user WHERE user_id ='"+userID+"'";
		String sql2 = "DELETE FROM user_info WHERE id='"+userID+"'";
		PreparedStatement ps;
		PreparedStatement ps2;
		try{
			ps = (PreparedStatement) con.prepareCall(sql);
			ps2 = (PreparedStatement) con.prepareCall(sql2);
			
			ps.executeUpdate();
			ps2.executeUpdate();
			
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<Infotaikhoan> getalltaikhoan() {
        try {
        	Connection conn = DBConnect.getConnecttion();
            String sql = "select * from user,user_info where user.user_id = user_info.id";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
             
            List<Infotaikhoan> listTaikhoan = new ArrayList<Infotaikhoan>();
            while (rs.next()) {
            	Infotaikhoan u = new Infotaikhoan();
            	
            	u.setUserName(rs.getString(1));
            	u.setUserPass(rs.getString(2));
            	u.setUserEmail(rs.getString(3));
            	u.setUserID(rs.getLong(4));
            	u.setUserdiachi(rs.getString("diachi"));
            	u.setUserten(rs.getString("ten"));
            	u.setUsergioitinh(rs.getInt("gioitinh"));
            	u.setUsersodienthoai(rs.getString("sodienthoai"));
            	u.setUserngaysinh(rs.getString("ngaysinh"));
            	u.setUserquyen(rs.getInt("quyen"));
            	listTaikhoan.add(u);
            }
            conn.close();
            return listTaikhoan;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
