package com.joad.sinkystar.Fragment.message.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Fade;

import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.Fragment.message.adapter.MessageAdapter;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Utility.Utility;
import com.joad.sinkystar.databinding.FragmentMessageBinding;

import java.util.ArrayList;

public class MessageFragment extends Fragment {

    FragmentMessageBinding binding;
    DashBoard dashBoard;
    Utility utility;
    MessageAdapter messageAdapter;


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
                inflater, R.layout.fragment_message, container, false);

        View view = binding.getRoot();
        initView();
        return view;
    }

    private void initView() {
        ArrayList<CommentModel> commentList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            commentList.add(new CommentModel("asjfd"));
        }

        messageAdapter = new MessageAdapter(getActivity(), commentList, 1);
        binding.recyclerView.setAdapter(messageAdapter);

//        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//                if(newState==0){
//                    Utility.getInstance(getActivity()).expand(binding.userNameLy);
//
//                }else{
//                    Utility.getInstance(getActivity()).collapse(binding.userNameLy);
//
//                }
//
//                System.out.println("sdfsad ::::  "+newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
////                System.out.println("sadfsadf :::  " + dy);
////                if (dy < 0) {
////
////                    Utility.getInstance(getActivity()).expand(binding.userNameLy);
////
////                } else if (dy > 0) {
////                    Utility.getInstance(getActivity()).collapse(binding.userNameLy);
////
////                    // Recycle view scrolling down...
////
////                }
//
//            }
//        });
        binding.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dashBoard.getMenu_btn().callOnClick();
            }
        });
    }



    @Override
    public void onResume() {
        super.onResume();
        dashBoard.message.setBackground(getResources().getDrawable(R.color.dart_trans));
        dashBoard.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        dashBoard.setbackgraund(dashBoard.message);
        dashBoard.flagFrag = 6;

    }

    private void setupWindowAnimations() {
        Fade exitTransition = new Fade();
        exitTransition.setDuration(300);

        setReturnTransition(exitTransition);

        setExitTransition(exitTransition);
        setEnterTransition(exitTransition);
    }

}