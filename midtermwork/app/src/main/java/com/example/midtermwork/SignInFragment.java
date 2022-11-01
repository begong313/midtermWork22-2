package com.example.midtermwork;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
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

public class SignInFragment extends Fragment {
    Button signInButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_sign_in, container, false);
        Context context = getContext();

        //database이용위한 setting
        String uriStr = "content://com.example.midtermwork/user";
        Uri uri = new Uri.Builder().build().parse(uriStr);
        Cursor cursor = context.getContentResolver().query(uri , null, null, null, null);

        //회원가입 버튼 눌렸을때
        signInButton = rootView.findViewById(R.id.registUserButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원가입 창의 작성된것들 가져오기
                String id =((TextView)rootView.findViewById(R.id.importRegitId)).getText().toString();
                String passWord =((TextView)rootView.findViewById(R.id.importRegitPW)).getText().toString();
                String checkPassWord =((TextView)rootView.findViewById(R.id.checkImportRegitPW)).getText().toString();
                String name =((TextView)rootView.findViewById(R.id.importName)).getText().toString();
                String phoneNum =((TextView)rootView.findViewById(R.id.importPhone)).getText().toString();
                String address =((TextView)rootView.findViewById(R.id.importLocate)).getText().toString();
                Boolean agree = ((RadioButton)rootView.findViewById(R.id.usingAgreeButton)).isChecked();

                //객체안에 담아서
                ContentValues values = new ContentValues();
                values.put("id", id);
                values.put("passWord", passWord);
                values.put("name", name);
                values.put("phoneNum", phoneNum);
                values.put("address",address);

                Boolean passWordCorrect = passWord.equals(checkPassWord); //비밀번호 일치확인
                Boolean passWordLengthCheck = passWord.length()>=12; // id 길이확인
                Boolean passWordContainCheck= false; // 특수문자 포함확인
                Boolean idCheck = true; // 아이디 중복확인

                //id 중복확인
                String[] columns = new String[]{"id"};
                context.getContentResolver().query(uri, columns,null,null,"name ASC");
                while (cursor.moveToNext()){
                    @SuppressLint("Range") String loadedId = cursor.getString(cursor.getColumnIndex("id"));
                    if (id.equals(loadedId)){
                        idCheck = false;
                        break;
                    }
                }

                // 특수문자 포함 확인
                for (int i = 0 ; i<passWord.length(); i++){
                    char temp = passWord.charAt(i);
                    if((48<= temp && temp<=57) || (65<=temp && 90<=temp )||(97<=temp && temp<=122)){
                        continue;
                    }else{
                        passWordContainCheck = true;
                        break;
                    }
                }

                //문제 없을때
                if (agree&&passWordCorrect&&idCheck && passWordContainCheck && passWordLengthCheck){
                    //db에 정보 추가
                    context.getContentResolver().insert(uri,values);
                    // 회원 가입 완료 시 창 닫고, 가입완료 message 출력
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.onFragmentChanged(0);// loginfragment 로 이동
                    Toast.makeText(context,"회원가입 완료.", Toast.LENGTH_LONG).show();
                    return;
                }else{
                    String notice="";
                    if (!idCheck){
                        notice = notice.concat("중복된 아이디입니다 \n");
                    }
                    if (!agree){
                        notice = notice.concat("약관에 동의하십시오 \n");
                    }
                    if (!passWordCorrect){
                        notice =notice.concat("비밀번호가 일치하지 않습니다. \n ");
                    }
                    if (!passWordContainCheck){
                        notice =notice.concat("비밀번호는 특수문자를 포함해야합니다 \n");
                    }
                    if (!passWordLengthCheck){
                        notice =notice.concat("비밀번호 길이는 12자 이상입니다. \n");
                    }
                    Toast.makeText(context,notice, Toast.LENGTH_LONG).show();
                }
            }
        });

        return rootView;
    }
}