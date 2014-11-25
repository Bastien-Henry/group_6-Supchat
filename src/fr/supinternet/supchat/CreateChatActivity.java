package fr.supinternet.supchat;

import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import fr.supinternet.supchat.R;
import fr.supinternet.supchat.fragment.ContactsFragment;
import fr.supinternet.supchat.manager.RequestManager;
import fr.supinternet.supchat.model.Chat;
import fr.supinternet.supchat.model.ChatData;
import fr.supinternet.supchat.model.Response;
import fr.supinternet.supchat.model.ResponseCode;
import fr.supinternet.supchat.model.User;

public class CreateChatActivity extends Activity{
	
	private static final String TAG = "CreateChatActivity";
	
	private ContactsFragment fragment;
	private EditText nameEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_chat);
		
		FragmentManager fragmentManager = getFragmentManager();
		fragment = (ContactsFragment) fragmentManager.findFragmentById(R.id.fragment_contacts);
		fragment.setSelectable(true);
		
		initViews();
		
	}
	
	private void initViews() {
		nameEdit = (EditText) findViewById(R.id.activity_create_chat_name);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.menu_validate, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (item.getItemId() == R.id.menu_validate){
			if (!checkName()){
				Toast.makeText(this, R.string.activity_create_chat_no_name_error, Toast.LENGTH_SHORT).show();
				return true;
			}
			
			if (!checkUserList()){
				Toast.makeText(this, R.string.activity_create_chat_no_users_error, Toast.LENGTH_SHORT).show();
				return true;
			}
			// Create chat
			ChatData data = new ChatData();
			data.setChat(createChat());
			data.setUsers(fragment.getUsersSelected());
			try {
				RequestManager.getInstance(this).createChat(data, new Listener<Response>() {

					@Override
					public void onResponse(Response response) {
						if (response.getCode() == ResponseCode.OK){
							onBackPressed();
						}
						Toast.makeText(CreateChatActivity.this, response.getStatus(), Toast.LENGTH_SHORT).show();
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						
					}
				});
			} catch (JSONException e) {
				Log.e(TAG, "Error executing the request");
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private Chat createChat() {
		Chat  chat = new Chat();
		chat.setChatName(nameEdit.getText().toString());
		return chat;
	}

	private boolean checkUserList() {
		ArrayList<User> usersSelected = fragment.getUsersSelected();
		if (usersSelected == null || usersSelected.size() == 0){
			return false;
		}
		return true;
	}

	private boolean checkName() {
		String chatName = nameEdit.getText().toString();
		
		if (chatName == null || chatName.length() == 0){
			return false;
		}
		return true;
	}

}