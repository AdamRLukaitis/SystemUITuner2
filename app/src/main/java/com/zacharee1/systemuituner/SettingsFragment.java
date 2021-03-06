package com.zacharee1.systemuituner;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Zacha on 4/15/2017.
 */

public class SettingsFragment extends Fragment {
    public View view;
    public MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (getActivity() instanceof MainActivity) {
            activity = (MainActivity) getActivity();
        }

        view = inflater.inflate(R.layout.fragment_settings, container, false);

        TextView title = (TextView) view.findViewById(R.id.title_settings);

        if (activity.sharedPreferences.getBoolean("isDark", false)) {
            title.setTextColor(getResources().getColor(android.R.color.primary_text_dark));
        } else {
            title.setTextColor(getResources().getColor(android.R.color.primary_text_light));
        }

        SharedPreferences sharedPrefs = activity.getSharedPreferences("com.zacharee1.sysuituner", MODE_PRIVATE);

        Switch darkMode = (Switch) view.findViewById(R.id.dark_mode);

        if (sharedPrefs.getBoolean("isDark", false)) {
            darkMode.setChecked(true);
        }

        darkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = activity.getSharedPreferences("com.zacharee1.sysuituner", MODE_PRIVATE).edit();
                if (isChecked) {
                    editor.putBoolean("isDark", true);
                    Utils.changeToTheme(activity, 1);

                } else {
                    editor.putBoolean("isDark", false);
                    Utils.changeToTheme(activity, 0);
                }
                editor.apply();
            }
        });

        return view;
    }
}
