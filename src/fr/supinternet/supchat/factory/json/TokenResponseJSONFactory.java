package fr.supinternet.supchat.factory.json;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import fr.supinternet.supchat.model.Response;
import fr.supinternet.supchat.model.ResponseCode;
import fr.supinternet.supchat.model.TokenResponse;

public class TokenResponseJSONFactory {
	
private static final String TAG = "TokenJsonFactory";
	
	public static JSONObject getJSONObject(TokenResponse r) throws JSONException{
		
		if (r == null){
			Log.e(TAG, "Unable to create JSONObject from Response caused by Response null");
			return null;
		}
		
		JSONObject result = new JSONObject();
		result.accumulate("code", r.getCode());
		result.accumulate("status", r.getStatus());
		return result;
	}
	
	public static TokenResponse parseFromJSONObject(JSONObject json) throws JSONException{
		
		if (json == null){
			Log.e(TAG, "Unable to create Response from Json caused by json null");
			return null;
		}
		
		TokenResponse result = new TokenResponse();
		
		result.setCode(ResponseCode.valueOf(json.getString("code")));
		result.setStatus(json.getString("status"));
		result.setToken(json.getString("token"));
		
		return result;
	}
	
}