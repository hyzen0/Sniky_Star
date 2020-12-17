package com.joad.sinkystar.Fragment.search.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.Fragment.moment.model.storyModel;
import com.joad.sinkystar.Fragment.search.model.UserModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.ItemHashtagBinding;

import java.util.ArrayList;
import java.util.List;


public class HashTagAdapter extends RecyclerView.Adapter<HashTagAdapter.myViewHolder> {


    int flag;
    private ArrayList<UserModel> properties;
    private Activity mContext;


    public HashTagAdapter(Activity mContext, ArrayList<UserModel> properties, int flag) {
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
        ItemHashtagBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_hashtag, parent, false);

//        v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_video_main, parent, false);

        return new myViewHolder(binding);
    }


    class myViewHolder extends RecyclerView.ViewHolder {


        public ItemHashtagBinding binding;

        public myViewHolder(ItemHashtagBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.binding = itemRowBinding;
//
//            Context context;
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//
//            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
//
//            binding.recyclerViewHashtagsItem.setLayoutManager(linearLayoutManager);
//
//            List<Object> recycleViewItem = new ArrayList<>();
//
//            recycleViewItem.add(new storyModel("view"));
//            recycleViewItem.add(new storyModel("view"));
//
//            for (int i = 0; i < 10; i++) {
//                recycleViewItem.add(new CommentModel("comment"));
//            }
//
//            HashTagBothAdapter hashTagBothAdapter = new HashTagBothAdapter(mContext, recycleViewItem, 1);
//
//            binding.recyclerViewHashtagsItem.setAdapter(hashTagBothAdapter);


            TrendingAdapter trendingAdapter = new TrendingAdapter(mContext,properties,2);

            binding.recyclerViewHashtagsItem.setAdapter(trendingAdapter);
        }


        public void bind(UserModel dataModel) {
            binding.executePendingBindings();
        }

    }
}



