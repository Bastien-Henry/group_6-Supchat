package fr.supinternet.supchat.request;

import org.json.JSONObject;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

import fr.supinternet.supchat.application.SupChatApplication;
import fr.supinternet.supchat.manager.RequestManager;

public class AbstractRequest extends JsonObjectRequest{

	public static final String SERVER_URL = "http://serv.makki.us:11223/Chat/";

	protected static Context mContext;

	private String requestId;
	
	public AbstractRequest(Context context, int method, String url, JSONObject jsonRequest, Listener<JSONObject> listener, ErrorListener errorListener) {
		super(method, url, jsonRequest, listener, errorListener);
		AbstractRequest.mContext = context;
	}

	public AbstractRequest(Context context, String url, JSONObject jsonRequest, Listener<JSONObject> listener, ErrorListener errorListener) {
		super(url, jsonRequest, listener, errorListener);
		AbstractRequest.mContext = context;
		initialiseRequest();
	}

	private void initialiseRequest() {
		setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 1, 1.0f));
		setShouldCache(false);
	}
	
	public String getRequestTag(){
		requestId = String.valueOf(RequestManager.getRequestId());
		return requestId;
	}
	
	public String getRequestId() {
		return requestId;
	}

	public void start(){
		SupChatApplication.getInstance().addToRequestQueue(this, getRequestTag());
	}

}