package com.example.midtermwork;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LoginFragment extends Fragment {
    Button loginButton;
    Button signInButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MainActivity mainActivity = (MainActivity) getActivity();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);

        SharedPreferences sharedPreferences = mainActivity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        // 로그인 버튼 클릭 시
        loginButton = rootView.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //db가져오기
                String uriStr = "content://com.example.midtermwork/user";
                Uri uri = new Uri.Builder().build().parse(uriStr);
                Cursor cursor = getContext().getContentResolver().query(uri , null, null, null, null);

                String id =((TextView)rootView.findViewById(R.id.importId)).getText().toString(); //입력된 Id
                String passWord =((TextView)rootView.findViewById(R.id.importPassword)).getText().toString(); //입력된 Pw

                Boolean IDPWCheck = false; //아이디 비번 일치확인
                String[] columns = new String[]{"id","passWord","name","address","phoneNum"};
                getContext().getContentResolver().query(uri, columns,null,null,"name ASC");

                // Cursor 이동하면서 아이디 체크
                while (cursor.moveToNext()){
                    @SuppressLint("Range") String loadedId = cursor.getString(cursor.getColumnIndex("id"));
                    @SuppressLint("Range") String loadedPW = cursor.getString(cursor.getColumnIndex("passWord"));
                    if (id.equals(loadedId) && passWord.equals(loadedPW) ){
                        IDPWCheck = true;
                        break;
                    }
                }


                if (IDPWCheck){
                    // Fragment에 회원정보 저장
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                    @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("address"));
                    @SuppressLint("Range") String phoneNum =cursor.getString(cursor.getColumnIndex("phoneNum"));
                    editor.clear();
                    editor.putBoolean("check",true);
                    editor.putString("id",id);
                    editor.putString("pw",passWord);
                    editor.putString("name",name);
                    editor.putString("address", address);
                    editor.putString("phoneNum", phoneNum);
                    editor.commit();

                    //로그인 성공 후 성공알림, 창 전환, 메뉴바 전환
                    Toast.makeText(getContext(),"login 성공", Toast.LENGTH_SHORT ).show();
                    ((BottomNavigationView)mainActivity.findViewById(R.id.bottom_navigation)).getMenu().findItem(R.id.home).setChecked(true);
                    mainActivity.recreate();
                }else{
                    Toast.makeText(getContext(),"login 실패", Toast.LENGTH_SHORT ).show();
                }
            }
        });


        //회원 가입 버튼 클릭 시
        signInButton = rootView.findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.onFragmentChanged(2);// signinfragment 로 이동
            }
        });


        return rootView;
    }


}