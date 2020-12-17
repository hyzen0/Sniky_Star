package com.joad.sinkystar.Fragment.search.viewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.Fragment.moment.model.storyModel;
import com.joad.sinkystar.Fragment.search.adapter.HashTagBothAdapter;
import com.joad.sinkystar.Fragment.search.model.UserModel;
import com.joad.sinkystar.R;

import java.util.ArrayList;
import java.util.List;

public class hashTagViewPager extends Fragment {
    int flag = 0;
    Bundle masterBundle;
    RecyclerView recyclerView;
    private ArrayList<UserModel> userModelList = new ArrayList<>();


    public static hashTagViewPager newInstance(int flag) {
        hashTagViewPager f = new hashTagViewPager();
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
        View view = inflater.inflate(R.layout.viewpager_moment_follow, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {


        recyclerView = view.findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        List<Object> recycleViewItem = new ArrayList<>();

        recycleViewItem.add(new storyModel("view"));
        recycleViewItem.add(new storyModel("view"));

        for (int i = 0; i < 10; i++) {
            recycleViewItem.add(new CommentModel("comment"));
            if (i == 5) {
                recycleViewItem.add(new storyModel("view"));

            }
        }

        HashTagBothAdapter hashTagBothAdapter = new HashTagBothAdapter(getActivity(), recycleViewItem, 1);

        recyclerView.setAdapter(hashTagBothAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
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