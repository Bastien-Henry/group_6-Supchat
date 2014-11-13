package fr.supinternet.supchat.request;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import fr.supinternet.supchat.factory.json.UserJsonFactory;
import fr.supinternet.supchat.model.User;

public class LoginRequest extends AbstractRequest{

	private static final String LOGIN = "login";

	public LoginRequest(Context context, int method, User data, Listener<JSONObject> listener, ErrorListener errorListener) throws JSONException {
		super(context, method, constructUrl(), constructJSONObject(data), listener, errorListener);
	}

	public LoginRequest(Context context, User data, Listener<JSONObject> listener, ErrorListener errorListener) throws JSONException {
		super(context,constructUrl(), constructJSONObject(data), listener, errorListener);
	}

	private static String constructUrl(){
		return SERVER_URL + LOGIN;
	}

	private static JSONObject constructJSONObject(User data) throws JSONException{
		JSONObject json = UserJsonFactory.getJSONObject(data);
		return json;
	}


}