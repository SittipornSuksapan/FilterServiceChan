package com.chan.revernue.filterapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chan.revernue.filterapplication.R;
import com.chan.revernue.filterapplication.adapter.ListCustomerAdapter;
import com.chan.revernue.filterapplication.manager.http.ApiService;
import com.chan.revernue.filterapplication.transaction.dao.ListCustomerDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class ItemCustomerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListCustomerAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    static String jsonData;
    static String customer_name,product_id_connected,customer_status,customer_adress;

    public static List<ListCustomerDao> categoery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_customer);
        initInstances();

    }
    private void initInstances() {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState
        categoery = new ArrayList<ListCustomerDao>();
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        callgetMemberData("21");
    }

    private void callgetMemberData(final String id) {
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
                                    product_id_connected = jsonObject2.getString("product_id_connected");
                                    customer_adress = jsonObject2.getString("customer_adress");


                                    categoery.add(new ListCustomerDao(customer_name,product_id_connected,customer_status,customer_adress,id));
                                }

                                // use a linear layout manager
                                mLayoutManager = new LinearLayoutManager(getApplicationContext());
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
