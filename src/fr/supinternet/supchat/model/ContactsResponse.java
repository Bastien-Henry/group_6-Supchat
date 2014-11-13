package fr.supinternet.supchat.model;

import java.util.ArrayList;

public class ContactsResponse extends Response{
	
	public ArrayList<User> users;

	public ContactsResponse() {
	}

	public ContactsResponse(String status, ResponseCode code) {
		super(status, code);
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	@Override
	public String toString() {
		return "ContactsResponse [users=" + users + "]";
	}

}