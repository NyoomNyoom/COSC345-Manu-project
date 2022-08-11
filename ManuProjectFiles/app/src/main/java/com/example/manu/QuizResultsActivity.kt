package com.example.manu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_quiz_results.*

class QuizResultsActivity : AppCompatActivity() {

    private var score:Int = -1
    private var totalQuestions:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_results)

        score = intent.getIntExtra("score", -1)
        totalQuestions =  intent.getIntExtra("totalQuestions", -1)

        Log.d("results, score", score.toString())
        Log.d("results, total", totalQuestions.toString())
        text_score.text = "$score / $totalQuestions"
    }

}