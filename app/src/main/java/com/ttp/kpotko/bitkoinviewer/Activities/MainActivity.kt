package com.ttp.kpotko.bitkoinviewer.Activities

import android.support.v7.app.AppCompatActivity
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ttp.kpotko.bitkoinviewer.Adapters.RecyclerViewAdapter
import com.ttp.kpotko.bitkoinviewer.Models.ResponseItem
import com.ttp.kpotko.bitkoinviewer.Network.RetrofitGetInterface
import com.ttp.kpotko.bitkoinviewer.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    var refresh: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        refresh = swipe_container


        setContentView(R.layout.activity_main)
        doRequest()
    }

    private fun doRequest(){
        val apiService = RetrofitGetInterface.create()
        apiService.getCryptocurrency()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result -> arrayListInit(result)
                }, { error ->
                    error.printStackTrace()
                })
    }

    private fun arrayListInit(result : List<ResponseItem>){
        recyclerView.adapter = RecyclerViewAdapter(result, resources)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
