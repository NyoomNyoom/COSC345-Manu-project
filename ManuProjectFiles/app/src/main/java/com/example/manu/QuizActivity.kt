package com.example.manu

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    private var currentQuestionIndex: Int = 0
    private var selectedOptionIndex: Int = -1
    private var score: Int = 0
    private var markedCurrentQuestion: Boolean = false
    private var optionSelected: Boolean = false
    private val submitText: String = "Submit"
    private val nextText: String = "Next"
    private val finishText: String = "Finish"
    private lateinit var optionButtons: ArrayList<MaterialButton>
    private lateinit var questions: ArrayList<QuestionData>
    private lateinit var buttonPress:Animation
    private lateinit var incorrectAnswerShake:Animation
    private lateinit var correctAnswerNod:Animation
    private lateinit var answerOptionEnter:Animation
    private lateinit var answerOptionExit:Animation
    private var correctAnswerHexCode:String = "#00FF00"
    private var incorrectAnswerHexCode:String = "#FF0000"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

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

    /**
     * Initialises the "lateinit" variables.
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
    }

    /**
     * Defines the behaviour for a button when it is clicked.
     */
    private fun setupOnClickListeners() {
        // The behaviour for the answer option buttons.
        btn_opt_0.setOnClickListener { selectOption(0) }
        btn_opt_1.setOnClickListener { selectOption(1) }
        btn_opt_2.setOnClickListener { selectOption(2) }
        btn_opt_3.setOnClickListener { selectOption(3) }

        btn_submit.setOnClickListener { submitButtonClickHandler() }  // The behaviour for the submit button.
    }

    /**
     * Resets the colour presentation of each answer option button. This removes any green and red left behind from when
     * the answers are marked.
     */
    private fun resetOptionButtons() {
        for (button in optionButtons) {
            button.setBackgroundColor(Color.parseColor(resources.getString(R.string.option_background_colour)))  // Set the background colour to black.
            button.setStrokeColorResource(R.color.option_background_colour)  // Set the outline colour to black.
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

        // Fill the selected option button with blue, and paint its outline blue as well.
        optionButtons[selectedOptionIndex].setBackgroundColor(Color.parseColor(resources.getString(R.string.option_selected_colour)))
        optionButtons[selectedOptionIndex].setStrokeColorResource(R.color.option_selected_colour)

        optionButtons[selectedOptionIndex].startAnimation(buttonPress)  // Start the button press animation.
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
     * Marks the players answer and updates the screen accordingly. If they are correct, their answer will be coloured
     * green. If they are incorrect, their answer will be coloured red, with the correct answer outlined in green. Other
     * answer options will disappear.
     */
    @SuppressLint("ResourceAsColor")
    private fun markAnswer() {
        if (selectedOptionIndex == questions[currentQuestionIndex].correctOptionIndex) {  // If correct.
            // Fill the correct answer with green, and paint its outline green as well.
            optionButtons[selectedOptionIndex].setBackgroundColor(Color.parseColor(resources.getString(R.string.option_correct_colour)))
            optionButtons[selectedOptionIndex].setStrokeColorResource(R.color.option_correct_colour)

            optionButtons[selectedOptionIndex].startAnimation(correctAnswerNod)  // Make the correct answer nod.
            score++  // Award the player one point.
        } else {  // If incorrect.
            // Fill the incorrect answer with red, and paint its outline red as well.
            optionButtons[selectedOptionIndex].setStrokeColorResource(R.color.option_incorrect_colour)
            optionButtons[selectedOptionIndex].setBackgroundColor(Color.parseColor(resources.getString(R.string.option_incorrect_colour)))

            // Outline the correct answer with green.
            optionButtons[questions[currentQuestionIndex].correctOptionIndex].setStrokeColorResource(R.color.option_correct_colour)

            optionButtons[selectedOptionIndex].startAnimation(incorrectAnswerShake)  // Make the incorrect answer shake.
        }

        /**
         * Start the answer option exit animation for all buttons that are not the correct answer, or the player's
         * incorrect selection.
         */
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