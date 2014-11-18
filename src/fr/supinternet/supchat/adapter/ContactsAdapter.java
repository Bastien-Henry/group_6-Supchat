package fr.supinternet.supchat.adapter;

import java.util.ArrayList;

import org.json.JSONException;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import fr.supinternet.supchat.R;
import fr.supinternet.supchat.fragment.ContactsFragment;
import fr.supinternet.supchat.manager.RequestManager;
import fr.supinternet.supchat.model.ContactsResponse;
import fr.supinternet.supchat.model.User;

public class ContactsAdapter extends BaseAdapter{
	
	private static final String TAG = "ContactsAdapter";
	
	private ArrayList<User> users;
	
	private LayoutInflater inflater;
	private RequestManager manager;
	private ContactsFragment fragment;
	
	public ContactsAdapter(ContactsFragment fragment) {
		inflater = LayoutInflater.from(fragment.getActivity());
		manager = RequestManager.getInstance(fragment.getActivity());
		this.fragment = fragment;
	}
	
	public void loadData(){
		try {
			manager.retrieveContacts(new Listener<ContactsResponse>() {

				@Override
				public void onResponse(ContactsResponse response) {
					if (response != null){
						users = response.getUsers();
					}else{
						Log.e(TAG, "Response null for retrieve contacts");
					}
					notifyDataSetChanged();
					fragment.dataLoaded();
				}
			}, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError arg0) {
					
				}
			});
		} catch (JSONException e) {
		}
	}
	
	@Override
	public int getCount() {
		return (users == null ? 0 : users.size());
	}

	@Override
	public Object getItem(int position) {
		return (users == null ? null : users.get(position));
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	private class ViewHolder{
		TextView pseudo;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		if (convertView == null){
			convertView = inflater.inflate(R.layout.activity_contacts_item, parent, false);
			holder = new ViewHolder();
			holder.pseudo = (TextView) convertView.findViewById(R.id.activit_contacts_item_pseudo);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		User user = (User) getItem(position);
		holder.pseudo.setText(user.getUserPseudo());
		
		return convertView;
	}

}