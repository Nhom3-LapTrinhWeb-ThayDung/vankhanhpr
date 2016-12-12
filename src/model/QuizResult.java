package model;

import java.sql.Timestamp;

public class QuizResult {
	private long result_id;
	private double scores;
	private String timework;
	private Timestamp timesubmit;
	private long quiz_id;
	private long user_id;
	private int socaudung;
	private int tongsocau;
	public QuizResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuizResult(long result_id, double scores, String timework, Timestamp timesubmit, long quiz_id, long user_id,
			int socaudung, int tongsocau) {
		super();
		this.result_id = result_id;
		this.scores = scores;
		this.timework = timework;
		this.timesubmit = timesubmit;
		this.quiz_id = quiz_id;
		this.user_id = user_id;
		this.socaudung = socaudung;
		this.tongsocau = tongsocau;
	}
	public long getResult_id() {
		return result_id;
	}
	public void setResult_id(long result_id) {
		this.result_id = result_id;
	}
	public double getScores() {
		return scores;
	}
	public void setScores(double scores) {
		this.scores = scores;
	}
	public String getTimework() {
		return timework;
	}
	public void setTimework(String timework) {
		this.timework = timework;
	}
	public Timestamp getTimesubmit() {
		return timesubmit;
	}
	public void setTimesubmit(Timestamp timesubmit) {
		this.timesubmit = timesubmit;
	}
	public long getQuiz_id() {
		return quiz_id;
	}
	public void setQuiz_id(long quiz_id) {
		this.quiz_id = quiz_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public int getSocaudung() {
		return socaudung;
	}
	public void setSocaudung(int socaudung) {
		this.socaudung = socaudung;
	}
	public int getTongsocau() {
		return tongsocau;
	}
	public void setTongsocau(int tongsocau) {
		this.tongsocau = tongsocau;
	}
	
}
