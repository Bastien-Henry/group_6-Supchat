package fr.supinternet.supchat.manager;

import android.content.Context;

public class RequestManager {
	
	private static RequestManager SINGLETON = null;
	
	private static final Object __synchronizedObject = new Object();
	
	private Context context;
	
	public static RequestManager getInstance(Context context){
		
		if(SINGLETON == null){
			synchronized (__synchronizedObject){
				if(SINGLETON == null){
					SINGLETON = new RequestManager(context);
				}
			}
		}
		
		return SINGLETON;
	}
	
	private RequestManager(Context context){
		this.context = context;
	}

}
