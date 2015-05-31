package it.unisa.guardianhouse.fragments;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.utils.LocationTracker;
import nl.changer.polypicker.ImagePickerActivity;
import nl.changer.polypicker.utils.ImageInternalFetcher;


public class ApartmentEntryFragment extends Fragment {

    private static final String TAG = ApartmentEntryFragment.class.getSimpleName();

    private static final int INTENT_REQUEST_GET_IMAGES = 13;
    private static final int INTENT_REQUEST_GET_N_IMAGES = 14;

    private Context mContext;

    private ViewGroup mSelectedImagesContainer;
    HashSet<Uri> mMedia = new HashSet<Uri>();


    Bundle bundle;
    String road;
    String descript;
    String meters;
    String carspot;
    String contract;
    String civic;
    String intern;
    String conditions;
    LocationTracker gps;

    private Double latitude;
    private Double longitude;
    private int distance;

    private ButtonRectangle btnLinkToPhoto;
    private EditText inputAddressRoad;
    private EditText inputDescrip;
    private EditText inputMeters;
    private EditText inputCarspot;
    private EditText inputContractDuration;
    private EditText inputCivicNumber;
    private EditText inputInternId;
    private ImageButton btnMap;
    private Spinner spinnerConditions;
    private ImageSwitcher aptPic;


    public ApartmentEntryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1_details, container, false);

        mContext = getActivity();
        mSelectedImagesContainer = (ViewGroup) view.findViewById(R.id.selected_photos_container);
        View getImages = view.findViewById(R.id.get_images);
        getImages.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getImages();
            }
        });

        btnLinkToPhoto = (ButtonRectangle) view.findViewById(R.id.button1);
        inputAddressRoad = (EditText) view.findViewById(R.id.address_road);
        inputDescrip = (EditText) view.findViewById(R.id.descrip);
        inputMeters = (EditText) view.findViewById(R.id.meters);
        inputCarspot = (EditText) view.findViewById(R.id.carspot);
        inputContractDuration = (EditText) view.findViewById(R.id.durata);
        inputCivicNumber = (EditText) view.findViewById(R.id.civic_number);
        inputInternId = (EditText) view.findViewById(R.id.intern_id);
        btnMap = (ImageButton) view.findViewById(R.id.img_map);
        spinnerConditions = (Spinner) view.findViewById(R.id.spinner);


        btnLinkToPhoto.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public void onClick(View view) {

                road = inputAddressRoad.getText().toString();
                descript = inputDescrip.getText().toString();
                meters = inputMeters.getText().toString();
                carspot = inputCarspot.getText().toString();
                contract = inputContractDuration.getText().toString();
                civic = inputCivicNumber.getText().toString();
                intern = inputInternId.getText().toString();
                conditions = spinnerConditions.getSelectedItem().toString();

                if (!road.isEmpty() && !civic.isEmpty() && !meters.isEmpty() && !carspot.isEmpty() && !contract.isEmpty() && !intern.isEmpty()) {
                    bundle = new Bundle();
                    bundle.putString("myCivic", civic);
                    bundle.putString("myInter", intern);
                    bundle.putString("myConditions", conditions);
                    bundle.putString("myRoad", road);
                    bundle.putString("myDescript", descript);
                    bundle.putString("myMeters", meters);
                    bundle.putString("myCarspot", carspot);
                    bundle.putString("Mycontract", contract);
                    ApartmentEntryRoomFragment apartmentEntryRoomFragment = new ApartmentEntryRoomFragment();
                    apartmentEntryRoomFragment.setArguments(bundle);
                    ((MaterialNavigationDrawer) getActivity()).setFragmentChild(apartmentEntryRoomFragment, "Composizione Casa");

                } else {
                    Toast.makeText(getActivity(),
                            "Riempi tutti i campi!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        return view;
    }

    private void getImages() {
        Intent intent = new Intent(mContext, ImagePickerActivity.class);
        startActivityForResult(intent, INTENT_REQUEST_GET_IMAGES);
    }

    @Override
    public void onActivityResult(int requestCode, int resuleCode, Intent intent) {
        super.onActivityResult(requestCode, resuleCode, intent);

        if (resuleCode == Activity.RESULT_OK) {
            if (requestCode == INTENT_REQUEST_GET_IMAGES || requestCode == INTENT_REQUEST_GET_N_IMAGES) {
                Parcelable[] parcelableUris = intent.getParcelableArrayExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);

                if (parcelableUris == null) {
                    return;
                }

                // Java doesn't allow array casting, this is a little hack
                Uri[] uris = new Uri[parcelableUris.length];
                System.arraycopy(parcelableUris, 0, uris, 0, parcelableUris.length);

                if (uris != null) {
                    for (Uri uri : uris) {
                        Log.i(TAG, " uri: " + uri);
                        mMedia.add(uri);
                    }

                    showMedia();
                }
            }
        }
    }

    private void showMedia() {
        mSelectedImagesContainer.removeAllViews();

        Iterator<Uri> iterator = mMedia.iterator();
        ImageInternalFetcher imageFetcher = new ImageInternalFetcher(getActivity(), 500);
        while (iterator.hasNext()) {
            Uri uri = iterator.next();

            Log.i(TAG, " uri: " + uri);
            if (mMedia.size() >= 1) {
                mSelectedImagesContainer.setVisibility(View.VISIBLE);
            }
            View imageHolder = LayoutInflater.from(getActivity()).inflate(R.layout.media_layout, null);
            ImageView thumbnail = (ImageView) imageHolder.findViewById(R.id.media_image);

            if (!uri.toString().contains("content://")) {
                uri = Uri.fromFile(new File(uri.toString()));
            }

            imageFetcher.loadImage(uri, thumbnail);
            mSelectedImagesContainer.addView(imageHolder);
            int wdpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
            int htpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(wdpx, htpx));
        }
    }


}
