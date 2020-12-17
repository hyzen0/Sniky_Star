package com.joad.sinkystar.Fragment.message.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.transition.Slide;

import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.Fragment.message.adapter.ChatAdapter;
import com.joad.sinkystar.Fragment.moment.model.storyModel;
import com.joad.sinkystar.Fragment.others.Bottom_sheet_add_photo;
import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Utility.Utility;
import com.joad.sinkystar.databinding.FragmentChatBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    FragmentChatBinding binding;
    DashBoard dashBoard;
    Utility utility;
    List<Object> recycleViewItem = new ArrayList<>();
    ChatAdapter chatAdapter;
    private String is_photo;
    private Uri ImageUri;
    private String filePath;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashBoard = (DashBoard) getActivity();
        utility = Utility.getInstance(getActivity());

      //  setupWindowAnimations();

    }

    public void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Collapse speed of 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density) * 2);
        v.startAnimation(a);
    }


    public void expand(final View v) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) v.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {

                v.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Expansion speed of 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density) * 2);
        v.startAnimation(a);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_chat, container, false);
        View view = binding.getRoot();
        initfun();
        return view;

    }


    private void initfun() {

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setStackFromEnd(true);
//        manager.setReverseLayout(true);
        binding.commentRecycleView.setLayoutManager(manager);
        chatAdapter = new ChatAdapter(getActivity(), recycleViewItem, 1);
        binding.commentRecycleView.setAdapter(chatAdapter);

        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.commentEdit.getText().toString().trim().length() > 0) {
                    recycleViewItem.add(new CommentModel("" + binding.commentEdit.getText().toString().trim()));
                    recycleViewItem.add(new storyModel("" + binding.commentEdit.getText().toString().trim()));
                    int newMsgPosition = recycleViewItem.size() - 1;
                    binding.commentEdit.setText("");
                    chatAdapter.notifyDataSetChanged();
                    binding.commentRecycleView.scrollToPosition(newMsgPosition);
                }
            }
        });
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        binding.userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                chooseProfilePic2();
//               / Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_messageFragment_to_bottom_sheet_add_photo);
                Bottom_sheet_add_photo filter_bottom_sheet = Bottom_sheet_add_photo.newInstance(getActivity(), ChatFragment.this);
                filter_bottom_sheet.show(getChildFragmentManager(), "tile");

            }
        });
        binding.removePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                is_photo = null;
                ImageUri = null;
                filePath = null;
                collapse(binding.photoLayout);
            }
        });
    }


    @Override
    public void onStop() {
        super.onStop();
        utility.hideKeyboard(getActivity());
    }

    private void setupWindowAnimations() {
//         Re-enter transition is executed when returning to this activity
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(300);
        setReenterTransition(slideTransition);


        Slide enterTransition = new Slide();
        enterTransition.setDuration(300);
        enterTransition.setSlideEdge(Gravity.RIGHT);
        setEnterTransition(enterTransition);

        Slide exitTransition = new Slide();
        exitTransition.setDuration(300);
        exitTransition.setSlideEdge(Gravity.RIGHT);
        setReturnTransition(exitTransition);

//        getWindow().setExitTransition(slideTransition);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("Sfdsa ::::  +" + requestCode);
        try {
            if (resultCode == Activity.RESULT_OK && requestCode == 2404) {
                is_photo = null;
                try {
                    ImageUri = data.getData();
                    assert ImageUri != null;
                    filePath = ImageUri.getPath();
                    binding.mainPhoto.setImageURI(ImageUri);
                    expand(binding.photoLayout);
//                    Bitmap bitmap = null;
//                    try {
//                        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), ImageUri);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

//                    saveToInternalStorage(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        dashBoard.message.setBackground(getResources().getDrawable(R.color.dart_trans));
        dashBoard.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        dashBoard.setbackgraund(dashBoard.message);
        dashBoard.flagFrag = 6;

    }

}