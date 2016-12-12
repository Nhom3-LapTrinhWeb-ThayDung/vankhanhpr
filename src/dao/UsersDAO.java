package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import connect.DBConnect;
import model.User_info;
import model.Users;

public class UsersDAO {
	private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
	public boolean checkEmail(String email) {
		Connection conn = DBConnect.getConnecttion();
		String sql = "select * from user where user_email='" + email + "'";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				conn.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean checkUsername(String username) {
		Connection conn = DBConnect.getConnecttion();
		String sql = "select * from user where user_name='" + username + "'";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				conn.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	// kiểm tra đăng nhập
	public Users login(String username, String password) {
		Connection con = DBConnect.getConnecttion();
		String sql = "select * from user where user_name='" + username + "' and user_pass='" + password + "'";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Users u = new Users();
				u.setUserName(rs.getString("user_name"));
				u.setUserPass(rs.getString("user_pass"));
				u.setUserEmail(rs.getString("user_email"));
				u.setUserID(rs.getLong("user_id"));
				con.close();
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// phương thức thêm tài khoản
	public boolean insertUser(Users u)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "insert into user values(?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setString(1, u.getUserName());
			ps.setString(2, u.getUserPass());
			ps.setString(3, u.getUserEmail());
			ps.setLong(4, u.getUserID());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean doimk(String user,String pass)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "update user set user_pass = '"+pass+"' where user_name ='"+user+"'";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
