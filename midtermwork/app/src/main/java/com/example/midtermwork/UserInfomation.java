package com.example.midtermwork;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class UserInfomation extends Fragment {
    TextView userName;
    TextView userId;
    TextView userAddress;
    TextView userPhoneNum;
    Button logOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity mainActivity = (MainActivity) getActivity();
        ViewGroup rootView =(ViewGroup)inflater.inflate(R.layout.fragment_user_infomation, container, false);

        userName = rootView.findViewById(R.id.username);
        userId = rootView.findViewById(R.id.userId);
        userAddress = rootView.findViewById(R.id.useraddress);
        userPhoneNum = rootView.findViewById(R.id.userphonenum);
        logOut = rootView.findViewById(R.id.logOut);


        SharedPreferences sharedPreferences = mainActivity.getPreferences(Context.MODE_PRIVATE);
        Boolean loginDataExist = sharedPreferences.getBoolean("check" ,false);
        if (loginDataExist==true){
            userName.setText(sharedPreferences.getString("name",""));
            userId.setText(sharedPreferences.getString("id",""));
            userAddress.setText(sharedPreferences.getString("address",""));
            userPhoneNum.setText(sharedPreferences.getString("phoneNum",""));
        }


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.clear();
                editor.commit();
                mainActivity.recreate();
            }
        });

        return rootView;
    }
}




