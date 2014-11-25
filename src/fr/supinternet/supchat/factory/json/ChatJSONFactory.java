package fr.supinternet.supchat.factory.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import fr.supinternet.supchat.model.Chat;

public class ChatJSONFactory {
	
	private static final String TAG = "ChatJsonFactory";
	
	public static JSONObject getJSONObject(Chat c) throws JSONException{
		
		if (c == null){
			Log.e(TAG, "Unable to create JSONObject from Chat caused by Chat null");
			return null;
		}
		
		JSONObject result = new JSONObject();
		result.accumulate("chatID", c.getChatID());
		result.accumulate("chatName", c.getChatName());
		result.accumulate("chatCreationDate", c.getChatCreationDate());
		return result;
	}
	
	public static JSONArray getJSONArray(ArrayList<Chat> chats) throws JSONException {
		if (chats == null){
			Log.e(TAG, "Unable to create JSONArray from Chat list caused by Chat list null");
			return null;
		}
		JSONArray result = new JSONArray();
		for (Chat c : chats){
			result.put(ChatJSONFactory.getJSONObject(c));
		}
		return result;
	}
	
	public static Chat parseFromJSONObject(JSONObject json) throws JSONException{
		
		if (json == null){
			Log.e(TAG, "Unable to create Chat from Json caused by json null");
			return null;
		}
		
		Chat result = new Chat();
		
		result.setChatID(json.getLong("chatID"));
		result.setChatName(json.getString("chatName"));
		result.setChatCreationDate(json.getLong("chatCreationDate"));
		
		return result;
	}
	
	public static ArrayList<Chat> parseFromJSONArray(JSONArray array) throws JSONException{

		if (array == null){
			Log.e(TAG, "Unable to create Chat List from Json caused by json null");
			return null;
		}
		
		ArrayList<Chat> result = new ArrayList<Chat>();
		int length = array.length();
		for (int i = 0 ; i < length ; i++){
			result.add(ChatJSONFactory.parseFromJSONObject(array.getJSONObject(i)));
		}
		return  result;
		
	}

}