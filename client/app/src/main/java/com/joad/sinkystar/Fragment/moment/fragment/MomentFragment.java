package com.joad.sinkystar.Fragment.moment.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.transition.Fade;
import androidx.transition.Slide;
import androidx.viewpager.widget.ViewPager;

import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.Fragment.home.adapter.MainViewPagerAdapter;
import com.joad.sinkystar.Fragment.moment.viewPager.followMomView;
import com.joad.sinkystar.R;

public class MomentFragment extends Fragment implements View.OnClickListener {

    private DashBoard dashBoard;
    private ViewPager viewPager;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private TextView mBtnFollow;
    private TextView mBtnPopular;
    private TextView mBtnNearBy;
    private ImageButton mBtnMenu;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashBoard = (DashBoard) getActivity();

//        setupWindowAnimations();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mBtnMenu = view.findViewById(R.id.menu_btn);
        mBtnFollow = (TextView) view.findViewById(R.id.follow_btn);
        mBtnFollow.setOnClickListener(this);

        mBtnNearBy = (TextView) view.findViewById(R.id.nearBy_btn);
        mBtnNearBy.setOnClickListener(this);
        viewPager = view.findViewById(R.id.view_pager);

        mainViewPagerAdapter = new MainViewPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mainViewPagerAdapter.addFragment(followMomView.newInstance(), "follow");


        mainViewPagerAdapter.addFragment(followMomView.newInstance(), "nearby");

        viewPager.setAdapter(mainViewPagerAdapter);
//        mVideosViewPager.setOffscreenPageLimit(4);
        setbuttonClick(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        mBtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dashBoard.getMenu_btn().callOnClick();
            }
        });


    }


    private void setbuttonClick(int postion) {

        mBtnFollow.setTextColor(getResources().getColor(R.color.yellow));
        mBtnNearBy.setTextColor(getResources().getColor(R.color.yellow));
        switch (postion) {
            case 0:
                mBtnFollow.setTextColor(getResources().getColor(R.color.grey));

                break;
            case 1:
                mBtnNearBy.setTextColor(getResources().getColor(R.color.grey));

                break;
        }

    }

    private void setupWindowAnimations() {

        Slide enterTransition = new Slide();
        enterTransition.setDuration(300);
        enterTransition.setSlideEdge(Gravity.TOP);
        setEnterTransition(enterTransition);

    }


    @Override
    public void onStart() {
        super.onStart();
        System.out.println("SAfdasd ::: onstart moment");
        dashBoard.home.setBackground(getResources().getDrawable(R.color.dart_trans));
        dashBoard.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        dashBoard.setbackgraund(dashBoard.moment);
        dashBoard.flagFrag = 1;


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.follow_btn:
                viewPager.setCurrentItem(0);
                break;
            case R.id.nearBy_btn:
                viewPager.setCurrentItem(1);
                break;

            default:
                break;
        }
    }
}