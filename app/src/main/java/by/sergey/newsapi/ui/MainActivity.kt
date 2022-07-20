package by.sergey.newsapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.sergey.newsapi.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}