package cn.syutung.network

import android.provider.Telephony.TextBasedSmsColumns.BODY
import cn.syutung.api.ArticlesApi
import cn.syutung.globalvariable.GlobalVariable
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.Provider
import java.util.concurrent.TimeUnit

class ArticlesNetWork {

    val service: ArticlesApi = create(ArticlesApi::class.java)

    lateinit var retrofit: Retrofit

    fun <S> create(serviceClass: Class<S>): S {
        val gson = GsonBuilder()
                .serializeNulls()
                .create()

        // create retrofit
        val retrofit = Retrofit.Builder().baseUrl(GlobalVariable.myBlogUris)
                .addConverterFactory(GsonConverterFactory.create()).build()


        return retrofit.create(serviceClass)
    }

}



