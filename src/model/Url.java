package model;

public class Url {
	long url_id;
	String url_name;
	long section_id;
	public Url() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Url(long url_id, String url_name, long section_id) {
		super();
		this.url_id = url_id;
		this.url_name = url_name;
		this.section_id = section_id;
	}
	public long getUrl_id() {
		return url_id;
	}
	public void setUrl_id(long url_id) {
		this.url_id = url_id;
	}
	public String getUrl_name() {
		return url_name;
	}
	public void setUrl_name(String url_name) {
		this.url_name = url_name;
	}
	public long getSection_id() {
		return section_id;
	}
	public void setSection_id(long section_id) {
		this.section_id = section_id;
	}
	
}
