package com.joad.sinkystar.Fragment.profile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.Fragment.profile.adapter.NotificationAdapter;
import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.FragmentNotificationBinding;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {


    ArrayList<CommentModel> commentList = new ArrayList<>();
    private FragmentNotificationBinding binding;
    private NotificationAdapter notificationAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_notification, container, false);
        View view = binding.getRoot();
        initView();
//      dashBoard.drawerLayout=binding.drawerLayout;
        return view;
    }

    private void initView() {
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        for (int i = 0; i < 10; i++) {
            commentList.add(new CommentModel("asjfd"));
        }
        setbuttonClick(0);

        binding.postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setbuttonClick(0);
            }
        });
        binding.requestNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setbuttonClick(1);
            }
        });

    }


    private void setbuttonClick(int postion) {

        binding.postBtn.setTextColor(getResources().getColor(R.color.yellow));
        binding.requestNoti.setTextColor(getResources().getColor(R.color.yellow));
        switch (postion) {
            case 0:
                binding.postBtn.setTextColor(getResources().getColor(R.color.grey));
                notificationAdapter = new NotificationAdapter(getActivity(), commentList, 1);

                binding.notificationRecycleView.setAdapter(notificationAdapter);
                break;
            case 1:
                binding.requestNoti.setTextColor(getResources().getColor(R.color.grey));
                notificationAdapter = new NotificationAdapter(getActivity(), commentList, 2);
                binding.notificationRecycleView.setAdapter(notificationAdapter);
                break;
        }

    }

}