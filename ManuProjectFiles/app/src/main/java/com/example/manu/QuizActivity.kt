package com.example.manu

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import kotlinx.android.synthetic.main.activity_menu.view.*
import kotlinx.android.synthetic.main.activity_quiz.*
import java.time.Duration

class QuizActivity : AppCompatActivity() {

    private var currentQuestionIndex: Int = 0
    private var selectedOptionIndex: Int = -1
    private var correctOptionIndex: Int = -1
    private var correctAnswerAnimationDuration:Long = 350
    private var submitButtonAnimationDuration:Long = 150
    var score: Int = 0
    private var markedCurrentQuestion: Boolean = false
    private var optionSelected: Boolean = false
    private val optionNormalAlpha: Float = 1f
    private val selectedOptionAlpha: Float = 0.5f
    private val submitText: String = "Submit"
    private val nextText: String = "Next"
    private lateinit var optionButtons: ArrayList<Button>
    private lateinit var questions: ArrayList<QuestionData>
    lateinit var scaleDownInitial:Animation
    lateinit var scaleDownReturn:Animation
    lateinit var scaleUpInitial:Animation
    lateinit var scaleUpReturn:Animation
    private val scaleInDuration:Long = 350

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        Log.d("QuizActivity", "Quiz launched!")

        questions = ArrayList()
        questions.add(QuestionData("3 - 7", "-4", "15", "7", "-2", 0))
        questions.add(QuestionData("4 + 2", "5", "9", "17", "6", 3))
        questions.add(QuestionData("6 + 7", "12", "13", "1", "15", 1))
        questions.add(QuestionData("11 + 11", "22", "1", "1111", "21", 0))
        questions.add(QuestionData("17 - 9", "4", "5", "8", "7", 2))

        initialiseVariables()
        setupOnClickListeners()
        presentQuestion(questions[currentQuestionIndex])
    }

    private fun initialiseVariables() {
        optionButtons = ArrayList()
        optionButtons.add(btn_opt_0)
        optionButtons.add(btn_opt_1)
        optionButtons.add(btn_opt_2)
        optionButtons.add(btn_opt_3)

        scaleDownInitial = AnimationUtils.loadAnimation(this, R.anim.scale_down_initial)
        scaleDownReturn = AnimationUtils.loadAnimation(this, R.anim.scale_down_return)
        scaleUpInitial = AnimationUtils.loadAnimation(this, R.anim.scale_up_initial)
        scaleUpReturn = AnimationUtils.loadAnimation(this, R.anim.scale_up_return)
    }

    private fun setupOnClickListeners() {
        btn_opt_0.setOnClickListener {
            selectOption(0)
        }
        btn_opt_1.setOnClickListener {
            selectOption(1)
        }
        btn_opt_2.setOnClickListener {
            selectOption(2)
        }
        btn_opt_3.setOnClickListener {
            selectOption(3)
        }
        btn_submit.setOnClickListener {
            scaleDownInitial.duration = submitButtonAnimationDuration.toLong()
            scaleDownReturn.duration = submitButtonAnimationDuration.toLong()
            runScaleDownAndUpAnimation(btn_submit, submitButtonAnimationDuration)

            if (markedCurrentQuestion) {
                markedCurrentQuestion = false
                currentQuestionIndex++

                if (currentQuestionIndex == questions.size) {
                    var intent = Intent(this, QuizResultsActivity::class.java)
                    Log.d("score", score.toString())
                    Log.d("totalQuestions", questions.size.toString())
                    intent.putExtra("score", score)
                    intent.putExtra("totalQuestions", questions.size)
                    startActivity(intent)
                    finish()
                } else {
                    resetOptionButtons()
                    presentQuestion(questions[currentQuestionIndex])
                }
            } else {
                if (optionSelected) {
                    optionSelected = false
                    markAnswer()
                }
            }
        }
    }

    private fun resetOptionButtons() {
        btn_opt_0.alpha = optionNormalAlpha
        btn_opt_1.alpha = optionNormalAlpha
        btn_opt_2.alpha = optionNormalAlpha
        btn_opt_3.alpha = optionNormalAlpha
        btn_opt_0.setBackgroundColor(Color.BLACK)
        btn_opt_1.setBackgroundColor(Color.BLACK)
        btn_opt_2.setBackgroundColor(Color.BLACK)
        btn_opt_3.setBackgroundColor(Color.BLACK)
    }

    private fun presentQuestion(question: QuestionData) {
        question_text.text = question.question
        btn_opt_0.text = question.option0
        btn_opt_1.text = question.option1
        btn_opt_2.text = question.option2
        btn_opt_3.text = question.option3
        correctOptionIndex = question.correctOptionIndex
        btn_submit.text = submitText
    }

    private fun selectOption(optionNumber: Int) {
        // Only mark the option if we haven't marked the answer.
        if (markedCurrentQuestion)
            return

        resetOptionButtons()
        optionSelected = true

        if (optionNumber == 0) {
            btn_opt_0.alpha = selectedOptionAlpha
            selectedOptionIndex = 0
        } else if (optionNumber == 1) {
            btn_opt_1.alpha = selectedOptionAlpha
            selectedOptionIndex = 1
        } else if (optionNumber == 2) {
            btn_opt_2.alpha = selectedOptionAlpha
            selectedOptionIndex = 2
        } else if (optionNumber == 3) {
            btn_opt_3.alpha = selectedOptionAlpha
            selectedOptionIndex = 3
        }
    }

    private fun markAnswer() {
        if (selectedOptionIndex == correctOptionIndex) {
            optionButtons[selectedOptionIndex].setBackgroundColor(Color.GREEN)
            scaleUpInitial.duration = correctAnswerAnimationDuration.toLong()
            scaleUpReturn.duration = correctAnswerAnimationDuration.toLong()
            runScaleUpAndDownAnimation(optionButtons[selectedOptionIndex], correctAnswerAnimationDuration)
            score++
        } else {
            optionButtons[selectedOptionIndex].setBackgroundColor(Color.RED)
            optionButtons[correctOptionIndex].setBackgroundColor(Color.GREEN)
        }

        btn_submit.text = nextText
        markedCurrentQuestion = true
        progress_bar.progress = ((currentQuestionIndex + 1).toFloat() / questions.size.toFloat() * 100).toInt()
    }

    private fun runScaleUpAndDownAnimation(view:View, duration:Long) {
        scaleUpInitial.duration = duration
        scaleUpReturn.duration = duration
        view.startAnimation(scaleUpInitial)
        view.startAnimation(scaleUpReturn)
    }

    private fun runScaleDownAndUpAnimation(view:View, duration:Long) {
        scaleDownInitial.duration = duration
        scaleDownReturn.duration = duration
        view.startAnimation(scaleDownInitial)
        view.startAnimation(scaleDownReturn)
    }

    private fun runInstantScaleUpTransitionScaleDownAnimation(view:View, duration:Long) {
        scaleUpInitial.duration = 0
        scaleUpReturn.duration = duration
        view.startAnimation(scaleUpInitial)
        view.startAnimation(scaleUpReturn)
    }

}