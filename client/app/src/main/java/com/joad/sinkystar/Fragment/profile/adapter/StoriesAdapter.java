package com.joad.sinkystar.Fragment.profile.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.ItemStoriesBinding;

import java.util.ArrayList;


public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.myViewHolder> {


    int flag;
    private ArrayList<CommentModel> properties;
    private Activity mContext;


    public StoriesAdapter(Activity mContext, ArrayList<CommentModel> properties, int flag) {
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
        ItemStoriesBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_stories, parent, false);
//        v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_video_main, parent, false);

        return new myViewHolder(binding);
    }


    class myViewHolder extends RecyclerView.ViewHolder {


        public ItemStoriesBinding binding;

        public myViewHolder(ItemStoriesBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.binding = itemRowBinding;
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ((MainActivity) mContext).showMenuOptions();
//                }
//            });

        }


        public void bind(CommentModel dataModel) {
//            binding.userComment.setText(dataModel.getComment());
            binding.executePendingBindings();
        }

    }
}



