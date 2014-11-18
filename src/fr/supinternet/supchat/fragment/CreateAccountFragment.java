package fr.supinternet.supchat.fragment;

import org.json.JSONException;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import fr.supinternet.supchat.R;
import fr.supinternet.supchat.ContactsActivity;
import fr.supinternet.supchat.LoginActivity;
import fr.supinternet.supchat.manager.RequestManager;
import fr.supinternet.supchat.model.Response;
import fr.supinternet.supchat.model.User;
import fr.supinternet.supchat.util.CryptoUtils;

public class CreateAccountFragment extends Fragment{
	
	private static final String TAG = "CreateAccountFragment";

	private EditText userNameEdit;
	private EditText passwordEdit;

	private Button validateBtn;
	private Button goToCreateBtn;
	
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_create_account, container, false);
		initViews();
		return view;
	}
	
	private void initViews() {

		userNameEdit = (EditText) view.findViewById(R.id.fragment_create_account_pseudo);
		passwordEdit = (EditText) view.findViewById(R.id.fragment_create_account_password);

		validateBtn = (Button) view.findViewById(R.id.fragment_create_account_validate);
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
		
		goToCreateBtn = (Button) view.findViewById(R.id.fragment_create_account_go_to_login);
		goToCreateBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goToLoginActvity();
			}
		});
	}
	
	private void createUser(User user){
		try {
			RequestManager.getInstance(getActivity()).createUser(user, new Listener<Response>() {

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
		Intent intent = new Intent(getActivity(), LoginActivity.class);
		startActivity(intent);
	}
	
	private void goToContactsActivity(){
		Intent intent = new Intent(getActivity(), ContactsActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
	}

}