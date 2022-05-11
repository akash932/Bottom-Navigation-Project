package com.example.bottomnavigation.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.SignInActivity;
import com.example.bottomnavigation.databinding.FragmentMore2Binding;
import com.example.bottomnavigation.models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MoreFragment extends Fragment {

    FragmentMore2Binding binding;

    FirebaseUser user;
    String uId;
    DatabaseReference reference;

    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMore2Binding.inflate(inflater, container, false);

        try {
            user = FirebaseAuth.getInstance().getCurrentUser();
            uId = user.getUid();
        }catch(Exception e){
            e.printStackTrace();
        }
        reference = FirebaseDatabase.getInstance().getReference("Users");


        reference.child(uId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);
                if(users != null){
                    String fullName = users.getUserName();
                    String eMail = users.getMail();

                    binding.textView1.setText("Welcome, "+ fullName + "!");
                    binding.textView3.setText(eMail);
                    binding.textView5.setText(fullName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something wrong happened!", Toast.LENGTH_SHORT).show();
            }
        });



        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(view.getContext(), SignInActivity.class));
            }
        });



        return binding.getRoot();
    }
}