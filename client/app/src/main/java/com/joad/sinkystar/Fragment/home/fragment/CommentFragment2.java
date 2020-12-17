package com.joad.sinkystar.Fragment.home.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionInflater;

import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.Fragment.home.adapter.CommentAdapter;
import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Utility.Utility;
import com.joad.sinkystar.databinding.FragmentComment2Binding;

import java.util.ArrayList;

public class CommentFragment2 extends Fragment {


    FragmentComment2Binding binding;

    ArrayList<CommentModel> commentList = new ArrayList<>();

    CommentAdapter commentAdapter;

    private Utility utility;
    private DashBoard dashBoard;
    private Bundle masterBundle;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        postponeEnterTransition();
//        setStatusBarTransparent();
      //  setupWindowAnimations();
        utility = Utility.getInstance(getActivity());
        dashBoard = (DashBoard) getActivity();
        masterBundle = getArguments();


    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.move));
        setAllowEnterTransitionOverlap(false);
        setAllowReturnTransitionOverlap(false);

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_comment2, container, false);

        View view = binding.getRoot();
        initView();
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        utility.hideKeyboard(getActivity());

    }

    @Override
    public void onStart() {
        super.onStart();
        dashBoard.menu_btn.setVisibility(View.GONE);
        dashBoard.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void initView() {

//        binding.allLayout.setTransitionName(masterBundle.getString("TranAll"));
//        startPostponedEnterTransition();

        commentAdapter = new CommentAdapter(getActivity(), commentList, 2);
        binding.commentRecycleView.setAdapter(commentAdapter);
        binding.ly2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                // Get finger position on screen
                final int Y = (int) event.getRawY();

                // Switch on motion event type
                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        // save default base layout height
                        getActivity().onBackPressed();
                        break;

                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });
        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.commentEdit.getText().toString().length() > 0) {

                    commentList.add(new CommentModel(binding.commentEdit.getText().toString()));
                    int newMsgPosition = commentList.size() - 1;

                    commentAdapter.notifyItemChanged(newMsgPosition);
                    binding.commentEdit.setText("");
                    binding.commentRecycleView.scrollToPosition(newMsgPosition);
//                    Utility.getInstance(getActivity()).collapse(binding.ly2);

                }
            }
        });


        binding.commentRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                System.out.println("Sfsadf ::::   ne   " + newState);
//                if (IsRecyclerViewAtTop()) {
//                    if (binding.ly2.getVisibility() == View.GONE) {
//                        Utility.getInstance(getActivity()).expand(binding.ly2);
//                        doubleBackToExitPressedOnce = true;
////                        swipdismiss();
//
//                    } else {
////                        if (newState == 0 && !doubleBackToExitPressedOnce) {
////                            getActivity().onBackPressed();
////                        }
//                    }
//
//                }


            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy < 0) {

                    // Recycle view scrolling up...
                    Utility.getInstance(getActivity()).expand(binding.ly2);

                } else if (dy > 0) {
                    Utility.getInstance(getActivity()).collapse(binding.ly2);

                    // Recycle view scrolling down...

                }
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private boolean IsRecyclerViewAtTop() {
        if (binding.commentRecycleView.getChildCount() == 0)
            return true;
        return binding.commentRecycleView.getChildAt(0).getTop() == 0;
    }

    private void swipdismiss() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 200);
    }
}