package ru.starfactory.pixel.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import ru.starfactory.pixel.R

class MainActivity : ComponentActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // TODO Sumin: конечно мы не будем весь код писать в активити, а сделаем по всем правилам но пока для теста оставлю так
        val wv = findViewById<WebView>(R.id.web_view).apply {
            settings.javaScriptEnabled = true
        }
        // TODO Sumin: Надо припилить какой-то параметр типа env, чтобы можно было подкидывать ссылку без изменения исходников
        wv.loadUrl("localhost:3000")
    }
}
