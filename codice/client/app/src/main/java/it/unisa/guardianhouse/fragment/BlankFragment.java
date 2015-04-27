package it.unisa.guardianhouse.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BlankFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView text = new TextView(this.getActivity());
        text.setText("Section");
        text.setGravity(Gravity.CENTER);
        return text;
    }

}
