/**
 * @author Madeline McCane
 */

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
import kotlinx.android.synthetic.main.credits.*

/**
 * Loads and controls the credits screen.
 */
class CreditActivity : AppCompatActivity() {

    private lateinit var buttonPress: Animation
    private var mediaPlayer = MediaPlayer()

    /**
     * This is run when the class is instantiated. It loads and sets up the credits screen.
     *
     * @param Bundle Saves information between separate loads of this activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.credits)

        mediaPlayer = MediaPlayer.create(this, R.raw.menu_ambience)
        mediaPlayer.start()

        // Hide the navigation and status bars.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowCompat.setDecorFitsSystemWindows(window, true)  // Places the layout outside the navbar and status bar.

        loadButtonPressAnimation()

        btn_quit.setOnClickListener {
            mediaPlayer.pause()
            btn_quit.startAnimation(buttonPress)
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom)
            finish()
        }
    }

    private fun loadButtonPressAnimation() {
        buttonPress = AnimationUtils.loadAnimation(this, R.anim.button_press)
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }
}