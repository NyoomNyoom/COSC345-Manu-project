/**
 * @author Jackson North
 */

package com.example.manu

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.credits.*
import kotlinx.android.synthetic.main.quiz_stats.*
import kotlinx.android.synthetic.main.quiz_stats.btn_back

/**
 * Creates and fills the stats activity regarding the players scores and play amount.
 */
class StatsActivity : AppCompatActivity() {

    private lateinit var quizzesPlayedPhoto: String
    private lateinit var quizAverageScorePhoto: String
    private lateinit var quizzesPlayedSound: String
    private lateinit var quizAverageScoreSound: String
    private lateinit var quizzesPlayedMaori: String
    private lateinit var quizAverageScoreMaori: String
    private lateinit var quizzesPlayedEnglish: String
    private lateinit var quizAverageScoreEnglish: String
    private lateinit var buttonPress: Animation

    /**
     * This is run when the class is instantiated. It sets up the stats layout.
     *
     * @param Bundle Saves information between separate loads of this activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_stats)

        getAllValues()
        updateStrings()

        AudioManager.resumeAudio()

        // Hide the navigation and status bars.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowCompat.setDecorFitsSystemWindows(window, true)  // Places the layout outside the navbar and status bar.

        loadAndStoreAnimations()

        btn_back.setOnClickListener {
            btn_back.isClickable = false

            var intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("soundFlag", true)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()

            object : CountDownTimer(300, 100) {

                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    btn_back.isClickable = true
                }
            }.start()
        }

        // Reset the stored statistics, then collect and display these new values.
        reset_stats.setOnClickListener {
            var intent = Intent(this, StatsPopupActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }

    /**
     * Collects the saved user data from StatsAdapter for display within the text boxes
     */
     fun getAllValues() {
        val returned = StatsAdapter.getPlayerStats(this)
        for (va in returned) {
            Log.d("StatsActivity", va.toString())
        }
        val playerStats = StatsAdapter.getPlayerStats(this)
        quizzesPlayedPhoto = playerStats[0].toString()
        if (playerStats[0] != 0) {
            quizAverageScorePhoto =
                StatsAdapter.round(playerStats[1].toFloat() / playerStats[0].toFloat(), 1).toString()
        }
        else {
            quizAverageScorePhoto = "0"
        }

        quizzesPlayedSound = playerStats[2].toString()
        if (playerStats[2] != 0) {
            quizAverageScoreSound =
                StatsAdapter.round(playerStats[3].toFloat() / playerStats[2].toFloat(), 1).toString()
        }
        else {
            quizAverageScoreSound = "0"
        }

        quizzesPlayedEnglish = playerStats[4].toString()
        if (playerStats[4] != 0) {
            quizAverageScoreEnglish =
                StatsAdapter.round(playerStats[5].toFloat() / playerStats[4].toFloat(), 1).toString()
        }
        else {
            quizAverageScoreEnglish= "0"
        }

        quizzesPlayedMaori = playerStats[6].toString()
        if (playerStats[6] != 0) {
            quizAverageScoreMaori =
                StatsAdapter.round(playerStats[7].toFloat() / playerStats[6].toFloat(), 1).toString()
        }
        else {
            quizAverageScoreMaori = "0"
        }
    }

    /**
     * Updates the score boards games played and average score for each game.
     */
    private fun updateStrings() {
        photo_total.text = "Games Played: " + quizzesPlayedPhoto
        photo_average.text = "Average Score: " + quizAverageScorePhoto

        sound_total.text = "Games Played: " + quizzesPlayedSound
        sound_average.text = "Average Score: " + quizAverageScoreSound

        english_total.text = "Games Played: " + quizzesPlayedEnglish
        english_average.text = "Average Score: " + quizAverageScoreEnglish

        maori_total.text = "Games Played: " + quizzesPlayedMaori
        maori_average.text = "Average Score: " + quizAverageScoreMaori
    }

    /**
     * Load the button press animation.
     */
    private fun loadAndStoreAnimations() {
        buttonPress = AnimationUtils.loadAnimation(this, R.anim.button_press)
    }

    /**
     * Pauses the audio when the app is quit or the screen closes.
     */
    override fun onPause() {
        super.onPause()
        AudioManager.pauseAudio()
    }

    /**
     * Resumes audio when the app is opened again.
     */
    override fun onResume() {
        super.onResume()
        AudioManager.resumeAudio()
    }

}