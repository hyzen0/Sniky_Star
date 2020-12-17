package com.joad.sinkystar.Fragment.search.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.Fragment.home.adapter.MainViewPagerAdapter;
import com.joad.sinkystar.Fragment.search.adapter.HashTagAdapter;
import com.joad.sinkystar.Fragment.search.adapter.TrendingAdapter;
import com.joad.sinkystar.Fragment.search.adapter.UserAdapter;
import com.joad.sinkystar.Fragment.search.model.UserModel;
import com.joad.sinkystar.Fragment.search.viewPager.hashTagViewPager;
import com.joad.sinkystar.Fragment.search.viewPager.userViewPager;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Utility.Utility;
import com.joad.sinkystar.databinding.FragmentSearch2Binding;

import java.util.ArrayList;


public class SearchFragment2 extends Fragment {

    DashBoard dashBoard;
    Utility utility;
    private UserAdapter userAdapter;
    private ArrayList<UserModel> userModelList = new ArrayList<>();
    private TrendingAdapter trendingAdapter;
    private HashTagAdapter hashTagAdapter;
    private UserAdapter musicAdapter;
    private FragmentSearch2Binding binding;
    private MainViewPagerAdapter mainViewPagerAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashBoard = (DashBoard) getActivity();
        utility = Utility.getInstance(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_search2, container, false);

        View view = binding.getRoot();
        initView();
        return view;

    }

    private void initView() {
        setbuttonClick(0);
        binding.searchText.setFocusable(true);
        mainViewPagerAdapter = new MainViewPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mainViewPagerAdapter.addFragment(userViewPager.newInstance(0), "users");
        mainViewPagerAdapter.addFragment(hashTagViewPager.newInstance(1), "hashtags");
        mainViewPagerAdapter.addFragment(userViewPager.newInstance(2), "music");
        binding.viewPager.setAdapter(mainViewPagerAdapter);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        binding.userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setbuttonClick(0);
            }
        });
        binding.hashtagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setbuttonClick(1);
            }
        });
        binding.musicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setbuttonClick(2);
            }
        });

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setbuttonClick(position);
//               mainViewPagerAdapter.addFragment(followViewPager.newInstance(),"popular");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void setbuttonClick(int postion) {
        binding.viewPager.setCurrentItem(postion);

        binding.userBtn.setTextColor(getResources().getColor(R.color.yellow));
        binding.hashtagBtn.setTextColor(getResources().getColor(R.color.yellow));
        binding.musicBtn.setTextColor(getResources().getColor(R.color.yellow));
        switch (postion) {
            case 0:
                binding.userBtn.setTextColor(getResources().getColor(R.color.grey));

                break;
            case 1:
                binding.hashtagBtn.setTextColor(getResources().getColor(R.color.grey));

                break;
            case 2:
                binding.musicBtn.setTextColor(getResources().getColor(R.color.grey));
                break;
        }

    }


}