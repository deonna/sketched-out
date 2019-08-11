package co.deonna.sketchedout

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxkotlin.subscribeBy
import network.ApiService

class MainActivity : AppCompatActivity() {

    private val apiService = ApiService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val disposable = apiService.getImageUrls()
            .subscribeBy(
                onNext = { urls ->

                    Log.d(MainActivity::class.java.simpleName, "Here are the urls: \n")

                    urls.forEach { url ->
                        Log.d("\uD83D\uDE00", url)
                    }
                },
                onError = { e ->

                    Log.d(MainActivity::class.java.simpleName, "Error: $e")

                }
            )
    }
}
