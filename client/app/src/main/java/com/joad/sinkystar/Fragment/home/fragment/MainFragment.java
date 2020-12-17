package com.joad.sinkystar.Fragment.home.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.transition.Fade;
import androidx.transition.Slide;
import androidx.transition.TransitionInflater;
import androidx.viewpager.widget.ViewPager;

import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.Fragment.home.adapter.MainViewPagerAdapter;
import com.joad.sinkystar.Fragment.home.viewPager.followViewPager;
import com.joad.sinkystar.R;

public class MainFragment extends Fragment implements View.OnClickListener {


    public boolean doubleBackToExitPressedOnce = false;
    DashBoard dashBoard;
    MainViewPagerAdapter mainViewPagerAdapter;
    private ImageButton mBtnMenu;
    private ImageView mImageView5;
    private TextView mBtnFollow;
    private TextView mBtnPopular;
    private TextView mBtnNearBy;
    private RelativeLayout action_bar_ly;
    private LinearLayout mLinearLayout3;
    private int mCurPos;
    private ViewPager viewPager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarTransparent();
      //  setupWindowAnimations();
        dashBoard = (DashBoard) getActivity();
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {

            @Override
            public void handleOnBackPressed() {

                if (doubleBackToExitPressedOnce) {
                    dashBoard.finishAffinity();
                    return;
                }
                doubleBackToExitPressedOnce = true;
                Toast.makeText(getActivity(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.move));
        setAllowEnterTransitionOverlap(false);
        setAllowReturnTransitionOverlap(false);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        return view;

    }


    @Override
    public void onStart() {
        super.onStart();
        setStatusBarTransparent();
        dashBoard.home.setBackground(getResources().getDrawable(R.color.dart_trans));
        dashBoard.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        dashBoard.setbackgraund(dashBoard.home);
        dashBoard.flagFrag = 0;
    }


    public void initView(View view) {
        viewPager = view.findViewById(R.id.view_pager);
        action_bar_ly = view.findViewById(R.id.action_bar_ly);
        mBtnMenu = (ImageButton) view.findViewById(R.id.menu_btn);
        mBtnMenu.setOnClickListener(this);

        mImageView5 = (ImageView) view.findViewById(R.id.imageView5);
        mBtnFollow = (TextView) view.findViewById(R.id.follow_btn);
        mBtnFollow.setOnClickListener(this);
        mBtnPopular = (TextView) view.findViewById(R.id.popular_btn);
        mBtnPopular.setOnClickListener(this);
        mBtnNearBy = (TextView) view.findViewById(R.id.nearBy_btn);
        mBtnNearBy.setOnClickListener(this);
        mLinearLayout3 = (LinearLayout) view.findViewById(R.id.linearLayout3);

        setbuttonClick(0);


        mainViewPagerAdapter = new MainViewPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mainViewPagerAdapter.addFragment(followViewPager.newInstance(0), "follow");
        mainViewPagerAdapter.addFragment(followViewPager.newInstance(1), "popular");
        mainViewPagerAdapter.addFragment(followViewPager.newInstance(2), "nearby");
        viewPager.setAdapter(mainViewPagerAdapter);


//        mVideosViewPager.setOffscreenPageLimit(4);


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


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.follow_btn:
                viewPager.setCurrentItem(0);
                break;
            case R.id.popular_btn:
                viewPager.setCurrentItem(1);
                break;
            case R.id.nearBy_btn:
                viewPager.setCurrentItem(2);
                break;

            default:
                break;
        }
    }


    private void setbuttonClick(int postion) {

        mBtnFollow.setTextColor(getResources().getColor(R.color.yellow));
        mBtnPopular.setTextColor(getResources().getColor(R.color.yellow));
        mBtnNearBy.setTextColor(getResources().getColor(R.color.yellow));
        switch (postion) {
            case 0:
                mBtnFollow.setTextColor(getResources().getColor(R.color.grey));

                break;
            case 1:
                mBtnPopular.setTextColor(getResources().getColor(R.color.grey));

                break;
            case 2:
                mBtnNearBy.setTextColor(getResources().getColor(R.color.grey));

                break;
        }

    }

    protected void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getActivity().getWindow().getDecorView();
            decorView.setOnApplyWindowInsetsListener((v, insets) -> {
                WindowInsets defaultInsets = v.onApplyWindowInsets(insets);
                return defaultInsets.replaceSystemWindowInsets(
                        defaultInsets.getSystemWindowInsetLeft(),
                        0,
                        defaultInsets.getSystemWindowInsetRight(),
                        defaultInsets.getSystemWindowInsetBottom());
            });
            ViewCompat.requestApplyInsets(decorView);
            getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getActivity(), android.R.color.transparent));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        removeTransparentStatusBar();
    }

    protected void removeTransparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getActivity().getWindow().getDecorView();
            decorView.setOnApplyWindowInsetsListener((v, insets) -> {
                WindowInsets defaultInsets = v.onApplyWindowInsets(insets);
                return defaultInsets.replaceSystemWindowInsets(
                        defaultInsets.getSystemWindowInsetLeft(),
                        defaultInsets.getSystemWindowInsetTop(),
                        defaultInsets.getSystemWindowInsetRight(),
                        defaultInsets.getSystemWindowInsetBottom());
            });
            ViewCompat.requestApplyInsets(decorView);
            getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        }
    }


}