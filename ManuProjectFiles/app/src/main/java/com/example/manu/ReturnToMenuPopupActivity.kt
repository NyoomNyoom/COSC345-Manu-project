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
import kotlinx.android.synthetic.main.return_to_menu_popup.*

/**
 * Controls the "Return to Menu?" popup that shows when you press the 'X' button during the quiz.
 */
class ReturnToMenuPopupActivity : Activity() {

    private lateinit var buttonPress: Animation

    /**
     * This is run when the class is instantiated. It sets up the quiz screen and starts the game.
     *
     * @param Bundle Saves information between separate loads of this activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.return_to_menu_popup)

        buttonPress = AnimationUtils.loadAnimation(this, R.anim.button_press)

        // Hide the navigation and status bars.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowCompat.setDecorFitsSystemWindows(window, true)  // Places the layout outside the navbar and status bar.

        btn_yes.setOnClickListener { returnToMenu() }
        btn_no.setOnClickListener { closePopup() }
    }

    /**
     * Animates the button press, closes the quiz, and loads the menu.
     */
    private fun returnToMenu() {
        btn_yes.startAnimation(buttonPress)
        var intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * Animates the button press and closes this popup.
     */
    private fun closePopup() {
        btn_no.startAnimation(buttonPress)
        finish()
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }

}