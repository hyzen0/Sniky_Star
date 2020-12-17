package com.joad.sinkystar.Fragment.search.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.joad.sinkystar.Fragment.search.model.UserModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.ItemUserBinding;
import com.joad.sinkystar.databinding.ItemUserListBinding;

import java.util.ArrayList;


public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    int flag;
    private ArrayList<UserModel> properties;
    private Activity mContext;


    public UserAdapter(Activity mContext, ArrayList<UserModel> properties, int flag) {
        this.mContext = mContext;
        this.properties = properties;
        this.flag = flag;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (flag == 0 || flag==2) {

        } else {
            myViewHolder holder1 = (myViewHolder) holder;
            UserModel dataModel = properties.get(position);
            holder1.bind(dataModel);

        }
    }


    @Override
    public int getItemCount() {

        return properties.size();


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if (flag == 0 || flag == 2) {

            ItemUserListBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_user_list, parent, false
            );
            return new userFollowholder(binding);

        } else {
            ItemUserBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_user, parent, false);
//        v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_video_main, parent, false);

            return new myViewHolder(binding);
        }
    }


    class myViewHolder extends RecyclerView.ViewHolder {


        public ItemUserBinding binding;

        public myViewHolder(ItemUserBinding itemRowBinding) {
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
                    if (flag == 1) {

                        Navigation.findNavController(mContext, R.id.nav_host_fragment).navigate(R.id.action_searchFragment_to_userProfileFragment);
                    }
                }
            });

        }


        public void bind(UserModel dataModel) {
            binding.executePendingBindings();
        }

    }

    class userFollowholder extends RecyclerView.ViewHolder {


        public ItemUserListBinding binding;

        public userFollowholder(ItemUserListBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.binding = itemRowBinding;

            if(flag==2){
                binding.btnTxt.setText("2.2k >");
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //user list from search fragment 2..
                    if(flag==0) {
                        Navigation.findNavController(mContext, R.id.nav_host_fragment).navigate(R.id.action_searchFragment2_to_userProfileFragment);
                    }else if (flag==2){

                    }
                }
            });

        }


        public void bind(UserModel dataModel) {
            binding.executePendingBindings();
        }

    }
}



