package com.cmtruong.udacity.views;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.cmtruong.udacity.R;

/**
 * Created by davidetruong on 20/03/2018.
 *
 * @author davidetruong
 * @version 1.0
 */

public class SettingsFragment extends PreferenceFragment {

    private static final String TAG = SettingsFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
    }
}
