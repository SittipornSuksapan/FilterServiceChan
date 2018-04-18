package com.chan.revernue.filterapplication.manager.http;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    //SplashScreenActivity
    @FormUrlEncoded
    @POST("selectListAll.php")
    Call<ResponseBody>getListItemCustomer(@Field("id_member") String id_member, @Field("product_id_connected") String product_id_connected);

    @FormUrlEncoded
    @POST("selectCustomer.php")
    Call<ResponseBody>getListCustomer(@Field("id_member") String id_member);

}
