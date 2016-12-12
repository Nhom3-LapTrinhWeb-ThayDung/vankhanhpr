package model;

public class Outline {
	private String outline;
	private long course_id;
	private String type;
	public Outline() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Outline(String outline, long course_id, String type) {
		super();
		this.outline = outline;
		this.course_id = course_id;
		this.type = type;
	}
	public String getOutline() {
		return outline;
	}
	public void setOutline(String outline) {
		this.outline = outline;
	}
	public long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
