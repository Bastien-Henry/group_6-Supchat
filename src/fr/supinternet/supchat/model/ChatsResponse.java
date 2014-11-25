package fr.supinternet.supchat.model;

import java.util.ArrayList;

public class ChatsResponse extends Response{
	
	public ArrayList<Chat> chats;

	public ChatsResponse() {
	}

	public ChatsResponse(String status, ResponseCode code) {
		super(status, code);
	}

	public ArrayList<Chat> getChats() {
		return chats;
	}

	public void setChats(ArrayList<Chat> chats) {
		this.chats = chats;
	}

}