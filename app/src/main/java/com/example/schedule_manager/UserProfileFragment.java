package com.example.schedule_manager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserProfileFragment extends Fragment {

    private ImageButton imagebtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.user_profile_fragment, container, false);
        /*// create ContextThemeWrapper from the original Activity Context with the custom theme
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_MaterialComponents_DayNight_DarkActionBar);

        // clone the inflater using the ContextThemeWrapper
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

        // inflate the layout using the cloned inflater, not default inflater
        return inflater.inflate(R.layout.user_profile_fragment, container, false);*/


        View view = inflater.inflate(R.layout.user_profile_fragment, container, false);

        imagebtn = (ImageButton) view.findViewById(R.id.settingsBTN);

        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySettings();
            }
        });

        return view;

    }


    public void openActivitySettings(){
        Intent intent = new Intent((getActivity()), UserProfileSettings.class);
        startActivity(intent);
    }



}
