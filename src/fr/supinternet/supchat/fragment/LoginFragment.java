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
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import fr.supinternet.supchat.R;
import fr.supinternet.supchat.ContactsActivity;
import fr.supinternet.supchat.CreateAccountActivity;
import fr.supinternet.supchat.manager.RequestManager;
import fr.supinternet.supchat.model.ResponseCode;
import fr.supinternet.supchat.model.TokenResponse;
import fr.supinternet.supchat.model.User;
import fr.supinternet.supchat.util.CryptoUtils;

public class LoginFragment extends Fragment{
	
	private static final String TAG = "LoginFragment";
	
	private View view;
	
	private EditText userNameEdit;
	private EditText passwordEdit;
	
	private Button validateBtn;
	private Button goToCreateBtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_login, container, false);
		initViews();
		return view;
	}
	
	private void goToCreateActvity() {
		Intent intent = new Intent(getActivity(), CreateAccountActivity.class);
		startActivity(intent);
	}
	
	private void initViews() {

		userNameEdit = (EditText) view.findViewById(R.id.fragment_login_pseudo);
		passwordEdit = (EditText) view.findViewById(R.id.fragment_login_password);

		validateBtn = (Button) view.findViewById(R.id.fragment_login_validate);
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
		
		goToCreateBtn = (Button) view.findViewById(R.id.fragment_login_go_to_create);
		goToCreateBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goToCreateActvity();
			}
		});
	}
	
	private void login(User user){
		try {
			RequestManager.getInstance(getActivity()).login(user, new Listener<TokenResponse>() {

				@Override
				public void onResponse(TokenResponse response) {
					Log.i(TAG, "response " + response);
					if (response != null && response.getCode() == ResponseCode.OK){
						goToContactsActivity();
					}else{
						Toast.makeText(getActivity(), response.getStatus(), Toast.LENGTH_SHORT).show();
					}
				}
			}, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					Toast.makeText(getActivity(), R.string.fragment_login_error_network, Toast.LENGTH_SHORT).show();
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
		Intent intent = new Intent(getActivity(), ContactsActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
	}


}