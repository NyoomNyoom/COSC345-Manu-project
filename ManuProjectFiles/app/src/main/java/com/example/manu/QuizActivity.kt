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
 * Runs the quiz's logic and controls its GUI.
 */
class QuizActivity : AppCompatActivity() {

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
    private lateinit var buttonPress: Animation

    /** Should the player select the incorrect option, that button will play this animation. */
    private lateinit var incorrectAnswerShake: Animation

    /** Should the player select the correct option, that button will play this animation. */
    private lateinit var correctAnswerNod: Animation

    /** The animation for an option button coming into view. */
    private lateinit var answerOptionEnter: Animation

    /** The animation for an option button leaving the screen. */
    private lateinit var answerOptionExit: Animation

    /** The animation for a correct answer when marked. */
    private lateinit var answerPop: Animation

    /**
     * Is run when this class is instantiated. It loads the quiz activity layout and starts the quiz.
     *
     * @param Bundle Saves information between separate loads of this activity view.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // Calls the superclass' creation method to inherit its functionality.
        setContentView(R.layout.activity_quiz)  // Loads the quiz activity layout.

        // Assembles a simple math quiz.
        questions = ArrayList()
        questions.add(QuestionData("3 - 7", "-4", "15", "7", "-2", 0))
        questions.add(QuestionData("4 + 2", "5", "9", "17", "6", 3))
        questions.add(QuestionData("6 + 7", "12", "13", "1", "15", 1))
        questions.add(QuestionData("11 + 11", "22", "1", "1111", "21", 0))
        questions.add(QuestionData("17 - 9", "4", "5", "8", "7", 2))

        initialiseVariables()  // Initialise the variables tagged with late initialisation.
        setupOnClickListeners()  // Setup the button listeners.
        presentQuestion(questions[currentQuestionIndex])  // Present the first question.
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }

    /**
     * Initialises the option buttons variable and loads the animations.
     */
    private fun initialiseVariables() {
        // Initialise and fill with the four option buttons.
        optionButtons = ArrayList()
        optionButtons.add(btn_opt_0)
        optionButtons.add(btn_opt_1)
        optionButtons.add(btn_opt_2)
        optionButtons.add(btn_opt_3)

        // Load the necessary animations into the respective variables.
        buttonPress = AnimationUtils.loadAnimation(this, R.anim.button_press)
        incorrectAnswerShake = AnimationUtils.loadAnimation(this, R.anim.incorrect_answer_shake)
        correctAnswerNod = AnimationUtils.loadAnimation(this, R.anim.correct_answer_nod)
        answerOptionEnter = AnimationUtils.loadAnimation(this, R.anim.answer_option_enter)
        answerOptionExit = AnimationUtils.loadAnimation(this, R.anim.answer_option_exit)
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
     * Resets the colour presentation of each answer option button. This removes any green and red left behind from when
     * the answers are marked.
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
        question_text.text = question.question  // Present the next question.

        // Present the answer options.
        btn_opt_0.text = question.option0
        btn_opt_1.text = question.option1
        btn_opt_2.text = question.option2
        btn_opt_3.text = question.option3

        btn_submit.text = submitText  // Make sure the submit button says "Submit" rather than "Next".

        // Start the entry animation for all the option buttons.
        for (button in optionButtons) {
            button.startAnimation(AnimationUtils.loadAnimation(this, R.anim.answer_option_enter))
        }
    }

    /**
     * Selects the option the user has tapped.
     *
     * @param optionNumber The index of the answer option button.
     */
    private fun selectOption(optionNumber: Int) {
        // Only select the option if we haven't marked the answer.
        if (markedCurrentQuestion)
            return

        resetOptionButtons()  // Reset all option buttons.
        optionSelected = true  // Flag this so we know the user has selected an option.
        selectedOptionIndex = optionNumber  // Save the index of the option button the user has clicked on.

        // Paint and animate the button that just got tapped.
        optionButtons[selectedOptionIndex].setBackgroundColor(Color.parseColor(buttonSelectedColourHex))
        optionButtons[selectedOptionIndex].startAnimation(buttonPress)
    }

    /**
     * Is called when the submit button is pressed. If necessary, it will mark the answer, and
     */
    private fun submitButtonClickHandler() {
        btn_submit.startAnimation(buttonPress)

        if (markedCurrentQuestion) {  // If we have already marked the current question.
            markedCurrentQuestion = false  // Reset this.
            currentQuestionIndex++  // Move to the next question.

            if (currentQuestionIndex == questions.size) {  // If this was the last question.
                var intent = Intent(this, QuizResultsActivity::class.java)  // Prepare the results screen.

                // Pass through the score.
                intent.putExtra("score", score)
                intent.putExtra("totalQuestions", questions.size)

                // Load the results screen and terminate this script.
                startActivity(intent)
                finish()
            } else {
                resetOptionButtons()  // Reset the answer option buttons for the next question.
                presentQuestion(questions[currentQuestionIndex])  // Load the next question.
            }
        } else {  // If we have not marked this question.
            if (optionSelected) {  // If the user has selected an option.
                optionSelected = false  // Reset this.
                markAnswer()  // Mark the answer.
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
     * Marks the players answer and updates the screen accordingly. If they are correct, their answer will be coloured
     * in and the other options will disappear. If they are incorrect, their choice and the correct choice will be
     * coloured in, and the other options will disappear.
     */
    @SuppressLint("ResourceAsColor")
    private fun markAnswer() {
        if (selectedOptionIndex == questions[currentQuestionIndex].correctOptionIndex) {  // If correct.
            // Paint the correct answer and animate it.
            optionButtons[selectedOptionIndex].setBackgroundColor(Color.parseColor(buttonCorrectColourHex))
            optionButtons[selectedOptionIndex].startAnimation(answerPop)
            score++
        } else {  // If incorrect.
            // Paint the incorrect answer and animate it.
            optionButtons[selectedOptionIndex].setBackgroundColor(Color.parseColor(buttonIncorrectColourHex))
            optionButtons[questions[currentQuestionIndex].correctOptionIndex].setBackgroundColor(Color.parseColor(buttonCorrectColourHex))
            optionButtons[selectedOptionIndex].startAnimation(incorrectAnswerShake)
        }

        /* Start the answer option exit animation for all buttons that are not the correct answer, or the player's
           incorrect selection. */
        for (i in 0..optionButtons.size - 1) {
            if (selectedOptionIndex != i && questions[currentQuestionIndex].correctOptionIndex != i) {
                optionButtons[i].startAnimation(AnimationUtils.loadAnimation(this, R.anim.answer_option_exit))
            }
        }

        // Make the submit button say "Finish" if this question was the last, or "Next" if there are more to come.
        if (currentQuestionIndex == questions.size - 1)
            btn_submit.text = finishText
        else
            btn_submit.text = nextText

        markedCurrentQuestion = true  // Flag this so we know we've marked the answer.

        // Increment the progress bar.
        progress_bar.progress = ((currentQuestionIndex + 1).toFloat() / questions.size.toFloat() * 100).toInt()
    }

}