package com.example.myapplication11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    private ContextDatabase mydb;
    int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //이름,id,password 주소값 가져오기.
        EditText name=findViewById(R.id.signup_name);
        EditText id=findViewById(R.id.signup_id);
        EditText pw=findViewById(R.id.signup_pw);
        EditText email=findViewById(R.id.signup_email);
        EditText pw_check=findViewById(R.id.signup_pw_check);
        //button6의 주소값 가져오고, button6 실행시 로그인 화면으로 돌아감.
        Button button6=findViewById(R.id.button6);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        //button7실행시 name,id,password의 데이터를 putExtra로 담음.
        Button button7=findViewById(R.id.button7);


                button7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String username = name.getText().toString();
                        String userid = id.getText().toString();
                        String userpw = pw.getText().toString();
                        String useremail = email.getText().toString();
                        String userpw_check = pw_check.getText().toString();
                        //이름,id,password의 입력칸이 하나라도 비었을시 알림이 뜸.
                        if (username.equals("") || userid.equals("") || userpw.equals("") || useremail.equals("") || userpw_check.equals("")) {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(SignupActivity.this);
                            dialog.setIcon(R.mipmap.ic_launcher);
                            dialog.setTitle("알림");
                            dialog.setMessage("모두 입력하시오.");
                            dialog.setNegativeButton("확인", null);
                            dialog.show();
                        }
                        //모두 입력했을시 데이터를 담고 회원가입 성향파악 화면으로 돌아감
                        else {
                            if (userpw.equals(userpw_check)) {
                                Map<String, Object> user = new HashMap<>();
                                user.put("userid", userid);
                                user.put("username", username);
                                user.put("password", userpw);
                                db.collection("information")
                                        .add(user);

                                Intent intent = new Intent(getApplicationContext(), Signup_preferenceActivity.class);
                                startActivity(intent);
                            } else {

                                AlertDialog.Builder dialog = new AlertDialog.Builder(SignupActivity.this);
                                dialog.setIcon(R.mipmap.ic_launcher);
                                dialog.setTitle("알림");
                                dialog.setMessage("비밀번호가 일치하지않습니다");
                                dialog.setNegativeButton("확인", null);
                                dialog.show();
                            }


                        }
                    }
                });
    }
       }

