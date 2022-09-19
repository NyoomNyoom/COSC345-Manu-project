/**
 * @author Daniel Robinson
 */

package com.example.manu

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import kotlinx.android.synthetic.main.activity_quiz_results.*

/**
 * Controls and displays the quiz results screen.
 */
class QuizResultsActivity : AppCompatActivity() {

    private lateinit var buttonPress: Animation

    /**
     * This is run when the class is instantiated. It loads the quiz results activity and presents the results.
     *
     * @param Bundle Saves information between separate loads of this activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_results)

        // Hide the navigation and status bars.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowCompat.setDecorFitsSystemWindows(window, true)  // Places the layout outside the navbar and status bar.

        // Collect the results that the quiz will pass through.
        val score: Int = intent.getIntExtra("score", -1)
        val totalQuestions: Int =  intent.getIntExtra("totalQuestions", -1)

        text_score.text = "$score / $totalQuestions"

        loadAnimations()

        /*
         * Play again.
         */
        btn_play_again.setOnClickListener {
            btn_play_again.startAnimation(buttonPress)
            var intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }

        /*
         * Go to the menu.
         */
        btn_menu.setOnClickListener {
            btn_menu.startAnimation(buttonPress)
            var intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }
    }

    private fun loadAnimations() {
        buttonPress = AnimationUtils.loadAnimation(this, R.anim.button_press)
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }

}