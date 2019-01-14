package com.example.android.moviesmania;

import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


    }

    public static class MoviePreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            Preference sortByPreference = findPreference(getString(R.string.movie_order_by_key));
            bindPreferenceToSummary(sortByPreference);
        }


        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String value = newValue.toString();
            if(preference instanceof ListPreference){
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(value);
                if(index >= 0){
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[index]);
                }

            }
            else {
                preference.setSummary(value);
            }
            return true;
        }

        private void bindPreferenceToSummary(Preference preference){
          preference.setOnPreferenceChangeListener(this);
          SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
          String preferenceString = sharedPreferences.getString(preference.getKey(),null);
          onPreferenceChange(preference,preferenceString);

        }
    }
}
