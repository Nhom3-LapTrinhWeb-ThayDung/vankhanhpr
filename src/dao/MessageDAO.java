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
import model.Message;
import model.User_info;

public class MessageDAO {
	private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MessageDAO msDAO = new MessageDAO();
		/*Message m = new Message();
		m.setId_message(new Date().getTime());
		m.setDaxem(false);
		m.setNoidung("wrgytuykgk,");
		m.setId_nguoigui(Long.parseLong("2"));
		m.setId_nguoinhan(Long.parseLong("1"));
		m.setThoigian(new Timestamp(new Date().getTime()));
		boolean f=msDAO.insert(m);*/
		List<Message> list =  msDAO.getListReceiveMessage(Long.parseLong("1"));
		for(Message x : list)
			System.out.println(x.getId_message()+x.getId_nguoigui()+x.getId_nguoinhan()+x.getNoidung()+x.getThoigian()+x.isDaxem());
	}
	public boolean insert(Message m)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "insert into message values(?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, m.getId_message());
			ps.setString(2, m.getNoidung());
			ps.setLong(3, m.getId_nguoigui());
			ps.setLong(4, m.getId_nguoinhan());
			ps.setTimestamp(5, m.getThoigian());
			ps.setBoolean(6, m.isDaxem());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Message> getListReceiveMessage(long user_id) {
        try {
        	Connection conn = DBConnect.getConnecttion();
            String sql = "select * from message where id_NguoiNhan='"+user_id+"'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
             
            List<Message> listMessage = new ArrayList<Message>();
            while (rs.next()) {
            	Message m = new Message();
            	m.setId_message(rs.getLong("id_message"));
            	m.setNoidung(rs.getString("NoiDung"));
            	m.setId_nguoigui(rs.getLong("id_NguoiGui"));
            	m.setId_nguoinhan(rs.getLong("id_NguoiNhan"));
            	m.setThoigian(rs.getTimestamp("ThoiGian"));
            	m.setDaxem(rs.getBoolean("DaXem"));
            	listMessage.add(m);
            }
            conn.close();
            return listMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
}
