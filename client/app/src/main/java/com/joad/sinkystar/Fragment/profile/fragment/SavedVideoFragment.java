package com.joad.sinkystar.Fragment.profile.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.transition.Slide;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.Fragment.profile.adapter.GridPhotoAdapter;
import com.joad.sinkystar.Fragment.profile.adapter.GridVideoAdapter;
import com.joad.sinkystar.Fragment.profile.adapter.StoriesAdapter;
import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.FragmentSavedVideoBinding;

import java.util.ArrayList;


public class SavedVideoFragment extends Fragment {

    private FragmentSavedVideoBinding binding;
    DashBoard dashBoard;
    private GridVideoAdapter gridVideoAdapter;
    private GridPhotoAdapter gridPhotoAdapter;
    private StoriesAdapter storiesAdapter;

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
                inflater, R.layout.fragment_saved_video, container, false);
        View view = binding.getRoot();
        initfun();
//        dashBoard.drawerLayout=binding.drawerLayout;
        return view;

    }

    private void initfun() {

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        ArrayList<CommentModel> commentList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            commentList.add(new CommentModel("asjfd"));
        }

        gridVideoAdapter = new GridVideoAdapter(getActivity(), commentList, 2);
        storiesAdapter = new StoriesAdapter(getActivity(), commentList, 2);

        binding.recyclerView.setAdapter(gridVideoAdapter);


        binding.taplayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    gridVideoAdapter = new GridVideoAdapter(getActivity(), commentList, 2);
                    binding.recyclerView.setAdapter(gridVideoAdapter);
                } else {
                    gridPhotoAdapter = new GridPhotoAdapter(getActivity(), commentList, 2);
                    binding.recyclerView.setAdapter(gridPhotoAdapter);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}