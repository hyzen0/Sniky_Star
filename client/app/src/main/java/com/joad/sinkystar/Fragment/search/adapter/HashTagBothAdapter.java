package com.joad.sinkystar.Fragment.search.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.Fragment.moment.model.storyModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.ItemHashtagMomentBinding;
import com.joad.sinkystar.databinding.ItemHashtagVideoBinding;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.List;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;


public class HashTagBothAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int horiZantal_Rec = 0;
    private static final int RECEIVER_ITEM_TYPE = 1;
    int flag;
    private Activity mContext;
    private List<Object> recyclerViewItems;
    private CommentModel dataModel;
    private storyModel secondModel;


    public HashTagBothAdapter(Activity mContext, List<Object> recyclerViewItems, int flag) {
        this.mContext = mContext;
        this.recyclerViewItems = recyclerViewItems;
        this.flag = flag;
    }

    @Override
    public int getItemViewType(int position) {
        Object recyclerViewItem = recyclerViewItems.get(position);
        if (recyclerViewItem instanceof CommentModel) {
            return RECEIVER_ITEM_TYPE;
        } else {
            return horiZantal_Rec;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case horiZantal_Rec:
                secondModel = (storyModel) recyclerViewItems.get(position);
                ((HashTagBothAdapter.viewHolderVideo) holder).bindStory(secondModel, position);
                break;
            case RECEIVER_ITEM_TYPE:
                dataModel = (CommentModel) recyclerViewItems.get(position);
                ((HashTagBothAdapter.myViewHolder) holder).bindMoment(dataModel, position);
                break;
        }


    }


    @Override
    public int getItemCount() {

        return recyclerViewItems.size();


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        if (viewType == RECEIVER_ITEM_TYPE) {
            ItemHashtagMomentBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_hashtag_moment, parent, false);
            return new HashTagBothAdapter.myViewHolder(binding);

        } else {
            ItemHashtagVideoBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_hashtag_video, parent, false
            );
            return new HashTagBothAdapter.viewHolderVideo(binding);

        }


    }


    class myViewHolder extends RecyclerView.ViewHolder {


        public ItemHashtagMomentBinding binding;

        public myViewHolder(ItemHashtagMomentBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.binding = itemRowBinding;

            PushDownAnim.setPushDownAnimTo(binding.likeBtn).setScale(MODE_SCALE, 0.89f);
            PushDownAnim.setPushDownAnimTo(binding.commentsBtn).setScale(MODE_SCALE, 0.89f);

            binding.likeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (binding.likeBtn.getDrawable().getConstantState().equals(mContext.getResources().getDrawable(R.drawable.ic_star_line).getConstantState())) {

                        binding.likeBtn.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_starcolor));

                    } else {
                        binding.likeBtn.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_star_line));

                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("sdfasdf ");

                    Navigation.findNavController(mContext, R.id.nav_host_fragment)
                            .navigate(R.id.photoFragment);

                }
            });


        }


        public void bindMoment(CommentModel dataModel, int position) {
            binding.executePendingBindings();
        }

    }


    class viewHolderVideo extends RecyclerView.ViewHolder {
        ItemHashtagVideoBinding binding;
        ArrayList<CommentModel> commentList = new ArrayList<>();


        public viewHolderVideo(ItemHashtagVideoBinding itemReycleviewHoriBinding) {
            super(itemReycleviewHoriBinding.getRoot());
            this.binding = itemReycleviewHoriBinding;
            for (int i = 0; i < 10; i++) {
                commentList.add(new CommentModel("comment"));
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("onlyOne", 1);
                    Navigation.findNavController(mContext, R.id.nav_host_fragment)
                            .navigate(R.id.video_own_view_frag, bundle);
                }
            });
        }

        public void bindStory(storyModel storyModel, int position) {

        }
    }
}



