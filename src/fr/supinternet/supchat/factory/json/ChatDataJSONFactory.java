package fr.supinternet.supchat.factory.json;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import fr.supinternet.supchat.model.ChatData;
import fr.supinternet.supchat.model.User;

public class ChatDataJSONFactory {
	
private static final String TAG = "ChatRequestFactory";
	
	public static JSONObject getJSONObject(ChatData t) throws JSONException{
		
		if (t == null){
			Log.e(TAG, "Unable to create JSONObject from ChatRequest caused by User null");
			return null;
		}
		
		JSONObject result = new JSONObject();
		result.accumulate("chat", ChatJSONFactory.getJSONObject(t.getChat()));
		result.accumulate("users", UserJSONFactory.getJSONArray((ArrayList<User>) t.getUsers()));
		result.accumulate("token", t.getToken());
		return result;
	}
	
}