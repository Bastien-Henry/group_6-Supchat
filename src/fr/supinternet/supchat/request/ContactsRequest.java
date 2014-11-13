package fr.supinternet.supchat.request;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import fr.supinternet.supchat.factory.json.TokenJsonFactory;
import fr.supinternet.supchat.factory.json.UserJsonFactory;
import fr.supinternet.supchat.model.Token;
import fr.supinternet.supchat.model.User;

public class ContactsRequest extends AbstractRequest{

	private static final String LOGIN = "list_users";

	public ContactsRequest(Context context, int method, Token data, Listener<JSONObject> listener, ErrorListener errorListener) throws JSONException {
		super(context, method, constructUrl(), constructJSONObject(data), listener, errorListener);
	}

	public ContactsRequest(Context context, Token data, Listener<JSONObject> listener, ErrorListener errorListener) throws JSONException {
		super(context,constructUrl(), constructJSONObject(data), listener, errorListener);
	}

	private static String constructUrl(){
		return SERVER_URL + LOGIN;
	}

	private static JSONObject constructJSONObject(Token data) throws JSONException{
		JSONObject json = TokenJsonFactory.getJSONObject(data);
		return json;
	}


}