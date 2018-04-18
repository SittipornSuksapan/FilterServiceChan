package com.chan.revernue.filterapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chan.revernue.filterapplication.R;
import com.chan.revernue.filterapplication.adapter.CategoryAdapter;
import com.chan.revernue.filterapplication.adapter.ListCustomerAdapter;
import com.chan.revernue.filterapplication.manager.http.ApiService;
import com.chan.revernue.filterapplication.transaction.dao.ListCustomerDao;
import com.chan.revernue.filterapplication.transaction.dao.ListItemCustomerDataDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ListItemCustomerProductEqupmentFragment extends Fragment {

    TextView tvIdCustomer, tvName, tvAddress;

    RecyclerView recyclerView;
    private ArrayList<ListItemCustomerDataDao> categoery;
    CategoryAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    static String jsonData;
    static String customer_name, product_brand,product_id_connected,
            equipment_status, member_email,
            member_fistname, customer_adress,
            product_name, product_system, equipment_id_connected,
            equipment_type, equipment_description, equipment_spare_parts,
            equipment_warning_date, equipment_installation_date;
    static String member_id,customer_id,name,address;



    public ListItemCustomerProductEqupmentFragment() {
        super();
    }

    public static ListItemCustomerProductEqupmentFragment newInstance() {
        ListItemCustomerProductEqupmentFragment fragment = new ListItemCustomerProductEqupmentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
        Intent intent = new Intent();
        
        member_id = intent.getStringExtra("member_id");
        customer_id = intent.getStringExtra("customer_id");
        name = intent.getStringExtra("customer_name");
        address = intent.getStringExtra("address");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_item_customer_product_equipment, container, false);
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
        tvIdCustomer = (TextView) rootView.findViewById(R.id.tvIdCustomer);
        tvName = (TextView) rootView.findViewById(R.id.tvName);
        tvAddress = (TextView) rootView.findViewById(R.id.tvAddress);



        categoery = new ArrayList<ListItemCustomerDataDao>();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        tvIdCustomer.setText(customer_id);
        tvName.setText(name);
        tvAddress.setText(address);

                callgetMemberData(member_id,customer_id);
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

    private void callgetMemberData(String member_id, String customer_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .build();

        ApiService api = retrofit.create(ApiService.class);
        api.getListItemCustomer(member_id,customer_id).enqueue(new retrofit2.Callback<ResponseBody>() {
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
                                    product_brand = jsonObject2.getString("product_brand");
                                    product_id_connected = jsonObject2.getString("product_id_connected");
                                    equipment_status = jsonObject2.getString("equipment_status");

                                    member_email = jsonObject2.getString("member_email");
                                    member_fistname = jsonObject2.getString("member_fistname");
                                    customer_adress = jsonObject2.getString("customer_adress");
                                    product_name = jsonObject2.getString("product_name");
                                    product_system = jsonObject2.getString("product_system");
                                    equipment_id_connected = jsonObject2.getString("equipment_id_connected");

                                    equipment_type = jsonObject2.getString("equipment_type");
                                    equipment_description = jsonObject2.getString("equipment_description");
                                    equipment_spare_parts = jsonObject2.getString("equipment_spare_parts");
                                    equipment_warning_date = jsonObject2.getString("equipment_warning_date");
                                    equipment_installation_date = jsonObject2.getString("equipment_installation_date");


//                                    categoery.add(new ListItemCustomerDataDao(customer_name, product_brand,product_id_connected,
//                                            equipment_status, member_email,
//                                            member_fistname, customer_adress,
//                                            product_name, product_system, equipment_id_connected,
//                                            equipment_type, equipment_description, equipment_spare_parts,
//                                            equipment_warning_date, equipment_installation_date));
                               }

                                // use a linear layout manager
                                mLayoutManager = new LinearLayoutManager(getContext());
                                recyclerView.setLayoutManager(mLayoutManager);

                                mAdapter = new CategoryAdapter(getActivity(), categoery);
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
