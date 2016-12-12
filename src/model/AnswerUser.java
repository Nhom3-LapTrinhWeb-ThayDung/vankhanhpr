package model;

public class AnswerUser {
	private int number;
    private String answer;
	public AnswerUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AnswerUser(int number, String answer) {
		super();
		this.number = number;
		this.answer = answer;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
    
}
