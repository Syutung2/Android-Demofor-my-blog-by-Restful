package cn.syutung.empty

import java.security.cert.CertPath
import java.util.*

data class Article (
    var name : String,
    var banner : String,
    var text:String,
    var path: String,
    var fenlei:String,
    var date: String,
    var uid_article:String,
    var uid_fenlei:String,
    var quanwen:String
    )