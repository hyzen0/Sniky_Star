package com.joad.sinkystar.Fragment.search.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.joad.sinkystar.Fragment.search.model.UserModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.ItemTrendingBinding;

import java.util.ArrayList;


public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.myViewHolder> {


    int flag;
    private ArrayList<UserModel> properties;
    private Activity mContext;


    public TrendingAdapter(Activity mContext, ArrayList<UserModel> properties, int flag) {
        this.mContext = mContext;
        this.properties = properties;
        this.flag = flag;
    }


    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {

        UserModel dataModel = properties.get(position);
        holder.bind(dataModel);


    }


    @Override
    public int getItemCount() {

        return properties.size();


    }


    @Override
    public myViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v;
        ItemTrendingBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_trending, parent, false);

//        v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_video_main, parent, false);

        return new myViewHolder(binding);
    }


    class myViewHolder extends RecyclerView.ViewHolder {


        public ItemTrendingBinding binding;

        public myViewHolder(ItemTrendingBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.binding = itemRowBinding;
//            binding.replyBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (flag == 1) {
//                        Navigation.findNavController(mContext, R.id.nav_host_fragment).navigate(R.id.action_commentFragment_to_commentFragment2);
//                    }
//                }
//            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("onlyOne",1);
                    Navigation.findNavController(mContext,R.id.nav_host_fragment).navigate(R.id.action_searchFragment_to_video_user_view_frag,
                            bundle,null,null);
                }
            });
        }


        public void bind(UserModel dataModel) {
            binding.executePendingBindings();
        }

    }
}



