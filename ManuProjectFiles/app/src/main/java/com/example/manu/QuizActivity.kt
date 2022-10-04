/**
 * @author Daniel Robinson
 * @author Madeline McCane
 */

package com.example.manu

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_quiz.img_question
import kotlinx.android.synthetic.main.activity_quiz.btn_back
import kotlinx.android.synthetic.main.activity_quiz.btn_opt_0
import kotlinx.android.synthetic.main.activity_quiz.btn_opt_1
import kotlinx.android.synthetic.main.activity_quiz.btn_opt_2
import kotlinx.android.synthetic.main.activity_quiz.btn_opt_3
import kotlinx.android.synthetic.main.activity_quiz.btn_submit
import kotlinx.android.synthetic.main.activity_quiz.progress_bar

/**
 * Runs and displays the quiz.
 */
class QuizActivity : AppCompatActivity() {

    private var currentQuestionIndex: Int = 0
    private var selectedOptionIndex: Int = -1
    private var score: Int = 0
    private var numQuestions: Int = 10
    private var numOptions: Int = 4
    private var markedCurrentQuestion: Boolean = false
    private var optionSelected: Boolean = false
    private val submitText: String = "Submit"
    private val nextText: String = "Next"
    private val finishText: String = "Finish"
    private val buttonColourHex:String = "#000000"
    private val buttonSelectedColourHex:String = "#808080"
    private val buttonCorrectColourHex:String = "#39db39"
    private val buttonIncorrectColourHex:String = "#FF6836"
    private lateinit var quizType: QuestionType
    private var questions: ArrayList<Question> = ArrayList()
    private var mediaPlayer = MediaPlayer()
    private lateinit var optionButtons: ArrayList<MaterialButton>
    private lateinit var buttonPress: Animation
    private lateinit var incorrectAnswerShake: Animation
    private lateinit var answerOptionAppear: Animation
    private lateinit var answerOptionDisappear: Animation
    private lateinit var answerPop: Animation

    /**
     * This is run when the class is instantiated. It sets up the quiz screen and starts the game.
     *
     * @param Bundle Saves information between separate loads of this activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quizType = QuestionTypeConverter.intToQuestionType(intent.getIntExtra("quizType", -1))

        if (quizType == QuestionType.PHOTO) {
            setContentView(R.layout.activity_quiz)
            questions = QuizGenerator.generateQuiz(QuestionType.PHOTO, numQuestions, numOptions)
        } else if (quizType == QuestionType.SOUND) {
            setContentView(R.layout.activity_quiz)
            questions = QuizGenerator.generateQuiz(QuestionType.SOUND, numQuestions, numOptions)
        } else if (quizType == QuestionType.ENGLISH) {
            setContentView(R.layout.activity_quiz)
            questions = QuizGenerator.generateQuiz(QuestionType.ENGLISH, numQuestions, numOptions)
        } else if (quizType == QuestionType.MAORI) {
            setContentView(R.layout.activity_quiz)
            questions = QuizGenerator.generateQuiz(QuestionType.MAORI, numQuestions, numOptions)
        }

        storeOptionButtons()
        loadAnimations()
        setupOnClickListeners()

        // Hide the navigation and status bars.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowCompat.setDecorFitsSystemWindows(window, true)  // Places the layout outside the navbar and status bar.

        presentQuestion(questions[currentQuestionIndex])  // Present the first question.
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }

    private fun storeOptionButtons() {
        optionButtons = ArrayList()
        optionButtons.add(btn_opt_0)
        optionButtons.add(btn_opt_1)
        optionButtons.add(btn_opt_2)
        optionButtons.add(btn_opt_3)
    }

    private fun loadAnimations() {
        buttonPress = AnimationUtils.loadAnimation(this, R.anim.button_press)
        incorrectAnswerShake = AnimationUtils.loadAnimation(this, R.anim.incorrect_answer_shake)
        answerOptionAppear = AnimationUtils.loadAnimation(this, R.anim.answer_option_appear)
        answerOptionDisappear = AnimationUtils.loadAnimation(this, R.anim.answer_option_disappear)
        answerPop = AnimationUtils.loadAnimation(this, R.anim.answer_pop)
    }

    private fun setupOnClickListeners() {
        for (buttonIndex in 0 until numOptions) {
            optionButtons[buttonIndex].setOnClickListener { selectOption(buttonIndex) }
        }

        btn_submit.setOnClickListener { submitButtonClickHandler() }
        btn_back.setOnClickListener { returnToMenu() }
    }

    private fun resetOptionButtons() {
        for (button in optionButtons) {
            button.setBackgroundColor(Color.parseColor(buttonColourHex))
        }
    }

    /**
     * Resets the screen with the next question.
     */
    private fun presentQuestion(question: Question) {
        if (quizType == QuestionType.PHOTO || quizType == QuestionType.ENGLISH || quizType == QuestionType.MAORI) {
            img_question.setImageResource(question.getQuestionResourceId())
        } else if (quizType == QuestionType.SOUND) {
            img_question.setImageResource(R.drawable.question_mark)
            AudioManager.playAudio(this, question.getQuestionResourceId())
            //mediaPlayer = MediaPlayer.create(this, question.getQuestionResourceId())
            //mediaPlayer.isLooping = true
            //mediaPlayer.start()
        }

        val options = question.getOptions()

        for (buttonIndex in 0 until numOptions) {
            optionButtons[buttonIndex].text = options[buttonIndex]
        }

        btn_submit.text = submitText

        for (button in optionButtons) {
            button.startAnimation(answerOptionAppear)
        }
    }

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

    private fun submitButtonClickHandler() {
        btn_submit.startAnimation(buttonPress)
        if (quizType == QuestionType.SOUND){
            mediaPlayer.pause()
        }

        if (markedCurrentQuestion) {
            markedCurrentQuestion = false
            currentQuestionIndex++

            if (currentQuestionIndex == questions.size) {  // If this was the last question.
                var resultsScreen = Intent(this, QuizResultsActivity::class.java)

                // Pass the score through to the results screen.
                resultsScreen.putExtra("score", score)
                resultsScreen.putExtra("totalQuestions", questions.size)
                resultsScreen.putExtra("quizType", QuestionTypeConverter.questionTypeToInt(quizType))

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

    private fun returnToMenu() {
        btn_back.startAnimation(buttonPress)
        AudioManager.pauseAudio()
        var intent = Intent(this, ReturnToMenuPopupActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
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
            mediaPlayer = MediaPlayer.create(this, R.raw.correct_2)
            mediaPlayer.start()
        }

        /*
         * If incorrect.
         */
        else {
            optionButtons[selectedOptionIndex].setBackgroundColor(Color.parseColor(buttonIncorrectColourHex))
            optionButtons[questions[currentQuestionIndex].getAnswerIndex()].setBackgroundColor(Color.parseColor(buttonCorrectColourHex))
            optionButtons[selectedOptionIndex].startAnimation(incorrectAnswerShake)
            mediaPlayer = MediaPlayer.create(this, R.raw.incorrect_answer)
            mediaPlayer.start()
        }

        /*
         * Display the correct bird's photo.
         */
        if (quizType == QuestionType.SOUND) {
            val imageResourceId = BirdDatabase.getBirdUsingResourceId(questions[currentQuestionIndex].getQuestionResourceId()).getPhotoResourceId()
            img_question.setImageResource(imageResourceId)
        }

        /*
         * Start the answer option exit animation for all buttons that are not the correct answer, or the player's
         * incorrect selection.
         */
        for (buttonIndex in 0 until optionButtons.size) {
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

    override fun onPause() {
        super.onPause()
        AudioManager.pauseAudio()
    }

    override fun onResume() {
        super.onResume()
        AudioManager.resumeAudio()
    }

}