package com.example.kastu.firebasedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity {

    private Button  signOutButton;
    private TextView helloUserText;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private Button submitButton;
    private EditText name;

    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Firebase.setAndroidContext(this);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        signOutButton = (Button) findViewById(R.id.sign_out);
        helloUserText = (TextView) findViewById(R.id.text_user);

        submitButton = (Button) findViewById(R.id.submit);
        name = (EditText) findViewById(R.id.name);

        mRef = new Firebase("https://first-firebase-project-77c24.firebaseio.com/");

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // if user is null launch login activity
                    startActivity(new Intent(UserActivity.this, LoginActivity.class));
                    finish();
                }else{
                    helloUserText.setText("Hello  " + user.getEmail() +"");

                    //final DatabaseReference pathReference = FirebaseDatabase.getInstance().getReference("Users");

                    submitButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //DatabaseReference pathReference = FirebaseDatabase.getInstance().getReference().child("Users");
                            //pathReference.child("Name").setValue(name.getText().toString());
                            Firebase mRefChild = mRef.child("Name");
                            mRefChild.setValue(name.getText().toString());
                            startActivity(new Intent(UserActivity.this,DetailActivity.class));

                        }
                    });
                }
            }
        };



        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutButton();
            }
        });

    }

    //sign out method
    public void signOutButton() {
        auth.signOut();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
