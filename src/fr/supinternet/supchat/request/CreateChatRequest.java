package fr.supinternet.supchat.request;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import fr.supinternet.supchat.factory.json.ChatRequestFactory;
import fr.supinternet.supchat.manager.AuthenticationManager;
import fr.supinternet.supchat.model.ChatData;

public class CreateChatRequest extends AbstractRequest{

	private static final String CREATE_CHAT = "create_chat";

	public CreateChatRequest(Context context, int method, ChatData data, Listener<JSONObject> listener, ErrorListener errorListener) throws JSONException {
		super(context, method, constructUrl(), constructJSONObject(data), listener, errorListener);
	}

	public CreateChatRequest(Context context, ChatData data, Listener<JSONObject> listener, ErrorListener errorListener) throws JSONException {
		super(context,constructUrl(), constructJSONObject(data), listener, errorListener);
	}

	private static String constructUrl(){
		return SERVER_URL + CREATE_CHAT;
	}

	private static JSONObject constructJSONObject(ChatData data) throws JSONException{
		data.setToken(AuthenticationManager.getInstance(mContext).getToken());
		JSONObject json = ChatRequestFactory.getJSONObject(data);
		return json;
	}


}