package it.unisa.guardianhouse.adapters;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import it.unisa.guardianhouse.AppController;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.models.Apartment;

public class ProfileApartmentListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Apartment> aptItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public ProfileApartmentListAdapter(Activity activity, List<Apartment> aptItems) {
        this.activity = activity;
        this.aptItems = aptItems;
    }

    @Override
    public int getCount() {
        return aptItems.size();
    }

    @Override
    public Apartment getItem(int position) {
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
            convertView = inflater.inflate(R.layout.list_item_profile_apt, null);
        TextView aptName = (TextView) convertView.findViewById(R.id.nameApt);

        Apartment apt = aptItems.get(position);

        aptName.setText(apt.getName());

        return convertView;
    }
}
