package com.joad.sinkystar.Fragment.moment.viewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joad.sinkystar.Fragment.moment.adapter.MomentAdapter;
import com.joad.sinkystar.Fragment.moment.model.storyModel;
import com.joad.sinkystar.Fragment.profile.adapter.StoriesAdapter;
import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.R;

import java.util.ArrayList;
import java.util.List;


public class followMomView extends Fragment {


    MomentAdapter momentAdapter;
    StoriesAdapter storiesAdapter;

    private RecyclerView mRecyleView;
    private RecyclerView recycler_view_stories;

    public static followMomView newInstance() {
        followMomView f = new followMomView();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_moment_follow, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyleView = view.findViewById(R.id.recycler_view);

//        recycler_view_stories = view.findViewById(R.id.recycler_view_stories);
//
//        recycler_view_stories.setVisibility(View.VISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        layoutManager.supportsPredictiveItemAnimations();

        mRecyleView.setLayoutManager(layoutManager);

        List<Object> recycleViewItem = new ArrayList<>();
        recycleViewItem.add(new storyModel("view"));

        for (int i = 0; i < 10; i++) {
            recycleViewItem.add(new CommentModel("comment"));
        }


        momentAdapter = new MomentAdapter(getActivity(),getParentFragment(), recycleViewItem, 1);

        mRecyleView.setAdapter(momentAdapter);
//
//        storiesAdapter = new StoriesAdapter(getActivity(), commentList, 1);
//        recycler_view_stories.setAdapter(storiesAdapter);

    }
}