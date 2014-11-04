package fr.supinternet.supchat.model;

public class User {
	
	private Long UserID;
	
	private String userPseudo;
	
	private String userHash;
	
	private Long userCreationDate;

	@Override
	public String toString() {
		return "User [UserID=" + UserID + ", userPseudo=" + userPseudo
				+ ", userHash=" + userHash + ", userCreationDate="
				+ userCreationDate + ", getUserID()=" + getUserID()
				+ ", getUserPseudo()=" + getUserPseudo() + ", getUserHash()="
				+ getUserHash() + ", getUserCreationDate()="
				+ getUserCreationDate() + "]";
	}

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public String getUserPseudo() {
		return userPseudo;
	}

	public void setUserPseudo(String userPseudo) {
		this.userPseudo = userPseudo;
	}

	public String getUserHash() {
		return userHash;
	}

	public void setUserHash(String userHash) {
		this.userHash = userHash;
	}

	public Long getUserCreationDate() {
		return userCreationDate;
	}

	public void setUserCreationDate(Long userCreationDate) {
		this.userCreationDate = userCreationDate;
	}
}
