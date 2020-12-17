package com.joad.sinkystar.Fragment.search.fragment;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.transition.Fade;

import com.google.android.material.chip.Chip;
import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.Fragment.search.adapter.HashTagAdapter;
import com.joad.sinkystar.Fragment.search.adapter.TrendingAdapter;
import com.joad.sinkystar.Fragment.search.adapter.UserAdapter;
import com.joad.sinkystar.Fragment.search.model.UserModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Utility.Utility;
import com.joad.sinkystar.databinding.FragmentSearchBinding;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    FragmentSearchBinding binding;
    DashBoard dashBoard;
    Utility utility;
    private UserAdapter userAdapter;
    private ArrayList<UserModel> userModelList = new ArrayList<>();
    private TrendingAdapter trendingAdapter;
    private HashTagAdapter hashTagAdapter;
    private UserAdapter musicAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashBoard = (DashBoard) getActivity();
        utility = Utility.getInstance(getActivity());

      //  setupWindowAnimations();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_search, container, false);

        View view = binding.getRoot();
        initView();
        return view;
    }

    private void initView() {
//        ArrayList<CommentModel> commentList = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            commentList.add(new CommentModel("asjfd"));
//        }
//
//        messageAdapter = new MessageAdapter(getActivity(), commentList, 1);
//        binding.recyclerView.setAdapter(messageAdapter);


        binding.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dashBoard.getMenu_btn().callOnClick();
            }
        });
        ArrayList<String> categorys = new ArrayList<>();
        categorys.add("test");
        categorys.add("test tes");
        categorys.add("test 2");
        categorys.add("test 3");
        categorys.add("tes 5 t");
        categorys.add("test e");
        categorys.add("test 4");

        setCategoryChips(categorys);

        for (int i = 0; i < 10; i++) {
            userModelList.add(new UserModel("sdfhj"));
        }


        userAdapter = new UserAdapter(getActivity(), userModelList, 1);
        musicAdapter = new UserAdapter(getActivity(), userModelList, 4);
        trendingAdapter = new TrendingAdapter(getActivity(), userModelList, 1);
        hashTagAdapter = new HashTagAdapter(getActivity(), userModelList, 1);

        binding.recyclerViewUser.setAdapter(userAdapter);
        binding.recyclerViewTrending.setAdapter(trendingAdapter);
        binding.recyclerViewMusic.setAdapter(musicAdapter);
        binding.recyclerViewHashtags.setAdapter(hashTagAdapter);

        binding.searchTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dashBoard.navController.navigate(R.id.action_searchFragment_to_searchFragment2);
            }
        });

    }

    public void setCategoryChips(ArrayList<String> categorys) {
        for (String category :
                categorys) {
            Chip mChip = (Chip) this.getLayoutInflater().inflate(R.layout.item_recentsearch_chips, null, false);
            mChip.setText(category);
            int paddingDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 10,
                    getResources().getDisplayMetrics()
            );
            mChip.setPadding(paddingDp, 0, paddingDp, 0);
            mChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                }
            });
            binding.chipsGroup.addView(mChip);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("SAfdasd ::: onstart moment");
        dashBoard.search.setBackground(getResources().getDrawable(R.color.dart_trans));
        dashBoard.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        dashBoard.setbackgraund(dashBoard.search);
        dashBoard.flagFrag = 4;


    }

    private void setupWindowAnimations() {
        Fade exitTransition = new Fade();
        exitTransition.setDuration(300);

        setReturnTransition(exitTransition);

        setExitTransition(exitTransition);
        setEnterTransition(exitTransition);
    }

}