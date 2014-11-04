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
				
				
				User user = new User();
				try {
					RequestManager.getInstance(CreateAccountActivity.this).createUser(user, new Listener<Response>() {

						@Override
						public void onResponse(Response arg0) {
							
						}
					}, new ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError arg0) {
							
						}
					});
				} catch (JSONException e) {
					Log.e(TAG, "Error executing request " + e.getMessage(), e);
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

	private void goToLoginActvity() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

}