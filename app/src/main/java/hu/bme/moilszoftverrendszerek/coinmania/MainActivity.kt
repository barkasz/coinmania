package hu.bme.moilszoftverrendszerek.coinmania

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import hu.bme.moilszoftverrendszerek.coinmania.data.Coin
import hu.bme.moilszoftverrendszerek.coinmania.data.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    //analytics
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //analytics
        firebaseAnalytics = Firebase.analytics
        //

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
            queryCoins()
        }

        btnDeleteAll.setOnClickListener {
            deleteAllGrades()
        }

        btnTestCrash.setOnClickListener{
            throw RuntimeException("Test Crash") // Force a crash
        }

    }

    fun saveCoin(coin: Coin) {
        Thread {
            AppDatabase.getInstance(this).coinDao().insertCoins(coin)
        }.start()

        Thread {
            //analytics
            firebaseAnalytics.logEvent("save_button_clicked", null)
        }
    }

    fun queryCoins(){
        Thread {
            val grades = AppDatabase.getInstance(this).coinDao().
            getAllCoins()

            runOnUiThread{
                tvResult.text = ""
                grades.forEach{
                    tvResult.append("Name: ${it.coinName} price: ${it.price}\n")
                }
            }

        }.start()

        Thread {
            //analytics integráció
            firebaseAnalytics.logEvent("query_coins", null)
        }.start()
    }

    private fun deleteAllGrades() {
        Thread {
            AppDatabase.getInstance(this).coinDao().deleteAllCoins()
        }.start()
    }

}