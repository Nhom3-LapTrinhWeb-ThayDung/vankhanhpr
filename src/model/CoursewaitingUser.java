package model;

import java.sql.Timestamp;

public class CoursewaitingUser {
	private long course_waiting_id;//Mã khóa học chờ
	private long user_id; //Id của user đang ký khóa học chờ
	private long course_id; // ID  của khóa học
	private String course_name; //Tên khóa học
	private String course_startdate; // Thời gian bắt đầu
	private String course_enddate; // Thời gian kết thúc
	private String course_place;  // Địa chỉ lớp học
	private String ten; // Tên học viên
	private String sodienthoai;//Số điện thoại
	public CoursewaitingUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CoursewaitingUser(long course_waiting_id,long user_id,long course_id,String course_name,String course_startdate,String course_enddate,String course_place,String ten,String sodienthoai) {
		super();
		this.course_waiting_id = course_waiting_id;
		this.user_id = user_id;
		this.course_id = course_id;
		this.course_name = course_name;
		this.course_startdate = course_startdate;
		this.course_enddate = course_enddate;
		this.course_place = course_place;
		this.ten = ten;
		this.sodienthoai = sodienthoai;
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
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_startdate() {
		return course_startdate;
	}
	public void setCourse_startdate(String course_startdate) {
		this.course_startdate = course_startdate;
	}
	public String getCourse_enddate() {
		return course_enddate;
	}
	public void setCourse_enddate(String course_enddate) {
		this.course_enddate = course_enddate;
	}
	public String getCourse_place() {
		return course_place;
	}
	public void setCourse_place(String course_place) {
		this.course_place = course_place;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getSodienthoai() {
		return sodienthoai;
	}
	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}

}
