package com.joad.sinkystar.Fragment.live;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.AgoraActivity.LiveActivity;
import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.ItemLiveViewBinding;

import java.util.ArrayList;

import io.agora.rtc.Constants;


public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.myViewHolder> {


    int flag;
    private ArrayList<CommentModel> properties;
    private Activity mContext;
    private Fragment fragment;


    public LiveAdapter(Activity mContext, Fragment fragment, ArrayList<CommentModel> properties, int flag) {
        this.mContext = mContext;
        this.properties = properties;
        this.flag = flag;
        this.fragment = fragment;
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
        ItemLiveViewBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_live_view, parent, false);

//        v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_video_main, parent, false);

        return new myViewHolder(binding);
    }


    class myViewHolder extends RecyclerView.ViewHolder {

        public ItemLiveViewBinding binding;

        public myViewHolder(ItemLiveViewBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.binding = itemRowBinding;
            if (flag == 0) {
                binding.liveStatus.setVisibility(View.VISIBLE);
                binding.title.setVisibility(View.GONE);
            } else if (flag == 1) {
                binding.title.setText("1.1 km");
                binding.liveStatus.setVisibility(View.GONE);
            } else {
                // 2;
                binding.title.setText("Schedule\nafter 2 min");
                binding.liveStatus.setVisibility(View.GONE);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String room = "hello";
                    ((DashBoard) mContext).config().setChannelName(room);
                    Intent intent = new Intent(mContext.getIntent());
                    intent.putExtra(com.joad.sinkystar.Utility.Constants.KEY_CLIENT_ROLE, Constants.CLIENT_ROLE_AUDIENCE);
                    intent.setClass(mContext.getApplicationContext(), LiveActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }

        public void bind(CommentModel dataModel) {
            binding.executePendingBindings();
        }

    }
}



