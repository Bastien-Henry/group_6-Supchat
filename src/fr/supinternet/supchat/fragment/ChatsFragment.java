package fr.supinternet.supchat.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;
import fr.supinternet.supchat.R;
import fr.supinternet.supchat.adapter.ChatsAdapter;

public class ChatsFragment extends Fragment{
	
	private View view;
	private PullToRefreshListView listView;
	private ChatsAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_chats, container, false);
		initViews();
		return view;
	}
	
	private void initViews() {
		listView = (PullToRefreshListView) view.findViewById(R.id.fragment_chat_list);
		adapter = new ChatsAdapter(this);
		listView.setAdapter(adapter);
		adapter.loadData();
		
		listView.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				adapter.loadData();
			}
		});
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		if (adapter != null){
			adapter.loadData();
		}
	}
	
	public void dataLoaded(){
		if (listView != null){
			listView.onRefreshComplete();
		}
	}

}