package it.unisa.guardianhouse.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import it.unisa.guardianhouse.AppController;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.models.Review;

/**
 * Created by Luigi on 26/05/2015.
 */
public class ReviewListAdapter extends BaseAdapter {

    //ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    private Activity activity;
    private LayoutInflater inflater;
    private List<Review> rewItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public ReviewListAdapter(Activity activity, ArrayList rewItems ) {
        this.activity = activity;
        this.rewItems = rewItems;
    }

    @Override
    public int getCount() {
        return rewItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rewItems.get(position);
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
            convertView = inflater.inflate(R.layout.list_item_review, null);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnailRew);
        TextView getRewUsername = (TextView) convertView.findViewById(R.id.username_value);
        TextView review = (TextView) convertView.findViewById(R.id.txtReview);


        Review rew = rewItems.get(position);


        thumbNail.setImageUrl(rew.getThumbnailUrl(), imageLoader);
        getRewUsername.setText(rew.getUsername());
        review.setText(rew.getDescription());
        return convertView;
    }
}
