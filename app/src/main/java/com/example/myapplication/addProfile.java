package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class addProfile extends AppCompatActivity {

    private EditText mname , mstanding, mmajor, mposition, minterest, mclub;
    private Button msave;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";

    private static final String KEY_STANDING = "standing";
    private static final String KEY_MAJOR = "major";
    private static final String KEY_POSITION = "position";

    private static final String KEY_Interest = "interest";

    private static final String KEY_CLUB = "clubs";
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("ProfileInfo");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addprofile);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String name = sharedPreferences.getString(KEY_NAME,null);


        mname = findViewById(R.id.name);
        mstanding = findViewById(R.id.college_standing);
        mmajor = findViewById(R.id.major);
        mposition = findViewById(R.id.position);
        minterest = findViewById(R.id.interest);
        mclub = findViewById(R.id.clubs);
        msave = findViewById(R.id.btn_save);

        msave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME,mname.getText().toString());
                editor.putString(KEY_STANDING,mstanding.getText().toString());
                editor.putString(KEY_MAJOR,mmajor.getText().toString());
                editor.putString(KEY_POSITION,mposition.getText().toString());
                editor.putString(KEY_Interest,minterest.getText().toString());
                editor.putString(KEY_CLUB,mclub.getText().toString());
                editor.apply();

                String name = mname.getText().toString();
                String standing = mstanding.getText().toString();
                String major = mmajor.getText().toString();
                String interest = minterest.getText().toString();
                String position = mposition.getText().toString();
                String club = mclub.getText().toString();

//              root.setValue(name);
                HashMap<String, String > profileMap = new HashMap<>();
                profileMap.put("name",name);
                profileMap.put("standing",standing);
                profileMap.put("major",major);
                profileMap.put("interest",interest);
                profileMap.put("position",position);
                profileMap.put("club",club);
                root.push().setValue(profileMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(addProfile.this, "Saved", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), SeeProfile.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }
}