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
import com.joad.sinkystar.Fragment.search.adapter.UserAdapter;
import com.joad.sinkystar.Fragment.search.model.UserModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Utility.Utility;
import com.joad.sinkystar.databinding.ViewpagerMomentFollowBinding;

import java.util.ArrayList;

public class likeViewShareViewPager extends Fragment {

    int flag = 0;
    ViewpagerMomentFollowBinding binding;

    ArrayList<CommentModel> commentList = new ArrayList<>();

    CommentAdapter commentAdapter;
    private ArrayList<UserModel> userModelList = new ArrayList<>();


    private Utility utility;
    private DashBoard dashBoard;
    private Bundle masterBundle;
    private boolean doubleBackToExitPressedOnce = false;

    private LinearLayout linearLayout;

    public static likeViewShareViewPager newInstance(int flag, LinearLayout linearLayout) {
        likeViewShareViewPager f = new likeViewShareViewPager();
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
                inflater, R.layout.viewpager_moment_follow, container, false);

        View view = binding.getRoot();
        initView();
        return view;
    }

    private void initView() {
        for (int i = 0; i < 10; i++) {
            userModelList.add(new UserModel("sdfhj"));

        }
        UserAdapter userAdapter = new UserAdapter(getActivity(), userModelList, flag);
        binding.recyclerView.setAdapter(userAdapter);

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                System.out.println("Sfsadf ::::   ne   " + newState);
                if (IsRecyclerViewAtTop()) {
                    try {
                        if (linearLayout.getVisibility() == View.GONE) {
                            Utility.getInstance(getActivity()).expand(linearLayout);
                            doubleBackToExitPressedOnce = true;
                            swipdismiss();

                        } else {
                            if (newState == 0 && !doubleBackToExitPressedOnce) {
                                getActivity().onBackPressed();
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
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
        if (binding.recyclerView.getChildCount() == 0)
            return true;
        return binding.recyclerView.getChildAt(0).getTop() == 0;
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
