package com.example.manu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)  // Set the layout to the menu layout.
        //val button = findViewById<View>(R.id.button1)
        // When this button is pressed, load the quiz, and exit from this script.
        button1.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }
        button2.setOnClickListener {
            val intent = Intent(this, InfoGraphicActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }
}