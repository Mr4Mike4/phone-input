package com.shaubert.ui.phone.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.shaubert.ui.phone.Countries;
import com.shaubert.ui.phone.PhoneInputLayout;

public class PhoneInputLayoutSampleFragment extends Fragment {

    private Button setRandomPhoneButton;
    private Button validatePhoneButton;
    private PhoneInputLayout phoneInputLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.phone_input_layout_sample, container, false);

        phoneInputLayout = (PhoneInputLayout) view.findViewById(R.id.phone_input_layout);

        setRandomPhoneButton = (Button) view.findViewById(R.id.set_random_phone);
        setRandomPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRandomPhone();
            }
        });

        validatePhoneButton = (Button) view.findViewById(R.id.validate);
        validatePhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatePhone();
            }
        });

        return view;
    }

    private void validatePhone() {
        if (phoneInputLayout.isValidPhoneNumber()) {
            Phonenumber.PhoneNumber number = phoneInputLayout.getPhoneNumber();
            String phoneNumber = phoneInputLayout.getFormattedPhoneNumber(PhoneNumberUtil.PhoneNumberFormat.E164);
            Toast.makeText(getContext(), "Valid: " + phoneNumber, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void setRandomPhone() {
        Phonenumber.PhoneNumber phoneNumber = Util.getRandomPhone(Countries.get(getContext()));
        if (phoneNumber != null) {
            phoneInputLayout.setPhoneNumber(phoneNumber);
        }
    }

}
