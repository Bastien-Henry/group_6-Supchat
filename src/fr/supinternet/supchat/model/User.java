package fr.supinternet.supchat.model;

public class User {
	
	private Long userID;
	
	private String userPseudo;
	
	private String userHash;
	
	private Long userCreationDate;

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
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

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userPseudo=" + userPseudo
				+ ", userHash=" + userHash + ", userCreationDate="
				+ userCreationDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((userPseudo == null) ? 0 : userPseudo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userPseudo == null) {
			if (other.userPseudo != null)
				return false;
		} else if (!userPseudo.equals(other.userPseudo))
			return false;
		return true;
	}
	
}