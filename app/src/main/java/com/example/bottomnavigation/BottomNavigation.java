package com.example.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.bottomnavigation.fragments.HomeFragment;
import com.example.bottomnavigation.fragments.MoreFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class BottomNavigation extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        auth = FirebaseAuth.getInstance();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container2, new HomeFragment());
        fragmentTransaction.commit();


         bottomNavigation = findViewById(R.id.bottomNavigation);

         bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                 FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                 switch(item.getItemId() ){
                     case R.id.home:

                         fragmentTransaction.replace(R.id.container2, new HomeFragment());

                         break;

                     case R.id.more:
                         fragmentTransaction.replace(R.id.container2, new MoreFragment());
                         break;

                 }
                 fragmentTransaction.commit();
                 return true;
             }
         });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){


            case R.id.logout:
                auth.signOut();
                Intent intent = new Intent(BottomNavigation.this, SignInActivity.class);
                startActivity(intent);
                break;
        }

        return true;
    }
}