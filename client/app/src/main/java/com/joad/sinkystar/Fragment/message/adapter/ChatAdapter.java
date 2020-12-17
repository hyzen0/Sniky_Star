package com.joad.sinkystar.Fragment.message.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.joad.sinkystar.Fragment.moment.model.storyModel;
import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.ItemMsgReceiveBinding;
import com.joad.sinkystar.databinding.ItemMsgSenderBinding;

import java.util.ArrayList;
import java.util.List;


public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int horiZantal_Rec = 0;
    private static final int RECEIVER_ITEM_TYPE = 1;
    int flag;
    private Activity mContext;
    private List<Object> recyclerViewItems;
    private CommentModel dataModel;
    private storyModel secondModel;


    public ChatAdapter(Activity mContext, List<Object> recyclerViewItems, int flag) {
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
                ((ChatAdapter.viewHolderStory) holder).bindSendMsg(secondModel, position);
                break;
            case RECEIVER_ITEM_TYPE:
                dataModel = (CommentModel) recyclerViewItems.get(position);
                ((ChatAdapter.myViewHolder) holder).bindMoment(dataModel, position);
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
            ItemMsgReceiveBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_msg_receive, parent, false);
            return new ChatAdapter.myViewHolder(binding);

        } else {
            ItemMsgSenderBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_msg_sender, parent, false
            );
            return new ChatAdapter.viewHolderStory(binding);

        }


    }


    class myViewHolder extends RecyclerView.ViewHolder {


        public ItemMsgReceiveBinding binding;

        public myViewHolder(ItemMsgReceiveBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.binding = itemRowBinding;

        }

//


        public void bindMoment(CommentModel dataModel, int position) {
            binding.msg.setText("" + dataModel.getComment());


            binding.executePendingBindings();
        }

    }

//    private void showPhoto(com.joad.sinkystar.Fragment.moment.adapter.MomentAdapter.myViewHolder holder) {
//            System.out.println("sdafsd traansing  ::: " + holder.binding.momentImg.getTransitionName());
//            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
//                    .addSharedElement(holder.binding.momentImg, "" + holder.binding.momentImg.getTransitionName())
//                    .addSharedElement(holder.binding.userProfile, "" + holder.binding.userProfile.getTransitionName())
//                    .addSharedElement(holder.binding.linearLayout2, "" + holder.binding.linearLayout2.getTransitionName())
//                    .addSharedElement(holder.binding.decMoment, "" + holder.binding.decMoment.getTransitionName())
//                    .build();
//            Bundle bundle = new Bundle();
////
//            bundle.putString("momentImgm", holder.binding.momentImg.getTransitionName());
//            bundle.putString("userProfilem", holder.binding.userProfile.getTransitionName());
//            bundle.putString("linearLayout2m", holder.binding.linearLayout2.getTransitionName());
//            bundle.putString("decMomentm", holder.binding.decMoment.getTransitionName());
//            Navigation.findNavController(mContext, R.id.nav_host_fragment)
//                    .navigate(R.id.action_momentFragment_to_photoFragment, bundle, null, extras);
//

//        }

    class viewHolderStory extends RecyclerView.ViewHolder {
        ItemMsgSenderBinding binding;
        ArrayList<CommentModel> commentList = new ArrayList<>();

        public viewHolderStory(ItemMsgSenderBinding itemReycleviewHoriBinding) {
            super(itemReycleviewHoriBinding.getRoot());
            this.binding = itemReycleviewHoriBinding;
        }

        public void bindSendMsg(storyModel storyModel, int position) {

            binding.msg.setText(storyModel.getStory());

        }
    }
}



