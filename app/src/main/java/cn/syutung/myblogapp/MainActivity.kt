package cn.syutung.myblogapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import cn.syutung.adapter.Adapter
import cn.syutung.api.ArticlesApi
import cn.syutung.empty.Article
import cn.syutung.globalvariable.GlobalVariable
import cn.syutung.json.JavaBean
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {


    override fun onRestart() {
        super.onRestart()
        //setContentView(R.layout.activity_main)
        //GlobalVariable.articles = null;
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navigation = findViewById<BottomNavigationView>(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            //val title = item.title.toString()
            when (item.itemId) {
                R.id.navigation_home -> {
                    if(this.localClassName!="MainActivity"){
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }
                R.id.navigation_dashboard -> {
                    if(this.localClassName!="SearchActivity"){
                        val intent = Intent(this,SearchActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }
                R.id.navigation_notifications -> {
                    if(this.localClassName!="MineActivity2"){
                        val intent = Intent(this,MineActivity2::class.java)
                        startActivity(intent)
                        finish()
                    }


                }
            }
            true
        })
        searchAll()
        //GetLists.getArtclesLists()

        val fab: com.google.android.material.floatingactionbutton.FloatingActionButton = findViewById(R.id.shuaxin)
        fab.setOnClickListener { view ->
            GlobalVariable.articles = null
            searchAll()
        }

    }

    private fun searchAll(){
        GlobalVariable.articles = ArrayList<Article>()
        val retrofit = Retrofit.Builder().baseUrl(GlobalVariable.myBlogUris)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(ArticlesApi::class.java)

        api.getUserInfo().enqueue(object : Callback<JavaBean?> {
            override fun onFailure(call: Call<JavaBean?>?, t: Throwable?) {
            }
            override fun onResponse(call: Call<JavaBean?>?, response: Response<JavaBean?>?) {
                response?.let {
                    it.body().let { it ->
                        val bean = it
                        if (it != null) {
                            println(it.status)
                            println(it.message)
                            it.data.dataSet.let {
                                var name : String
                                var banner : String
                                var text:String
                                var path: String
                                var fenlei:String
                                var data:String
                                var uid_article:String
                                var uid_fenlei:String
                                var quanwen:String
                                for (index in it.indices){
                                    val q = it[index]
                                    name = q.title
                                    data = q.year+"-"+q.month+"-"+q.day
                                    q.categories.let {
                                        fenlei = it.get(0).name
                                        uid_fenlei = it.get(0).mid
                                    }
                                    quanwen = q.text
                                    banner = q.fields.banner.value
                                    text = q.fields.excerpt.value

                                    path = q.permalink
                                    uid_article = q.cid
                                    val a = Article(
                                        name,banner,text,path,fenlei,data,uid_article,uid_fenlei,quanwen
                                    )
                                    GlobalVariable.articles.add(
                                        a
                                    )
                                    //println(a.toString())//输出0..9
                                }

                            }
                        }
                    }
                }
                println(GlobalVariable.articles.toString())
                val listView = findViewById<ListView>(R.id.listview)
                val adapter = Adapter(this@MainActivity, R.layout.article, GlobalVariable.articles)
                listView.adapter = adapter
                onPause()
                listView.onItemClickListener = AdapterView.OnItemClickListener(){ adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
                    val a:Article = GlobalVariable.articles.get(i);
                    //val b = GlobalVariable.articles;
                    //GlobalVariable.articles = b
                    GlobalVariable.dangqian = a

                    val intent = Intent(this@MainActivity,ArticleActivity::class.java)
                    startActivity(intent)
                }
            }
        })
    }





}





