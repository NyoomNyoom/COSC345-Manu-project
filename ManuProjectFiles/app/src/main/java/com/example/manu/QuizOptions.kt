package com.example.manu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import kotlinx.android.synthetic.main.quiz_options.*
import androidx.core.view.GestureDetectorCompat

/**
 * Runs and displays the main menu.
 */
class QuizOptions : AppCompatActivity() {

    /**
     * This is run when the class is instantiated. Hands control to either the infographic screen
     * or the quiz menu on a button press.
     * Button names need to be changed.
     *
     * @param Bundle Saves information between separate loads of this activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_options)  // Set the layout to the menu layout.
        //val button = findViewById<View>(R.id.button1)
        // When this button is pressed, load the quiz, and exit from this script.

        img_eng_button.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }
        img_mao_button.setOnClickListener {
            val intent = Intent(this, ReturnToMenuPopupActivity::class.java)
            startActivity(intent)
            finish()
        }

        sou_eng_button.setOnClickListener {
            val intent = Intent(this, ReturnToMenuPopupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}