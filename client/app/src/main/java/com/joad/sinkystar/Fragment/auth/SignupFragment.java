package com.joad.sinkystar.Fragment.auth;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.transition.TransitionInflater;

import com.joad.sinkystar.Fragment.auth.model.loginModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Retrofit.RetrofitApi;
import com.joad.sinkystar.Utility.Utility;
import com.joad.sinkystar.databinding.FragmentSingupBinding;

import java.util.Map;

public class SignupFragment extends Fragment implements RetrofitApi.ResponseListener {


    int type = 0;

    FragmentSingupBinding binding;
    private Bundle masterBundle;
    private Dialog mProgressBar;
    private Utility utility;
    private int buttonType = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utility = Utility.getInstance(getActivity());
        masterBundle = this.getArguments();
        if (masterBundle.containsKey("type")) {
            type = masterBundle.getInt("type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.move));
        setAllowEnterTransitionOverlap(false);
        setAllowReturnTransitionOverlap(false);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_singup, container, false);
        View view = binding.getRoot();
        initView();
        return view;
    }


    private void initView() {

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //0= for sending otp, 1= verify otp
                if (buttonType == 0) {
                    Map<String, Object> login = new ArrayMap<>();
                    if (type == 0) {
                        login.put("phone", "" + binding.emaPhone.getText().toString().trim());
                    } else {
                        login.put("email", "" + binding.emaPhone.getText().toString().trim());

                    }
                    mProgressBar = RetrofitApi.getInstance().getProgress(getActivity());
                    mProgressBar.show();
                    RetrofitApi.getInstance().RegistrationUser(getActivity(), SignupFragment.this,
                            login);
                }
            }
        });

        //mobile number
        if (type == 0) {
            binding.emaPhone.setInputType(InputType.TYPE_CLASS_NUMBER);
            binding.emaPhoneLy.setHint(getString(R.string.phone_number));
            binding.desTitle.setText(R.string.enter_your_contact_number_to_get_an_one_time_password);
            binding.emaPhoneLy.setPrefixText("+91 ");

        } else if (type == 1) {
            binding.emaPhone.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            binding.emaPhoneLy.setHint(getString(R.string.email_address));
            binding.desTitle.setText(R.string.enter_your_email_id_to_get_a_verification_code);
        }

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


    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {
        if (obj instanceof loginModel) {
            try {
                loginModel checkModel = (loginModel) obj;
                if (checkModel.getCode() == 200) {
                    if (buttonType == 0) {
                        if (checkModel.getMsg() != null) {
                            utility.displayAlert(getActivity(), "Sent", "An OTP Sent to Your Number.");
                            binding.otpView.setVisibility(View.VISIBLE);
                            binding.loginBtn.setText(getString(R.string.verify));
                            buttonType = 1;
                        } else {
                            Toast.makeText(getContext(), "Something Went Wrong !!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (checkModel.getMsg() != null) {
                            utility.displayAlert(getActivity(), "Success !!", checkModel.getMsg());
                            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                                    .addSharedElement(binding.imageView2, "logoTrans")
                                    .addSharedElement(binding.termCon, "termCon")
                                    .build();
                            Navigation.findNavController(getView())
                                    .navigate(R.id.action_signupFragment_to_verificationActivity, null, null, extras);
                        } else {
                            Toast.makeText(getContext(), "Something Went Wrong !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    if (checkModel.getMsg() != null) {
                        utility.displayAlert(getActivity(), "Invalid !!", checkModel.getMsg());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Something Went Wrong !!", Toast.LENGTH_SHORT).show();
                System.out.println("ASdfasd ::::");

            }
        }
    }
}