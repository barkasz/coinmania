package hu.bme.moilszoftverrendszerek.coinmania

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.moilszoftverrendszerek.coinmania.data.Coin
import hu.bme.moilszoftverrendszerek.coinmania.data.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener {
            var newCoin = Coin(
                null,
                etName.text.toString(),
                etPrice.text.toString().toInt(),
                0,
                ""
            )
            saveCoin(newCoin)
        }

        btnQuery.setOnClickListener {
            queryGrades()
        }

        btnDeleteAll.setOnClickListener {
            deleteAllGrades()
        }

    }

    fun saveCoin(coin: Coin) {
        Thread {
            AppDatabase.getInstance(this).gradeDao().insertCoins(coin)
        }.start()
    }

    fun queryGrades(){
        Thread {
            val grades = AppDatabase.getInstance(this).gradeDao().
            getAllCoins()

            runOnUiThread{
                tvResult.text = ""
                grades.forEach{
                    tvResult.append("Name: ${it.coinName} grade: ${it.price}\n")
                }
            }

        }.start()
    }

    private fun deleteAllGrades() {
        Thread {
            AppDatabase.getInstance(this).gradeDao().deleteAllCoins()
        }.start()
    }

}