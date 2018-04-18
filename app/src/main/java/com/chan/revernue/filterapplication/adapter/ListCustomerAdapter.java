package com.chan.revernue.filterapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chan.revernue.filterapplication.R;
import com.chan.revernue.filterapplication.activity.HomeActivity;
import com.chan.revernue.filterapplication.activity.ListItemCustomerProductEquipmentActivity;
import com.chan.revernue.filterapplication.transaction.dao.ListCustomerDao;
import com.chan.revernue.filterapplication.transaction.dao.ListItemCustomerDataDao;

import java.util.ArrayList;
import java.util.List;


public class ListCustomerAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ListCustomerDao> mDataset;

//    public ListCustomerAdapter(FragmentActivity activity, List<ListCustomerDao> categoery) {
//        context = activity;
//        mDataset = categoery;
//    }

    public ListCustomerAdapter(Context applicationContext, List<ListCustomerDao> categoery) {
        context = applicationContext;
        mDataset = categoery;
    }

    public ListCustomerAdapter(Context applicationContext, ArrayList<ListItemCustomerDataDao> categoery) {
    }

//    public ReviewsAdapter(Context context,List<ReviewsDataDao> categoery) {
//        mDataset = categoery;
//        this.context = context;
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_cardview,parent,false);
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
        public TextView tv_Name,tv_Id,tv_Description;
        public ImageView ivStatus;
        public CardView cardView;
//
        public ListViewHolder(View itemView){
            super(itemView);
            tv_Name = (TextView) itemView.findViewById(R.id.tvName);
            tv_Description = (TextView) itemView.findViewById(R.id.tvDescription);
            tv_Id = (TextView) itemView.findViewById(R.id.tvID);
            ivStatus = (ImageView) itemView.findViewById(R.id.ivStatus);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
//
            itemView.setOnClickListener(this);
        }

        public void bindView(final int position){
//
            tv_Name.setText(mDataset.get(position).getCustomer_name());
            tv_Id.setText(mDataset.get(position).getCustomer_id());
            tv_Description.setText(mDataset.get(position).getCustomer_adress());
            if(mDataset.get(position).getCustomer_status().equals("1")){
                ivStatus.setBackgroundColor(context.getResources().getColor(R.color.colorOrange1));
            }else if (mDataset.get(position).getCustomer_status().equals("2")){
                ivStatus.setBackgroundColor(context.getResources().getColor(R.color.colorGreen));
            }else {
                ivStatus.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
            }

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.putExtra("member_id", mDataset.get(position).getId_member());
                    intent.putExtra("customer_id", mDataset.get(position).getCustomer_id());
                    intent.putExtra("customer_name", mDataset.get(position).getCustomer_name());
                    intent.putExtra("address", mDataset.get(position).getCustomer_adress());
                    v.getContext().startActivity(intent);

                }
            });


        }

        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(context, ListItemCustomerProductEqupmentFragment.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            context.startActivity(intent);
        }
    }
}