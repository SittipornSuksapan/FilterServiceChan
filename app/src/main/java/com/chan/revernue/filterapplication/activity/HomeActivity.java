package com.chan.revernue.filterapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chan.revernue.filterapplication.R;
import com.chan.revernue.filterapplication.adapter.CategoryAdapter;
import com.chan.revernue.filterapplication.adapter.ListCustomerAdapter;
import com.chan.revernue.filterapplication.manager.http.ApiService;
import com.chan.revernue.filterapplication.transaction.dao.ListItemCustomerDataDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity {

    TextView tvIdCustomer, tvName, tvAddress;
    String member_id_data, customer_id_data;

    RecyclerView recyclerView;
    private List<ListItemCustomerDataDao> categoery;
    CategoryAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    static String jsonData;
    static String customer_id, customer_name, customer_adress
            ,product_id,product_brand, product_id_connected,product_name,product_system
            ,equipment_id,equipment_brand,equipment_id_connected,equipment_type,equipment_description,equipment_spare_parts,equipment_warning_date,equipment_status,equipment_installation_date
            ,process;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();

        customer_id_data = intent.getStringExtra("customer_id");
        member_id_data = intent.getStringExtra("member_id");

        initInstances();


    }

    private void initInstances() {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState
        tvIdCustomer = (TextView) findViewById(R.id.tvIdCustomer);
        tvName = (TextView) findViewById(R.id.tvName);
        tvAddress = (TextView) findViewById(R.id.tvAddress);


        categoery = new ArrayList<ListItemCustomerDataDao>();
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

//        tvIdCustomer.setText(customer_id);
//        tvName.setText(name);
//        tvAddress.setText(address);

        callgetMemberData(member_id_data, customer_id_data);
    }

    private void callgetMemberData(String IDmember,String IDcustomer) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .build();

        ApiService api = retrofit.create(ApiService.class);
        api.getListItemCustomer(IDmember, IDcustomer).enqueue(new retrofit2.Callback<ResponseBody>() {
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
                                JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                                customer_id = jsonObject1.getString("customer_id");
                                customer_name = jsonObject1.getString("customer_name");
                                customer_adress = jsonObject1.getString("customer_adress");
                                JSONArray productArray = jsonObject1.getJSONArray("product");

                                 product_id = null;
                                 product_brand = null;
                                 product_id_connected = null;
                                 product_name = null;
                                 product_system = null;
                                 equipment_id = null;
                                 equipment_brand = null;
                                 equipment_id_connected = null;
                                 equipment_type = null;
                                 equipment_description = null;
                                 equipment_spare_parts = null;
                                 equipment_warning_date = null;
                                 equipment_status = null;
                                 equipment_installation_date = null;
                                 process = "0";

                                 categoery.add(new ListItemCustomerDataDao(customer_id, customer_name, customer_adress
                                        ,product_id,product_brand, product_id_connected,product_name,product_system
                                        ,equipment_id,equipment_brand,equipment_id_connected,equipment_type,equipment_description,equipment_spare_parts,equipment_warning_date,equipment_status,equipment_installation_date
                                        ,process));

                                 for (int i = 0; i < productArray.length(); i++){

                                     JSONObject productJsonObject = productArray.getJSONObject(i);
                                     JSONArray equipmentArray = productJsonObject.getJSONArray("equipment");
                                     product_id = productJsonObject.getString("product_name");
                                     product_brand = productJsonObject.getString("product_brand");
                                     product_id_connected = productJsonObject.getString("product_id_connected");
                                     product_name = productJsonObject.getString("product_name");
                                     product_system = productJsonObject.getString("product_system");


                                     customer_id = null;
                                     customer_name = null;
                                     customer_adress = null;
                                     equipment_id = null;
                                     equipment_brand = null;
                                     equipment_id_connected = null;
                                     equipment_type = null;
                                     equipment_description = null;
                                     equipment_spare_parts = null;
                                     equipment_warning_date = null;
                                     equipment_status = null;
                                     equipment_installation_date = null;
                                     process = "1";

                                     categoery.add(new ListItemCustomerDataDao(customer_id, customer_name, customer_adress
                                             ,product_id,product_brand, product_id_connected,product_name,product_system
                                             ,equipment_id,equipment_brand,equipment_id_connected,equipment_type,equipment_description,equipment_spare_parts,equipment_warning_date,equipment_status,equipment_installation_date
                                             ,process));

                                     for (int j = 0; j < equipmentArray.length(); j++){

                                         JSONObject equipmentJsonObject = equipmentArray.getJSONObject(j);

                                         equipment_id = equipmentJsonObject.getString("equipment_id");
                                         equipment_id_connected = equipmentJsonObject.getString("equipment_id_connected");
                                         equipment_type = equipmentJsonObject.getString("equipment_type");
                                         equipment_description = equipmentJsonObject.getString("equipment_description");
                                         equipment_spare_parts = equipmentJsonObject.getString("equipment_spare_parts");
                                         equipment_warning_date = equipmentJsonObject.getString("equipment_warning_date");
                                         equipment_status = equipmentJsonObject.getString("equipment_status");
                                         equipment_installation_date = equipmentJsonObject.getString("equipment_installation_date");

                                         customer_id = null;
                                         customer_name = null;
                                         customer_adress = null;
                                         product_id = null;
                                         product_brand = null;
                                         product_id_connected = null;
                                         product_name = null;
                                         product_system = null;
                                         process = "2";

                                         categoery.add(new ListItemCustomerDataDao(customer_id, customer_name, customer_adress
                                                 ,product_id,product_brand, product_id_connected,product_name,product_system
                                                 ,equipment_id, equipment_brand,equipment_id_connected,equipment_type,equipment_description,equipment_spare_parts,equipment_warning_date,equipment_status,equipment_installation_date
                                                 ,process));

                                     }
                                 }

                                // use a linear layout manager
                                mLayoutManager = new LinearLayoutManager(HomeActivity.this);
                                recyclerView.setLayoutManager(mLayoutManager);

                                mAdapter = new CategoryAdapter(getApplicationContext(), categoery);
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
