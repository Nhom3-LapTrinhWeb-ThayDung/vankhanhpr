package model;

import java.sql.Timestamp;

public class CourseWaiting {

	private long course_waiting_id;
	private long user_id;
	private long course_id;
	private Timestamp time_register;
	
	
	public CourseWaiting(long course_waiting_id, long user_id, long course_id, Timestamp time_register) {
		super();
		this.course_waiting_id = course_waiting_id;
		this.user_id = user_id;
		this.course_id = course_id;
		this.time_register = time_register;
	}


	public CourseWaiting() {
		super();
		// TODO Auto-generated constructor stub
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	public long getCourse_waiting_id() {
		return course_waiting_id;
	}


	public void setCourse_waiting_id(long course_waiting_id) {
		this.course_waiting_id = course_waiting_id;
	}


	public long getUser_id() {
		return user_id;
	}


	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}


	public long getCourse_id() {
		return course_id;
	}


	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}


	public Timestamp getTime_register() {
		return time_register;
	}


	public void setTime_register(Timestamp time_register) {
		this.time_register = time_register;
	}

}
