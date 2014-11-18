package fr.supinternet.supchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import fr.supinternet.supchat.R;

public class ChatsActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chats);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.menu_chat, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (item.getItemId() == R.id.menu_chat){
			goToCreateChatActivity();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void goToCreateChatActivity() {
		Intent intent = new Intent(this, CreateChatActivity.class);
		startActivity(intent);
	}

}