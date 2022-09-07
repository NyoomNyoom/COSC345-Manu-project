/**
 * @author Daniel Robinson
 */

package com.example.manu

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
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
        /*Log.d("InfographicPopupActivity.txt_bird_name.measuredHeight", txt_bird_name.measuredHeight.toString())
        Log.d("InfographicPopupActivity.txt_bird_name.minLines", txt_bird_name.minLines.toString())
        Log.d("InfographicPopupActivity.txt_bird_name.maxLines", txt_bird_name.maxLines.toString())
        Log.d("InfographicPopupActivity.txt_bird_name.lineCount", txt_bird_name.lineCount.toString())
        Log.d("InfographicPopupActivity.txt_bird_name.lineHeight", txt_bird_name.lineHeight.toString())
        Log.d("InfographicPopupActivity.txt_bird_name.totalPaddingBottom", txt_bird_name.totalPaddingBottom.toString())*/
        //if (txt_bird_name.text.length > 20)
            //txt_bird_fact.setPadding(0, 75,0,0)

        //txt_bird_fact.setPadding(0,txt_bird_name.lineHeight,0,0)
    }

    /**
     * Animates the button press and closes this popup.
     */
    private fun closePopup() {
        btn_close.startAnimation(buttonPress)
        finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)  // Must occur after we close the popup.
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }

    // https://stackoverflow.com/questions/29664993/how-to-convert-dp-px-sp-among-each-other-especially-dp-and-sp
    /*fun dpToPx(dp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics())
            .toInt()

            // float px = sp * getResources().getDisplayMetrics().scaledDensity;
    }*/

}