package com.example.kastu.firebasedemo;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Firebase.setAndroidContext(this);

        //auth = FirebaseAuth.getInstance();

       mRef = new Firebase("https://first-firebase-project-77c24.firebaseio.com");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("data");

        recyclerView= findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
     super.onStart();

        FirebaseRecyclerAdapter<ListItem, MyAdapter> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ListItem, MyAdapter>(

                ListItem.class,
                R.layout.list_item,
                MyAdapter.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(MyAdapter viewHolder, ListItem model, int position) {
                viewHolder.setHead(model.getHead());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setLike(model.getLike());
                viewHolder.setHate(model.getHate());
                viewHolder.setLove(model.getLove());
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class MyAdapter extends RecyclerView.ViewHolder{

        View mView;

        public MyAdapter(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setHead(String head) {

            TextView textViewHead = mView.findViewById(R.id.textViewHead);
            textViewHead.setText(head);
        }

        public void setDesc(String desc) {
            TextView textViewDesc = mView.findViewById(R.id.textViewDesc);
            textViewDesc.setText(desc);
        }

        public void setLike(String like) {
            TextView likes = mView.findViewById(R.id.likes);
            likes.setText(like);
        }

        public void setHate(String hate) {
            TextView hates = mView.findViewById(R.id.hates);
            hates.setText(hate);
        }

        public void setLove(String love) {
            TextView loved = mView.findViewById(R.id.loved);
            loved.setText(love);
        }
    }
}
