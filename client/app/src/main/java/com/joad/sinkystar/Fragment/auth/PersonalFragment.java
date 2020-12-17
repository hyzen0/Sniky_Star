package com.joad.sinkystar.Fragment.auth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.transition.Fade;
import androidx.transition.Slide;
import androidx.transition.TransitionInflater;

import com.google.android.material.button.MaterialButton;
import com.joad.sinkystar.Fragment.others.Bottom_sheet_add_photo;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Utility.Utility;
import com.joad.sinkystar.databinding.FragmentPersonalDetailsBinding;

public class PersonalFragment extends Fragment implements MaterialButton.OnCheckedChangeListener {


    FragmentPersonalDetailsBinding binding;
    Bundle masterBundle;
    private String is_photo;
    private Uri ImageUri;
    private String filePath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      //  setupWindowAnimations();
        masterBundle = getArguments();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.move));
        setAllowEnterTransitionOverlap(false);
        setAllowReturnTransitionOverlap(false);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_personal_details, container, false);
        View view = binding.getRoot();
        initView();
        return view;
    }

    private void initView() {

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
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
//                        .addSharedElement(binding.imageView2, "logoTrans")
//                        .addSharedElement(binding.termCon, "termCon")
//                        .build();

//                ActivityNavigator(this)
//                        .createDestination()
//                        .setIntent(Intent(this, SecondActivity::class.java))
//                    .navigate(null, null)


//                Intent i = new Intent(getActivity(), DashBoard.class);
//                ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pairs);
//                startActivity(i);
                Navigation.findNavController(getView())
                        .navigate(R.id.action_personalFragment_to_mainFragment);


            }
        });

        binding.femaleBtn.addOnCheckedChangeListener(this);
        binding.maleBtn.addOnCheckedChangeListener(this);
        binding.otherBtn.addOnCheckedChangeListener(this);
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
                Utility.getInstance(getActivity()).hideKeyboard(getActivity());
            }
        });

        if (masterBundle != null) {
            if (masterBundle.getString("type").equalsIgnoreCase("updateProfile")) {
                binding.layoutBack.setVisibility(View.VISIBLE);
                binding.titile1.setText("UPDATE PROFILE");
            }
        }

        binding.addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_personalFragment_to_bottom_sheet_add_photo2);

                Bottom_sheet_add_photo filter_bottom_sheet = Bottom_sheet_add_photo.newInstance(getActivity(), PersonalFragment.this);
                filter_bottom_sheet.show(getChildFragmentManager(), "tile");


            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("Sfdsa ::::  +" + requestCode);
        try {
            if (resultCode == Activity.RESULT_OK && requestCode == 2404) {
                is_photo = null;
                try {
                    ImageUri = data.getData();
                    assert ImageUri != null;
                    filePath = ImageUri.getPath();
                    binding.addPhoto.setImageURI(ImageUri);
//                    Bitmap bitmap = null;
//                    try {
//                        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), ImageUri);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    saveToInternalStorage(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void setupWindowAnimations() {
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(300);
        setReenterTransition(slideTransition);

        Fade enterTransition = new Fade();
        enterTransition.setDuration(300);
        setExitTransition(enterTransition);

        Slide exitTransition = new Slide();
        exitTransition.setDuration(300);
        exitTransition.setSlideEdge(Gravity.RIGHT);
        setReturnTransition(exitTransition);
    }

    @Override
    public void onCheckedChanged(MaterialButton button, boolean isChecked) {
        if (isChecked) {
            switch (button.getId()) {
                case R.id.female_btn:

                    filterdata(0);

                    break;
                case R.id.male_btn:

                    filterdata(1);

                    break;
                case R.id.other_btn:

                    filterdata(2);

                    break;
            }
        } else {
            if (binding.toggleGroup2.getCheckedButtonId() == -1) {
                button.setChecked(true);
            }

        }
    }

    private void filterdata(int i) {
        binding.maleBtn.setTextColor(getResources().getColor(R.color.grey));
        binding.femaleBtn.setTextColor(getResources().getColor(R.color.grey));
        binding.otherBtn.setTextColor(getResources().getColor(R.color.grey));
        switch (i) {
            case 0:
                binding.femaleBtn.setTextColor(getResources().getColor(R.color.yellow));
                break;
            case 1:
                binding.maleBtn.setTextColor(getResources().getColor(R.color.yellow));

                break;
            case 2:
                binding.otherBtn.setTextColor(getResources().getColor(R.color.yellow));

                break;
        }
    }
}