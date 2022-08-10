package com.example.manu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Change "HelloWorld" below to the class you wish to instantiate when the app is launched.
         * Make sure your chosen class specifies which view to use. See HelloWorld.kt for an
         * example.
         */
        val intent = Intent(this, HelloWorld::class.java)
        startActivity(intent)  // Launch the class script.
        finish()  // Terminate this class script.
    }
}