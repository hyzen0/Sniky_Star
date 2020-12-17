package com.joad.sinkystar.Fragment.others;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Utility.Utility;
import com.joad.sinkystar.databinding.FragmentPhotoBinding;
import com.squareup.picasso.Callback;


public class PhotoFragment extends Fragment {

    FragmentPhotoBinding binding;


    private Utility utility;
    private DashBoard dashBoard;
    private Bundle masterBundle;

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
        setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.move));
        setAllowEnterTransitionOverlap(false);
        setAllowReturnTransitionOverlap(false);

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_photo, container, false);

        View view = binding.getRoot();
        initView();
        return view;
    }

    private void initView() {
        if(masterBundle!=null) {
            binding.photoView.setTransitionName(masterBundle.getString("momentImgm"));
            binding.userProfile.setTransitionName(masterBundle.getString("userProfilem"));
            binding.linearLayout2.setTransitionName(masterBundle.getString("linearLayout2m"));
            binding.decMoment.setTransitionName(masterBundle.getString("decMomentm"));

        }
        binding.photoView.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.dummy_moment));

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
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