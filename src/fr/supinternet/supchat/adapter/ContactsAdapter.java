package fr.supinternet.supchat.adapter;

import java.util.ArrayList;

import org.json.JSONException;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
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
	
	private boolean selectable = false;
	
	private boolean[] checked;
	
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
						checked = new boolean [users.size()];
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
		CheckBox check;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		if (convertView == null){
			convertView = inflater.inflate(R.layout.activity_contacts_item, parent, false);
			holder = new ViewHolder();
			holder.pseudo = (TextView) convertView.findViewById(R.id.fragment_contacts_item_pseudo);
			holder.check = (CheckBox) convertView.findViewById(R.id.fragment_contacts_item_check);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		if (selectable){
			holder.check.setVisibility(View.VISIBLE);
			holder.check.setSelected(checked[position]);
		}else{
			holder.check.setVisibility(View.INVISIBLE);
		}
		
		holder.check.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				checked[position] = !checked[position];
				v.setSelected(checked[position]);
			}
		});
		
		User user = (User) getItem(position);
		holder.pseudo.setText(user.getUserPseudo());
		
		return convertView;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
		notifyDataSetChanged();
	}
	
}