package fr.supinternet.supchat.model;

public class TokenResponse extends Response{
	
	public String token;

	public TokenResponse() {
	}

	public TokenResponse(String status, ResponseCode code) {
		super(status, code);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "TokenResponse [token=" + token + "]";
	}
}