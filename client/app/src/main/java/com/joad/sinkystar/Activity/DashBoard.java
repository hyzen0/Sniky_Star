package com.joad.sinkystar.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.joad.sinkystar.AddVideoActivity.RecordActivity;
import com.joad.sinkystar.AgoraActivity.BaseActivity;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Utility.Utility;

public class DashBoard extends BaseActivity implements View.OnClickListener {

    public DrawerLayout drawerLayout;
    public ConstraintLayout content;
    public ImageButton menu_btn;
    public LinearLayout home, moment, live, make_video, search, profile, message;
    public NavController navController;
    public int flagFrag = 0;
    private NavOptions.Builder navBuilder;
    public CallbackManager mCallbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.fullyInitialize();
        mCallbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_dash_board);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        Utility.getInstance(DashBoard.this).addPreferences(DashBoard.this, "canPlay", "0");
        initView();
        drawerLayout.setScrimColor(Color.TRANSPARENT);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_app_bar_open_drawer_description, R.string.exo_track_role_closed_captions) {
            private float scaleFactor = 6f;

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                drawerLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                content.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                float slideX = drawerView.getWidth() * slideOffset;
                content.setTranslationX(-slideX);
//                binding.content.setScaleX(1 - (slideOffset / scaleFactor));
//                binding.content.setScaleY(1 - (slideOffset / scaleFactor));
                menu_btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_back));

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                drawerLayout.setBackgroundColor(Color.TRANSPARENT);
                content.setBackgroundColor(Color.TRANSPARENT);

                menu_btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_button));

                switch (flagFrag) {
                    case 1:

                        break;
                }

            }
        };


        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        drawerLayout.setDrawerElevation(-10f);

        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

    private void initView() {
        drawerLayout = findViewById(R.id.drawerLayout);
//        navigationView = findViewById(R.id.nav_view);
        menu_btn = findViewById(R.id.menu_btn);
        content = findViewById(R.id.content);

        //menu item
        home = findViewById(R.id.nav_home);
        moment = findViewById(R.id.nav_moment);
        make_video = findViewById(R.id.nav_make_video);
        live = findViewById(R.id.nav_live);
        search = findViewById(R.id.nav_search);
        profile = findViewById(R.id.nav_Profile);
        message = findViewById(R.id.nav_message);


        //onclick list
        home.setOnClickListener(this);
        moment.setOnClickListener(this);
        make_video.setOnClickListener(this);
        live.setOnClickListener(this);
        search.setOnClickListener(this);
        profile.setOnClickListener(this);
        message.setOnClickListener(this);

        menu_btn.setVisibility(View.GONE);

        navBuilder = new NavOptions.Builder();
        navBuilder.setEnterAnim(R.anim.animate_slide_up_enter).setExitAnim(R.anim.animate_slide_up_exit)
                .setPopEnterAnim(R.anim.animate_slide_down_enter).setPopExitAnim(R.anim.animate_slide_down_exit);

    }

    public ImageButton getMenu_btn() {
        return this.menu_btn;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(mCallbackManager!=null){
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
        try {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                fragment.onActivityResult(requestCode, resultCode, data);
                Log.d("Activity", "ON RESULT CALLED");
            }
        } catch (Exception e) {
            Log.d("ERROR", e.toString());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.nav_home:
                if (flagFrag != 0) {
                    navController.popBackStack(R.id.mainFragment, false);
                    flagFrag = 0;
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }
                break;
            case R.id.nav_moment:
                if (flagFrag != 1) {


                    navController.navigate(R.id.momentFragment, null, navBuilder.build());
                    flagFrag = 1;
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }
                break;
            case R.id.nav_live:
                if (flagFrag != 2) {
                    navController.navigate(R.id.liveFragment, null, navBuilder.build());
                    flagFrag = 2;
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }
                break;
            case R.id.nav_make_video:

                startActivity(new Intent(DashBoard.this, RecordActivity.class));

                break;


            case R.id.nav_search:
                if (flagFrag != 4) {
                    navController.navigate(R.id.searchFragment, null, navBuilder.build());
                    flagFrag = 4;
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }
                break;
            case R.id.nav_message:
                if (flagFrag != 6) {
                    navController.navigate(R.id.messageFragment, null, navBuilder.build());
                    flagFrag = 6;
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }
                break;

            case R.id.nav_Profile:
                if (flagFrag != 5) {
                    navController.navigate(R.id.ownProfile, null, navBuilder.build());
                    flagFrag = 5;
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }
                break;
        }
    }

    public void setbackgraund(View view) {
        home.setBackground(getResources().getDrawable(android.R.color.transparent));
        moment.setBackground(getResources().getDrawable(android.R.color.transparent));
        profile.setBackground(getResources().getDrawable(android.R.color.transparent));
        search.setBackground(getResources().getDrawable(android.R.color.transparent));
        live.setBackground(getResources().getDrawable(android.R.color.transparent));
        make_video.setBackground(getResources().getDrawable(android.R.color.transparent));
        message.setBackground(getResources().getDrawable(android.R.color.transparent));
        view.setBackground(getResources().getDrawable(R.color.dart_trans));
    }

}