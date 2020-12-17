package com.joad.sinkystar.Fragment.home.viewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionInflater;

import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.Fragment.home.adapter.CommentAdapter;
import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Utility.Utility;
import com.joad.sinkystar.databinding.ViewpagerCommentBinding;

import java.util.ArrayList;

public class commentViewPager extends Fragment {

    int flag = 0;
    ViewpagerCommentBinding binding;

    ArrayList<CommentModel> commentList = new ArrayList<>();

    CommentAdapter commentAdapter;

    private Utility utility;
    private DashBoard dashBoard;
    private Bundle masterBundle;
    private boolean doubleBackToExitPressedOnce = false;

    private LinearLayout linearLayout;

    public static commentViewPager newInstance(int flag, LinearLayout linearLayout) {
        commentViewPager f = new commentViewPager();
        f.flag = flag;
        f.linearLayout = linearLayout;
        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                inflater, R.layout.viewpager_comment, container, false);

        View view = binding.getRoot();
        initView();
        return view;
    }

    private void initView() {
        commentAdapter = new CommentAdapter(getActivity(), commentList, 1);
        binding.commentRecycleView.setAdapter(commentAdapter);

        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.commentEdit.getText().toString().length() > 0) {

                    commentList.add(new CommentModel(binding.commentEdit.getText().toString()));

                    int newMsgPosition = commentList.size() - 1;

                    commentAdapter.notifyItemChanged(newMsgPosition);
                    binding.commentEdit.setText("");
                    binding.commentRecycleView.scrollToPosition(newMsgPosition);
                    Utility.getInstance(getActivity()).collapse(linearLayout);

                }
            }
        });

        binding.commentRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                System.out.println("Sfsadf ::::   ne   " + newState);
                if (IsRecyclerViewAtTop()) {
                    if (linearLayout.getVisibility() == View.GONE) {
                        Utility.getInstance(getActivity()).expand(linearLayout);
                        doubleBackToExitPressedOnce = true;
                        swipdismiss();

                    } else {
                        if (newState == 0 && !doubleBackToExitPressedOnce) {
                            getActivity().onBackPressed();
                        }
                    }

                }


            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy < 0) {
                    doubleBackToExitPressedOnce = false;

                    // Recycle view scrolling up...
//                    Utility.getInstance(getActivity()).expand(binding.ly2);

                } else if (dy > 0) {
                    Utility.getInstance(getActivity()).collapse(linearLayout);
                    doubleBackToExitPressedOnce = true;

                    // Recycle view scrolling down...

                }
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
