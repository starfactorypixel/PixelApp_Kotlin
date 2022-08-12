package ru.starfactory.pixel.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import android.webkit.WebView
import androidx.activity.ComponentActivity
import ru.starfactory.pixel.R

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // TODO Sumin: конечно мы не будем весь код писать в активити, а сделаем по всем правилам но пока для теста оставлю так
        val wv = findViewById<WebView>(R.id.web_view)
        wv.loadUrl("file:///android_asset/src/index.html")
    }
}
