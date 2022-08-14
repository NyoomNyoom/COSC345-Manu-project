package com.example.manu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_quiz_results.*

class QuizResultsActivity : AppCompatActivity() {

    private var score: Int = -1
    private var totalQuestions: Int = -1

    /**
     * Is run when this class is instantiated. It loads the quiz results activity layout and presents the results.
     *
     * @param Bundle Saves information between separate loads of this activity view.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_results)

        score = intent.getIntExtra("score", -1)
        totalQuestions =  intent.getIntExtra("totalQuestions", -1)

        Log.d("results, score", score.toString())
        Log.d("results, total", totalQuestions.toString())
        text_score.text = "$score / $totalQuestions"

        btn_play_again.setOnClickListener {
            var intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_menu.setOnClickListener {
            var intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}