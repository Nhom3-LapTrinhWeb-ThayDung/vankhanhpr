package model;

public class Infotaikhoan {
	private long userID;
    private String userName;
    private String userEmail;
    private String userPass;
    private String userten;
    private String usersodienthoai;
    private int    usergioitinh;
    private String userngaysinh;
    private String userdiachi;
    private int    userquyen;
    private String useranhdaidien;
	public Infotaikhoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Infotaikhoan(long userID, String userName, String userEmail, String userPass, String userten,
			String usersodienthoai, int usergioitinh, String userngaysinh, String userdiachi, int userquyen,String useranhdaidien) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPass = userPass;
		this.userten = userten;
		this.usersodienthoai = usersodienthoai;
		this.usergioitinh = usergioitinh;
		this.userngaysinh = userngaysinh;
		this.userdiachi = userdiachi;
		this.userquyen = userquyen;
		this.useranhdaidien=useranhdaidien;
	}
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserten() {
		return userten;
	}
	public void setUserten(String userten) {
		this.userten = userten;
	}
	public String getUsersodienthoai() {
		return usersodienthoai;
	}
	public void setUsersodienthoai(String usersodienthoai) {
		this.usersodienthoai = usersodienthoai;
	}
	public int getUsergioitinh() {
		return usergioitinh;
	}
	public void setUsergioitinh(int usergioitinh) {
		this.usergioitinh = usergioitinh;
	}
	public String getUserngaysinh() {
		return userngaysinh;
	}
	public void setUserngaysinh(String userngaysinh) {
		this.userngaysinh = userngaysinh;
	}
	public String getUserdiachi() {
		return userdiachi;
	}
	public void setUserdiachi(String userdiachi) {
		this.userdiachi = userdiachi;
	}
	public int getUserquyen() {
		return userquyen;
	}
	public void setUserquyen(int userquyen) {
		this.userquyen = userquyen;
	}
	
	public String getAnhdaidien() {
		return useranhdaidien;
	}
	public void setAnhdaidien(String useranhdaidien) {
		this.useranhdaidien = useranhdaidien;
	}
	
	
	
	
}
