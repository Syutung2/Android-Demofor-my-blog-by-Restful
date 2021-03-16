package cn.syutung.myblogapp.ui.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import cn.syutung.api.ArticlesApi
import cn.syutung.empty.Article
import cn.syutung.globalvariable.GlobalVariable
import cn.syutung.myblogapp.R
import cn.syutung.utils.MyUtils
import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val button : Button = findViewById(R.id.register)

        val email : EditText = findViewById(R.id.email)
        val username : EditText = findViewById(R.id.username)







        button.setOnClickListener {

            val formMaildata: MutableMap<String,
                    RequestBody> = HashMap()


            println(formMaildata.get("name"))

            val retrofit = Retrofit.Builder().baseUrl(GlobalVariable.myBlogUris)
                .addConverterFactory(GsonConverterFactory.create()).build()
            val api = retrofit.create(ArticlesApi::class.java)
            api.re(MyUtils.toRequestBody(username.text.toString()),MyUtils.toRequestBody(email.text.toString()))
                    ?.enqueue(object :Callback<JsonObject?> {
                        override fun onFailure(call: Call<JsonObject?>?, t: Throwable?) {
                        }

                        override fun onResponse(call: Call<JsonObject?>?, response: Response<JsonObject?>?) {
                            if (response != null) {
                                println(response.message())

                                if (response.code()==302){
                                    println("注册成功")
                                }
                            }
                        }
                    })

        }

    }

    private fun searchAll(){
        val text1 : EditText = findViewById(R.id.editTextTextPersonName)

        GlobalVariable.searcharticles = ArrayList<Article>()

    }

}


