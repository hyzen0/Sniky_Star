package com.joad.sinkystar.Fragment.profile.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.ItemGridVideoBinding;

import java.util.ArrayList;


public class GridVideoAdapter extends RecyclerView.Adapter<GridVideoAdapter.myViewHolder> {


    int flag;
    private ArrayList<CommentModel> properties;
    private Activity mContext;


    public GridVideoAdapter(Activity mContext, ArrayList<CommentModel> properties, int flag) {
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
        ItemGridVideoBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_grid_video, parent, false);
//        v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_video_main, parent, false);

        return new myViewHolder(binding);
    }


    class myViewHolder extends RecyclerView.ViewHolder {


        public ItemGridVideoBinding binding;

        public myViewHolder(ItemGridVideoBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.binding = itemRowBinding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (flag == 0) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("onlyOne", 1);
                        Navigation.findNavController(mContext, R.id.nav_host_fragment)
                                .navigate(R.id.action_ownProfile_to_show_own_view_frag, bundle, null, null);

                    } else if (flag == 1) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("onlyOne", 1);
                        Navigation.findNavController(mContext, R.id.nav_host_fragment)
                                .navigate(R.id.action_userProfileFragment_to_video_user_view_frag, bundle, null, null);

                    } else if (flag == 2) {

                        Bundle bundle = new Bundle();
                        bundle.putInt("onlyOne", 1);
                        Navigation.findNavController(mContext, R.id.nav_host_fragment)
                                .navigate(R.id.action_savedVideoFragment_to_video_saved_view_frag, bundle, null, null);

                    } else if (flag == 3) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("onlyOne", 1);
                        Navigation.findNavController(mContext, R.id.nav_host_fragment)
                                .navigate(R.id.action_songFragment_to_video_song_view_frag, bundle, null, null);

                    }

                }
            });

        }


        public void bind(CommentModel dataModel) {
//            binding.comments.setText("1");
            binding.executePendingBindings();
        }

    }
}



