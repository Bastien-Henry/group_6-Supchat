package fr.supinternet.supchat;

import android.app.Activity;
import android.os.Bundle;
import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;
import fr.supinternet.supchat.R;
import fr.supinternet.supchat.adapter.ContactsAdapter;

public class ContactsActivity extends Activity{
	
	private PullToRefreshListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		initViews();
	}

	private void initViews() {
		listView = (PullToRefreshListView) findViewById(R.id.activity_contacts_list);
		final ContactsAdapter adapter = new ContactsAdapter(this);
		listView.setAdapter(adapter);
		adapter.loadData();
		
		listView.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				adapter.loadData();
			}
		});
	}
	
	public void dataLoaded(){
		if (listView != null){
			listView.onRefreshComplete();
		}
	}

}