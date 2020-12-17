package com.joad.sinkystar.Fragment.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.Fragment.home.adapter.CommentAdapter;
import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.Fragment.profile.adapter.GridVideoAdapter;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Utility.Utility;
import com.joad.sinkystar.databinding.FragmentSongBinding;

import java.util.ArrayList;

public class SongFragment extends Fragment {

    FragmentSongBinding binding;

    ArrayList<CommentModel> commentList = new ArrayList<>();

    CommentAdapter commentAdapter;

    private Utility utility;
    private DashBoard dashBoard;
    private Bundle masterBundle;
    private boolean doubleBackToExitPressedOnce = false;
    private GridVideoAdapter gridVideoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utility = Utility.getInstance(getActivity());
        dashBoard = (DashBoard) getActivity();
        masterBundle = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_song, container, false);

        View view = binding.getRoot();
        initView();
        return view;
    }

    private void initView() {
        ArrayList<CommentModel> commentList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            commentList.add(new CommentModel("asjfd"));
        }

        gridVideoAdapter = new GridVideoAdapter(getActivity(), commentList, 3);


        binding.commentRecycleView.setAdapter(gridVideoAdapter);


        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).popBackStack();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        dashBoard.menu_btn.setVisibility(View.GONE);
        dashBoard.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }
}