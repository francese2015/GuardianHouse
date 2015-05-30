package it.unisa.guardianhouse.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.unisa.guardianhouse.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ApartmentEntryFragment extends Fragment {

    private ButtonRectangle btnLinkToPhoto;
    private EditText inputName;
    private EditText inputDescrip;
    private EditText inputMeters;
    private EditText inputCarspot;
    private EditText inputContractDuration;
    Bundle bundle;


    String name;
    String descript;
    String meters;
    String carspot;
    String contract;





    public ApartmentEntryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_1_details, container, false);

        btnLinkToPhoto = (ButtonRectangle) view.findViewById(R.id.button1);
        inputName = (EditText) view.findViewById(R.id.name);
        inputDescrip = (EditText) view.findViewById(R.id.descrip);
        inputMeters = (EditText) view.findViewById(R.id.descrip);
        inputCarspot = (EditText) view.findViewById(R.id.carspot);
        inputContractDuration = (EditText) view.findViewById(R.id.durata);


        //link event alla schermata d'inserimento Foto

        btnLinkToPhoto.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {

            name = inputName.getText().toString();

            descript = inputDescrip.getText().toString();

            meters = inputMeters.getText().toString();

            carspot = inputCarspot.getText().toString();

            contract = inputContractDuration.getText().toString();



            if(!name.isEmpty() && !descript.isEmpty() && !meters.isEmpty() && !carspot.isEmpty() && !contract.isEmpty() ){




                bundle = new Bundle();
                bundle.putString("myName", name);
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
