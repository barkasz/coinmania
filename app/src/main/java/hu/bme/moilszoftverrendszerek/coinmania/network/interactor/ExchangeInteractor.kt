package hu.bme.moilszoftverrendszerek.coinmania.network.interactor

import hu.bme.moilszoftverrendszerek.coinmania.network.api.ExchangeApi
import hu.bme.moilszoftverrendszerek.coinmania.network.model.CoinData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ExchangeInteractor {

    private var retrofit: Retrofit? = null

    fun getExchangeData(
        baseCurrency: String,
        onResponse: (CoinData) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.exchangeratesapi.io") //teszt URL
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }

        val exchangeApi = retrofit!!.create(ExchangeApi::class.java)

        exchangeApi.getRates(baseCurrency).enqueue(object : Callback<CoinData> {
            override fun onResponse(call: Call<CoinData>, response: Response<CoinData>) {
                onResponse(response.body()!!)
            }

            override fun onFailure(call: Call<CoinData>, t: Throwable) {
                onFailure(t)
            }
        })
    }
}