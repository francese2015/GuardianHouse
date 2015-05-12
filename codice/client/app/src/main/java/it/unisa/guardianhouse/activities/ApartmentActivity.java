package it.unisa.guardianhouse.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import it.unisa.guardianhouse.R;


public class ApartmentActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RelativeLayout dimensionRelative = (RelativeLayout) findViewById(R.id.relative_dimension);
        RelativeLayout statusRelative = (RelativeLayout) findViewById(R.id.relative_status);
        RelativeLayout priceRelative = (RelativeLayout) findViewById(R.id.relative_price);
        RelativeLayout descriptionRelative = (RelativeLayout) findViewById(R.id.relative_description);
        RelativeLayout positionRelative = (RelativeLayout) findViewById(R.id.relative_position);
        RelativeLayout poiRelative = (RelativeLayout) findViewById(R.id.relative_poi);
        RelativeLayout thermicRelative = (RelativeLayout) findViewById(R.id.relative_thermic);
        RelativeLayout servicesRelative = (RelativeLayout) findViewById(R.id.relative_services);
        RelativeLayout testRelative = (RelativeLayout) findViewById(R.id.relative_test);
        Button btnDeleteApartment = (Button) findViewById(R.id.button_delete_apartment);

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
                String title = "Modifica Descrizione Capacità Termica";
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

        btnDeleteApartment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showAlertDialog();
            }
        });


    }

    protected void showInputDialog(String title, String hint) {

        LayoutInflater layoutInflater = LayoutInflater.from(ApartmentActivity.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ApartmentActivity.this);
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

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ApartmentActivity.this);

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
                        ApartmentActivity.this.finish();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
