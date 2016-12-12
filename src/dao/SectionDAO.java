package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.DBConnect;
import model.Resources;
import model.Section;
import model.Url;

public class SectionDAO {
    private PreparedStatement ps;
    private ResultSet rs;
	public List<Section> getListSection(long section_id) {
        try {
        	Connection conn = DBConnect.getConnecttion();
            String sql = "select * from section where course_id ='"+section_id+"'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
             
            List<Section> listSection = new ArrayList<Section>();
            while (rs.next()) {
            	Section s = new Section();
            	s.setSection_id(rs.getLong("section_id"));
            	s.setSection_name(rs.getString("section_name"));
            	s.setSection_content(rs.getString("section_content"));
            	s.setCourse_id(rs.getLong("course_id"));
            	listSection.add(s);
            }
            conn.close();
            return listSection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	public Section getSection(long section_id) {
        try {
        	Connection conn = DBConnect.getConnecttion();
            String sql = "select * from section where section_id ='"+section_id+"'";
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
	
	//them
	public boolean insert(Section s)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "insert into section values(?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, s.getSection_id());
			ps.setString(2, s.getSection_name());
			ps.setString(3, s.getSection_content());
			ps.setLong(4, s.getCourse_id());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(Section s)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "update section set section_name=?,section_content=? where section_id=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			
			ps.setString(1, s.getSection_name());
			ps.setString(2, s.getSection_content());
			ps.setLong(3, s.getSection_id());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(long section_id)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "delete from section where section_id=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, section_id);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean insertresources(Resources src)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "insert into resources values(?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, src.getResources_id());
			ps.setString(2, src.getResources_name());
			ps.setString(3, src.getResources_type());
			ps.setLong(4, src.getSection_id());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteresources(long id)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "DELETE from resources where resources_id=?";
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
	
	public boolean deleteUrl(long id )
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "delete from url where url_id=?";
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
	public boolean insertUrl(Url u)
	{
		Connection con = DBConnect.getConnecttion();
		String sql = "insert into url values(?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, u.getUrl_id());
			ps.setString(2, u.getUrl_name());
			ps.setLong(3, u.getSection_id());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<Url> getListUrl(long section_id) {
        try {
        	Connection conn = DBConnect.getConnecttion();
            String sql = "select * from Url where section_id ='"+section_id+"'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
             
            List<Url> listSection = new ArrayList<Url>();
            while (rs.next()) {
            	Url u = new Url();
            	u.setUrl_id(rs.getLong(1));
            	u.setUrl_name(rs.getString(2));
            	u.setSection_id(rs.getLong(3));
            	listSection.add(u);
            }
            conn.close();
            return listSection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	public static void main(String[] args) {
		/*// TODO Auto-generated method stub
		Long c_id = Long.parseLong("1479799303858");
		SectionDAO dao = new SectionDAO();
		for (Section s : dao.getListSection(c_id)) {
			System.out.println(s.getCourse_id() +"-"+s.getSection_name());
		}*/
		Resources s = new Resources();
		s.setResources_id(Long.parseLong("231313"));
		s.setResources_name("asdacdgfdgsd");
		s.setResources_type(".docx");
		s.setSection_id(Long.parseLong("2323"));
		SectionDAO sd = new SectionDAO();
		boolean f = sd.insertresources(s);
		System.out.println(f);
	}

}
