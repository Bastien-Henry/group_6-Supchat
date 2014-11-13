package fr.supinternet.supchat;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import fr.supinternet.supchat.R;
import fr.supinternet.supchat.manager.RequestManager;
import fr.supinternet.supchat.model.TokenResponse;
import fr.supinternet.supchat.model.User;
import fr.supinternet.supchat.util.CryptoUtils;

public class LoginActivity extends Activity {
	
	private static final String TAG = "LoginActivity";
	
	private EditText userNameEdit;
	private EditText passwordEdit;
	
	private Button validateBtn;
	private Button goToCreateBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initViews();
		
	}

	private void goToCreateActvity() {
		Intent intent = new Intent(this, CreateAccountActivity.class);
		startActivity(intent);
	}
	
	private void initViews() {

		userNameEdit = (EditText) findViewById(R.id.activity_login_pseudo);
		passwordEdit = (EditText) findViewById(R.id.activity_login_password);

		validateBtn = (Button) findViewById(R.id.activity_login_validate);
		validateBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (checkFields()){
					User user = fillValues();
					login(user);
				}else{
					
				}
			}
		});
		
		goToCreateBtn = (Button) findViewById(R.id.activity_login_go_to_create);
		goToCreateBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goToCreateActvity();
			}
		});
	}
	
	private void login(User user){
		try {
			RequestManager.getInstance(this).login(user, new Listener<TokenResponse>() {

				@Override
				public void onResponse(TokenResponse response) {
					Log.i(TAG, "response " + response);
					goToContactsActivity();
				}
			}, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					Toast.makeText(LoginActivity.this, R.string.activity_login_error_network, Toast.LENGTH_SHORT).show();
				}
			});
		} catch (JSONException e) {
			Log.e(TAG, "Error executing request " + e.getMessage(), e);
		}
	}

	protected User fillValues() {
		User user = new User();
		user.setUserPseudo(userNameEdit.getText().toString());
		user.setUserHash(CryptoUtils.getHash(passwordEdit.getText().toString()));
		return user;
	}

	protected boolean checkFields() {
		String userName = userNameEdit.getText().toString();
		String password = passwordEdit.getText().toString();
		
		if (userName == null || userName.length() == 0){
			return false;
		}
		
		if (password == null || password.length() == 0){
			return false;
		}
		
		return true;
	}
	
	private void goToContactsActivity(){
		Intent intent = new Intent(this, ContactsActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
	}

}