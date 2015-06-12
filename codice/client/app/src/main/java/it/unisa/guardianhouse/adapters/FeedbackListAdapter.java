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

import java.util.List;

import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.models.Feedback;
import it.unisa.guardianhouse.models.User;

/**
 * Created by thecomputerwhisper on 11/06/15.
 */
public class FeedbackListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Feedback> feedbacksItems;

    public FeedbackListAdapter(Activity activity, List<Feedback> feedItems) {
        this.activity = activity;
        this.feedbacksItems = feedItems;
    }

    @Override
    public int getCount() {
        return feedbacksItems.size();
    }

    @Override
    public Object getItem(int position) {
        return feedbacksItems.get(position);
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
            convertView = inflater.inflate(R.layout.list_item_feedback, null);

        //ImageView feedProfile = (ImageView) convertView.findViewById(R.id.feedProfileImage);
        TextView username = (TextView) convertView.findViewById(R.id.feedUsername);
        TextView feedback_text = (TextView) convertView.findViewById(R.id.surname);
        RatingBar feedrating = (RatingBar) convertView.findViewById(R.id.feedRatingBar);

        Feedback f = feedbacksItems.get(position);

        username.setText(f.getUsername());
        feedback_text.setText(f.getFeed_text());
        feedrating.setRating(f.getRating());


        return convertView;
    }

}
