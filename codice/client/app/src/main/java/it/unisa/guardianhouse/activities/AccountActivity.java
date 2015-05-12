package it.unisa.guardianhouse.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import it.unisa.guardianhouse.R;


public class AccountActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // richiamo i vari relative layout
        RelativeLayout nameRelative = (RelativeLayout) findViewById(R.id.relative_name);
        RelativeLayout surnameRelative = (RelativeLayout) findViewById(R.id.relative_surname);
        RelativeLayout emailRelative = (RelativeLayout) findViewById(R.id.relative_email);
        RelativeLayout usernameRelative = (RelativeLayout) findViewById(R.id.relative_username);
        RelativeLayout passwordRelative = (RelativeLayout) findViewById(R.id.relative_password);
        RelativeLayout birthdateRelative = (RelativeLayout) findViewById(R.id.relative_birthdate);
        RelativeLayout birthplaceRelative = (RelativeLayout) findViewById(R.id.relative_birthplace);
        RelativeLayout addressRelative = (RelativeLayout) findViewById(R.id.relative_address);
        RelativeLayout phoneRelative = (RelativeLayout) findViewById(R.id.relative_phone);
        RelativeLayout accountTypeRelative = (RelativeLayout) findViewById(R.id.relative_account);
        Button btnDeleteAccount = (Button) findViewById(R.id.button_delete_account);

        nameRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica nome";
                String hint = "Inserisci il nuovo nome";
                showInputDialog(title, hint);
            }
        });

        surnameRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica cognome";
                String hint = "Inserisci il nuovo cognome";
                showInputDialog(title, hint);
            }
        });

        emailRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica indirizzo email";
                String hint = "Inserisci il nuovo indirizzo email";
                showInputDialog(title, hint);
            }
        });

        usernameRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica username";
                String hint = "Inserisci il nuovo username";
                showInputDialog(title, hint);
            }
        });

        passwordRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica password";
                String hint = "Inserisci la nuova password";
                showInputDialog(title, hint);
            }
        });

        birthdateRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica data di nascita";
                String hint = "Inserisci la nuova data di nascita";
                showInputDialog(title, hint);
            }
        });

        birthplaceRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica luogo di nascita";
                String hint = "Inserisci il nuovo luogo di nascita";
                showInputDialog(title, hint);
            }
        });

        addressRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica indirizzo";
                String hint = "Inserisci il nuovo indirizzo";
                showInputDialog(title, hint);
            }
        });

        phoneRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = "Modifica telefono";
                String hint = "Inserisci il nuovo telefono";
                showInputDialog(title, hint);
            }
        });

        accountTypeRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // per ora non fa nulla
            }
        });

        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showAlertDialog();
            }
        });

    }

    protected void showInputDialog(String title, String hint) {

        LayoutInflater layoutInflater = LayoutInflater.from(AccountActivity.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AccountActivity.this);
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

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AccountActivity.this);

        // set title
        alertDialogBuilder.setTitle("Elimina account");

        // set dialog message
        alertDialogBuilder
                .setMessage("Sei sicuro di voler eliminare l'account?")
                .setCancelable(false)
                .setPositiveButton("SI",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        AccountActivity.this.finish();
                    }
                })
                .setNegativeButton("NO",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
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
