package com.joad.sinkystar.Retrofit;


import android.app.Activity;
import android.app.Dialog;

import com.joad.sinkystar.Fragment.auth.model.loginModel;
import com.joad.sinkystar.R;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitApi {

    private static RetrofitApi retrofitApi = null;
    private ResponseListener mlistener_response;

    private Dialog mProgressDialog;

    public static RetrofitApi getInstance() {

        if (retrofitApi != null)
            return retrofitApi;
        else
            return new RetrofitApi();
    }

    public Dialog getProgress(Activity activity) {
        mProgressDialog = new Dialog(activity, R.style.DialogSlideAnim);
        mProgressDialog.setContentView(R.layout.progress_dialog);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        return mProgressDialog;

    }

    public void loginUser(final Activity activity, final ResponseListener mlistener_response, Map<String, Object> jsonParams) {
        System.out.println(" sakfj in ");
        this.mlistener_response = mlistener_response;
        getProgress(activity);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

        RetrofitClient.getClient().loginUser(body).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<loginModel>() {
            @Override
            public void onCompleted() {
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(loginModel mobileLoginModel) {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                mlistener_response._onNext(mobileLoginModel);

            }
        });
    }

    public void RegistrationUser(final Activity activity, final ResponseListener mlistener_response, Map<String, Object> jsonParams) {
        this.mlistener_response = mlistener_response;
        getProgress(activity);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

        RetrofitClient.getClient().RegistrationUser(body).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<loginModel>() {
            @Override
            public void onCompleted() {
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(loginModel mobileLoginModel) {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                mlistener_response._onNext(mobileLoginModel);

            }
        });
    }

    public void googleLogin(final Activity activity, final ResponseListener mlistener_response, Map<String, Object> jsonParams) {
        System.out.println(" sakfj in ");
        this.mlistener_response = mlistener_response;
        getProgress(activity);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

        RetrofitClient.getClient().googleLogin(body).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<loginModel>() {
            @Override
            public void onCompleted() {

                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(loginModel mobileLoginModel) {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                mlistener_response._onNext(mobileLoginModel);

            }
        });
    }

    public void facebookLogin(final Activity activity, final ResponseListener mlistener_response, Map<String, Object> jsonParams) {
        System.out.println(" sakfj in ");
        this.mlistener_response = mlistener_response;
        getProgress(activity);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

        RetrofitClient.getClient().facebookLogin(body).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<loginModel>() {
            @Override
            public void onCompleted() {
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(loginModel mobileLoginModel) {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                mlistener_response._onNext(mobileLoginModel);

            }
        });
    }


    public void sendOtpMobile(final Activity activity, final ResponseListener mlistener_response, Map<String, Object> jsonParams) {
        System.out.println(" sakfj in ");
        this.mlistener_response = mlistener_response;
        getProgress(activity);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

        RetrofitClient.getClient().sendOtpMobile(body).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<loginModel>() {
            @Override
            public void onCompleted() {
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(loginModel mobileLoginModel) {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                mlistener_response._onNext(mobileLoginModel);

            }
        });
    }


    public interface ResponseListener {

        void _onCompleted();

        void _onError(Throwable e);

        void _onNext(Object obj);


    }

}
