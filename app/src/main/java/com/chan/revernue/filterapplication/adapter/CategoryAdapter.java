package com.chan.revernue.filterapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chan.revernue.filterapplication.R;
import com.chan.revernue.filterapplication.transaction.dao.ListMemberDataDao;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ListMemberDataDao> mDataset;

    public CategoryAdapter(FragmentActivity activity, List<ListMemberDataDao> categoery) {
        context = activity;
        mDataset = categoery;
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
//
        public ListViewHolder(View itemView){
            super(itemView);
            tv_Name = (TextView) itemView.findViewById(R.id.tvName);
            tv_Description = (TextView) itemView.findViewById(R.id.tvDescription);
            tv_Id = (TextView) itemView.findViewById(R.id.tvID);
//
            itemView.setOnClickListener(this);
        }

        public void bindView(final int position){
//
            tv_Name.setText(mDataset.get(position).getCustomer_name());
            tv_Id.setText(mDataset.get(position).getEquipment_id_connected());
            tv_Description.setText(mDataset.get(position).getEquipment_type());

        }

        @Override
        public void onClick(View v) {

        }
    }
}