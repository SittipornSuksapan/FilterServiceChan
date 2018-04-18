package com.chan.revernue.filterapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


import com.chan.revernue.filterapplication.R;
import com.chan.revernue.filterapplication.fragment.HomeMainFragment;
import com.chan.revernue.filterapplication.transaction.dao.RealmMember;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class HomeMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Realm realm;
    String id_user,name_user;
    ImageView imgUser;
    TextView tvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, HomeMainFragment.newInstance())
                    .commit();
        }

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(config);

        realm.beginTransaction();
        RealmResults<RealmMember> realmMembers = realm.where(RealmMember.class).findAll();
        id_user = realmMembers.get(0).getId();
        name_user = realmMembers.get(0).getMember_fistname();
        realm.commitTransaction();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        tvUser = (TextView)findViewById(R.id.textViewNameMember);
//        imgUser = (ImageView)findViewById(R.id.imageViewNameMember);

//        tvUser.setText(name_user);
//        imgUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RealmResults<RealmMember> Members = realm.where(RealmMember.class).findAll();
//                Members.deleteAllFromRealm();
//                realm.commitTransaction();
//            }
//        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_main, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            realm.beginTransaction();
            RealmResults<RealmMember> Members = realm.where(RealmMember.class).findAll();
                Members.deleteAllFromRealm();
            realm.commitTransaction();
            Intent intent = new Intent(HomeMainActivity.this, LoginActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
