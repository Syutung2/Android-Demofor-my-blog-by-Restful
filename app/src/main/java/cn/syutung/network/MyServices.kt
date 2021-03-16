package cn.syutung.network

import cn.syutung.api.ArticlesApi

internal fun providesApiService(): ArticlesApi {
    return ArticlesNetWork().service
}

