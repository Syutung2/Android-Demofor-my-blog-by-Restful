package cn.syutung.myblogapp

import android.content.res.Configuration
import android.os.Bundle
import android.text.Html
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cn.syutung.globalvariable.GlobalVariable

class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        var bigtitle : TextView = findViewById(R.id.bigtile)
        var time : TextView = findViewById(R.id.time1)
        bigtitle.text = GlobalVariable.dangqian.name
        time.text=GlobalVariable.dangqian.date
        val listView = findViewById<WebView>(R.id.web)

        var data = "<html>${GlobalVariable.dangqian.quanwen}</html>"
        data = data.replace("src=", "height=\"auto\" width=\"60%\" src=")
            .replace("height=\"\\d+\"".toRegex(), "")
        println(data)
        listView.isScrollContainer = false;

        listView.isHorizontalScrollBarEnabled = false

        listView.setBackgroundColor(0)
        if (isNightMode()){
            data = "<head><style>div,body,p,span,h1,h2,h3,h4{ color:#ffffff}\n" +
                    ".rd{ color:#F00}</style></head>" + data
        }else{
            data = "<head><style>div,body,p,span,h1,h2,h3,h4{ color:#000000}\n" +
                    ".rd{ color:#F00}</style></head>" + data
        }
        listView.loadDataWithBaseURL("http://wechat.cn", data, "text/html", "UTF-8", null);


    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    override fun onRestart() {
        super.onRestart()
    }

    fun isNightMode(): Boolean {
        return when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
    }
}