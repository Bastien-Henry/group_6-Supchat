package fr.supinternet.supchat.manager;

import fr.supinternet.supchat.util.PrefsUtils;
import android.content.Context;

public class AuthenticationManager {

//	private static final String TAG = "AuthenticationManager";

	/**
	 * The unique instance of the manager.
	 */
	private static AuthenticationManager SINGLETON = null;

	/**
	 * The lock for thread safety.
	 */
	private static final Object __synchronizedObject = new Object();

	private Context context;

	public static AuthenticationManager getInstance(Context context) {

		if (SINGLETON == null) {
			synchronized (__synchronizedObject) {
				if (SINGLETON == null) {
					SINGLETON = new AuthenticationManager(context);
				}
			}
		}
		return SINGLETON;
	}

	private AuthenticationManager(Context context) {
		this.context = context;
	}
	
	public void setToken(String token){
		PrefsUtils.setString(context, PrefsUtils.TOKEN_S, token);
	}
	
	public String getToken(){
		return PrefsUtils.getString(context, PrefsUtils.TOKEN_S, "");
	}
	
	public void setPseudo(String pseudo){
		PrefsUtils.setString(context, PrefsUtils.PSEUDO_S, pseudo);
	}
	
	public String getPseudo(){
		return PrefsUtils.getString(context, PrefsUtils.PSEUDO_S, "");
	}
	
	public void setHash(String hash){
		PrefsUtils.setString(context, PrefsUtils.PWORD_S, hash);
	}
	
	public String getHash(){
		return PrefsUtils.getString(context, PrefsUtils.PWORD_S, "");
	}
	
	public boolean hasCredentials(){
		if (getHash() == null || getHash().length() == 0){
			return false;
		}
		if (getPseudo() == null || getPseudo().length() == 0){
			return false;
		}
		return true;
	}

}