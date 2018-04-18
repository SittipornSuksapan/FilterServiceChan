package com.chan.revernue.filterapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import android.widget.ListView;
import android.widget.TextView;

import com.chan.revernue.filterapplication.R;
import com.chan.revernue.filterapplication.adapter.ListCustomerAdapter;
import com.chan.revernue.filterapplication.manager.http.ApiService;
import com.chan.revernue.filterapplication.transaction.dao.ListCustomerDao;
import com.chan.revernue.filterapplication.transaction.dao.RealmMember;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class ListCustomerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Realm realm;
    String id_user, name_user;
    ImageView imgUser;
    TextView tvUser;


    RecyclerView recyclerView;
    ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;
    ListCustomerAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    static String jsonData;
    static String customer_name, customer_id, customer_status, customer_adress, id_member;

    public static List<ListCustomerDao> categoery;

    private static final OkHttpClient client = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer);

        initInstances(savedInstanceState);

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

    private void initInstances(Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(config);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        categoery = new ArrayList<ListCustomerDao>();
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);


//        // use a linear layout manager
//        mLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//
//        mAdapter = new CategoryAdapter(getActivity(),categoery);
//        recyclerView.setAdapter(mAdapter);

        realm.beginTransaction();
        RealmResults<RealmMember> realmMembers = realm.where(RealmMember.class).findAll();
        id_user = realmMembers.get(0).getId();
        realm.commitTransaction();
        callgetMemberData(id_user);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                categoery.clear();
                callgetMemberData(id_user);
            }
        });

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
        getMenuInflater().inflate(R.menu.list_customer, menu);
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
            Intent intent = new Intent(ListCustomerActivity.this, LoginActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void callgetMemberData(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .build();

        ApiService api = retrofit.create(ApiService.class);
        api.getListCustomer(id).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                if (!response.isSuccessful()) {
                    try {
                        throw new IOException("Unexpected code " + response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("Response:", response.toString());
                    try {
                        jsonData = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = null;
// JSONArray jsonArray = null;

                                jsonObject = new JSONObject(jsonData);
                                JSONArray jsonArray = jsonObject.getJSONArray("data");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                                    customer_name = jsonObject2.getString("customer_name");
                                    customer_status = jsonObject2.getString("customer_status");
                                    customer_id = jsonObject2.getString("customer_id");
                                    customer_adress = jsonObject2.getString("customer_adress");
                                    id_member = jsonObject2.getString("id_member");


                                    categoery.add(new ListCustomerDao(customer_name, customer_id, customer_status, customer_adress, id_member));
                                }

                                swipeRefreshLayout.setRefreshing(false);
                                // use a linear layout manager
                                mLayoutManager = new LinearLayoutManager(ListCustomerActivity.this);
                                recyclerView.setLayoutManager(mLayoutManager);

                                mAdapter = new ListCustomerAdapter(getApplicationContext(), categoery);
                                recyclerView.setAdapter(mAdapter);


                            } catch (JSONException e) {
                                e.getMessage();
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
