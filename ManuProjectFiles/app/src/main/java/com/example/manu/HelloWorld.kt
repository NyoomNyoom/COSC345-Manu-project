/**
 * This script is an exact copy of a view controlling Kotlin class (like the default MainActivity.kt
 * script class). The lines with comments saying "ADDED: " are in addition to the default code.
 */

package com.example.manu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log  // ADDED: Allows you to print debugging statements.

class HelloWorld : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // Change "activity_main" to your desired view.
        Log.d("HelloWorld", "Well, that worked!")  // ADDED: Print text to prove it works.
    }

}