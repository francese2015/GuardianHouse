package it.unisa.guardianhouse.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.models.Apartment;

/**
 * Created by Nae on 13.04.2015.
 */
public class ApartmentListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Apartment> aptItems;

    public ApartmentListAdapter(Activity activity, List<Apartment> aptItems) {
        this.activity = activity;
        this.aptItems = aptItems;
    }

    @Override
    public int getCount() {
        return aptItems.size();
    }

    @Override
    public Object getItem(int position) {
        return aptItems.get(position);
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
            convertView = inflater.inflate(R.layout.list_item_apt, null);

        TextView description = (TextView) convertView.findViewById(R.id.nameApt);

        Apartment apt = aptItems.get(position);
        description.setText(apt.getDescription());

        return convertView;
    }
}
