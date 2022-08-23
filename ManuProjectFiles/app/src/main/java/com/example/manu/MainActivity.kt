/**
 * @author Daniel Robinson
 */

package com.example.manu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * This is run when the app is launched. It hands control to another script.
 */
class MainActivity : AppCompatActivity() {
    /**
     * This is run when the class is instantiated. It loads the script to which this script transfers control.
     *
     * @param Bundle Saves information between separate loads of this activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BirdDatabase.compileDatabase()
        val intent = Intent(this, InfoGraphicActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }
}