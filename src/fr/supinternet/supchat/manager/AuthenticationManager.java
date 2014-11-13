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

}