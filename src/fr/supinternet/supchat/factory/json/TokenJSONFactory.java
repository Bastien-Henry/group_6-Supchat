package fr.supinternet.supchat.factory.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import fr.supinternet.supchat.model.Token;

public class TokenJSONFactory {
	
	private static final String TAG = "TokenJsonFactory";
	
	public static JSONObject getJSONObject(Token t) throws JSONException{
		
		if (t == null){
			Log.e(TAG, "Unable to create JSONObject from Token caused by User null");
			return null;
		}
		
		JSONObject result = new JSONObject();
		result.accumulate("tokenValue", t.getTokenValue());
		return result;
	}
	
	public static JSONArray getJSONArray(ArrayList<Token> tokens) throws JSONException {
		if (tokens == null){
			Log.e(TAG, "Unable to create JSONArray from Token list caused by Token list null");
			return null;
		}
		JSONArray result = new JSONArray();
		for (Token t : tokens){
			result.put(TokenJSONFactory.getJSONObject(t));
		}
		return result;
	}
	
	public static Token parseFromJSONObject(JSONObject json) throws JSONException{
		
		if (json == null){
			Log.e(TAG, "Unable to create Token from Json caused by json null");
			return null;
		}
		
		Token result = new Token();
		
		result.setTokenValue(json.getString("tokenValue"));
		
		return result;
	}
	
	public static ArrayList<Token> parseFromJSONArray(JSONArray array) throws JSONException{

		if (array == null){
			Log.e(TAG, "Unable to create Token List from Json caused by json null");
			return null;
		}
		
		ArrayList<Token> result = new ArrayList<Token>();
		int length = array.length();
		for (int i = 0 ; i < length ; i++){
			result.add(TokenJSONFactory.parseFromJSONObject(array.getJSONObject(i)));
		}
		return  result;
		
	}

}