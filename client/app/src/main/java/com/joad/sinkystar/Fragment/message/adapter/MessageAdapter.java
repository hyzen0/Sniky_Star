package com.joad.sinkystar.Fragment.message.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.ItemMessageBinding;

import java.util.ArrayList;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.myViewHolder> {


    int flag;
    private ArrayList<CommentModel> properties;
    private Activity mContext;


    public MessageAdapter(Activity mContext, ArrayList<CommentModel> properties, int flag) {
        this.mContext = mContext;
        this.properties = properties;
        this.flag = flag;
    }


    @Override
    public void onBindViewHolder(final MessageAdapter.myViewHolder holder, final int position) {

        CommentModel dataModel = properties.get(position);
        holder.bind(dataModel);


    }


    @Override
    public int getItemCount() {

        return properties.size();


    }


    @Override
    public MessageAdapter.myViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v;
        ItemMessageBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_message, parent, false);

//        v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_video_main, parent, false);

        return new MessageAdapter.myViewHolder(binding);
    }


    class myViewHolder extends RecyclerView.ViewHolder {


        public ItemMessageBinding binding;

        public myViewHolder(ItemMessageBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.binding = itemRowBinding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(mContext, R.id.nav_host_fragment).navigate(R.id.action_messageFragment_to_chatFragment2);
                }
            });
        }


        public void bind(CommentModel dataModel) {
            binding.userFullName.setText(dataModel.getComment());
            binding.executePendingBindings();
        }

    }
}



