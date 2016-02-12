package com.shaubert.ui.phone;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

public class CountryPickerDialogManager {
    private CountryPickerCallbacks callbacks;
    private String scrollToCountryIsoCode;

    private String tag;
    private FragmentManager fragmentManager;

    public CountryPickerDialogManager(String tag, FragmentManager fragmentManager) {
        this.tag = "county-picker-dialog-fragment-" + tag;
        this.fragmentManager = fragmentManager;
    }

    public void setCallbacks(CountryPickerCallbacks callbacks) {
        this.callbacks = callbacks;
        setCallbacks(callbacks, find());
    }

    public void setScrollToCountryIsoCode(String scrollToCountryIsoCode) {
        this.scrollToCountryIsoCode = scrollToCountryIsoCode;
        setScrollToCountryIsoCode(scrollToCountryIsoCode, find());
    }

    public void hide() {
        CountryPickerDialogFragment fragment = find();
        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .remove(fragment)
                    .commit();
        }
    }

    public void show() {
        if (find() == null) {
            fragmentManager.beginTransaction()
                    .add(createFragment(), tag)
                    .commit();
        }
    }

    private CountryPickerDialogFragment createFragment() {
        CountryPickerDialogFragment fragment = new CountryPickerDialogFragment();
        fragment.setCallbacks(callbacks);
        fragment.setScrollToCountryIsoCode(scrollToCountryIsoCode);
        return fragment;
    }

    public @Nullable CountryPickerDialogFragment find() {
        return (CountryPickerDialogFragment) fragmentManager.findFragmentByTag(tag);
    }

    protected void setCallbacks(CountryPickerCallbacks callbacks, CountryPickerDialogFragment fragment) {
        if (fragment != null) {
            fragment.setCallbacks(callbacks);
        }
    }

    protected void setScrollToCountryIsoCode(String scrollToCountryIsoCode, CountryPickerDialogFragment fragment) {
        if (fragment != null) {
            fragment.setScrollToCountryIsoCode(scrollToCountryIsoCode);
        }
    }

}