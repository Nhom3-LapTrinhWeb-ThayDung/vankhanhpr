package model;

public class Section {
	private long section_id;
	private String section_name;
	private String section_content;
	private long course_id;
	public Section() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Section(long section_id, String section_name, String section_content, long course_id) {
		super();
		this.section_id = section_id;
		this.section_name = section_name;
		this.section_content = section_content;
		this.course_id = course_id;
	}
	public long getSection_id() {
		return section_id;
	}
	public void setSection_id(long section_id) {
		this.section_id = section_id;
	}
	public String getSection_name() {
		return section_name;
	}
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	public String getSection_content() {
		return section_content;
	}
	public void setSection_content(String section_content) {
		this.section_content = section_content;
	}
	public long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}
	
	
}
