package fr.supinternet.supchat.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;
import fr.supinternet.supchat.R;
import fr.supinternet.supchat.adapter.ContactsAdapter;

public class ContactsFragment extends Fragment{
	
	private View view;
	private PullToRefreshListView listView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_contacts, container, false);
		initViews();
		return view;
	}
	
	private void initViews() {
		listView = (PullToRefreshListView) view.findViewById(R.id.fragment_contacts_list);
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