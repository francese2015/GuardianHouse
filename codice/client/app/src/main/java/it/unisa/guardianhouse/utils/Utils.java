package it.unisa.guardianhouse.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import it.unisa.guardianhouse.R;

public class Utils {

    public static boolean hasConnection(Context c) {

        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }

        return false;

    }


    //Chiama il Layour "Nessun Risultato nel caso di ricerca"

    public static View getNoResultView(Context c) {

        LayoutInflater inf = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inf.inflate(R.layout.empty_list, null);

        return v;
    }


    //Mostra un alert con il bottone "OK"

    public static void showAlertView(Activity act, int resIdTitle, int resIdMessage) {

        AlertDialog.Builder alert = new AlertDialog.Builder(act);
        alert.setTitle(resIdTitle);
        alert.setMessage(resIdMessage);
        alert.setPositiveButton(act.getResources().getString(R.string.ok),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });

        alert.create();
        alert.show();
    }




    public static void showAlertView(Activity act, int resIdTitle, String message) {

        AlertDialog.Builder alert = new AlertDialog.Builder(act);
        alert.setTitle(resIdTitle);
        alert.setMessage(message);
        alert.setPositiveButton(act.getResources().getString(R.string.ok),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });

        alert.create();
        alert.show();
    }

    public static String getStringFromResource(Context c, int resid) {

        return c.getResources().getString(resid);
    }

    public static void showNotifier(Activity c, int offsetY) {


        LayoutInflater inf = (LayoutInflater) c.getLayoutInflater();
        View v = inf.inflate(R.layout.no_results, (ViewGroup) c.findViewById(R.id.root));
        v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        Toast toast = new Toast(c);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, offsetY);
        toast.setView(v);
//        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }


    public static void showNotifier(Activity c, int offsetY, int stringId) {

        LayoutInflater inf = (LayoutInflater) c.getLayoutInflater();
        View v = inf.inflate(R.layout.notifier_results, (ViewGroup) c.findViewById(R.id.root));
        v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView tvNotifier = (TextView) v.findViewById(R.id.tvNotifier);
        tvNotifier.setText(stringId);

        Toast toast = new Toast(c);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, offsetY);
        toast.setView(v);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}


