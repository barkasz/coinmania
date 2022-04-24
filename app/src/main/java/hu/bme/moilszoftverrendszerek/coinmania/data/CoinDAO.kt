package hu.bme.moilszoftverrendszerek.coinmania.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CoinDAO {
    @Query("SELECT * FROM coin")
    fun getAllCoins(): List<Coin>

    @Insert
    fun insertCoins(vararg coins: Coin)

    @Delete
    fun deleteCoin(coin: Coin)

    @Query("DELETE FROM coin")
    fun deleteAllCoins()
}