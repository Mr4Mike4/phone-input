package com.shaubert.ui.phone;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.util.Locale;

public class Phones {

    public static String[] getPossibleRegions(Context context) {
        return new String[] {
                getRegionFromSim(context),
                getRegionFromNetwork(context),
                getRegionFromLocale(),
        };
    }

    public static String getRegionFromSim(Context context) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return manager.getSimCountryIso();
        } catch (Exception ignored) {
        }
        return null;
    }

    public static String getRegionFromNetwork(Context context) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return manager.getNetworkCountryIso();
        } catch (Exception ignored) {
        }
        return null;
    }

    public static String getRegionFromLocale() {
        return Locale.getDefault().getCountry();
    }

}
