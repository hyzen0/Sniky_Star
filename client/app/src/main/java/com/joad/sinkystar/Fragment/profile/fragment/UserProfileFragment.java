package com.joad.sinkystar.Fragment.profile.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.transition.Slide;

import com.google.android.material.tabs.TabLayout;
import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.Fragment.profile.adapter.GridPhotoAdapter;
import com.joad.sinkystar.Fragment.profile.adapter.GridVideoAdapter;
import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.FragmentUserProfileBinding;

import java.util.ArrayList;

public class UserProfileFragment extends Fragment {


    FragmentUserProfileBinding binding;
    DashBoard dashBoard;
    private GridVideoAdapter gridVideoAdapter;
    private GridPhotoAdapter gridPhotoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setupWindowAnimations();
        dashBoard = (DashBoard) getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_user_profile, container, false);
        View view = binding.getRoot();
        initfun();
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        dashBoard.menu_btn.setVisibility(View.GONE);
        dashBoard.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private void initfun() {

        binding.drawerLayout.setScrimColor(Color.TRANSPARENT);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), binding.drawerLayout, R.string.nav_app_bar_open_drawer_description, R.string.exo_track_role_closed_captions) {
            private float scaleFactor = 6f;

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                binding.drawerLayout.setBackgroundColor(getActivity().getResources().getColor(R.color.grey));
                binding.content.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimaryDark));
                float slideX = drawerView.getWidth() * slideOffset;
                binding.content.setTranslationX(-slideX);
//                binding.content.setScaleX(1 - (slideOffset / scaleFactor));
//                binding.content.setScaleY(1 - (slideOffset / scaleFactor));
                binding.moreOptionBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_forword));

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                binding.drawerLayout.setBackgroundColor(Color.TRANSPARENT);
                binding.content.setBackgroundColor(Color.TRANSPARENT);
                binding.moreOptionBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_more_vert));

            }
        };

        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        binding.drawerLayout.setDrawerElevation(-10f);


        ArrayList<CommentModel> commentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            commentList.add(new CommentModel("asjfd"));
        }
        gridVideoAdapter = new GridVideoAdapter(getActivity(), commentList, 1);
        binding.recyclerView.setAdapter(gridVideoAdapter);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).popBackStack();
            }
        });
        binding.taplayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    gridVideoAdapter = new GridVideoAdapter(getActivity(), commentList, 1);
                    binding.recyclerView.setAdapter(gridVideoAdapter);
                } else {
                    gridPhotoAdapter = new GridPhotoAdapter(getActivity(), commentList, 1);
                    binding.recyclerView.setAdapter(gridPhotoAdapter);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        binding.moreOptionBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PopupMenu menu = new PopupMenu(getContext(), view);
//
//                menu.getMenu().add("Block");
//                menu.getMenu().add("Report...");
//                menu.getMenu().add("Share This Profile");
//                menu.getMenu().add("Mute");
//                menu.getMenu().add("Add To Close Friends");
//
//                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
////                        if (menuItem.getTitle().toString().equals("Change Password")) {
////                            startActivity(new Intent(MainActivity.this, NewPassword.class).putExtra("flag", 1));
////
////                        } else if (menuItem.getTitle().equals("Logout")) {
////                            logoutAlert(MainActivity.this, "Alert !!", "Do you want to Logout  ? ");
////                        } else if (menuItem.getTitle().equals("Refresh")) {
////                            getMainData();
////                        }
//                        return true;
//                    }
//                });
//                menu.show();
//            }
//        });

        binding.moreOptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
        binding.addMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dashBoard.navController.navigate(R.id.action_userProfileFragment_to_messageFragment);
            }
        });
    }


    private void setupWindowAnimations() {
//         Re-enter transition is executed when returning to this activity
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(300);
        setReenterTransition(slideTransition);


        Slide enterTransition = new Slide();
        enterTransition.setDuration(300);
        enterTransition.setSlideEdge(Gravity.RIGHT);
        setEnterTransition(enterTransition);

        Slide exitTransition = new Slide();
        exitTransition.setDuration(300);
        exitTransition.setSlideEdge(Gravity.RIGHT);
        setReturnTransition(exitTransition);

//        getWindow().setExitTransition(slideTransition);
    }
}