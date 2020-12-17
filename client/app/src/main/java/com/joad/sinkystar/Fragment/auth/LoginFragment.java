package com.joad.sinkystar.Fragment.auth;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.ArrayMap;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.joad.sinkystar.Activity.DashBoard;
import com.joad.sinkystar.Fragment.auth.model.loginModel;
import com.joad.sinkystar.R;
import com.joad.sinkystar.Retrofit.RetrofitApi;
import com.joad.sinkystar.Utility.Utility;
import com.joad.sinkystar.databinding.FragmentLoginBinding;

import java.util.Arrays;
import java.util.Map;


public class LoginFragment extends Fragment implements RetrofitApi.ResponseListener {


    FragmentLoginBinding binding;
    int flag = 0;
    Utility utility;
    private GoogleSignInClient mGoogleSignInClient;
    private int GOOGLE_SIGN_IN = 100;
    private Dialog mProgressBar;
    private DashBoard dashBoard;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        utility = Utility.getInstance(getActivity());
        dashBoard = (DashBoard) getActivity();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.move));
        setAllowEnterTransitionOverlap(false);
        setAllowReturnTransitionOverlap(false);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_login, container, false);
        View view = binding.getRoot();
        initView();
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
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


        binding.phoneBtn.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("type", 0);
            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(binding.imageView2, "logoTrans")
                    .build();

            Navigation.findNavController(getView())
                    .navigate(R.id.action_loginFragment_to_signupFragment, bundle, null, extras);


        });
        binding.emailBtn.setOnClickListener(view -> {

            Bundle bundle = new Bundle();
            bundle.putInt("type", 1);
            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(binding.imageView2, "logoTrans")
                    .build();

            Navigation.findNavController(getView())
                    .navigate(R.id.action_loginFragment_to_signupFragment, bundle, null, extras);


        });


        final GestureDetector gd = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {


            //here is the method for double tap


            @Override
            public boolean onDoubleTap(MotionEvent e) {

                //your action here for double tap e.g.
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);

            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onDown(MotionEvent motionEvent) {

                return true;
            }


        });

        gd.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                //your action here for double tap e.g.
                Log.d("OnDoubleTapListener", "onsingletap");

                Utility.getInstance(getActivity()).addPreferences(getActivity(), "isSkiped", "1");
                binding.skipRegBtn.setVisibility(View.GONE);
                binding.progressCircular.setVisibility(View.VISIBLE);

                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                        .addSharedElement(binding.imageView2, "logoTrans")
                        .build();
                Navigation.findNavController(getView())
                        .navigate(R.id.action_loginFragment_to_mainFragment, null, null, extras);

                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent motionEvent) {
                //your action here for double tap e.g.
                Log.d("OnDoubleTapListener", "onDoubleTap");
                Utility.getInstance(getActivity()).addPreferences(getActivity(), "isSkiped", "1");
                binding.skipRegBtn.setVisibility(View.GONE);
                binding.progressCircular.setVisibility(View.VISIBLE);

                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                        .addSharedElement(binding.imageView2, "logoTrans")
                        .build();

                Navigation.findNavController(getView())
                        .navigate(R.id.action_loginFragment_to_mainFragment, null, null, extras);


                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent motionEvent) {
                return false;
            }
        });

        binding.skipRegBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                binding.skipRegBtn.setVisibility(View.GONE);
                binding.progressCircular.setVisibility(View.VISIBLE);
                return gd.onTouchEvent(motionEvent);
            }
        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.userName.getText().toString().length() > 4 && !binding.password.getText().toString().isEmpty()) {
                    loginUser();
                } else {
                    Toast.makeText(getContext(), "Please Enter Valid email/password !!", Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar = RetrofitApi.getInstance().getProgress(getActivity());
                mProgressBar.show();
                loginWithGoogle();


//                RetrofitApi.getInstance().googleLogin(getActivity(), LoginFragment.this);


            }
        });
        binding.fbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar = RetrofitApi.getInstance().getProgress(getActivity());
                mProgressBar.show();
                LoginWithFB();
            }
        });

    }


    public void LoginWithFB() {

        boolean loggedIn = AccessToken.getCurrentAccessToken() != null;
        System.out.println("sdfsd :::: " + loggedIn);
        if (loggedIn) {
            if (mProgressBar != null) {
                mProgressBar.dismiss();
            }
            System.out.println("Sdfsa  token  :: " + AccessToken.getCurrentAccessToken().getToken());
            System.out.println("Sdfsa  token  :: " + AccessToken.getCurrentAccessToken().getUserId());
            Map<String, Object> facebookLogin = new ArrayMap<>();
            facebookLogin.put("userID", "" + AccessToken.getCurrentAccessToken().getToken());
            facebookLogin.put("accessToken", "" + AccessToken.getCurrentAccessToken().getUserId());
            RetrofitApi.getInstance().facebookLogin(getActivity(), LoginFragment.this, facebookLogin);
        } else {
            // initialze the facebook sdk and request to facebook for login
            LoginManager.getInstance().logInWithReadPermissions(getActivity(), Arrays.asList("email", "public_profile"));
//        AppEventsLogger.activateApp(getActivity().getApplication());
            if (FacebookSdk.isInitialized()) {

                Log.d("AppLog", "key: " + FacebookSdk.getApplicationSignature(getContext()));
                LoginManager.getInstance().registerCallback(dashBoard.mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        if (mProgressBar != null) {
                            mProgressBar.dismiss();
                        }
                        Map<String, Object> facebookLogin = new ArrayMap<>();
                        facebookLogin.put("userID", "" + loginResult.getAccessToken().getUserId());
                        facebookLogin.put("accessToken", "" + loginResult.getAccessToken().getToken());
                        RetrofitApi.getInstance().facebookLogin(getActivity(), LoginFragment.this, facebookLogin);

                    }

                    @Override
                    public void onCancel() {
                        if (mProgressBar != null) {
                            mProgressBar.dismiss();
                        }
                        // App code
                        System.out.println("asdfasd fb token ::: cancel");

                        Toast.makeText(getActivity(), "Login Cancel", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        if (mProgressBar != null) {
                            mProgressBar.dismiss();
                        }
                        System.out.println("asdfasd fb token ::: " + error.toString());

                        Log.d("resp", "" + error.toString());
                        Toast.makeText(getActivity(), "Login Error" + error.toString(), Toast.LENGTH_SHORT).show();
                    }

                });
            } else {
                if (mProgressBar != null) {
                    mProgressBar.dismiss();
                }
            }
        }

    }


    private void loginWithGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mProgressBar != null) {
            mProgressBar.dismiss();
        }
        //google signin
        System.out.println("Asdfs ::::  " + resultCode);
        if (requestCode == GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else if (dashBoard.mCallbackManager != null) {
            dashBoard.mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String idToken = account.getIdToken();
            Map<String, Object> googlelogin = new ArrayMap<>();
            googlelogin.put("idToken", "" + idToken);
            RetrofitApi.getInstance().googleLogin(getActivity(), LoginFragment.this, googlelogin);
            System.out.println("google name " + account.getDisplayName());

            System.out.println("SDfasd ::: " + idToken);
            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            System.out.println("goolge failed" + "  signInResult:failed code=" + e.getMessage());
        }
    }

    private void loginUser() {

        binding.loginBtn.setVisibility(View.GONE);
        binding.progressLogin.setVisibility(View.VISIBLE);
        Map<String, Object> login = new ArrayMap<>();
        if (utility.isValidPhone(binding.userName.getText().toString())) {
            login.put("number", "" + binding.userName.getText().toString().trim());
        } else {
            login.put("email", "" + binding.userName.getText().toString().trim());
        }
        login.put("password", "" + binding.password.getText().toString().trim());
        RetrofitApi.getInstance().loginUser(getActivity(), LoginFragment.this,
                login);
    }


    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {
        binding.progressLogin.setVisibility(View.GONE);
        binding.loginBtn.setVisibility(View.VISIBLE);
        utility.displayAlert(getActivity(), "Error !!", "Something Went Wrong !!");
        e.printStackTrace();
    }

    @Override
    public void _onNext(Object obj) {
        System.out.println("ASfsa ::: " + obj);
        if (obj instanceof loginModel) {
            try {
                loginModel checkModel = (loginModel) obj;
                if (checkModel.getCode() == 200) {
                    if (checkModel.getToken() != null) {

                        FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                                .addSharedElement(binding.imageView2, "logoTrans")
                                .build();

                        Navigation.findNavController(getView())
                                .navigate(R.id.action_loginFragment_to_mainFragment, null, null, extras);

                    } else {
                        Toast.makeText(getContext(), "Something Went Wrong !!", Toast.LENGTH_SHORT).show();
                        binding.progressLogin.setVisibility(View.GONE);
                        binding.loginBtn.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (checkModel.getMsg() != null) {
                        utility.displayAlert(getActivity(), "Invalid !!", checkModel.getMsg());
                        binding.progressLogin.setVisibility(View.GONE);
                        binding.loginBtn.setVisibility(View.VISIBLE);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Something Went Wrong !!", Toast.LENGTH_SHORT).show();
                System.out.println("ASdfasd ::::");
                binding.progressLogin.setVisibility(View.GONE);
                binding.loginBtn.setVisibility(View.VISIBLE);
            }
        }
    }
}