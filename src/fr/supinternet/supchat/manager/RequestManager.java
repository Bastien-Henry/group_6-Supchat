package fr.supinternet.supchat.manager;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import fr.supinternet.supchat.factory.json.ContactsResponseJSONFactory;
import fr.supinternet.supchat.factory.json.ResponseJSONFactory;
import fr.supinternet.supchat.factory.json.TokenResponseJSONFactory;
import fr.supinternet.supchat.model.ChatData;
import fr.supinternet.supchat.model.ContactsResponse;
import fr.supinternet.supchat.model.Response;
import fr.supinternet.supchat.model.ResponseCode;
import fr.supinternet.supchat.model.Token;
import fr.supinternet.supchat.model.TokenResponse;
import fr.supinternet.supchat.model.User;
import fr.supinternet.supchat.request.ContactsRequest;
import fr.supinternet.supchat.request.CreateChatRequest;
import fr.supinternet.supchat.request.CreateUserRequest;
import fr.supinternet.supchat.request.LoginRequest;

public class RequestManager {

	private static final String TAG = "RequestManager";

	/**
	 * The unique instance of the manager.
	 */
	private static RequestManager SINGLETON = null;

	/**
	 * The lock for thread safety.
	 */
	private static final Object __synchronizedObject = new Object();

	private Context context;

	private static int requestId = -1;

	public static RequestManager getInstance(Context context) {

		if (SINGLETON == null) {
			synchronized (__synchronizedObject) {
				if (SINGLETON == null) {
					SINGLETON = new RequestManager(context);
				}
			}
		}
		return SINGLETON;
	}

	private RequestManager(Context context) {
		this.context = context;
	}

	public static int getRequestId(){
		return requestId++;
	}

	public void createUser(final User user, final Listener<Response> listener, ErrorListener errorListener) throws JSONException {

		CreateUserRequest request = new CreateUserRequest(context, user, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject arg0) {
				Response response = null;
				try {
					response = storeToken(arg0);
					storeCredentials(user.getUserPseudo(), user.getUserHash());
				} catch (JSONException e) {
					Log.e(TAG, "An error occurred parsing create user response", e);
				}

				if (listener != null){
					listener.onResponse(response);
				}
			}
		}, errorListener);
		request.start();
	}

	public void login(final User user, final Listener<TokenResponse> listener, ErrorListener errorListener) throws JSONException {

		LoginRequest request = new LoginRequest(context, user, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject jsonResponse) {
				TokenResponse response = null;
				try {
					response = storeToken(jsonResponse);
					storeCredentials(user.getUserPseudo(), user.getUserHash());
				} catch (JSONException e) {
					Log.e(TAG, "An error occurred parsing create user response", e);
				}

				if (listener != null){
					listener.onResponse(response);
				}
			}
		}, errorListener);
		request.start();
	}
	
	private void storeCredentials(String pseudo, String hash){
		AuthenticationManager.getInstance(context).setPseudo(pseudo);
		AuthenticationManager.getInstance(context).setHash(hash);
	}

	protected TokenResponse storeToken(JSONObject arg0) throws JSONException {
		TokenResponse response = TokenResponseJSONFactory.parseFromJSONObject(arg0);
		AuthenticationManager.getInstance(context).setToken(response.getToken());
		return response;
	}
	
	public void retrieveContacts(final Listener<ContactsResponse> listener, final ErrorListener errorListener) throws JSONException {

		Token token = new Token();
		token.setTokenValue(AuthenticationManager.getInstance(context).getToken());
		ContactsRequest request = new ContactsRequest(context, token, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject jsonResponse) {
				ContactsResponse response = null;
				Log.i(TAG, "response " + jsonResponse);
				try {
					response = ContactsResponseJSONFactory.parseFromJSONObject(jsonResponse);
					
					if (response == null || response.getCode().equals(ResponseCode.TOKEN_INVALID)){
						autoLogin(new Listener<TokenResponse>(){

							@Override
							public void onResponse(TokenResponse arg0) {
								try {
									retrieveContacts(listener, errorListener);
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}
							
						}, errorListener);
					}
					
				} catch (JSONException e) {
					Log.e(TAG, "An error occurred parsing create user response", e);
				}

				if (listener != null){
					listener.onResponse(response);
				}
			}
		}, errorListener);
		request.start();
	}
	
	public void createChat(final ChatData data, final Listener<Response> listener, final ErrorListener errorListener) throws JSONException {

		CreateChatRequest request = new CreateChatRequest(context, data, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject json) {
				Response response = null;
				try {
					response = ResponseJSONFactory.parseFromJSONObject(json);
					if (response == null || response.getCode().equals(ResponseCode.TOKEN_INVALID)){
					}
					
				} catch (JSONException e) {
					Log.e(TAG, "An error occurred parsing create user response", e);
				}

				if (listener != null){
					listener.onResponse(response);
				}
			}
		}, errorListener);
		request.start();
	}
	
	private void autoLogin(final Listener<TokenResponse> listener, final ErrorListener errorListener) throws JSONException{
		final User user = new User();
		user.setUserPseudo(AuthenticationManager.getInstance(context).getPseudo());
		user.setUserHash(AuthenticationManager.getInstance(context).getHash());
		Log.i(TAG, "user sent " + user);
		LoginRequest request = new LoginRequest(context, user, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject jsonResponse) {
				TokenResponse response = null;
				try {
					response = storeToken(jsonResponse);
					storeCredentials(user.getUserPseudo(), user.getUserHash());
				} catch (JSONException e) {
					Log.e(TAG, "An error occurred parsing create user response", e);
				}

				if (listener != null){
					listener.onResponse(response);
				}
			}
		}, errorListener);
		request.start();
	}

}