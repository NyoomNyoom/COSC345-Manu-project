package com.example.manu

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.WindowCompat
import kotlinx.android.synthetic.main.popup_window.*

class Pop : Activity() {

    private lateinit var buttonPress: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_window)

        buttonPress = AnimationUtils.loadAnimation(this, R.anim.button_press)

        // Hide the navigation and status bars.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowCompat.setDecorFitsSystemWindows(window, true)  // Places the layout outside the navbar and status bar.

        btn_yes.setOnClickListener { returnToMenu() }
        btn_no.setOnClickListener { closePopup() }
    }

    private fun returnToMenu() {
        btn_yes.startAnimation(buttonPress)
        var intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

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