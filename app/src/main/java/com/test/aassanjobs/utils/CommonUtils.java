package com.test.aassanjobs.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Darshan on 11-08-2017.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */

public class CommonUtils {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Check if WIFI or Mobile data is connected or not
     *
     * @param context1 context
     * @return TRUE if connected else FALSE
     */
    public static boolean isConnectionAvailable(Context context1) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context1.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null) {
                String type = info.getTypeName();
                if (type.equalsIgnoreCase("WIFI") || type.equalsIgnoreCase("MOBILE")) {
                    if (info.isConnected())
                        return true;
                }
            }
        }
        return false;
    }
}
