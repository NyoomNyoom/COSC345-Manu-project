package com.example.manu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        Log.d("QuizActivity", "Quiz launched!")
    }

}