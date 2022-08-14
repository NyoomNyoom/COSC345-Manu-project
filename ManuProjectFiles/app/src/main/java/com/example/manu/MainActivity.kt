package com.example.manu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * The very first script to run when the app is launched. This script does not load a content view. Instead, it runs a
 * script which should load its own content view.
 */
class MainActivity : AppCompatActivity() {
    /**
     * Is run when this class is instantiated. It will load the specified script.
     *
     * @param Bundle Saves information between separate loads of this activity view.
     */
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