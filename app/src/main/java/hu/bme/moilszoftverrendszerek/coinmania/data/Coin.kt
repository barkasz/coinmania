package hu.bme.moilszoftverrendszerek.coinmania.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin")
data class Coin(
    @PrimaryKey(autoGenerate = true) var coinId: Long?,
    @ColumnInfo(name = "coinname") var coinName: String,
    @ColumnInfo(name = "price") var price: Int,
    @ColumnInfo(name = "rate") var rate: Int,
    @ColumnInfo(name = "photourl") var photourl: String
)