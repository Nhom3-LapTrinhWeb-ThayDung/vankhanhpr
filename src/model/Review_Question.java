package model;

public class Review_Question {
	private int number;
	private String question;
	public Review_Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Review_Question(int number, String question) {
		super();
		this.number = number;
		this.question = question;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	
}
