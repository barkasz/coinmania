package hu.bme.moilszoftverrendszerek.coinmania.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CoinData(val rates: Rates?, val base: String?, val date: String?)

@JsonClass(generateAdapter = true)
class Rates(val BNB: Double?, val BTC: Double?, val ETH: Double?, val ADA: Double?)