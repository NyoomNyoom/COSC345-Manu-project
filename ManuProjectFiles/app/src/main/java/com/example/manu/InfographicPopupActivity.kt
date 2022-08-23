/**
 * @author Daniel Robinson
 */

package com.example.manu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.WindowCompat
import kotlinx.android.synthetic.main.activity_infographic_popup.*

/**
 * Controls the infographic popup that displays when you click on a bird in the infographics screen.
 */
class InfographicPopupActivity : Activity() {

    private lateinit var buttonPress: Animation

    /**
     * This is run when the class is instantiated. It sets up infographic popup layout.
     *
     * @param Bundle Saves information between separate loads of this activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infographic_popup)

        buttonPress = AnimationUtils.loadAnimation(this, R.anim.button_press)

        // Hide the navigation and status bars.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowCompat.setDecorFitsSystemWindows(window, true)  // Places the layout outside the navbar and status bar.

        btn_close.setOnClickListener { closePopup() }

        txt_bird_name.text = intent.getStringExtra("birdName")
        txt_bird_fact.text = intent.getStringExtra("birdFact")
    }

    /**
     * Animates the button press and closes this popup.
     */
    private fun closePopup() {
        btn_close.startAnimation(buttonPress)
        finish()
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }

}