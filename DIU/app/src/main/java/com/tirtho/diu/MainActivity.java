package com.tirtho.diu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences=getSharedPreferences(LoginActivity.mySharedPreference, Context.MODE_PRIVATE);

        boolean isLogin=preferences.getBoolean(LoginActivity.isLogin,false);

        if (!isLogin){
            Intent intent= new Intent(this,LoginActivity.class);
            startActivity(intent);
            this.finish();
        }

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });







        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }







    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_logout) {

            SharedPreferences.Editor editor= preferences.edit();
            editor.putBoolean(LoginActivity.isLogin,false);
            editor.commit();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            this.finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_class_routine ) {
            startActivity(new Intent(MainActivity.this,ClassRoutine.class));
        } else if (id == R.id.nav_class_notice) {

        } else if (id == R.id.nav_account_details) {

        } else if (id == R.id.nav_academic_result) {

        }else if (id == R.id.nav_class_academic_calender) {
            startActivity(new Intent(MainActivity.this,AcademicCalendar.class));
        }else if (id == R.id.nav_administration) {
            startActivity(new Intent(MainActivity.this,Administration.class));
        }else if (id == R.id.nav_faculity_members) {
            startActivity(new Intent(MainActivity.this,FaculityMenber.class));
        }else if (id == R.id.nav_student_comunity) {
            startActivity(new Intent(MainActivity.this,StudentsCommunity.class));
        }else if (id == R.id.nav_transport) {
            startActivity(new Intent(MainActivity.this,Transport.class));
        }else if (id == R.id.nav_photo_gallery) {
            startActivity(new Intent(MainActivity.this,Gallery.class));
        }else if (id == R.id.nav_class_academic_calender) {
            startActivity(new Intent(MainActivity.this,AcademicCalendar.class));
        }else if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this,AboutUs.class));
        }else if (id == R.id.nav_rules) {
            startActivity(new Intent(MainActivity.this,RulesAndRegulation.class));
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
