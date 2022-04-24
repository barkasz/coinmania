package hu.bme.moilszoftverrendszerek.coinmania.network.api

import hu.bme.moilszoftverrendszerek.coinmania.network.model.CoinData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeApi  {

    @GET("/coinlist")
    fun getRates(@Query("coinname") base: String): Call<CoinData>

}