package fr.supinternet.supchat.model;

import java.util.List;

public class ChatData extends TokenData {

	private Chat chat;
	
	private List<User> users;

	public ChatData() {
		super();
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TokenChat [chat=");
		builder.append(chat);
		builder.append("]");
		return builder.toString();
	}
}