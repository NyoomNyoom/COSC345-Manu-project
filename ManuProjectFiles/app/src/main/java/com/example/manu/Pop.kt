package com.example.manu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.core.view.WindowCompat
import kotlinx.android.synthetic.main.popup_window.*

class Pop : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_window)

        // Hide the navigation and status bars.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowCompat.setDecorFitsSystemWindows(window, true)  // Places the layout outside the navbar and status bar.

        val dm: DisplayMetrics = DisplayMetrics()
        getWindowManager().getDefaultDisplay().getMetrics(dm)
        val width: Int = dm.widthPixels
        val height: Int = dm.heightPixels

        getWindow().setLayout((width * 0.7).toInt(), (height * 0.5).toInt())

        val params: WindowManager.LayoutParams = getWindow().getAttributes()
        params.gravity = Gravity.CENTER
        params.x = 0
        params.y = -20

        getWindow().setAttributes(params)

        btn_yes.setOnClickListener { returnToMenu() }
        btn_no.setOnClickListener { closePopup() }
    }

    private fun returnToMenu() {
        var intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun closePopup() {
        finish()
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }

}