package fr.supinternet.supchat.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class PrefsUtils {
	
	private static final String TAG = "PrefsUtils";
	
	public static final String TOKEN_S = "TOKEN_S";


	private static SharedPreferences prefs;

	public static Long getLong(Context context, String key, Long defaultValue){
		return getPrefs(context).getLong(key, defaultValue);
	}

	public static Integer getInt(Context context, String key, Integer defaultValue){
		return getPrefs(context).getInt(key, defaultValue);
	}

	public static Float getFloat(Context context, String key, Float defaultValue){
		return getPrefs(context).getFloat(key, defaultValue);
	}

	public static String getString(Context context, String key, String defaultValue){
		String value = getPrefs(context).getString(key, defaultValue);
		return value;
	}

	public static Boolean getBoolean(Context context, String key, Boolean defaultValue){
		Log.i(TAG, "Boolean GET " + key + " value " + getPrefs(context).getBoolean(key, defaultValue));
		return getPrefs(context).getBoolean(key, defaultValue);
	}

	public static void setLong(Context context, String key, Long value){
		getPrefs(context).edit().putLong(key, (value == null ? 0l : value)).commit();
	}

	public static void setInt(Context context, String key, Integer value){
		getPrefs(context).edit().putInt(key, (value == null ? 0 : value)).commit();
	}

	public static void setFloat(Context context, String key, Float value){
		getPrefs(context).edit().putFloat(key, (value == null ? 0 : value)).commit();
	}

	public static void setString(Context context, String key, String value){
		getPrefs(context).edit().putString(key, value).commit();
	}

	public static void setBoolean(Context context, String key, Boolean value){
		Log.i(TAG, "Boolean SET " + key + " value " + value);
		getPrefs(context).edit().putBoolean(key, (value == null ? false : value)).commit();
	}

	private static SharedPreferences getPrefs(Context context){
		if (prefs == null){
			prefs = PreferenceManager.getDefaultSharedPreferences(context);
		}
		return prefs;
	}

}