package com.shaubert.ui.phone;

import android.content.Context;
import android.text.TextUtils;

import java.util.*;

public class Countries {
    private Context appContext;
    private List<Country> countries = new ArrayList<>();
    private Map<String, Country> isoCountriesMap = new HashMap<>();
    private Map<Country, String> displayCountryNames = new HashMap<>();
    private String language;

    private final Comparator<Country> countryComparator = new Comparator<Country>() {
        @Override
        public int compare(Country country1, Country country2) {
            String lhsName = displayCountryNames.get(country1);
            String rhsName = displayCountryNames.get(country2);
            return lhsName.compareTo(rhsName);
        }
    };

    private static Countries instance;

    public static synchronized Countries get(Context context) {
        if (instance == null) {
            List<Country> countries = Utils.parseCountries(Utils.getCountriesJSON(context));
            instance = new Countries(countries, context);
        }

        return instance;
    }

    private Countries(List<Country> countries, Context context) {
        this.countries = countries;
        this.appContext = context.getApplicationContext();

        for (Country country : countries) {
            isoCountriesMap.put(country.getIsoCode().toLowerCase(Locale.US), country);
        }

        updateDisplayCountyNamesIfNeeded();
    }

    private void updateDisplayCountyNamesIfNeeded() {
        String newLanguage = appContext.getResources().getConfiguration().locale.getLanguage();
        if (TextUtils.equals(newLanguage, language)) {
            return;
        }

        language = newLanguage;
        displayCountryNames.clear();
        for (Country country : countries) {
            String name = new Locale(language, country.getIsoCode()).getDisplayCountry();
            displayCountryNames.put(country, name);
        }

        Collections.sort(countries, countryComparator);
    }

    public List<Country> getCountries() {
        return new ArrayList<>(countries);
    }

    public Country getCountryByIso(String iso) {
        if (TextUtils.isEmpty(iso)) {
            return null;
        }
        return isoCountriesMap.get(iso.toLowerCase(Locale.US));
    }

    public String getDisplayCountryName(Country country) {
        updateDisplayCountyNamesIfNeeded();
        return displayCountryNames.get(country);
    }

    public int getFlagResId(Country country) {
        return Utils.getCountryResId(appContext, country);
    }

}