package com.example.schedule_manager.adminUI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.schedule_manager.Ergazomenoi;
import com.example.schedule_manager.R;

import java.util.ArrayList;

public class AdminShowEmployeesListAdapter extends ArrayAdapter<Ergazomenoi> {

    public AdminShowEmployeesListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Ergazomenoi> customListAdminEmployeesArraylist) {
        super(context, resource, customListAdminEmployeesArraylist);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_admin_employees,parent,false);

        }

        Ergazomenoi currentItem = getItem(position);


        TextView id = listItemView.findViewById(R.id.idEmployee);
        id.setText(Integer.toString(currentItem.getErg_id()));

        TextView firstname = listItemView.findViewById(R.id.firstNameEmployee);
        firstname.setText(currentItem.getOnoma());

        TextView lastname = listItemView.findViewById(R.id.lastNameEmployee);
        lastname.setText(currentItem.getEpitheto());

        TextView birthday = listItemView.findViewById(R.id.birthEmployee);
        birthday.setText(Integer.toString(currentItem.getEvWres()));

        TextView email = listItemView.findViewById(R.id.emailEmployee);
        email.setText(currentItem.getMail());

        TextView phone = listItemView.findViewById(R.id.phoneEmployee);
        phone.setText(currentItem.getPhone());

        TextView job = listItemView.findViewById(R.id.jobEmployee);
        job.setText(currentItem.getEidikotita());

        return listItemView;
    }
}
