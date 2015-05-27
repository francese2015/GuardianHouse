package it.unisa.guardianhouse.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Utils {

    private static Typeface robotoTypeFace;

    public static void setRobotoFont (Context context, View view)
    {
        if (robotoTypeFace == null)
        {
            robotoTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto/Roboto-Regular.ttf");
        }
        setFont(view, robotoTypeFace);
    }

    private static void setFont (View view, Typeface robotoTypeFace)
    {
        if (view instanceof ViewGroup)
        {
            for (int i = 0; i < ((ViewGroup)view).getChildCount(); i++)
            {
                setFont(((ViewGroup)view).getChildAt(i), robotoTypeFace);
            }
        }
        else if (view instanceof TextView)
        {
            ((TextView) view).setTypeface(robotoTypeFace);
        }
    }

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

}


