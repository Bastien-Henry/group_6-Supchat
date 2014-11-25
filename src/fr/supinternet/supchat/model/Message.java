package fr.supinternet.supchat.model;

import java.io.Serializable;

/**
 * The persistent class for the users database table.
 * 
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 7155289756191166503L;

	private Long messageID;
	
	private String messageText;
	
	private Long messageDate;
	
	public Message() {
		super();
	}

	public Long getMessageID() {
		return messageID;
	}

	public void setMessageID(Long messageID) {
		this.messageID = messageID;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Long getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Long messageDate) {
		this.messageDate = messageDate;
	}

}