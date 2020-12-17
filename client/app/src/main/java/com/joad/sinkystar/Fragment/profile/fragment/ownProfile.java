package com.joad.sinkystar.Fragment.profile.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.Fragment.profile.adapter.GridPhotoAdapter;
import com.joad.sinkystar.Fragment.profile.adapter.GridVideoAdapter;
import com.joad.sinkystar.Fragment.profile.adapter.StoriesAdapter;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Utility.Utility;
import com.joad.sinkystar.databinding.FragmentOwnProfileBinding;

import java.util.ArrayList;


public class ownProfile extends Fragment {


    FragmentOwnProfileBinding binding;
    DashBoard dashBoard;
    private GridVideoAdapter gridVideoAdapter;
    private GridPhotoAdapter gridPhotoAdapter;
    private StoriesAdapter storiesAdapter;

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
                inflater, R.layout.fragment_own_profile, container, false);
        View view = binding.getRoot();
        initfun();
//        dashBoard.drawerLayout=binding.drawerLayout;
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

        gridVideoAdapter = new GridVideoAdapter(getActivity(), commentList, 0);

        storiesAdapter = new StoriesAdapter(getActivity(), commentList, 0);

        binding.recyclerView.setAdapter(gridVideoAdapter);


        binding.recyclerViewStories.setAdapter(storiesAdapter);


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
                    gridVideoAdapter = new GridVideoAdapter(getActivity(), commentList, 0);
                    binding.recyclerView.setAdapter(gridVideoAdapter);
                } else {
                    gridPhotoAdapter = new GridPhotoAdapter(getActivity(), commentList, 0);
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


        binding.moreOptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
        binding.navEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.closeDrawer(Gravity.RIGHT);
                Bundle bundle = new Bundle();
                bundle.putString("type", "updateProfile");
                dashBoard.navController.navigate(R.id.action_ownProfile_to_editProfile, bundle);

            }
        });
        binding.navSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.closeDrawer(Gravity.RIGHT);
                dashBoard.navController.navigate(R.id.action_ownProfile_to_profileSettingFragment);

            }
        });

        binding.navSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.closeDrawer(Gravity.RIGHT);
                dashBoard.navController.navigate(R.id.action_ownProfile_to_savedVideoFragment);

            }
        });

        binding.navLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.closeDrawer(Gravity.RIGHT);

                showDialog("logout");
            }
        });

        binding.navClearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.closeDrawer(Gravity.RIGHT);

                showDialog("cache");
            }
        });
        binding.navNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.closeDrawer(Gravity.RIGHT);
                dashBoard.navController.navigate(R.id.action_ownProfile_to_notificationFragment);

            }

        });


    }

    private void showDialog(String text) {
        final Dialog itemView = new Dialog(getActivity(), R.style.DialogSlideAnim);

        itemView.setContentView(R.layout.alert_box_common);
        itemView.setCanceledOnTouchOutside(true);
        itemView.setCancelable(true);
        itemView.show();


        TextView message = (TextView) itemView.findViewById(R.id.msg);
        TextView nagtive = (TextView) itemView.findViewById(R.id.nagtiv_btn);

        TextView positive = (TextView) itemView.findViewById(R.id.pstv_btn);

        nagtive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemView.dismiss();
            }
        });
        if (text.equalsIgnoreCase("logout")) {

            message.setText(getActivity().getResources().getString(R.string.logout_msg));
            nagtive.setText(getActivity().getResources().getString(R.string.cancel));
            positive.setText(getActivity().getResources().getString(R.string.logout));
            positive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemView.dismiss();
                    Utility.getInstance(getActivity()).addPreferences(getActivity(), "isSkiped", "" + 0);
                    dashBoard.navController.popBackStack();
                    dashBoard.navController.navigate(R.id.loginFragment);
                }
            });

        } else if (text.equalsIgnoreCase("cache")) {
            message.setText(getActivity().getResources().getString(R.string.clear_cache_msg));
            nagtive.setText(getActivity().getResources().getString(R.string.cancel));
            positive.setText(getActivity().getResources().getString(R.string.clear_cache));
            positive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemView.dismiss();
                }
            });
        }

    }


    private void setupWindowAnimations() {

        Slide enterTransition = new Slide();
        enterTransition.setDuration(300);
        enterTransition.setSlideEdge(Gravity.LEFT);
        setEnterTransition(enterTransition);

        Slide returnTransition = new Slide();
        returnTransition.setDuration(300);
        returnTransition.setSlideEdge(Gravity.LEFT);
        setReturnTransition(returnTransition);


        Slide exitTransition = new Slide();
        exitTransition.setDuration(300);
        exitTransition.setSlideEdge(Gravity.LEFT);
        setExitTransition(exitTransition);

    }
}