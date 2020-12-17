package com.joad.sinkystar.Fragment.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.transition.Fade;
import androidx.transition.Slide;

import com.joad.sinkystar.R;
import com.joad.sinkystar.Utility.Utility;
import com.joad.sinkystar.databinding.FragmentSplashBinding;

public class SplashScreen extends Fragment {

    FragmentSplashBinding binding;
    boolean shouldGo = false;
    private long SPLASH_TIME_OUT = 1000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setupWindowAnimations();

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    shouldGo = true;
                    goToNext();


                }


            }
        };
        timerThread.start();
    }

    private void goToNext() {
        if (Utility.getInstance(getActivity()).getPreferences(getActivity(), "isSkiped") != null) {
            if (Utility.getInstance(getActivity()).getPreferences(getActivity(), "isSkiped").equalsIgnoreCase("1")) {
//                getActivity().runOnUiThread(new Runnable() {
//                    public void run() {
//
//
//                    }
//                });

                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                        .addSharedElement(binding.imageView, "logoTrans")
                        .build();

                Navigation.findNavController(getView())
                        .navigate(R.id.action_splashScreen_to_mainFragment, null, null, extras);


            } else {
                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                        .addSharedElement(binding.imageView, "logoTrans")
                        .build();

                Navigation.findNavController(getView())
                        .navigate(R.id.action_splashScreen_to_loginFragment, null, null, extras);
            }
        } else {
            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(binding.imageView, "logoTrans")
                    .build();

            Navigation.findNavController(getView())
                    .navigate(R.id.action_splashScreen_to_loginFragment, null, null, extras);

        }


    }


    @Override
    public void onResume() {
        super.onResume();
        if (shouldGo) {
            goToNext();
        }
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_splash, container, false);
        View view = binding.getRoot();
        return view;
    }


    private void transitionToActivity(Class target, int type) {
        Pair image = Pair.create(binding.imageView, "logoTrans");
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(binding.imageView, "logoTrans");

        startActivity(target, pairs, type);
    }

    private void startActivity(Class target, Pair<View, String>[] pairs, int type) {
        Intent i = new Intent(getActivity(), target);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pairs);
        i.putExtra("type", type);
        startActivity(i, transitionActivityOptions.toBundle());
    }

}