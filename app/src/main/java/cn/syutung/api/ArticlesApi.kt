package cn.syutung.api

import cn.syutung.json.JavaBean
import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ArticlesApi {

    @GET("api/posts?showContent=true")
    fun getUserInfo(): retrofit2.Call<JavaBean?>

    @GET("api/posts?filterType=search&&showContent=true")
    fun search(
        @Query("filterSlug") filterSlug:String
    ): retrofit2.Call<JavaBean?>

    @FormUrlEncoded
    @POST("index.php/action/register?_=880d4dcbc6410e4b0141fac2a38c24df")
    fun re(@Field("name") username: RequestBody?, @Field("mail") password: RequestBody?): Call<JsonObject?>?


    @POST("index.php/action/users-profile?_=4a2699f665dbefea2f2bc0216778ff3d")
    fun genggaimima(
        @Query("password") password:String,
        @Query("confirm") confirm:String,
        @Query("do") doing:String
    ): retrofit2.Call<String?>

    @POST("index.php/action/login?_=87114210dcd1ad22b38d16ebaa3e4019")
    fun login(
        @Query("password") password:String,
        @Query("username") username:String,
        @Query("referer") referer:String
    ): retrofit2.Call<String?>
    //referer: https://we-chat.cn/admin/profile.php
}