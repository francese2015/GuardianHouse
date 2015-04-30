package it.unisa.guardianhouse.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.model.User;

/**
 * Created by Carlo on 12/04/2015.
 */
public class UserListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<User> userItems;

    public UserListAdapter(Activity activity, List<User> userItems) {
        this.activity = activity;
        this.userItems = userItems;
    }

    @Override
    public int getCount() {
        return userItems.size();
    }

    @Override
    public Object getItem(int position) {
        return userItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_item_user, null);

        TextView username = (TextView) convertView.findViewById(R.id.username);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView surname = (TextView) convertView.findViewById(R.id.surname);

        User u = userItems.get(position);

        username.setText(u.getUsername());
        name.setText(u.getName());
        surname.setText(u.getSurname());

        return convertView;
    }

}