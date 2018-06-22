package com.example.kastu.firebasedemo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    public TextView textViewHead;
    public TextView textViewDesc;
    public ImageView imageurl;
    public ViewPager viewPager;
    public TextView likes;
    public TextView hates;
    public TextView loved;
    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Firebase.setAndroidContext(this);

        textViewHead= findViewById(R.id.textViewHead);
        textViewDesc= findViewById(R.id.textViewDesc);
        imageurl = findViewById(R.id.image);
        likes = findViewById(R.id.likes);
        hates= findViewById(R.id.hates);
        loved= findViewById(R.id.loved);

        mRef = new Firebase("https://first-firebase-project-77c24.firebaseio.com/feeds/data/0");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String,String> map = dataSnapshot.getValue(Map.class);

                String head = map.get("head");
                String desc = map.get("desc");
                String like = map.get("like");
                String hate = map.get("hate");
                String love = map.get("love");

                Log.v("E_Value", "Head " +head);
                Log.v("E_Value", "desc " +desc);
                Log.v("E_Value", "like " +like);
                Log.v("E_Value", "hate " +hate);
                Log.v("E_Value", "love " +love);

                textViewHead.setText(head);
                textViewDesc.setText(desc);
                likes.setText(like);
                hates.setText(hate);
                loved.setText(love);



            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
