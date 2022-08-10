package com.example.manu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class HelloWorld : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_graphic_activity)
        Log.d("HelloWorld", "Well, that worked!")
    }

}