package com.example.myapplication11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup_preferenceActivity2 extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // 데이터 생성
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);

        CheckedTextView ckdTv1 = findViewById(R.id.ckdTv1);
        CheckedTextView ckdTv2 = findViewById(R.id.ckdTv2);
        CheckedTextView ckdTv3 = findViewById(R.id.ckdTv3);

        ckdTv1.setChecked(false);
        ckdTv2.setChecked(false);
        ckdTv3.setChecked(false);
        ckdTv1.setClickable(true);
        ckdTv2.setClickable(true);
        ckdTv3.setClickable(true);


        View.OnClickListener listner = new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {
                ((CheckedTextView) view).toggle();
            }};

        ckdTv1.setOnClickListener(listner);
        ckdTv2.setOnClickListener(listner);
        ckdTv3.setOnClickListener(listner);
        Button button7=findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> user = new HashMap<>();
                if (ckdTv1.isChecked()) {
                    user.put("user_preference_2", "지성");
                }
                if (ckdTv2.isChecked()) {
                    user.put("user_preference_2", "건성");
                }
                if (ckdTv3.isChecked()) {
                    user.put("user_preference_2", "0");
                }
                db.collection("information")
                        .add(user);

                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}


