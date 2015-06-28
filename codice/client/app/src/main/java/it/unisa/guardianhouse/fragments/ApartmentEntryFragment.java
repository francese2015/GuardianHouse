package it.unisa.guardianhouse.fragments;


import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;

import java.io.IOException;
import java.util.List;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.config.Config;
import it.unisa.guardianhouse.utils.LocationTracker;
import it.unisa.guardianhouse.utils.Utils;


public class ApartmentEntryFragment extends Fragment {

    //    private static final int INTENT_REQUEST_GET_IMAGES = 13;
//    private static final int INTENT_REQUEST_GET_N_IMAGES = 14;
//    HashSet<Uri> mMedia = new HashSet<Uri>();
    Bundle bundle;
    String route;
    String description;
    String mq;
    String car_place;
    String contract_time;
    String street_number;
    String intern_id;
    String status;
    String name;
    String locality;
    String cost;
    String country;
    String postalCode;
    String adminAreaLevel;
    String subAdminAreaLevel;
    private Context mContext;
    private ViewGroup mSelectedImagesContainer;
    Double latitude;
    Double longitude;
    private ButtonRectangle btnLinkToRoom;
    private EditText inputAddressRoad;
    private EditText inputDescrip;
    private EditText inputMeters;
    private EditText inputCarspot;
    private EditText inputContractDuration;
    private EditText inputCivicNumber;
    private EditText inputInternId;
    //private ImageButton btnMap;
    private Spinner spinnerConditions;
    private EditText inputTitle;
    private EditText inputLocality;
    private EditText inputCost;


    public ApartmentEntryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1_details, container, false);

//        mContext = getActivity();
//        mSelectedImagesContainer = (ViewGroup) view.findViewById(R.id.selected_photos_container);
//        View getImages = view.findViewById(R.id.get_images);
//        getImages.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                getImages();
//            }
//        });

        btnLinkToRoom = (ButtonRectangle) view.findViewById(R.id.button1);
        inputAddressRoad = (EditText) view.findViewById(R.id.address_road);
        inputDescrip = (EditText) view.findViewById(R.id.descrip);
        inputMeters = (EditText) view.findViewById(R.id.meters);
        inputCarspot = (EditText) view.findViewById(R.id.carspot);
        inputContractDuration = (EditText) view.findViewById(R.id.durata);
        inputCivicNumber = (EditText) view.findViewById(R.id.civic_number);
        inputInternId = (EditText) view.findViewById(R.id.intern_id);
        //btnMap = (ImageButton) view.findViewById(R.id.img_map);
        spinnerConditions = (Spinner) view.findViewById(R.id.spinner);
        inputTitle = (EditText) view.findViewById(R.id.title);
        inputLocality = (EditText) view.findViewById(R.id.city);
        inputCost = (EditText) view.findViewById(R.id.costo);

        Button btnGetImages = (Button) view.findViewById(R.id.get_images);
        btnGetImages.setEnabled(false);

        btnLinkToRoom.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public void onClick(View view) {


                route = inputAddressRoad.getText().toString();
                description = inputDescrip.getText().toString();
                mq = inputMeters.getText().toString();
                car_place = inputCarspot.getText().toString();
                contract_time = inputContractDuration.getText().toString();
                street_number = inputCivicNumber.getText().toString();
                intern_id = inputInternId.getText().toString();
                status = spinnerConditions.getSelectedItem().toString();
                name = inputTitle.getText().toString();
                locality = inputLocality.getText().toString();
                cost = inputCost.getText().toString();

                Geocoder gc = new Geocoder(getActivity());

                String addressString = route + " " + street_number + " " + locality;



                if (!route.isEmpty() && !street_number.isEmpty() && !mq.isEmpty() && !car_place.isEmpty() && !contract_time.isEmpty() && !intern_id.isEmpty()) {

                    if (gc.isPresent()) {
                        List<Address> list = null;
                        try {
                            list = gc.getFromLocationName(addressString, 1);

                            if (list.isEmpty()) {
                                Toast.makeText(getActivity(),
                                        "L'indirizzo inserito non e' valido!", Toast.LENGTH_LONG).show();
                            } else {

                                Address address = list.get(0);

                                latitude = address.getLatitude();
                                longitude = address.getLongitude();
                                postalCode = address.getPostalCode();
                                adminAreaLevel = address.getAdminArea();
                                subAdminAreaLevel = address.getSubAdminArea();
                                country = address.getCountryName();

                                bundle = new Bundle();

                                bundle.putString("myTitle", name);
                                bundle.putString("myDescript", description);
                                bundle.putString("myMeters", mq);
                                bundle.putString("myCarspot", car_place);
                                bundle.putString("mycontract", contract_time);
                                bundle.putString("myCost", cost);
                                bundle.putString("myConditions", status);

                                bundle.putString("myCivic", street_number);
                                bundle.putString("myRoad", route);
                                bundle.putString("myCity", locality);
                                bundle.putString("myInter", intern_id);
                                bundle.putString("postal_code", postalCode);
                                //bundle.putString("admin_level", adminAreaLevel);
                                //bundle.putString("sub_admin_level", subAdminAreaLevel);
                                bundle.putString("country", country);

                                bundle.putDouble("myLat", latitude);
                                bundle.putDouble("myLong", longitude);

                                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

                                ApartmentEntryRoomFragment apartmentEntryRoomFragment = new ApartmentEntryRoomFragment();
                                apartmentEntryRoomFragment.setArguments(bundle);
                                ((MaterialNavigationDrawer) getActivity()).setFragmentChild(apartmentEntryRoomFragment, "Composizione Casa");
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),
                                    "L'indirizzo inserito non e' valido!", Toast.LENGTH_LONG).show();
                        }


                    }

                } else {
                    Toast.makeText(getActivity(),
                            "Riempi tutti i campi!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        return view;
    }

//    private void getImages() {
//        Intent intent = new Intent(mContext, ImagePickerActivity.class);
//        startActivityForResult(intent, INTENT_REQUEST_GET_IMAGES);
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resuleCode, Intent intent) {
//        super.onActivityResult(requestCode, resuleCode, intent);
//
//        if (resuleCode == Activity.RESULT_OK) {
//            if (requestCode == INTENT_REQUEST_GET_IMAGES || requestCode == INTENT_REQUEST_GET_N_IMAGES) {
//                Parcelable[] parcelableUris = intent.getParcelableArrayExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);
//
//                if (parcelableUris == null) {
//                    return;
//                }
//
//                // Java doesn't allow array casting, this is a little hack
//                Uri[] uris = new Uri[parcelableUris.length];
//                System.arraycopy(parcelableUris, 0, uris, 0, parcelableUris.length);
//
//                if (uris != null) {
//                    for (Uri uri : uris) {
//                        Log.i(TAG, " uri: " + uri);
//                        mMedia.add(uri);
//                    }
//
//                    showMedia();
//                }
//            }
//        }
//    }

//    private void showMedia() {
//        mSelectedImagesContainer.removeAllViews();
//
//        Iterator<Uri> iterator = mMedia.iterator();
//        ImageInternalFetcher imageFetcher = new ImageInternalFetcher(getActivity(), 500);
//        while (iterator.hasNext()) {
//            Uri uri = iterator.next();
//
//            Log.i(TAG, " uri: " + uri);
//            if (mMedia.size() >= 1) {
//                mSelectedImagesContainer.setVisibility(View.VISIBLE);
//            }
//            View imageHolder = LayoutInflater.from(getActivity()).inflate(R.layout.media_layout, null);
//            ImageView thumbnail = (ImageView) imageHolder.findViewById(R.id.media_image);
//
//            if (!uri.toString().contains("content://")) {
//                uri = Uri.fromFile(new File(uri.toString()));
//            }
//
//            imageFetcher.loadImage(uri, thumbnail);
//            mSelectedImagesContainer.addView(imageHolder);
//            int wdpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
//            int htpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
//            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(wdpx, htpx));
//        }
//    }


}