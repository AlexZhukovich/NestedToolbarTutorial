package com.alexzh.nestedtoolbartutorial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {
    public final static String COUNTRY = "country";

    public static Fragment newInstance(String country) {
        Fragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(COUNTRY, country);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        TextView mCountry = (TextView) rootView.findViewById(R.id.countryTextView);
        mCountry.setText(getArguments().getString(COUNTRY));

        return rootView;
    }
}
