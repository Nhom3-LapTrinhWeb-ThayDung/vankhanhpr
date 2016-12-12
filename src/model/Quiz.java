package model;

import java.sql.Date;
import java.sql.Time;

import org.json.simple.JSONAware;

public class Quiz implements JSONAware {
	private long id;
	private String quiz_name;
	private String start_date;
	private String end_date;
	private String time;
	private int count;
	private String description;
	private long section_id;
	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Quiz(long id, String quiz_name, String start_date, String end_date, String time, int count, String description,long section_id) {
		super();
		this.id = id;
		this.quiz_name = quiz_name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.time = time;
		this.count = count;
		this.description = description;
		this.section_id=section_id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getQuiz_name() {
		return quiz_name;
	}
	public void setQuiz_name(String quiz_name) {
		this.quiz_name = quiz_name;
	}
	
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getSection_id() {
		return section_id;
	}
	public void setSection_id(long section_id) {
		this.section_id = section_id;
	}
	@Override
    public String toJSONString() {
 
        StringBuffer sb = new StringBuffer();
        sb.append("{"); // Bắt đầu một đối tượng JSON là dấu mở ngoặc nhọn
 
        sb.append("\"id\":\"" + getId()+ "\""); // dòng này có nghĩa là
                                                    // "id":"Giá_Trị"
        sb.append(","); // sau mỗi cặp key/value là một dấu phẩy
 
        sb.append("\"quiz_name\":\"" + getQuiz_name() + "\"");
        sb.append(",");
        sb.append("\"start_date\":\"" + getStart_date() + "\"");
        sb.append(",");
        sb.append("\"end_date\":\"" + getEnd_date()+ "\"");
        sb.append(",");
        sb.append("\"time\":\"" + getTime() + "\"");
        sb.append(",");
        sb.append("\"count\":\"" + getCount() + "\"");
        sb.append(",");
        sb.append("\"description\":\"" + getDescription() + "\"");
        sb.append(",");
        sb.append("\"section_id\":\"" + getSection_id() + "\"");
 
        sb.append("}"); // Kết thúc một đối tượng JSON là dấu đóng ngoặc nhọn
          
        return sb.toString();
    }
	
}
