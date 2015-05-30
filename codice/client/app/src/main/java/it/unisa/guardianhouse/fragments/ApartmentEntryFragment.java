package it.unisa.guardianhouse.fragments;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.Spinner;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.utils.LocationTracker;


/**
 * A simple {@link Fragment} subclass.
 */
public class ApartmentEntryFragment extends Fragment {


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
        aptPic = (ImageSwitcher) view.findViewById(R.id.imgViewPhoto);


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


}
