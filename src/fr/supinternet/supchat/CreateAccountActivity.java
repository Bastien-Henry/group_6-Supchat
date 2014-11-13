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

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import fr.supinternet.supchat.R;
import fr.supinternet.supchat.manager.RequestManager;
import fr.supinternet.supchat.model.Response;
import fr.supinternet.supchat.model.User;
import fr.supinternet.supchat.util.CryptoUtils;

public class CreateAccountActivity extends Activity{
	
	private static final String TAG = "CreateAccountActivity";

	private EditText userNameEdit;
	private EditText passwordEdit;

	private Button validateBtn;
	private Button goToCreateBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
		initViews();
	}

	private void initViews() {

		userNameEdit = (EditText) findViewById(R.id.activity_create_account_pseudo);
		passwordEdit = (EditText) findViewById(R.id.activity_create_account_password);

		validateBtn = (Button) findViewById(R.id.activity_create_account_validate);
		validateBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (checkFields()){
					User user = fillValues();
					createUser(user);
				}else{
					
				}
			}
		});
		
		goToCreateBtn = (Button) findViewById(R.id.activity_create_account_go_to_login);
		goToCreateBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goToLoginActvity();
			}
		});
	}
	
	private void createUser(User user){
		try {
			RequestManager.getInstance(CreateAccountActivity.this).createUser(user, new Listener<Response>() {

				@Override
				public void onResponse(Response response) {
					Log.i(TAG, "response " + response);
					goToContactsActivity();
				}
			}, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					Log.i(TAG, "Error during the request");
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

	private void goToLoginActvity() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}
	
	private void goToContactsActivity(){
		Intent intent = new Intent(this, ContactsActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
	}
	
}