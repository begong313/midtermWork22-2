package com.example.midtermwork;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    LoginFragment loginFragment; //0
    ItemShowFragment listFragment; //1
    SignInFragment signInFragment;  // 2
    UserInfomation userInfomation; //3


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //로그인 안돼있을때는 button nav  default 값을 setting으로
        //로그인 되어있을때는 home으로
        loginFragment = new LoginFragment();
        listFragment = new ItemShowFragment();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);

        //첫 화면 로딩 시 로그인 데이터가 있으면 Home 아니면 setting으환
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        Boolean loginDataExist = sharedPreferences.getBoolean("check" ,false);

        if (loginDataExist){
            bottomNavigation.getMenu().findItem(R.id.home).setChecked(true);
            onFragmentChanged(1);
        }else{
            bottomNavigation.getMenu().findItem(R.id.setting).setChecked(true);
            onFragmentChanged(0);
        }

        //메뉴바 클릭 시 동작
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        onFragmentChanged(1);
                        return true;
                    case R.id.setting:
                        if (loginDataExist){
                            onFragmentChanged(3);
                        }else {
                            onFragmentChanged(0);
                        }
                        return true;
                }
                return false;
            }
        });
    }
    public void onFragmentChanged(int index) {
        switch (index) {
            case 0: //logIn
                loginFragment = new LoginFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, loginFragment).commit();
                break;
            case 1: //itemList
                getSupportFragmentManager().beginTransaction().replace(R.id.container, listFragment).commit();
                break;
            case 2: //signIn
                signInFragment = new SignInFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, signInFragment).commit();
                break;
            case 3:
                userInfomation = new UserInfomation();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, userInfomation).commit();
                break;
        }
    }

}