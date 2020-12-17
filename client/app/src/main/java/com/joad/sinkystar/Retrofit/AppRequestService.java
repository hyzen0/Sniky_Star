package com.joad.sinkystar.Retrofit;


import com.joad.sinkystar.Fragment.auth.model.loginModel;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * * Interface through which all the api calls will be performed
 */
public interface AppRequestService {

    @POST("api/login")
    Observable<loginModel> loginUser(@Body RequestBody body);


    @POST("api/register")
    Observable<loginModel> RegistrationUser(@Body RequestBody body);

    @POST("api/googlelogin")
    Observable<loginModel> googleLogin(@Body RequestBody body);

    @POST("api/facebooklogin")
    Observable<loginModel> facebookLogin(@Body RequestBody body);

    @POST("api/sendotp")
    Observable<loginModel> sendOtpMobile(@Body RequestBody body);

//
//    @POST("Login/postUser")
//    @Multipart
//    Observable<InsertModel> addUser(@Part("full_name") RequestBody fullName,
//                                    @Part("email_id") RequestBody email,
//                                    @Part("mobile_number") RequestBody mobno,
//                                    @Part("isActive") RequestBody isActive,
//                                    @Part MultipartBody.Part body);
//
//    @POST("Login/postUser")
//    @Multipart
//    Observable<UpdateModel> updateUser(@Part("full_name") RequestBody fullName,
//                                       @Part("email_id") RequestBody email,
//                                       @Part("mobile_number") RequestBody mobno,
//                                       @Part("isActive") RequestBody isActive,
//                                       @Part("id") RequestBody userId,
//                                       @Part MultipartBody.Part body);
//
//    // Todo Cab Api
//
//    @POST("Login/postLocation")
//    @FormUrlEncoded
//    Observable<InsertModel> postLocation(@Field("full_address") String full_address,
//                                         @Field("latitude") String latitude,
//                                         @Field("longitude") String longitude,
//                                         @Field("full_address2") String full_address2,
//                                         @Field("latitude2") String latitude2,
//                                         @Field("longitude2") String longitude2,
//                                         @Field("user_id") String user_id,
//                                         @Field("booking_id") String booking_id,
//                                         @Field("food_order_id") String food_order_id);
//
//    @POST("Cab/postCabBooking")
//    @FormUrlEncoded
//    Observable<InsertModel> postCabBooking(@Field("vehicle_id") String vehicle_id,
//                                           @Field("company_id") String company_id,
//                                           @Field("total_tax") String total_tax,
//                                           @Field("status") String status,
//                                           @Field("total_charge") String total_charge,
//                                           @Field("user_id") String user_id,
//                                           @Field("total_time") String total_time);
//
//    @POST("Food/postFoodOrders")
//    @FormUrlEncoded
//    Observable<InsertModel> postFoodOrders(@Field("resturant_id") String resturant_id,
//                                           @Field("total_quantity") String total_quantity,
//                                           @Field("total_price") String total_price,
//                                           @Field("total_tax") String total_tax,
//                                           @Field("status") String status,
//                                           @Field("user_id") String user_id,
//                                           @Field("added") String added);
//
//    @GET("Login/getuser/mobile_number/{mobNo}")
//    Observable<GetUserModel> getUserDetails(@Path("mobNo") String mobNo);
//
//    @GET("Cab/getCabCompanies")
//    Observable<GetCabCompanies> getCabCompanies();
//
//    @GET("Cab/getCabBooking/cab_booking.user_id/{userId}")
//    Observable<GetCabBookingModel> getCabBookingDetails(@Path("userId") String userId);
//
//    @GET("Login/getLocation")
//    Observable<GetLocationModel> getLocation();
//
//    @GET("Cab/getVehicles")
//    Observable<GetVehicleModel> getVehicleDetails();
//
//    // Todo Food Api
//
//    @GET("Food/getCategory")
//    Observable<GetFoodCategoryModel> getFoodCategory();
//
//    @GET("Food/getSubCategory/cat_id/{catId}")
//    Observable<GetFoodSubCatModel> getFoodSubCategory(@Path("catId") String catId);
//
//    @GET("Food/getFoods/sub_cat_id/{subCatId}")
//    Observable<GetFoodModel> getFoods(@Path("subCatId") String subCatId);
//
//    @GET("Food/getFoods/resturant_id/{resturant_id}")
//    Observable<GetFoodModel> getFoodsRes(@Path("resturant_id") String resturant_id);
//
//    @GET("Food/getFoodOrders/user_id/{userId}")
//    Observable<GetFoodOrdersModel> getFoodOrders(@Path("userId") String userId);
//
//    @GET("Food/getRestaurants")
//    Observable<GetRestaurantsModel> getRestaurants();
}