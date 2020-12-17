package com.joad.sinkystar.Fragment.profile.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.transition.Slide;

import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.FragmentProfileSettingBinding;


public class ProfileSettingFragment extends Fragment {

    FragmentProfileSettingBinding binding;
    DashBoard dashBoard;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setupWindowAnimations();

        dashBoard = (DashBoard) getActivity();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_profile_setting, container, false);
        View view = binding.getRoot();
        initView();
//        dashBoard.drawerLayout=binding.drawerLayout;
        return view;
    }

    private void initView() {

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        dashBoard.menu_btn.setVisibility(View.GONE);
        dashBoard.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }



    private void setupWindowAnimations() {
        Slide enterTransition = new Slide();
        enterTransition.setDuration(300);
        enterTransition.setSlideEdge(Gravity.RIGHT);
        setEnterTransition(enterTransition);

        Slide exitTransition = new Slide();
        exitTransition.setDuration(300);
        exitTransition.setSlideEdge(Gravity.RIGHT);
        setReturnTransition(exitTransition);


        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(300);
        setReenterTransition(slideTransition);


    }

}