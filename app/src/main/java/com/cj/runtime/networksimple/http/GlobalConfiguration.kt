package com.cj.runtime.networksimple.http

import android.content.Context
import com.cj.network.http.ApiConstant
import com.cj.network.http.ApiService
import com.cj.runtime.integration.ConfigModule
import com.cj.runtime.integration.IRepositoryManager
import com.cj.runtime.logger.HttpLoggingInterceptor
import com.cj.runtime.module.GlobalConfigModule
import com.cj.runtime.module.HttpModule

import okhttp3.OkHttpClient
import java.util.logging.Level

class GlobalConfiguration : ConfigModule {
    override fun applyOptions(context: Context, builder: GlobalConfigModule.Builder) {
        builder.baseUrl(ApiConstant.BASE_URL)
            .okHttpConfiguration(object : HttpModule.OkHttpConfiguration {
                override fun configOkHttp(context: Context, builder: OkHttpClient.Builder) {
                    builder.addInterceptor(HttpLoggingInterceptor("OkHttp").apply {
                        setPrintLevel(HttpLoggingInterceptor.Level.BASIC)
                        setColorLevel(Level.INFO)
                    })
                }
            })
    }

    override fun registerComponents(context: Context, repositoryManager: IRepositoryManager) {
        repositoryManager.addInjectRetrofitService(
            ApiConstant.BASE_URL,
            ApiService::class.java)
    }
}