package com.example.manu

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.WindowCompat
import kotlinx.android.synthetic.main.quiz_options.*
import kotlinx.android.synthetic.main.quiz_stats.*

class StatsActivity : AppCompatActivity() {

    private lateinit var photo_games: String
    private lateinit var photo_av: String
    private lateinit var sound_games: String
    private lateinit var sound_av: String
    private lateinit var maori_games: String
    private lateinit var maori_av: String
    private lateinit var english_games: String
    private lateinit var english_av: String
    private lateinit var buttonPress: Animation
    private var mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_stats)

        mediaPlayer = MediaPlayer.create(this, R.raw.menu_ambience)
        mediaPlayer.start()

        getAllValues()
        updateStrings()

        // Hide the navigation and status bars.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowCompat.setDecorFitsSystemWindows(window, true)  // Places the layout outside the navbar and status bar.

        loadAnimations()

        btn_back.setOnClickListener{
            mediaPlayer.pause()
            var intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

    fun getAllValues(){
        photo_games = StatsAdapter.getStatsBasedOnType(QuestionType.PHOTO).getTotalQuizzesPlayed().toString()
        photo_av = StatsAdapter.getStatsBasedOnType(QuestionType.PHOTO).getAverage().toString()

        sound_games = StatsAdapter.getStatsBasedOnType(QuestionType.SOUND).getTotalQuizzesPlayed().toString()
        sound_av = StatsAdapter.getStatsBasedOnType(QuestionType.SOUND).getAverage().toString()

        maori_games = StatsAdapter.getStatsBasedOnType(QuestionType.MAORI).getTotalQuizzesPlayed().toString()
        maori_av = StatsAdapter.getStatsBasedOnType(QuestionType.MAORI).getAverage().toString()

        english_games = StatsAdapter.getStatsBasedOnType(QuestionType.ENGLISH).getTotalQuizzesPlayed().toString()
        english_av = StatsAdapter.getStatsBasedOnType(QuestionType.ENGLISH).getAverage().toString()
    }

    fun updateStrings(){
        photo_total.text = "Games played: " + photo_games
        photo_average.text = "Average score: " + photo_av

        sound_total.text = "Games played: " + sound_games
        sound_average.text = "Average score: " + sound_av

        maori_total.text = "Games played: " + maori_games
        maori_average.text = "Average score: " + maori_av

        english_total.text = "Games played: " + english_games
        english_average.text = "Average score: " + english_av
    }

    /**
     * Loads and stores the animations.
     */
    private fun loadAnimations() {
        buttonPress = AnimationUtils.loadAnimation(this, R.anim.button_press)
    }
}