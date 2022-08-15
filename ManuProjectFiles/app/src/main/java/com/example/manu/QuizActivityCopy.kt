/**
 * @author Daniel Robinson
 */

package com.example.manu

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_quiz.*

/**
 * Runs and displays the quiz.
 */
class QuizActivityCopy : AppCompatActivity() {

    /** The index of the current quiz question. */
    private var currentQuestionIndex: Int = 0

    /** The index of the selected option. */
    private var selectedOptionIndex: Int = -1

    /** The player's score, measured in correct answers. */
    private var score: Int = 0

    /** True if the current question has been marked. */
    private var markedCurrentQuestion: Boolean = false

    /** True if the player has tapped on one of the options. */
    private var optionSelected: Boolean = false

    /** The text to display on the submit button when the player has not submitted an answer to the current question. */
    private val submitText: String = "Submit"

    /** The text to display on the submit button when the player has had their current question marked. */
    private val nextText: String = "Next"

    /** The text to display on the submit button when the player has had the last question marked. */
    private val finishText: String = "Finish"

    /** The colour of an unselected option button. */
    private val buttonColourHex:String = "#000000"

    /** The colour of a selected option button. */
    private val buttonSelectedColourHex:String = "#0000FF"

    /** The colour of the correct answer. */
    private val buttonCorrectColourHex:String = "#00FF00"

    /** The colour of the incorrect answer. */
    private val buttonIncorrectColourHex:String = "#FF0000"

    /** A list containing the answer option buttons. */
    private lateinit var optionButtons: ArrayList<MaterialButton>

    /** A list containing this quiz's questions. */
    private lateinit var questions: ArrayList<QuestionData>

    /** The button press animation. */
    private var buttonPress: Animation = AnimationUtils.loadAnimation(this, R.anim.button_press)

    /** Should the player select the incorrect option, that button will play this animation. */
    private var incorrectAnswerShake: Animation = AnimationUtils.loadAnimation(this, R.anim.incorrect_answer_shake)

    /** The animation for an option button coming into view. */
    private var answerOptionEnter: Animation = AnimationUtils.loadAnimation(this, R.anim.answer_option_enter)

    /** The animation for an option button leaving the screen. */
    private var answerOptionExit: Animation = AnimationUtils.loadAnimation(this, R.anim.answer_option_exit)

    /** The animation for a correct answer when marked. */
    private var answerPop: Animation = AnimationUtils.loadAnimation(this, R.anim.answer_pop)

    /**
     * This is run when the class is instantiated. It sets up the quiz screen and starts the game.
     *
     * @param Bundle Saves information between separate loads of this activity view.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Assembles a simple math quiz.
        questions = ArrayList()
        questions.add(QuestionData("3 - 7", "-4", "15", "7", "-2", 0))
        questions.add(QuestionData("4 + 2", "5", "9", "17", "6", 3))
        questions.add(QuestionData("6 + 7", "12", "13", "1", "15", 1))
        questions.add(QuestionData("11 + 11", "22", "1", "1111", "21", 0))
        questions.add(QuestionData("17 - 9", "4", "5", "8", "7", 2))

        saveOptionButtons()
        setupOnClickListeners()

        presentQuestion(questions[currentQuestionIndex])  // Present the first question.
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
    private fun presentQuestion(question: QuestionData) {
        question_text.text = question.question

        btn_opt_0.text = question.option0
        btn_opt_1.text = question.option1
        btn_opt_2.text = question.option2
        btn_opt_3.text = question.option3

        btn_submit.text = submitText

        for (button in optionButtons) {
            button.startAnimation(answerOptionEnter)
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
        // If correct.
        if (selectedOptionIndex == questions[currentQuestionIndex].correctOptionIndex) {
            optionButtons[selectedOptionIndex].setBackgroundColor(Color.parseColor(buttonCorrectColourHex))
            optionButtons[selectedOptionIndex].startAnimation(answerPop)
            score++
        }

        // If incorrect.
        else {
            optionButtons[selectedOptionIndex].setBackgroundColor(Color.parseColor(buttonIncorrectColourHex))
            optionButtons[questions[currentQuestionIndex].correctOptionIndex].setBackgroundColor(Color.parseColor(buttonCorrectColourHex))
            optionButtons[selectedOptionIndex].startAnimation(incorrectAnswerShake)
        }

        /*
         * Start the answer option exit animation for all buttons that are not the correct answer, or the player's
         * incorrect selection.
         */
        for (i in 0..optionButtons.size - 1) {
            if (selectedOptionIndex != i && questions[currentQuestionIndex].correctOptionIndex != i) {
                optionButtons[i].startAnimation(answerOptionExit)
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