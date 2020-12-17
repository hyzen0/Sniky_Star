package com.joad.sinkystar.Utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.joad.sinkystar.R;
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;
import com.treebo.internetavailabilitychecker.InternetConnectivityListener;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class Utility {
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String UPI_REGEX = "^[\\w\\.\\-_]{3,}@[a-zA-Z]{3,}";
    private static final String IFSC_REGEX = "^[A-Za-z]{4}[a-zA-Z0-9]{7}$";
    private static final String PHONE_REGEX = "^[1-9][0-9]{9}$";
    private static Utility Tools = null;
    private static Activity activity;
    //    private Dialog Progress = new Dialog(activity, R.style.DialogSlideAnim);
//    private Dialog commonDialog = new Dialog(activity, R.style.DialogSlideAnim);
    private InternetAvailabilityChecker mInternetAvailabilityChecker;

    public static Utility getInstance(Activity activity) {
        Utility.activity = activity;

        if (Tools != null)
            return Tools;
        else
            return new Utility();
    }

    public static boolean hasText(TextInputEditText editText, String REQUIRED_MSG) {

        String text = Objects.requireNonNull(editText.getText()).toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }


    public void expand(final View v) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) v.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Expansion speed of 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Collapse speed of 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }


    public void AnimationBounce(Context context, View view, int isInfinite, double frequency) {
        final Animation myAnim;
        if (isInfinite == 1) {
            myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce_rep);
        } else {
            myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce_rep);
        }
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, frequency);
        myAnim.setInterpolator(interpolator);
        view.startAnimation(myAnim);
        myAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void addConnectionCheck(InternetConnectivityListener internetConnectivityListener) {
        //System.out.println("sadflhas hello check");
        InternetAvailabilityChecker.init(activity);
        mInternetAvailabilityChecker = InternetAvailabilityChecker.getInstance();
        mInternetAvailabilityChecker.addInternetConnectivityListener(internetConnectivityListener);
//                isConnected -> {
//            //System.out.println("asfdsjl net qoe chage");
//            if (!isConnected) {
//                showCommonDialog(1, false, "No Internet Connection \nPlease check your Internet connection and Try again.").show();
//            } else {
//                if (commonDialog != null) {
//                    if (commonDialog.isShowing()) {
//                        commonDialog.dismiss();
//                    }
//                }
//            }

//        });


    }

    //    public Dialog getProgress() {
//
//        Progress.setContentView(R.layout.progress_dialog);
//        Progress.setCanceledOnTouchOutside(false);
//        Progress.setCancelable(false);
//
//        LottieAnimationView animationView = Progress.findViewById(R.id.animation_view);
//
//        return Progress;
//
//    }
//
//    public Dialog showCommonDialog(int flag, boolean cancelable, String messagestext) {
//
////        flag=1=no internet;
////        flag=2=done dialog;
//        commonDialog.setContentView(R.layout.done_dialog);
//        commonDialog.setCanceledOnTouchOutside(cancelable);
//        commonDialog.setCancelable(cancelable);
//        LottieAnimationView animationView = commonDialog.findViewById(R.id.animation_view);
//        if (flag == 1) {
//            animationView.setAnimation(R.raw.no_internet);
//        } else if (flag == 2) {
//            animationView.setAnimation(R.raw.well_done);
//        }
//        TextView button = commonDialog.findViewById(R.id.button);
//        TextView messages = commonDialog.findViewById(R.id.message);
//        messages.setText(messagestext);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (flag == 1) {
//                    if (mInternetAvailabilityChecker.getCurrentInternetAvailabilityStatus()) {
//                        if (commonDialog.isShowing()) {
//                            commonDialog.dismiss();
//                        }
//                    } else {
//                        if (!commonDialog.isShowing()) {
//                            commonDialog.show();
//                        }
//                    }
//                } else if (flag == 2) {
//                    commonDialog.dismiss();
//                }
//            }
//        });
//
//
//        return commonDialog;
//
//    }
//
//    public boolean isEmailAddress(TextInputEditText editText, boolean required) {
//        return isValid(editText, EMAIL_REGEX, "Please enter a Valid Email.", required);
//    }
//
//    public boolean isUpiId(TextInputEditText editText, boolean required) {
//        return isValid(editText, UPI_REGEX, "Please enter a Valid UPI ID.", required);
//    }
//
//    public boolean isIFSC(TextInputEditText editText, boolean required) {
//        return isValid(editText, IFSC_REGEX, "Please enter a Valid IFSC.", required);
//    }
//
//    // call this method when you need to check phone number validation
//    public boolean isPhoneNumber(TextInputEditText editText, boolean required) {
//        return isValid(editText, PHONE_REGEX, "Please enter a valid number.", required);
//
//
//    }
//
//    public boolean isValid(TextInputEditText editText, String regex, String errMsg, boolean required) {
//
//        String text = Objects.requireNonNull(editText.getText()).toString().trim();
//        // clearing the error, if it was previously set by some other values
//        editText.setError(null);
//
//        // text required and editText is blank, so return false
//        if (required && !hasText(editText, errMsg)) return false;
//
//        // pattern doesn't match so returning false
//        if (required && !Pattern.matches(regex, text)) {
//            return false;
//        }
//        ;
//
//        return true;
//    }
//
    public void addPreferences(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("_snikyStar", MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void clearPreferenceData(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("_snikyStar", MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

    public String getPreferences(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("_snikyStar", MODE_PRIVATE);
        String text = prefs.getString(key, "");
        return text;
    }

    public void displayAlert(final Activity context, String title, String msg) {
        try {
            new AlertDialog.Builder(context).setMessage(msg).setTitle(title).setCancelable(true).
                    setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                            dialog.dismiss();


                        }

                    }).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean isValidPhone(CharSequence phone) {
        if (TextUtils.isEmpty(phone)) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(phone).matches();
        }
    }

//
//    public void addUserPref(Context context, userWithDevice_model responseEmpAttenDense) {
//        SharedPreferences.Editor editor = context.getSharedPreferences("_myFuture", MODE_PRIVATE).edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(responseEmpAttenDense);
//        editor.putString("userData", json);
//        editor.apply();
//    }
//
//    public userWithDevice_model getUserPref(Context context) {
//        SharedPreferences prefs = context.getSharedPreferences("_myFuture", MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = prefs.getString("userData", "");
//        return gson.fromJson(json, userWithDevice_model.class);
//
//    }
//
//    public String getVersion() {
//        String version = "null";
//        try {
//            PackageManager manager = activity.getPackageManager();
//            PackageInfo info = manager.getPackageInfo(
//                    activity.getPackageName(), 0);
//            version = "V." + info.versionName;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return version;
//    }
//
//    public String getCurrentDateTime() {
//
//        Date c = Calendar.getInstance().getTime();
//
//        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:SS");
//
//        return df.format(c);
//    }

    public class MyBounceInterpolator implements Interpolator {
        private double mAmplitude = 1;
        private double mFrequency = 4;

        private MyBounceInterpolator(double amplitude, double frequency) {
            mAmplitude = amplitude;
            mFrequency = frequency;
        }

        public float getInterpolation(float time) {
            return (float) (-1 * Math.pow(Math.E, -time / mAmplitude) *
                    Math.cos(mFrequency * time) + 1);
        }

    }
//
//    public boolean isConnectionAvailable(Context context) {
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivityManager != null) {
//            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
//            if (netInfo != null && netInfo.isConnected()
//                    && netInfo.isConnectedOrConnecting()
//                    && netInfo.isAvailable()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    public void firebaseLink(String code, Dialog progressBar) {
//
//        progressBar.show();
//
//        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
//                .setLink(Uri.parse("https://com.joad.myfuture/invite?code=" + code))
//                .setDomainUriPrefix("https://myfuture.page.link/")
//                // Open links with this app on Android
//                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
//                // Open links with com.example.ios on iOS
////                .setIosParameters(new DynamicLink.IosParameters.Builder("com.globussoft.Chingari").setAppStoreId("1450361582").build())
//                .buildDynamicLink();
//
//        Uri dynamicLinkUri = dynamicLink.getUri();
//        Log.e("DynamicLink", "\n" + dynamicLinkUri);
//
//
//        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
//                .setLongLink(dynamicLinkUri)
//                .buildShortDynamicLink(ShortDynamicLink.Suffix.SHORT);
//
//        shortLinkTask.addOnCompleteListener(new OnCompleteListener<ShortDynamicLink>() {
//            @Override
//            public void onComplete(@NonNull Task<ShortDynamicLink> task) {
//                if (task.isSuccessful()) {
//                    Uri shortLink = task.getResult().getShortLink();
//                    Uri flowchartLink = task.getResult().getPreviewLink();
//                    Log.e("ShortDynamicLink", shortLink + "");
//
//                    if (progressBar.isShowing()) {
//                        progressBar.dismiss();
//                    }
//                    shareDirect(shortLink, null);
//                } else {
//
//                    if (progressBar.isShowing()) {
//                        progressBar.dismiss();
//                    }
//                    Toast.makeText(activity, "Failed to share", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
//
//    }
//
//    private void shareDirect(Uri shortLink, String s) {
//
//        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
//        whatsappIntent.setType("text/plain");
//        if (s != null) {
//            whatsappIntent.setPackage(s);
//        }
//        whatsappIntent.putExtra(Intent.EXTRA_TEXT, shortLink.toString());
//        try {
//            activity.startActivity(whatsappIntent);
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(activity, "App have not been installed.", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void valueanim(final TextView view, int text) {
//        ValueAnimator animator = new ValueAnimator();
//        animator.setObjectValues(text, text);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            public void onAnimationUpdate(ValueAnimator animation) {
//                view.setText(String.valueOf(animation.getAnimatedValue()));
//            }
//        });
//        animator.setDuration(500); // here you set the duration of the anim
//        animator.start();
//    }
}
