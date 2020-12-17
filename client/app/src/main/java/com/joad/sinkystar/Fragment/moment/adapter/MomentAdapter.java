package com.joad.sinkystar.Fragment.moment.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.RecyclerView;

import com.joad.sinkystar.Fragment.message.fragment.ChatFragment;
import com.joad.sinkystar.Fragment.moment.model.storyModel;
import com.joad.sinkystar.Fragment.others.Bottom_sheet_add_photo;
import com.joad.sinkystar.Fragment.others.Bottom_sheet_option;
import com.joad.sinkystar.Fragment.profile.adapter.StoriesAdapter;
import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.ItemMomentBinding;
import com.joad.sinkystar.databinding.ItemReycleviewHoriBinding;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.List;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;


public class MomentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int horiZantal_Rec = 0;
    private static final int RECEIVER_ITEM_TYPE = 1;
    int flag;
    private Activity mContext;
    private List<Object> recyclerViewItems;
    private CommentModel dataModel;
    private storyModel secondModel;

    private Fragment fragment;

    public MomentAdapter(Activity mContext, Fragment fragment, List<Object> recyclerViewItems, int flag) {
        this.mContext = mContext;
        this.recyclerViewItems = recyclerViewItems;
        this.flag = flag;
        this.fragment = fragment;
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
                ((viewHolderStory) holder).bindStory(secondModel, position);
                break;
            case RECEIVER_ITEM_TYPE:
                dataModel = (CommentModel) recyclerViewItems.get(position);
                ((myViewHolder) holder).bindMoment(dataModel, position);
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
            ItemMomentBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_moment, parent, false);
            return new MomentAdapter.myViewHolder(binding);

        } else {
            ItemReycleviewHoriBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_reycleview_hori, parent, false
            );
            return new MomentAdapter.viewHolderStory(binding);

        }


    }


    class myViewHolder extends RecyclerView.ViewHolder {


        public ItemMomentBinding binding;

        public myViewHolder(ItemMomentBinding itemRowBinding) {
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

            binding.userProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(mContext, R.id.nav_host_fragment)
                            .navigate(R.id.action_momentFragment_to_userProfileMoment);
                }
            });
            binding.commentsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    moveToDetailsFragment(myViewHolder.this);
                }
            });

            binding.momentImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPhoto(myViewHolder.this);
                }
            });
            binding.moreVertOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("type","moment");
                    Bottom_sheet_option filter_bottom_sheet = Bottom_sheet_option.newInstance(mContext, bundle);
                    filter_bottom_sheet.show(fragment.getChildFragmentManager(), "tile");


//                    Navigation.findNavController(mContext, R.id.nav_host_fragment)
//                            .navigate(R.id.action_momentFragment_to_bottom_sheet_option,bundle);
//                   Bottom_sheet_option filter_bottom_sheet = Bottom_sheet_option.newInstance(mContext);
////                    filter_bottom_sheet.show(mContext.getFragmentManager(),filter_bottom_sheet.TAG);
                }
            });
        }

        private void showPhoto(myViewHolder holder) {
            System.out.println("sdafsd traansing  ::: " + holder.binding.momentImg.getTransitionName());
            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(holder.binding.momentImg, "" + holder.binding.momentImg.getTransitionName())
                    .addSharedElement(holder.binding.userProfile, "" + holder.binding.userProfile.getTransitionName())
                    .addSharedElement(holder.binding.linearLayout2, "" + holder.binding.linearLayout2.getTransitionName())
                    .addSharedElement(holder.binding.decMoment, "" + holder.binding.decMoment.getTransitionName())
                    .build();
            Bundle bundle = new Bundle();
//
            bundle.putString("momentImgm", holder.binding.momentImg.getTransitionName());
            bundle.putString("userProfilem", holder.binding.userProfile.getTransitionName());
            bundle.putString("linearLayout2m", holder.binding.linearLayout2.getTransitionName());
            bundle.putString("decMomentm", holder.binding.decMoment.getTransitionName());
            Navigation.findNavController(mContext, R.id.nav_host_fragment)
                    .navigate(R.id.action_momentFragment_to_photoFragment, bundle, null, extras);


        }

        private void moveToDetailsFragment(MomentAdapter.myViewHolder holder) {
            Navigation.findNavController(mContext, R.id.nav_host_fragment)
                    .navigate(R.id.action_momentFragment_to_momentDetailsFragment);
        }

        public void bindMoment(CommentModel dataModel, int position) {
            binding.userName.setText("User Name");

            binding.momentImg.setTransitionName("userimgm" + position);
            binding.userProfile.setTransitionName("userProfilem" + position);
            binding.linearLayout2.setTransitionName("linearLayout2m" + position);
            binding.decMoment.setTransitionName("decMomentm" + position);


            binding.executePendingBindings();
        }

    }


    class viewHolderStory extends RecyclerView.ViewHolder {
        ItemReycleviewHoriBinding binding;
        ArrayList<CommentModel> commentList = new ArrayList<>();


        public viewHolderStory(ItemReycleviewHoriBinding itemReycleviewHoriBinding) {
            super(itemReycleviewHoriBinding.getRoot());
            this.binding = itemReycleviewHoriBinding;
            for (int i = 0; i < 10; i++) {
                commentList.add(new CommentModel("comment"));
            }
        }

        public void bindStory(storyModel storyModel, int position) {


            StoriesAdapter storiesAdapter = new StoriesAdapter(mContext, commentList, 1);
            binding.recyclerViewStories.setAdapter(storiesAdapter);
        }
    }
}



