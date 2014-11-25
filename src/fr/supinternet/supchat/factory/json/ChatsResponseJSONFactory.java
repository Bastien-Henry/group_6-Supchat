package fr.supinternet.supchat.factory.json;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import fr.supinternet.supchat.model.ChatsResponse;
import fr.supinternet.supchat.model.ResponseCode;

public class ChatsResponseJSONFactory {

	private static final String TAG = "ChatsResponseJSONFactory";

	public static ChatsResponse parseFromJSONObject(JSONObject json) throws JSONException{

		if (json == null){
			Log.e(TAG, "Unable to create ChatsResponse from Json caused by json null");
			return null;
		}

		ChatsResponse result = new ChatsResponse();

		result.setCode(ResponseCode.valueOf(json.getString("code")));
		result.setStatus(json.getString("status"));
		result.setChats(ChatJSONFactory.parseFromJSONArray(json.getJSONArray("chats")));

		return result;
	}

}