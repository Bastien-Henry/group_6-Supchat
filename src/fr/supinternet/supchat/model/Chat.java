package fr.supinternet.supchat.model;

import java.io.Serializable;


public class Chat implements Serializable{

	private static final long serialVersionUID = -5777594064483617719L;
	
	private Long chatID;
	
	private String chatName;
	
	private Long chatCreationDate;
	
	public Long getChatID() {
		return chatID;
	}

	public void setChatID(Long chatID) {
		this.chatID = chatID;
	}

	public String getChatName() {
		return chatName;
	}

	public void setChatName(String chatName) {
		this.chatName = chatName;
	}

	public Long getChatCreationDate() {
		return chatCreationDate;
	}

	public void setChatCreationDate(Long chatCreationDate) {
		this.chatCreationDate = chatCreationDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Chat [chatID=");
		builder.append(chatID);
		builder.append(", chatName=");
		builder.append(chatName);
		builder.append(", chatCreationDate=");
		builder.append(chatCreationDate);
		builder.append("]");
		return builder.toString();
	}
	
}