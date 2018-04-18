package com.chan.revernue.filterapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chan.revernue.filterapplication.R;
import com.chan.revernue.filterapplication.fragment.HomeMainFragment;
import com.chan.revernue.filterapplication.fragment.ListItemCustomerProductEqupmentFragment;

public class ListItemCustomerProductEquipmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_customer_product_equipment);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, ListItemCustomerProductEqupmentFragment.newInstance())
                    .commit();
        }
    }
}
