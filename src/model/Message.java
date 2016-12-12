package model;

import java.sql.Timestamp;

public class Message {
	private long id_message;
	private String noidung;
	private long id_nguoigui;
	private long id_nguoinhan;
	private Timestamp thoigian;
	private boolean daxem;
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public long getId_message() {
		return id_message;
	}



	public void setId_message(long id_message) {
		this.id_message = id_message;
	}



	public String getNoidung() {
		return noidung;
	}



	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}



	public long getId_nguoigui() {
		return id_nguoigui;
	}



	public void setId_nguoigui(long id_nguoigui) {
		this.id_nguoigui = id_nguoigui;
	}



	public long getId_nguoinhan() {
		return id_nguoinhan;
	}



	public void setId_nguoinhan(long id_nguoinhan) {
		this.id_nguoinhan = id_nguoinhan;
	}



	public Timestamp getThoigian() {
		return thoigian;
	}



	public void setThoigian(Timestamp thoigian) {
		this.thoigian = thoigian;
	}



	public boolean isDaxem() {
		return daxem;
	}



	public void setDaxem(boolean daxem) {
		this.daxem = daxem;
	}



	public Message(long id_message, String noidung, long id_nguoigui, long id_nguoinhan, Timestamp thoigian,
			boolean daxem) {
		super();
		this.id_message = id_message;
		this.noidung = noidung;
		this.id_nguoigui = id_nguoigui;
		this.id_nguoinhan = id_nguoinhan;
		this.thoigian = thoigian;
		this.daxem = daxem;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub	
	}

	

}
