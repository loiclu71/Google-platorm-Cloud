package model;

public class Messages {
	
	int id;
	String name;
	String textmessage;
	
	public Messages(int id, String name, String textmessage) {
		this.id=id;
		this.name=name;
		this.textmessage=textmessage;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public String getTextmessage() {
		return textmessage;
	}
	public void setTextMessage(String textmessage) {
		this.textmessage=textmessage;
	}
}
