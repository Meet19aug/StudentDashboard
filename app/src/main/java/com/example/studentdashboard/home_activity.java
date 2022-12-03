package com.example.studentdashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

public class home_activity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    private MyFragmentAdapter adapter;
    public String emailofuser="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Hello Student");
        
        Bundle extras = getIntent().getExtras();
        emailofuser = extras.getString("emailofuser");

        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

        
        tabLayout = findViewById(R.id.TL);
        viewPager2  = findViewById(R.id.VP);

        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("General Notice"));
        tabLayout.addTab(tabLayout.newTab().setText("Add Notice"));
        tabLayout.addTab(tabLayout.newTab().setText("Map"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new MyFragmentAdapter(fragmentManager,getLifecycle());
        viewPager2.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
    public String getMyData(){
        return emailofuser;
    }
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        // To integrate the menu layout's data using inflater.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout)
            gotoRegisterPage();

        return super.onOptionsItemSelected(item);

    }

    private void gotoRegisterPage() {
        finish();
        Toast.makeText(home_activity.this, "Logout Sucessfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(home_activity.this, MainActivity.class);
        startActivity(intent);
    }
}