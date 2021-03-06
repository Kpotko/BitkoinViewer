package com.ttp.kpotko.bitkoinviewer.Network

import com.ttp.kpotko.bitkoinviewer.Models.ResponseItem
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by vlad on 11.12.2017.
 */
interface RetrofitGetInterface {
    @GET("v1/ticker/")
    fun getCryptocurrency(): Observable<List<ResponseItem>>


    companion object Factory {
        fun create(): RetrofitGetInterface {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(" https://api.coinmarketcap.com/")
                    .build()

            return retrofit.create(RetrofitGetInterface::class.java)
        }
    }
}