package com.cj.runtime.networksimple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.cj.network.http.ApiService
import com.cj.runtime.networksimple.http.GlobalConfiguration
import com.cj.runtime.Network
import com.cj.runtime.integration.ConfigModule
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.tv)

        Network.init(this, mutableListOf<ConfigModule>().apply {
            add(GlobalConfiguration())
        })

        GlobalScope.launch(Dispatchers.IO) {
            val result = Network.repositoryManager.obtainRetrofitService(ApiService::class.java).getTopList()
            println("result:${result}")
            tv.post {
                tv.text = Gson().toJson(result)
            }
        }
    }
}