/**
 * @author Daniel Robinson
 */

package com.example.manu

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_quiz.*

/**
 * Runs and displays the quiz.
 */
class QuizActivity : AppCompatActivity() {

    private var currentQuestionIndex: Int = 0
    private var selectedOptionIndex: Int = -1
    private var score: Int = 0
    private var numQuestions: Int = 5
    private var numOptions: Int = 4
    private var markedCurrentQuestion: Boolean = false
    private var optionSelected: Boolean = false
    private val submitText: String = "Submit"
    private val nextText: String = "Next"
    private val finishText: String = "Finish"
    private val buttonColourHex:String = "#FFFFFF"
    private val buttonSelectedColourHex:String = "#0000FF"
    private val buttonCorrectColourHex:String = "#00FF00"
    private val buttonIncorrectColourHex:String = "#FF0000"
    private var questions: ArrayList<QuestionTemp> = ArrayList()
    private lateinit var optionButtons: ArrayList<MaterialButton>
    private lateinit var buttonPress: Animation
    private lateinit var incorrectAnswerShake: Animation
    private lateinit var answerOptionAppear: Animation
    private lateinit var answerOptionDisappear: Animation
    private lateinit var answerPop: Animation

    /**
     * This is run when the class is instantiated. It sets up the quiz screen and starts the game.
     *
     * @param Bundle Saves information between separate loads of this activity view.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        questions = QuizGenerator.generateQuiz(QuestionType.PHOTO, numQuestions, numOptions)

        saveOptionButtons()
        loadAnimations()
        setupOnClickListeners()

        presentQuestion(questions[currentQuestionIndex])  // Present the first question.

        // Jackson's code is below (commented out).
        //val minput = InputStreamReader(getAssets().open("bird-data.csv"), "UTF-8")
        //val fileIN = InputStreamReader(assets.open("bird-data.csv"))
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }

    /**
     * Stores all of the option buttons.
     */
    private fun saveOptionButtons() {
        optionButtons = ArrayList()
        optionButtons.add(btn_opt_0)
        optionButtons.add(btn_opt_1)
        optionButtons.add(btn_opt_2)
        optionButtons.add(btn_opt_3)
    }

    /**
     * Loads and stores the animations.
     */
    private fun loadAnimations() {
        buttonPress = AnimationUtils.loadAnimation(this, R.anim.button_press)
        incorrectAnswerShake = AnimationUtils.loadAnimation(this, R.anim.incorrect_answer_shake)
        answerOptionAppear = AnimationUtils.loadAnimation(this, R.anim.answer_option_appear)
        answerOptionDisappear = AnimationUtils.loadAnimation(this, R.anim.answer_option_disappear)
        answerPop = AnimationUtils.loadAnimation(this, R.anim.answer_pop)
    }

    /**
     * Defines the behaviour for each button when it is clicked.
     */
    private fun setupOnClickListeners() {
        btn_opt_0.setOnClickListener { selectOption(0) }
        btn_opt_1.setOnClickListener { selectOption(1) }
        btn_opt_2.setOnClickListener { selectOption(2) }
        btn_opt_3.setOnClickListener { selectOption(3) }
        btn_submit.setOnClickListener { submitButtonClickHandler() }
        btn_back.setOnClickListener { returnToMenu() }
    }

    /**
     * Resets the colour presentation of each option button.
     */
    private fun resetOptionButtons() {
        for (button in optionButtons) {
            button.setBackgroundColor(Color.parseColor(buttonColourHex))
        }
    }

    /**
     * Resets the screen with the next question.
     */
    private fun presentQuestion(question: QuestionTemp) {
        img_question.setImageResource(question.getQuestionResourceId())
        val options = question.getOptions()

        btn_opt_0.text = options[0]
        btn_opt_1.text = options[1]
        btn_opt_2.text = options[2]
        btn_opt_3.text = options[3]

        btn_submit.text = submitText

        for (button in optionButtons) {
            button.startAnimation(answerOptionAppear)
        }
    }

    /**
     * Selects the option the user has tapped.
     *
     * @param optionNumber The index of the option button.
     */
    private fun selectOption(optionNumber: Int) {
        // Only select the option if we haven't marked the answer.
        if (markedCurrentQuestion)
            return

        resetOptionButtons()
        optionSelected = true
        selectedOptionIndex = optionNumber

        optionButtons[selectedOptionIndex].setBackgroundColor(Color.parseColor(buttonSelectedColourHex))
        optionButtons[selectedOptionIndex].startAnimation(buttonPress)
    }

    /**
     * Call this when the submit button is pressed. Depending on the state of the game, it either marks the answer,
     * loads the next question, or proceeds to the results screen.
     */
    private fun submitButtonClickHandler() {
        btn_submit.startAnimation(buttonPress)

        if (markedCurrentQuestion) {
            markedCurrentQuestion = false
            currentQuestionIndex++

            if (currentQuestionIndex == questions.size) {  // If this was the last question.
                var resultsScreen = Intent(this, QuizResultsActivity::class.java)

                // Pass the score through to the results screen.
                resultsScreen.putExtra("score", score)
                resultsScreen.putExtra("totalQuestions", questions.size)

                startActivity(resultsScreen)
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

    /**
     * Loads the menu screen.
     */
    private fun returnToMenu() {
        var intent = Intent(this, Pop::class.java)
        startActivity(intent)
    }

    /**
     * Marks the player's answer and updates the screen accordingly. If they are correct, their answer will be coloured
     * in and the other options will disappear. If they are incorrect, their choice and the correct choice will be
     * coloured in, and the other options will disappear. This method also animates the player's answer selection.
     */
    @SuppressLint("ResourceAsColor")
    private fun markAnswer() {
        /*
         * If correct.
         */
        if (selectedOptionIndex == questions[currentQuestionIndex].getAnswerIndex()) {
            optionButtons[selectedOptionIndex].setBackgroundColor(Color.parseColor(buttonCorrectColourHex))
            optionButtons[selectedOptionIndex].startAnimation(answerPop)
            score++
        }

        /*
         * If incorrect.
         */
        else {
            optionButtons[selectedOptionIndex].setBackgroundColor(Color.parseColor(buttonIncorrectColourHex))
            optionButtons[questions[currentQuestionIndex].getAnswerIndex()].setBackgroundColor(Color.parseColor(buttonCorrectColourHex))
            optionButtons[selectedOptionIndex].startAnimation(incorrectAnswerShake)
        }

        /*
         * Start the answer option exit animation for all buttons that are not the correct answer, or the player's
         * incorrect selection.
         */
        for (buttonIndex in 0..optionButtons.size - 1) {
            if (selectedOptionIndex != buttonIndex && questions[currentQuestionIndex].getAnswerIndex() != buttonIndex) {
                optionButtons[buttonIndex].startAnimation(answerOptionDisappear)
            }
        }

        if (currentQuestionIndex == questions.size - 1)  // If this was the last question.
            btn_submit.text = finishText
        else
            btn_submit.text = nextText

        markedCurrentQuestion = true

        // Increment the progress bar.
        progress_bar.progress = ((currentQuestionIndex + 1).toFloat() / questions.size.toFloat() * 100).toInt()
    }

}