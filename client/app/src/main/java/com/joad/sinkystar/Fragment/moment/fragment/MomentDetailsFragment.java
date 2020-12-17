package com.joad.sinkystar.Fragment.moment.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.transition.TransitionInflater;

import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.Fragment.home.adapter.CommentAdapter;
import com.joad.sinkystar.Fragment.home.model.CommentModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Utility.Utility;
import com.joad.sinkystar.databinding.FragmentMomentDetailsBinding;

import java.util.ArrayList;

public class MomentDetailsFragment extends Fragment {

    FragmentMomentDetailsBinding binding;

    ArrayList<CommentModel> commentList = new ArrayList<>();

    CommentAdapter commentAdapter;

    private Utility utility;
    private DashBoard dashBoard;
    private Bundle masterBundle ;
    private boolean doubleBackToExitPressedOnce=false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      //  setupWindowAnimations();
        utility = Utility.getInstance(getActivity());
        dashBoard = (DashBoard) getActivity();
        masterBundle = getArguments();
    }


    private void setupWindowAnimations() {
        Slide enterTransition = new Slide();
        enterTransition.setDuration(300);
        enterTransition.setSlideEdge(Gravity.BOTTOM);
        setEnterTransition(enterTransition);

        Slide exitTransition = new Slide();
        exitTransition.setDuration(300);
        exitTransition.setSlideEdge(Gravity.BOTTOM);
        setReturnTransition(exitTransition);


        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.BOTTOM);
        slideTransition.setDuration(300);
        setReenterTransition(slideTransition);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.move));
        setAllowEnterTransitionOverlap(false);
        setAllowReturnTransitionOverlap(false);

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_moment_details, container, false);

        View view = binding.getRoot();
        initView();
        return view;
    }
    @Override
    public void onStop() {
        super.onStop();
        utility.hideKeyboard(getActivity());

    }
    @Override
    public void onStart() {
        super.onStart();
        dashBoard.menu_btn.setVisibility(View.GONE);
        dashBoard.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }
    @SuppressLint("ClickableViewAccessibility")
    public void initView() {

//        System.out.println("asdfsd ::::  "+masterBundle.getString("userimgm"));
//
//        binding.momentThum.setTransitionName(masterBundle.getString("userimgm"));
//        binding.userProfile.setTransitionName(masterBundle.getString("userProfilem"));
//        binding.linearLayout2.setTransitionName(masterBundle.getString("linearLayout2m"));
//        binding.decMoment.setTransitionName(masterBundle.getString("decMomentm"));
//        binding.linearLayout2.setTransitionName(masterBundle.getString("linearLayout2"));
//        startPostponedEnterTransition();

        commentAdapter = new CommentAdapter(getActivity(), commentList, 1);
        binding.commentRecycleView.setAdapter(commentAdapter);
        binding.ly2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                // Get finger position on screen
                final int Y = (int) event.getRawY();

                // Switch on motion event type
                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        // save default base layout height
                        getActivity().onBackPressed();
                        break;

                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });
        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.commentEdit.getText().toString().length() > 0) {

                    commentList.add(new CommentModel(binding.commentEdit.getText().toString()));
                    int newMsgPosition = commentList.size() - 1;

                    commentAdapter.notifyItemChanged(newMsgPosition);
                    binding.commentEdit.setText("");
                    binding.commentRecycleView.scrollToPosition(newMsgPosition);
                    Utility.getInstance(getActivity()).collapse(binding.ly2);

                }
            }
        });



        binding.commentRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                System.out.println("Sfsadf ::::   ne   "+newState);
                if(IsRecyclerViewAtTop()){
                    if(binding.ly2.getVisibility()==View.GONE) {
                        Utility.getInstance(getActivity()).expand(binding.ly2);
                        doubleBackToExitPressedOnce=true;
                        swipdismiss();

                    }else{
                        if(newState==0 && !doubleBackToExitPressedOnce){
                            getActivity().onBackPressed();
                        }
                    }

                }


            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy < 0) {
                    doubleBackToExitPressedOnce=false;

                    // Recycle view scrolling up...
//                    Utility.getInstance(getActivity()).expand(binding.ly2);

                } else if (dy > 0) {
                    Utility.getInstance(getActivity()).collapse(binding.ly2);
                    doubleBackToExitPressedOnce=true;

                    // Recycle view scrolling down...

                }
            }
        });
    }
    private boolean IsRecyclerViewAtTop()   {
        if(binding.commentRecycleView.getChildCount() == 0)
            return true;
        return binding.commentRecycleView.getChildAt(0).getTop() == 0;
    }

    private  void swipdismiss(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 200);
    }
}