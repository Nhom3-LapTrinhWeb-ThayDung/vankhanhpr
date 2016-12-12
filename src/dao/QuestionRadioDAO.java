package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import connect.DBConnect;
import model.QuestionQuiz;
import model.Quiz;
import model.QuizResult;
import model.Users;

public class QuestionRadioDAO {
	private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    // Láº¥y ra danh sĂ¡ch cĂ¢u há»�i
    public List<QuestionQuiz> getListQuestionRadios(long quiz_id) {
        try {
            conn = DBConnect.getConnecttion();
            String sql = "SELECT * FROM quiz_question where quiz_id='"+quiz_id+"'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
             
            List<QuestionQuiz> listQuestionRadios = new ArrayList<QuestionQuiz>();
            while (rs.next()) {
            	QuestionQuiz qq = new QuestionQuiz();
                qq.setId(rs.getLong("id"));
                qq.setNumber(rs.getInt("number"));
                qq.setQuestion(rs.getString("question"));
                qq.setOption1(rs.getString("option1"));
                qq.setOption2(rs.getString("option2"));
                qq.setOption3(rs.getString("option3"));
                qq.setOption4(rs.getString("option4"));
                qq.setAnswer(rs.getString("answer"));
                qq.setQuiz_id(rs.getLong("quiz_id"));
                listQuestionRadios.add(qq);
            }
             
            return listQuestionRadios;
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
        return null;
    }
    
    // Tráº£ vá»� tá»•ng sá»‘ cĂ¢u há»�i cĂ³ trong báº£ng
    public int getCountRow(long quiz_id) {
        int countRow = 0;
        try {
            conn = DBConnect.getConnecttion();
            String sql = "select count from quiz where id = '"+quiz_id+"'";
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
    
    //Láº¥y quiz
    public Quiz getQuiz(long quiz_id) {
		Connection con = DBConnect.getConnecttion();
		String sql = "select * from Quiz where id = '"+quiz_id+"'";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Quiz q = new Quiz();
				q.setId(rs.getLong("id"));
				q.setQuiz_name(rs.getString("quiz_name"));
				q.setStart_date(rs.getString("start_date"));
				q.setEnd_date(rs.getString("end_date"));
				q.setTime(rs.getString("time"));
				q.setCount(rs.getInt("count"));
				q.setDescription(rs.getString("description"));
				q.setSection_id(rs.getLong("section_id"));
				con.close();
				return q;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
    
    //láº¥y ds bĂ i quiz
    public List<Quiz> getListQuiz(long section_id) {
        try {
            conn = DBConnect.getConnecttion();
            String sql = "SELECT * FROM quiz where section_id='"+section_id+"'";
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
  //thĂªm bĂ i thi
    public boolean insertQuiz(Quiz q)
    {
    	Connection con = DBConnect.getConnecttion();
		String sql = 
				"insert into quiz values(?, ?, ?, ?, ?, ?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, q.getId());
			ps.setString(2, q.getQuiz_name());
			ps.setString(3, q.getStart_date());
			ps.setString(4, q.getEnd_date());
			ps.setString(5, q.getTime());
			ps.setInt(6, q.getCount());
			ps.setString(7, q.getDescription());
			ps.setLong(8, q.getSection_id());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }
    public boolean update(Quiz q)
    {
    	Connection con = DBConnect.getConnecttion();
		String sql = 
				"update quiz set quiz_name=?,start_date=?,end_date=?,time=?,count=?,description=?,section_id=? where id=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(8, q.getId());
			ps.setString(1, q.getQuiz_name());
			ps.setString(2, q.getStart_date());
			ps.setString(3, q.getEnd_date());
			ps.setString(4, q.getTime());
			ps.setInt(5, q.getCount());
			ps.setString(6, q.getDescription());
			ps.setLong(7, q.getSection_id());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }
    
    public boolean deleteQuiz(long quiz_id)
    {
    	Connection con = DBConnect.getConnecttion();
		String sql = "delete from quiz where id=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, quiz_id);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }

    //thĂªm cĂ¢u há»�i
    public boolean insertQuestion(QuestionQuiz q)
    {
    	Connection con = DBConnect.getConnecttion();
		String sql = 
				"insert into quiz_question  values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, q.getId());
			ps.setInt(2, q.getNumber());
			ps.setString(3, q.getQuestion());
			ps.setString(4, q.getOption1());
			ps.setString(5, q.getOption2());
			ps.setString(6, q.getOption3());
			ps.setString(7, q.getOption4());
			ps.setString(8, q.getAnswer());
			ps.setLong(9, q.getQuiz_id());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }
    public boolean updateQuestion(QuestionQuiz q)
    {
    	Connection con = DBConnect.getConnecttion();
		String sql = 
				"update quiz_question set question=?,option1=?,option2=?,option3=?,option4=?,answer=? where id=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(7, q.getId());
			ps.setInt(1, q.getNumber());
			ps.setString(1, q.getQuestion());
			ps.setString(2, q.getOption1());
			ps.setString(3, q.getOption2());
			ps.setString(4, q.getOption3());
			ps.setString(5, q.getOption4());
			ps.setString(6, q.getAnswer());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }
    public boolean deleteQuestion(long quiz_id)
    {
    	Connection con = DBConnect.getConnecttion();
		String sql = 
				"delete from quiz_question where quiz_id=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1, quiz_id);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }
    
    public boolean deleteQuizResult(long qr)
    {
    	Connection con = DBConnect.getConnecttion();
		String sql = "delete from quiz_result where quiz_id=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			ps.setLong(1,qr);
			
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }
    public boolean checkQuizResult(long quiz_id) {
		Connection conn = DBConnect.getConnecttion();
		String sql = "select * from quiz_result where quiz_id ='" + quiz_id + "'";
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
    public boolean insertQuizResult(QuizResult qr)
    {
    	Connection con = DBConnect.getConnecttion();
		String sql = "insert into quiz_result values("+ qr.getResult_id()+","+qr.getSocaudung()
		+","+qr.getTongsocau()+","+qr.getScores()+",'"+qr.getTimework()
		+"','"+qr.getTimesubmit()+"',"+qr.getQuiz_id()+","+qr.getUser_id()+")";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareCall(sql);
			/*ps.setLong(1, qr.getResult_id());
			ps.setInt(2, qr.getSocaudung());
			ps.setInt(3, qr.getTongsocau());
			ps.setDouble(4, qr.getScores());
			ps.setString(5, qr.getTimework());
			ps.setTimestamp(6, qr.getTimesubmit());
			ps.setLong(7, qr.getQuiz_id());
			ps.setLong(8, qr.getUser_id());*/
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }
    
    public QuizResult getQuizResult(long result_id) {
		Connection con = DBConnect.getConnecttion();
		String sql = "select * from quiz_result where result_id = '"+result_id+"'";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				QuizResult qr = new QuizResult();
				qr.setResult_id(rs.getLong(1));
				qr.setSocaudung(rs.getInt(2));
				qr.setTongsocau(rs.getInt(3));
    			qr.setScores(rs.getDouble(4));
    			qr.setTimework(rs.getString(5));
    			qr.setTimesubmit(rs.getTimestamp(6));
    			qr.setQuiz_id(rs.getLong(7));
    			qr.setUser_id(rs.getLong(8));
				con.close();
				return qr;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
    
    
    
    public static void main(String[] args)throws SQLException
    {
    	QuestionRadioDAO dao = new QuestionRadioDAO();
    	/*for (QuestionQuiz q : dao.getListQuestionRadios(Long.parseLong("1"))) {
    		System.out.println(q.getId() + "-" + q.getNumber()+ "-" + q.getQuestion()
    		 + "-" + q.getOption1()  + "-" + q.getOption2()
    		 + "-" + q.getOption3() + "-" + q.getOption4()
    		 + "-" + q.getAnswer());
    		 }*/
    	/*Quiz q = new Quiz();
    	q = dao.getQuiz(1);
    	System.out.println(q.getId());*/
    	
    	//boolean f = dao.insertQuizResult(new java.util.Date().getTime(), "3.5", "0:59:2" , new java.sql.Date(new java.util.Date().getDate()), Long.parseLong("213"), Long.parseLong("45345"));
    	//System.out.println(new java.sql.Date(new java.util.Date().getTime()));
    	//QuizResult qr = new QuizResult(new java.util.Date().getTime(), 3.5, "0:59:2" , new Timestamp(new java.util.Date().getTime()), Long.parseLong("213"), Long.parseLong("45345"));
    	//boolean f = dao.insertQuizResult(qr);
    	/*boolean f = dao.deleteQuiz(Long.parseLong("1479875617192"));
    	System.out.println(f);*/
    	
    	
    }
}
