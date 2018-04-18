package com.chan.revernue.filterapplication.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chan.revernue.filterapplication.R;
import com.chan.revernue.filterapplication.transaction.dao.ListItemCustomerDataDao;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ListItemCustomerDataDao> mDataset;

    public CategoryAdapter(Context activity, List<ListItemCustomerDataDao> categoery) {
        context = activity;
        mDataset = categoery;
    }

//    public ReviewsAdapter(Context context,List<ReviewsDataDao> categoery) {
//        mDataset = categoery;
//        this.context = context;
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_customer_product_equipment_cardview,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((ListViewHolder)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView
                 tvCustomerName, tvCustomerId, tvCustomerAddress, tvProductId, tvProductBrand
                , tvProductName, tvProductSystem,tvEquipmentID,tvEquipmentBrand, tvEquipmentType
                , tvEquipmentDescriptions, tvEquipmentSpareParts, tvEquipmentWarningDate, tvEquipmentInstallationsDate;
        public LinearLayout lyCustomer,lyProduct, lyEquipment;
//
        public ListViewHolder(View itemView){
            super(itemView);
            tvCustomerName = (TextView) itemView.findViewById(R.id.tvCustomerName);
            tvCustomerId = (TextView) itemView.findViewById(R.id.tvCustomerId);
            tvCustomerAddress = (TextView) itemView.findViewById(R.id.tvCustomerAddress);

            tvProductId = (TextView) itemView.findViewById(R.id.tvProductId);
            tvProductBrand = (TextView) itemView.findViewById(R.id.tvProductBrand);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvProductSystem = (TextView) itemView.findViewById(R.id.tvProductSystem);

            tvEquipmentID = (TextView) itemView.findViewById(R.id.tvEquipmentID);
            tvEquipmentBrand = (TextView) itemView.findViewById(R.id.tvEquipmentBrand);
            tvEquipmentType = (TextView) itemView.findViewById(R.id.tvEquipmentType);
            tvEquipmentDescriptions = (TextView) itemView.findViewById(R.id.tvEquipmentDescriptions);
            tvEquipmentSpareParts = (TextView) itemView.findViewById(R.id.tvEquipmentSpareParts);
            tvEquipmentWarningDate = (TextView) itemView.findViewById(R.id.tvEquipmentWarningDate);
            tvEquipmentInstallationsDate = (TextView) itemView.findViewById(R.id.tvEquipmentInstallationsDate);


            lyCustomer = (LinearLayout) itemView.findViewById(R.id.lyCustomer);
            lyProduct = (LinearLayout) itemView.findViewById(R.id.lyProduct);
            lyEquipment = (LinearLayout) itemView.findViewById(R.id.lyEquipment);
//
            itemView.setOnClickListener(this);
        }

        public void bindView(final int position){
//
           if (mDataset.get(position).getProcess().equals("1")){
               tvCustomerName.setText(mDataset.get(position).getCustomer_name());
               tvCustomerId.setText(mDataset.get(position).getCustomer_id());
               tvCustomerAddress.setText(mDataset.get(position).getCustomer_adress());

               tvProductId.setText(mDataset.get(position).getProduct_id());
               tvProductBrand.setText(mDataset.get(position).getProduct_brand());
               tvProductName.setText(mDataset.get(position).getProduct_name());
               tvProductSystem.setText(mDataset.get(position).getProduct_system());

               tvEquipmentID.setText(mDataset.get(position).getEquipment_id());
               tvEquipmentBrand.setText(mDataset.get(position).getEquipment_brand());
               tvEquipmentType.setText(mDataset.get(position).getEquipment_type());
               tvEquipmentDescriptions.setText(mDataset.get(position).getEquipment_description());
               tvEquipmentSpareParts.setText(mDataset.get(position).getEquipment_spare_parts());
               tvEquipmentWarningDate.setText(mDataset.get(position).getEquipment_warning_date());
               tvEquipmentInstallationsDate.setText(mDataset.get(position).getEquipment_installation_date());

               lyCustomer.setVisibility(View.GONE);
               lyProduct.setVisibility(View.VISIBLE);
               lyEquipment.setVisibility(View.GONE);

           }else if (mDataset.get(position).getProcess().equals("2")){
               tvCustomerName.setText(mDataset.get(position).getCustomer_name());
               tvCustomerId.setText(mDataset.get(position).getCustomer_id());
               tvCustomerAddress.setText(mDataset.get(position).getCustomer_adress());

               tvProductId.setText(mDataset.get(position).getProduct_id());
               tvProductBrand.setText(mDataset.get(position).getProduct_brand());
               tvProductName.setText(mDataset.get(position).getProduct_name());
               tvProductSystem.setText(mDataset.get(position).getProduct_system());

               tvEquipmentID.setText(mDataset.get(position).getEquipment_id());
               tvEquipmentBrand.setText(mDataset.get(position).getEquipment_brand());
               tvEquipmentType.setText(mDataset.get(position).getEquipment_type());
               tvEquipmentDescriptions.setText(mDataset.get(position).getEquipment_description());
               tvEquipmentSpareParts.setText(mDataset.get(position).getEquipment_spare_parts());
               tvEquipmentWarningDate.setText(mDataset.get(position).getEquipment_warning_date());
               tvEquipmentInstallationsDate.setText(mDataset.get(position).getEquipment_installation_date());

               lyCustomer.setVisibility(View.GONE);
               lyProduct.setVisibility(View.GONE);
               lyEquipment.setVisibility(View.VISIBLE);

           }else {
               tvCustomerName.setText(mDataset.get(position).getCustomer_name());
               tvCustomerId.setText(mDataset.get(position).getCustomer_id());
               tvCustomerAddress.setText(mDataset.get(position).getCustomer_adress());

               tvProductId.setText(mDataset.get(position).getProduct_id());
               tvProductBrand.setText(mDataset.get(position).getProduct_brand());
               tvProductName.setText(mDataset.get(position).getProduct_name());
               tvProductSystem.setText(mDataset.get(position).getProduct_system());

               tvEquipmentID.setText(mDataset.get(position).getEquipment_id());
               tvEquipmentBrand.setText(mDataset.get(position).getEquipment_brand());
               tvEquipmentType.setText(mDataset.get(position).getEquipment_type());
               tvEquipmentDescriptions.setText(mDataset.get(position).getEquipment_description());
               tvEquipmentSpareParts.setText(mDataset.get(position).getEquipment_spare_parts());
               tvEquipmentWarningDate.setText(mDataset.get(position).getEquipment_warning_date());
               tvEquipmentInstallationsDate.setText(mDataset.get(position).getEquipment_installation_date());

               lyCustomer.setVisibility(View.VISIBLE);
               lyProduct.setVisibility(View.GONE);
               lyEquipment.setVisibility(View.GONE);
           }
        }

        @Override
        public void onClick(View v) {

        }
    }
}