package com.joad.sinkystar.Fragment.home.viewPager;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.joad.sinkystar.Fragment.home.adapter.MainVideoAdapter;
import com.joad.sinkystar.Fragment.home.model.ModelMainVideo;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Utility.Utility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class followViewPager extends Fragment {
    public static List<ModelMainVideo> tiktokData;
    int flag = 0;
    Bundle masterBundle;
    private ViewPager2 mRecyleView;
    private MainVideoAdapter mTiktok3Adapter;
    private RelativeLayout topLayout;
    private ImageButton back_btn;
    private ArrayList<ModelMainVideo> MainVideoList = new ArrayList<>();

    public static List<ModelMainVideo> getTiktokDataFromAssets(Context context) {
        try {
            if (tiktokData == null) {
                InputStream is = context.getAssets().open("tiktok_data");
                int length = is.available();
                byte[] buffer = new byte[length];
                is.read(buffer);
                is.close();
                String result = new String(buffer, Charset.forName("UTF-8"));
                tiktokData = ModelMainVideo.arrayTiktokBeanFromData(result);
            }
            return tiktokData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static followViewPager newInstance(int flag) {
        followViewPager f = new followViewPager();
        f.flag = flag;
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        masterBundle = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_follow, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyleView = view.findViewById(R.id.recycler_view);
        topLayout = view.findViewById(R.id.top_layout);
        back_btn = view.findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        layoutManager.supportsPredictiveItemAnimations();


        mTiktok3Adapter = new MainVideoAdapter(getActivity(), followViewPager.this, MainVideoList, mRecyleView, flag);
        mRecyleView.setAdapter(mTiktok3Adapter);
        mRecyleView.setOrientation(ViewPager2.ORIENTATION_VERTICAL);


        mRecyleView.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                System.out.println("sdfs seleted :: " + flag + "  sdfs   " + Utility.getInstance(getActivity()).getPreferences(getActivity(), "canPlay"));
                if (Utility.getInstance(getActivity()).getPreferences(getActivity(), "canPlay").equalsIgnoreCase("" + flag)) {
                    resumePlayer();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                System.out.println("sdfs chagedn :: " + state);
            }
        });
        addData();

    }

    public void addData() {

        if (masterBundle != null) {
            if (masterBundle.getInt("onlyOne", 0) == 1) {

                topLayout.setVisibility(View.VISIBLE);

                MainVideoList.clear();

                MainVideoList.addAll(getTiktokDataFromAssets(getActivity()));

            } else {
                MainVideoList.addAll(getTiktokDataFromAssets(getActivity()));
            }
        } else {
            MainVideoList.addAll(getTiktokDataFromAssets(getActivity()));
        }

        if (mTiktok3Adapter != null) {
            mTiktok3Adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        System.out.println("sfd   on pause ");
        stopPlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("sfd   on resume from resume ");
        Utility.getInstance(getActivity()).addPreferences(getActivity(), "canPlay", "" + flag);
        resumePlayer();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (masterBundle != null) {
            if (masterBundle.getInt("onlyOne", 0) == 1) {
                setStatusBarTransparent();
            }
        }
        if (Utility.getInstance(getActivity()).getPreferences(getActivity(), "canPlay").equalsIgnoreCase("" + flag)) {

            resumePlayer();
        }

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
        System.out.println("sfd   on stop ");
        if (masterBundle != null) {
            if (masterBundle.getInt("onlyOne", 0) == 1) {
                removeTransparentStatusBar();
            }
        }
        stopPlayer();
        releasePlayer();
    }


    public void releasePlayer() {


        int currentPosition = mRecyleView.getCurrentItem();

        mRecyleView.post(new Runnable() {
            @Override
            public void run() {


                RecyclerView mrevcue2 = (RecyclerView) mRecyleView.getChildAt(0);
                View child = mrevcue2.getLayoutManager().findViewByPosition(currentPosition);

                System.out.println("cureent positions" + currentPosition);
                if (child != null) {
                    MainVideoAdapter.myViewHolder holder = (MainVideoAdapter.myViewHolder) child.getTag();

                    if (holder != null) {
                        holder.stopPlayer();
                        holder.releasePlayer();
                    }

                } else {
                    System.out.println("chilid not found");
                }

            }

        });


    }

    public void stopPlayer() {


        int currentPosition = mRecyleView.getCurrentItem();


        mRecyleView.post(new Runnable() {
            @Override
            public void run() {


                RecyclerView mrevcue2 = (RecyclerView) mRecyleView.getChildAt(0);
                View child = mrevcue2.getLayoutManager().findViewByPosition(currentPosition);

                if (child != null) {
                    MainVideoAdapter.myViewHolder holder = (MainVideoAdapter.myViewHolder) child.getTag();

                    try {
                        if (holder != null) {
                            if (holder.isPlaying()) {
                                holder.pushPlayer();
                            }
                        }
                    } catch (Exception e) {
                        //System.out.println("sdf "+e.toString());
                    }
                } else {
                    //System.out.println("chilid not found");
                }

            }

        });


    }

    public void resumePlayer() {


        int currentPosition = mRecyleView.getCurrentItem();


        mRecyleView.post(new Runnable() {
            @Override
            public void run() {

                RecyclerView mrevcue2 = (RecyclerView) mRecyleView.getChildAt(0);
                View child = mrevcue2.getLayoutManager().findViewByPosition(currentPosition);

                System.out.println("cureent positions " + currentPosition);

                if (child != null) {

                    MainVideoAdapter.myViewHolder holder = (MainVideoAdapter.myViewHolder) child.getTag();
                    if (holder != null) {
                        System.out.println("sadfs on resume :::  " + child);

                        holder.resumePlayer();
                    }
                } else {
                    System.out.println("sadfs on resume ::: child not found ");

                }

            }

        });


    }

}