package com.chan.revernue.filterapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class HomeMainFragment extends Fragment {
    Realm realm;
    RecyclerView recyclerView;
    ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;
    ListCustomerAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    static String jsonData;
    static String customer_name, product_id_connected, customer_status, customer_adress, id_member,id_user;

    public static List<ListCustomerDao> categoery;

    private static final OkHttpClient client = new OkHttpClient();

    public HomeMainFragment() {
        super();
    }

    public static HomeMainFragment newInstance() {
        HomeMainFragment fragment = new HomeMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_main, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        Realm.init(getContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(config);
        categoery = new ArrayList<ListCustomerDao>();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
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

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
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
                    getActivity().runOnUiThread(new Runnable() {
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
                                    product_id_connected = jsonObject2.getString("product_id_connected");
                                    customer_adress = jsonObject2.getString("customer_adress");
                                    id_member = jsonObject2.getString("id_member");


                                    categoery.add(new ListCustomerDao(customer_name, product_id_connected, customer_status, customer_adress, id_member));
                                }

                                // use a linear layout manager
                                mLayoutManager = new LinearLayoutManager(getContext());
                                recyclerView.setLayoutManager(mLayoutManager);

                                mAdapter = new ListCustomerAdapter(getActivity(), categoery);
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
