package com.joad.sinkystar.Fragment.live;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.AgoraActivity.LiveActivity;
import com.joad.sinkystar.Fragment.home.adapter.MainViewPagerAdapter;
import com.joad.sinkystar.R;

import io.agora.rtc.Constants;

public class LiveFragment extends Fragment implements View.OnClickListener {

    private static final int MIN_INPUT_METHOD_HEIGHT = 200;
    private static final int ANIM_DURATION = 200;
    // Permission request code of any integer value
    private static final int PERMISSION_REQ_CODE = 1 << 4;
    private DashBoard dashBoard;
    private ViewPager viewPager;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private TextView mBtnFollow;
    private TextView mBtnContest;
    private TextView mBtnNearBy;
    private ImageButton mBtnMenu;
    private FloatingActionButton goLive;
    private String[] PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashBoard = (DashBoard) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        mBtnMenu = view.findViewById(R.id.menu_btn);
        goLive = view.findViewById(R.id.go_live);
        goLive.setOnClickListener(this);
        mBtnFollow = (TextView) view.findViewById(R.id.follow_btn);
        mBtnContest = (TextView) view.findViewById(R.id.contest);
        mBtnContest.setOnClickListener(this);

        mBtnFollow.setOnClickListener(this);

        mBtnNearBy = (TextView) view.findViewById(R.id.nearBy_btn);
        mBtnNearBy.setOnClickListener(this);
        viewPager = view.findViewById(R.id.view_pager);

        mainViewPagerAdapter = new MainViewPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mainViewPagerAdapter.addFragment(followLiveView.newInstance(0), "follow");
        mainViewPagerAdapter.addFragment(followLiveView.newInstance(1), "nearby");
        mainViewPagerAdapter.addFragment(followLiveView.newInstance(2), "contest");

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
        mBtnContest.setTextColor(getResources().getColor(R.color.yellow));
        switch (postion) {
            case 0:
                mBtnFollow.setTextColor(getResources().getColor(R.color.grey));

                break;
            case 1:
                mBtnNearBy.setTextColor(getResources().getColor(R.color.grey));

                break;
            case 2:
                mBtnContest.setTextColor(getResources().getColor(R.color.grey));
                break;
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        System.out.println("SAfdasd ::: onstart moment");
        dashBoard.home.setBackground(getResources().getDrawable(R.color.dart_trans));
        dashBoard.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        dashBoard.setbackgraund(dashBoard.live);
        dashBoard.flagFrag = 2;


    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("SAfdasd ::: onstart resume");
        dashBoard.home.setBackground(getResources().getDrawable(R.color.dart_trans));
        dashBoard.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        dashBoard.setbackgraund(dashBoard.live);
        dashBoard.flagFrag = 2;

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
            case R.id.contest:
                viewPager.setCurrentItem(2);
                break;
            case R.id.go_live:
                onStartBroadcastClicked();


//                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                break;

            default:
                break;
        }
    }

    public void onStartBroadcastClicked() {
        checkPermission();
    }

    private void checkPermission() {
        boolean granted = true;
        for (String per : PERMISSIONS) {
            if (!permissionGranted(per)) {
                granted = false;
                break;
            }
        }

        if (granted) {
            resetLayoutAndForward();
        } else {
            requestPermissions();
        }
    }

    private void resetLayoutAndForward() {
        gotoRoleActivity();
    }


    public void gotoRoleActivity() {
        String room = "hello";
        ((DashBoard) getActivity()).config().setChannelName(room);
        gotoLiveActivity(Constants.CLIENT_ROLE_BROADCASTER);
    }

    private boolean permissionGranted(String permission) {
        return ContextCompat.checkSelfPermission(
                getActivity(), permission) == PackageManager.PERMISSION_GRANTED;
    }

    private void gotoLiveActivity(int role) {
        Intent intent = new Intent(getActivity().getIntent());
        intent.putExtra(com.joad.sinkystar.Utility.Constants.KEY_CLIENT_ROLE, role);
        intent.setClass(getActivity().getApplicationContext(), LiveActivity.class);
        startActivity(intent);
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, PERMISSION_REQ_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQ_CODE) {
            boolean granted = true;
            for (int result : grantResults) {
                granted = (result == PackageManager.PERMISSION_GRANTED);
                if (!granted) break;
            }

            if (granted) {
                resetLayoutAndForward();
            } else {
                Toast.makeText(getActivity(), R.string.need_necessary_permissions, Toast.LENGTH_LONG).show();
            }
        }
    }
}