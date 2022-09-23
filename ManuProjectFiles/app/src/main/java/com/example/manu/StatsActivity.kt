package com.example.manu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_stats)

        getAllValues()
        updateStrings()

    }

    fun getAllValues(){
        photo_games = StatsAdapter.getStatsBasedOnType(QuestionType.PHOTO).getTotalPlayed().toString()
        photo_av = StatsAdapter.getStatsBasedOnType(QuestionType.PHOTO).getAverage().toString()

        sound_games = StatsAdapter.getStatsBasedOnType(QuestionType.SOUND).getTotalPlayed().toString()
        sound_av = StatsAdapter.getStatsBasedOnType(QuestionType.SOUND).getAverage().toString()

        maori_games = StatsAdapter.getStatsBasedOnType(QuestionType.MAORI).getTotalPlayed().toString()
        maori_av = StatsAdapter.getStatsBasedOnType(QuestionType.MAORI).getAverage().toString()

        english_games = StatsAdapter.getStatsBasedOnType(QuestionType.ENGLISH).getTotalPlayed().toString()
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
}