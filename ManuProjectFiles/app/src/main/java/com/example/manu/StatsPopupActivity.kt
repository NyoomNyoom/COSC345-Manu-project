/**
 * @author Daniel Robinson, Madeline McCane
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
import kotlinx.android.synthetic.main.return_to_menu_popup.*

/**
 * Controls the "Return to Menu?" popup that shows when you press the 'X' button during the quiz.
 */
class StatsPopupActivity : Activity() {

    private lateinit var buttonPress: Animation

    /**
     * This is run when the class is instantiated. It sets up the "Return to Menu?" layout.
     *
     * @param Bundle Saves information between separate loads of this activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.are_you_sure_popup)
        //intent.put

        buttonPress = AnimationUtils.loadAnimation(this, R.anim.button_press)

        // Hide the navigation and status bars.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowCompat.setDecorFitsSystemWindows(window, true)  // Places the layout outside the navbar and status bar.

        btn_yes.setOnClickListener {
            yes_closePopup()
        }
        btn_no.setOnClickListener { no_closePopup() }
    }

    /**
     * Animates the no button press and closes this popup.
     */
    private fun no_closePopup() {
        btn_no.startAnimation(buttonPress)
        AudioManager.resumeAudio()
        finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)  // Must occur after we close the popup.
    }

    private fun yes_closePopup() {
        StatsAdapter.resetStats(this)
        btn_yes.startAnimation(buttonPress)
        AudioManager.resumeAudio()
        val intent = Intent(this, StatsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out)  // Must occur after we close the popup.
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }

}