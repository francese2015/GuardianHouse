package it.unisa.guardianhouse.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.model.Apartment;

/**
 * Created by Nae on 13.04.2015.
 */
public class ApartmentListAdapter extends BaseAdapter {

    private Activity activityOne;
    private LayoutInflater inflaterOne;
    private List<Apartment> aptItems;

    public ApartmentListAdapter(Activity activityOne, List<Apartment> aptItems) {
        this.activityOne = activityOne;
        this.aptItems = aptItems;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflaterOne == null)
            inflaterOne = (LayoutInflater) activityOne
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflaterOne.inflate(R.layout.list_item_apt, null);

        TextView nameApt = (TextView) convertView.findViewById(R.id.nameApt);
        RatingBar ratingApt = (RatingBar) convertView.findViewById(R.id.ratingApt);

        Apartment apt = aptItems.get(position);
        nameApt.setText(apt.getName());

        return convertView;
    }
}
