package model;

import org.json.simple.JSONAware;

public class Resources implements JSONAware {
	private long resources_id;
	private String resources_name;
	private String resources_type;
	private long section_id;
	public Resources() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Resources(long resources_id, String resources_name, String resources_type, long section_id) {
		super();
		this.resources_id = resources_id;
		this.resources_name = resources_name;
		this.resources_type = resources_type;
		this.section_id = section_id;
	}
	public long getResources_id() {
		return resources_id;
	}
	public void setResources_id(long resources_id) {
		this.resources_id = resources_id;
	}
	public String getResources_name() {
		return resources_name;
	}
	public void setResources_name(String resources_name) {
		this.resources_name = resources_name;
	}
	public String getResources_type() {
		return resources_type;
	}
	public void setResources_type(String resources_style) {
		this.resources_type = resources_style;
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
 
        sb.append("\"resources_id\":\"" + getResources_id() + "\""); // dòng này có nghĩa là
                                                    // "id":"Giá_Trị"
        sb.append(","); // sau mỗi cặp key/value là một dấu phẩy
 
        sb.append("\"resources_name\":\"" + getResources_name() + "\"");
        sb.append(",");
        sb.append("\"resources_type\":\"" + getResources_type() + "\"");
        sb.append(",");

        sb.append("\"section_id\":\"" + getSection_id() + "\"");
 
        sb.append("}"); // Kết thúc một đối tượng JSON là dấu đóng ngoặc nhọn
          
        return sb.toString();
    }
	
}
