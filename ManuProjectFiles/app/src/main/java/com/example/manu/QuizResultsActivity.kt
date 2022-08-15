package com.example.manu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_quiz_results.*

/**
 * Controls and displays the quiz results screen.
 */
class QuizResultsActivity : AppCompatActivity() {

    /**
     * Is run when this class is instantiated. It loads the quiz results activity layout and presents the results.
     *
     * @param Bundle Saves information between separate loads of this activity view.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // Calls the superclass' creation method to inherit its functionality.
        setContentView(R.layout.activity_quiz_results)  // Loads the quiz results activity layout.

        // Collects the results that should be passed from the quiz activity script.
        val score: Int = intent.getIntExtra("score", -1)
        val totalQuestions: Int =  intent.getIntExtra("totalQuestions", -1)

        text_score.text = "$score / $totalQuestions"  // Create and present the score.

        // If the player taps the play again button, load the quiz activity and terminate this script.
        btn_play_again.setOnClickListener {
            var intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }

        // If the player wishes to return to the menu, load the menu activity and terminate this script.
        btn_menu.setOnClickListener {
            var intent = Intent(this, MenuActivity::class.java)
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