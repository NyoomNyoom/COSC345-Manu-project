package com.example.manu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_graphic_activity)

        //btn_reading_text.setOnClickListener {
           // readFromAsset()
       // }
    }

   /* private fun readFromAsset() {
        var string = ""
        try {
            val inputStream = assets.open("birdTest.txt")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer).toString()

            string = String(buffer)

        } catch (e: IOException) {
            e.printStackTrace()
        }
        tv_text.text = string
    } */
}