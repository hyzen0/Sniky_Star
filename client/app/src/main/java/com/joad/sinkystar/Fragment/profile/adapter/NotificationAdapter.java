package com.joad.sinkystar.Fragment.profile.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.ItemNotiPostBinding;

import java.util.ArrayList;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.myViewHolder> {


    int flag;
    private ArrayList<CommentModel> properties;
    private Activity mContext;


    public NotificationAdapter(Activity mContext, ArrayList<CommentModel> properties, int flag) {
        this.mContext = mContext;
        this.properties = properties;
        this.flag = flag;
    }


    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {

        CommentModel dataModel = properties.get(position);
        holder.bind(dataModel);


    }


    @Override
    public int getItemCount() {

        return properties.size();


    }


    @Override
    public myViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v;
        ItemNotiPostBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_noti_post, parent, false);

//        v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_video_main, parent, false);

        return new myViewHolder(binding);
    }


    class myViewHolder extends RecyclerView.ViewHolder {


        public ItemNotiPostBinding binding;

        public myViewHolder(ItemNotiPostBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.binding = itemRowBinding;


            if(flag==2){
                binding.btn1.setText("Accept");
            }

        }


        public void bind(CommentModel dataModel) {
//            binding.userComment.setText(dataModel.getComment());
            binding.executePendingBindings();
        }

    }
}



