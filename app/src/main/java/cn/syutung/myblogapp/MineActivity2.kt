package cn.syutung.myblogapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import cn.syutung.myblogapp.ui.login.RegisterActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MineActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mine2)
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

        val textView4 : TextView = findViewById(R.id.textView4)
        val textView5 : TextView = findViewById(R.id.textView5)

        textView4.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        textView5.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }


    }
}