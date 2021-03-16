# Android App for my blog by Restful

### Typecho：Restful API调用插件

这是一个将 Typecho 博客 RESTful 化的插件。启用此插件，你可以通过请求 API 向站点请求或写入信息（获取文章内容、获取评论、添加评论等）。

下载地址：[直连下载](https://we-chat.cn/Restful.rar)

1.常规安装

​	下载插件并解压，将解压后的目录重命名为 Restful (区分大小写)，然后到后台插件管理页面启用并设置即可。开启网站伪静态

`if (!-e $request_filename) {
        rewrite ^(.*)$ /index.php$1 last;
    }`

​	如果没有的话，那么需要在下文列出的请求的 URI 前加上 `/index.php`，例如：`/api/posts => /index.php/api/posts`.

2.Api 介绍

1.获取文章列表

`GET /api/posts`

| 参数        | 类型   | 描述                                     | 备注 |
| :---------- | :----- | :--------------------------------------- | :--- |
| page        | int    | 当前页                                   | 可选 |
| pageSize    | int    | 分页数                                   | 可选 |
| filterType  | string | category 或 tag 或 search，可用请求搜索  | 可选 |
| filterSlug  | string | 分类名或标签名或搜索关键字，可用请求搜索 | 可选 |
| showContent | bool   | 是否显示文章具体内容，获取文章所有内容   | 可选 |

返回形式 Json

`{
  "status":"success",
  "message":"",
  "data":{
    "page":1,
    "pageSize":5,
    "pages":1,
    "count":2,
    "dataSet":[
      {
        "cid":"18",
        "title":"激活MIUI + for Unoffical Model",
        "created":"1615280220",
        "modified":"1615434301",
        "slug":"18",
        "commentsNum":"0",
        "type":"post",
        "text":"",
        "categories":[
          {
            "mid":"1",
            "name":"日常分享",
            "slug":"default",
            "type":"category",
            "description":"日常分享",
            "count":"2",
            "order":"1",
            "parent":"0",
            "cid":"18",
            "directory":[
              "default"
            ],
            "permalink":"https://we-chat.cn/index.php/category/default/",
            "feedUrl":"https://we-chat.cn/index.php/feed/category/default/",
            "feedRssUrl":"https://we-chat.cn/index.php/feed/rss/category/default/",
            "feedAtomUrl":"https://we-chat.cn/index.php/feed/atom/category/default/"
          }
        ],
        "category":"default",
        "directory":[
          "default"
        ],
        "date":{
          "timeStamp":1615280220
        },
        "year":"2021",
        "month":"03",
        "day":"09",
        "hidden":false,
        "pathinfo":"/archives/18/",
        "permalink":"https://we-chat.cn/index.php/archives/18/",
        "feedUrl":"https://we-chat.cn/index.php/feed/archives/18/",
        "feedRssUrl":"https://we-chat.cn/index.php/feed/rss/archives/18/",
        "feedAtomUrl":"https://we-chat.cn/index.php/feed/atom/archives/18/",
        "fields":{
          "banner":{
            "name":"banner",
            "type":"str",
            "value":"https://we-chat.cn/usr/uploads/2021/03/3469144072.jpg"
          },
          "commentShow":{
            "name":"commentShow",
            "type":"str",
            "value":"0"
          },
          "excerpt":{
            "name":"excerpt",
            "type":"str",
            "value":""
          },
          "meta":{
            "name":"meta",
            "type":"str",
            "value":""
          }
        }
      },
      {
        "cid":"12",
        "title":"「中英双语」Android12即将到来，新的特性变化",
        "created":"1613855220",
        "modified":"1615764801",
        "slug":"12",
        "commentsNum":"3",
        "type":"post",
        "text":"",
        "categories":[
          {
            "mid":"1",
            "name":"日常分享",
            "slug":"default",
            "type":"category",
            "description":"日常分享",
            "count":"2",
            "order":"1",
            "parent":"0",
            "cid":"12",
            "directory":[
              "default"
            ],
            "permalink":"https://we-chat.cn/index.php/category/default/",
            "feedUrl":"https://we-chat.cn/index.php/feed/category/default/",
            "feedRssUrl":"https://we-chat.cn/index.php/feed/rss/category/default/",
            "feedAtomUrl":"https://we-chat.cn/index.php/feed/atom/category/default/"
          }
        ],
        "category":"default",
        "directory":[
          "default"
        ],
        "date":{
          "timeStamp":1613855220
        },
        "year":"2021",
        "month":"02",
        "day":"21",
        "hidden":false,
        "pathinfo":"/archives/12/",
        "permalink":"https://we-chat.cn/index.php/archives/12/",
        "feedUrl":"https://we-chat.cn/index.php/feed/archives/12/",
        "feedRssUrl":"https://we-chat.cn/index.php/feed/rss/archives/12/",
        "feedAtomUrl":"https://we-chat.cn/index.php/feed/atom/archives/12/",
        "fields":{
          "banner":{
            "name":"banner",
            "type":"str",
            "value":" https://we-chat.cn/usr/uploads/2021/02/3092317494.png"
          },
          "commentShow":{
            "name":"commentShow",
            "type":"str",
            "value":"0"
          },
          "excerpt":{
            "name":"excerpt",
            "type":"str",
            "value":" 今天，2月19日（美国时间。2月18日），谷歌发布了新的Android 12开发者预览版，以帮助开发者为下一个系统版本开发其OEM系统和应用。"
          },
          "meta":{
            "name":"meta",
            "type":"str",
            "value":"日常"
          }
        }
      }
    ]
  }
}`

Android需要引入库

```kotlin
implementation 'com.squareup.okhttp3:okhttp:3.10.0'
implementation 'com.squareup.retrofit2:retrofit:2.1.0'
implementation 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
```

Api类写法

```kotlin
interface ArticlesApi {

    @GET("api/posts?showContent=true")
    fun getUserInfo(): retrofit2.Call<JavaBean?> //获取所有文件及其详细文章

    @GET("api/posts?filterType=search&&showContent=true")
    fun search(
        @Query("filterSlug") filterSlug:String
    ): retrofit2.Call<JavaBean?> //请求搜索
    
}
```

文章实体类

```kotlin
package cn.syutung.empty

data class Article (
    var name : String, //标题
    var banner : String, //头图
    var text:String, //文章概述
    var path: String, //文章地址
    var fenlei:String, //文章分类
    var date: String, //文章发表日期
    var uid_article:String, //文章ID
    var uid_fenlei:String, //文章分类ID
    var quanwen:String // 文章全文
    )
```

按照Json的嵌套关系，把Json的 实体类 JavaBean 写好，可以用软件生成，在kotlin中建议使用data class

```kotlin
package cn.syutung.json

data class JavaBean(
    val `data`: Data,
    val message: String,
    val status: String
){
    data class Data(
            val count: Int,
            val dataSet: List<DataSet>,
            val page: Int,
            val pageSize: Int,
            val pages: Int
    ){
        data class DataSet(
                val categories: List<Category>,
                val category: String,
                val cid: String,
                val commentsNum: String,
                val created: String,
                val date: Date,
                val day: String,
                val directory: List<String>,
                val feedAtomUrl: String,
                val feedRssUrl: String,
                val feedUrl: String,
                val fields: Fields,
                val hidden: Boolean,
                val modified: String,
                val month: String,
                val pathinfo: String,
                val permalink: String,
                val slug: String,
                val text: String,
                val title: String,
                val type: String,
                val year: String
        ){
            data class Category(
                    val cid: String,
                    val count: String,
                    val description: String,
                    val directory: List<String>,
                    val feedAtomUrl: String,
                    val feedRssUrl: String,
                    val feedUrl: String,
                    val mid: String,
                    val name: String,
                    val order: String,
                    val parent: String,
                    val permalink: String,
                    val slug: String,
                    val type: String
            )
            data class Date(
                    val timeStamp: Int
            )
            data class Fields(
                    val banner: Banner,
                    val commentShow: CommentShow,
                    val excerpt: Excerpt,
                    val meta: Meta
            ){
                data class Banner(
                        val name: String,
                        val type: String,
                        val value: String
                )

                data class CommentShow(
                        val name: String,
                        val type: String,
                        val value: String
                )

                data class Excerpt(
                        val name: String,
                        val type: String,
                        val value: String
                )

                data class Meta(
                        val name: String,
                        val type: String,
                        val value: String
                )
            }
        }
    }
}
```

写请求，添加到listview上

```kotlin
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
```