package fr.supinternet.supchat.model;


public class MessageData extends TokenData{
	
	private Message message;

	public MessageData() {
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}