package it.unisa.guardianhouse.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import it.unisa.guardianhouse.R;


public class ApartmentFragment extends Fragment {


    public ApartmentFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apartment, container, false);

        RelativeLayout dimensionRelative = (RelativeLayout) view.findViewById(R.id.relative_dimension);
        RelativeLayout statusRelative = (RelativeLayout) view.findViewById(R.id.relative_status);
        RelativeLayout priceRelative = (RelativeLayout) view.findViewById(R.id.relative_price);
        RelativeLayout descriptionRelative = (RelativeLayout) view.findViewById(R.id.relative_description);
        RelativeLayout positionRelative = (RelativeLayout) view.findViewById(R.id.relative_position);
        RelativeLayout poiRelative = (RelativeLayout) view.findViewById(R.id.relative_poi);
        RelativeLayout thermicRelative = (RelativeLayout) view.findViewById(R.id.relative_thermic);
        RelativeLayout servicesRelative = (RelativeLayout) view.findViewById(R.id.relative_services);
        RelativeLayout testRelative = (RelativeLayout) view.findViewById(R.id.relative_test);
        Button btnDeleteApartment = (Button) view.findViewById(R.id.button_delete_apartment);

        /*

        dimensionRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica Dimensione";
                String hint = "Inserisci nuove dimensioni";
                showInputDialog(title, hint);
            }
        });

        statusRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica Status/Condizioni";
                String hint = "Inserisci nuovo Status/Condizioni";
                showInputDialog(title, hint);
            }
        });

        priceRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica il prezzo mensile";
                String hint = "Inserisci nuovo prezzo";
                showInputDialog(title, hint);
            }
        });

        descriptionRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica descrizione";
                String hint = "Inserisci nuova descrizione";
                showInputDialog(title, hint);
            }
        });

        positionRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica Collegamenti";
                String hint = "Inserisci nuovi Collegamenti";
                showInputDialog(title, hint);
            }
        });

        poiRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica Vicinanza ai Punti d'Interesse";
                String hint = "Inserisci Nuovi Punti d'Interesse";
                showInputDialog(title, hint);
            }
        });

        thermicRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica Descrizione Capacita' Termica";
                String hint = "Inserisci Nuova Descrizione";
                showInputDialog(title, hint);
            }
        });

        servicesRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica Servizi Standard";
                String hint = "Inserisci Nuovi Servizi Standard";
                showInputDialog(title, hint);
            }
        });

        testRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Quanto Puzza?";
                String hint = "Inserisci un valore minimo di 1000";
                showInputDialog(title, hint);
            }
        });

        */

        btnDeleteApartment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        return view;
    }

    protected void showInputDialog(String title, String hint) {

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(promptView);

        alertDialogBuilder.setTitle(title);
        final EditText dialogHint = (EditText) promptView.findViewById(R.id.editText);
        dialogHint.setHint(hint);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //resultText.setText("Hello, " + editText.getText());
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    protected void showAlertDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        // set title
        alertDialogBuilder.setTitle("Elimina appartamento");

        // set dialog message
        alertDialogBuilder
                .setMessage("Sei sicuro di voler eliminare la scheda appartamento?")
                .setCancelable(false)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        getActivity().finish();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

}
