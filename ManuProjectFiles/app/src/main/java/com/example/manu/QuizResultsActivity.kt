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
     * This is run when the class is instantiated. It loads the quiz results activity and presents the results.
     *
     * @param Bundle Saves information between separate loads of this activity view.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_results)

        // Collect the results that the quiz will pass through.
        val score: Int = intent.getIntExtra("score", -1)
        val totalQuestions: Int =  intent.getIntExtra("totalQuestions", -1)

        text_score.text = "$score / $totalQuestions"

        /*
         * Play again.
         */
        btn_play_again.setOnClickListener {
            var intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }

        /*
         * Go to the menu.
         */
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