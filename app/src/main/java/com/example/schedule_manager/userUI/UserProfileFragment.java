package com.example.schedule_manager.userUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.schedule_manager.R;

public class UserProfileFragment extends Fragment {

    private ImageButton imagebtn;
    private TextView username, specialty;

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
        specialty= (TextView) view.findViewById(R.id.Specialty);
        username = (TextView) view.findViewById(R.id.User_UserName);
        specialty.append(" "+ UserLoginActivity.getEidikotita());
        username.setText(UserLoginActivity.getUsername());

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
