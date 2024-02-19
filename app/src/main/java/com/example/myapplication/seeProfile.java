package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SeeProfile extends AppCompatActivity {

    private TextView mname , mstanding, mmajor, mposition, minterest, mclub;
    private Button Logout;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";

    private static final String KEY_STANDING = "standing";
    private static final String KEY_MAJOR = "major";
    private static final String KEY_POSITION = "position";

    private static final String KEY_Interest = "interest";

    private static final String KEY_CLUB = "clubs";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_profile);

        mname = findViewById(R.id.nameTextView);
        mstanding = findViewById(R.id.majorTextView);
        mmajor = findViewById(R.id.scholarClassTextView);
        mposition = findViewById(R.id.hobbyTextView);
        minterest = findViewById(R.id.emailTextView);
        mclub = findViewById(R.id.phonenumTextView);
        Logout = findViewById(R.id.text_back);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME,null);
        String standing = sharedPreferences.getString(KEY_STANDING,null);
        String major = sharedPreferences.getString(KEY_MAJOR,null);
        String position = sharedPreferences.getString(KEY_POSITION,null);
        String interest = sharedPreferences.getString(KEY_Interest,null);
        String club = sharedPreferences.getString(KEY_CLUB,null);

        mname.setText("Name  -  " + name);
        mstanding.setText("Standing  -  " + standing);
        mmajor.setText("Major  -  " + major);
        mposition.setText("Position  -  " + position);
        minterest.setText("Interests  -  " + interest);
        mclub.setText("Clubs  -  " + club);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(SeeProfile.this, "Back Successful", Toast.LENGTH_SHORT).show();
            }
        });



    }
}