package com.joad.sinkystar.Fragment.home.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.transition.TransitionInflater;
import androidx.viewpager.widget.ViewPager;

import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.Fragment.home.adapter.CommentAdapter;
import com.joad.sinkystar.Fragment.home.adapter.MainViewPagerAdapter;
import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.Fragment.home.viewPager.commentViewPager;
import com.joad.sinkystar.Fragment.home.viewPager.likeViewShareViewPager;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Utility.Utility;
import com.joad.sinkystar.databinding.FragmentCommentBinding;

import java.util.ArrayList;

public class CommentFragment extends Fragment {


    FragmentCommentBinding binding;

    ArrayList<CommentModel> commentList = new ArrayList<>();

    CommentAdapter commentAdapter;

    private Utility utility;
    private DashBoard dashBoard;
    private Bundle masterBundle;
    private boolean doubleBackToExitPressedOnce = false;
    private MainViewPagerAdapter mainViewPagerAdapter;

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
                inflater, R.layout.fragment_comment, container, false);

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

        mainViewPagerAdapter = new MainViewPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        mainViewPagerAdapter.addFragment(likeViewShareViewPager.newInstance(0, binding.ly2), "likes");
        mainViewPagerAdapter.addFragment(commentViewPager.newInstance(1, binding.ly2), "comment");
        mainViewPagerAdapter.addFragment(likeViewShareViewPager.newInstance(0, binding.ly2), "views");
        mainViewPagerAdapter.addFragment(likeViewShareViewPager.newInstance(0, binding.ly2), "shares");

        binding.viewPager.setAdapter(mainViewPagerAdapter);
        System.out.println("Asfsda :::  "+getArguments().getString("type"));
        switch (getArguments().getString("type")) {

            case "0":
                binding.viewPager.setCurrentItem(0);
                setlayout(0);

                break;
            case "1":
                binding.viewPager.setCurrentItem(1);
                setlayout(1);

                break;
            case "2":
                binding.viewPager.setCurrentItem(2);
                setlayout(2);

                break;
            case "3":
                binding.viewPager.setCurrentItem(3);
                setlayout(3);

                break;
        }

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setlayout(position);
//               mainViewPagerAdapter.addFragment(followViewPager.newInstance(),"popular");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        binding.likesLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setlayout(0);
            }
        });

        binding.commentLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setlayout(1);
            }
        });

        binding.viewsLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setlayout(2);
            }
        });
        binding.shareLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setlayout(3);
            }
        });


    }

    private void setlayout(int i) {

        binding.view1.setBackgroundColor(getActivity().getResources().getColor(R.color.grey));
        binding.view2.setBackgroundColor(getActivity().getResources().getColor(R.color.grey));
        binding.view3.setBackgroundColor(getActivity().getResources().getColor(R.color.grey));
        binding.view4.setBackgroundColor(getActivity().getResources().getColor(R.color.grey));
        binding.viewPager.setCurrentItem(i);

        switch (i) {
            case 0:
                binding.view1.setBackgroundColor(getActivity().getResources().getColor(R.color.yellow));
                break;
            case 1:
                binding.view2.setBackgroundColor(getActivity().getResources().getColor(R.color.yellow));
                break;
            case 2:
                binding.view3.setBackgroundColor(getActivity().getResources().getColor(R.color.yellow));
                break;
            case 3:
                binding.view4.setBackgroundColor(getActivity().getResources().getColor(R.color.yellow));
                break;

        }
    }


}