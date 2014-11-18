package fr.supinternet.supchat;

import fr.supinternet.supchat.R;
import fr.supinternet.supchat.fragment.ContactsFragment;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class CreateChatActivity extends Activity{
	
	private ContactsFragment fragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_chat);
		
		FragmentManager fragmentManager = getFragmentManager();
		fragment = (ContactsFragment) fragmentManager.findFragmentById(R.id.fragment_contacts);
		fragment.setSelectable(true);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.menu_validate, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (item.getItemId() == R.id.menu_validate){
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}