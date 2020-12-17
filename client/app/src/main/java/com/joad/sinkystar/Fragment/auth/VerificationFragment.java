package com.joad.sinkystar.Fragment.auth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.transition.TransitionInflater;

import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.FragmentVerificationBinding;

public class VerificationFragment extends Fragment {


    FragmentVerificationBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      //  setupWindowAnimations();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.move));
        setAllowEnterTransitionOverlap(false);
        setAllowReturnTransitionOverlap(false);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_verification, container, false);
        View view = binding.getRoot();
        initView();
        return view;
    }

    private void initView() {
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                        .addSharedElement(binding.imageView2, "logoTrans")
                        .addSharedElement(binding.termCon, "termCon")
                        .build();

                Navigation.findNavController(getView())
                        .navigate(R.id.action_verificationActivity_to_personalFragment, null, null, extras);


            }
        });

        String condition = binding.termCon.getText().toString();


        SpannableString sss = new SpannableString(condition);


        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://time-pass-d5a49.web.app/privacy_policy.html")));

            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.grey));
                ds.setUnderlineText(false);
            }
        };

        sss.setSpan(clickableSpan2, 38, 57, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.termCon.setText(sss);
        binding.termCon.setMovementMethod(LinkMovementMethod.getInstance());

    }


}