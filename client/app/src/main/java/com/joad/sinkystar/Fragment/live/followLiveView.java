package com.joad.sinkystar.Fragment.live;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.Fragment.profile.adapter.StoriesAdapter;
import com.joad.sinkystar.R;

import java.util.ArrayList;


public class followLiveView extends Fragment {


    LiveAdapter liveAdapter;
    StoriesAdapter storiesAdapter;

    private RecyclerView mRecyleView;
    private RecyclerView recycler_view_stories;
    private int flag;

    public static followLiveView newInstance(int flag) {
        followLiveView f = new followLiveView();
        f.flag = flag;
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
//        recycler_view_stories.setVisibility(View.VISIBLE);

        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        layoutManager.supportsPredictiveItemAnimations();

        mRecyleView.setLayoutManager(layoutManager);

        ArrayList<CommentModel> recycleViewItem = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            recycleViewItem.add(new CommentModel("comment"));
        }


        liveAdapter = new LiveAdapter(getActivity(), getParentFragment(), recycleViewItem,flag );

        mRecyleView.setAdapter(liveAdapter);
//
//        storiesAdapter = new StoriesAdapter(getActivity(), commentList, 1);
//        recycler_view_stories.setAdapter(storiesAdapter);

    }
}