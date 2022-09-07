package com.example.manu

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.WindowCompat
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.info_graphic_activity.*
import kotlinx.android.synthetic.main.quiz_options.*


/**
 * Runs and displays the main menu.
 */
class QuizOptions : AppCompatActivity() {

    private lateinit var gestureDetector: GestureDetectorCompat
    private lateinit var buttonPress: Animation

    /**
     * This is run when the class is instantiated. Hands control to either the infographic screen
     * or the quiz menu on a button press.
     * Button names need to be changed.
     *
     * @param Bundle Saves information between separate loads of this activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_options)

        gestureDetector = GestureDetectorCompat(this, GestureListener())


        // Hide the navigation and status bars.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowCompat.setDecorFitsSystemWindows(window, true)  // Places the layout outside the navbar and status bar.

        loadAnimations()

        // Set up all the buttons.
        btn_image.setOnClickListener {
            btn_image.startAnimation(buttonPress)
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        btn_sound.setOnClickListener {
            btn_sound.startAnimation(buttonPress)
            // Do nothing.
        }

        btn_to_maori.setOnClickListener {
            btn_to_maori.startAnimation(buttonPress)
            // Do nothing.
        }

        btn_to_eng.setOnClickListener {
            btn_to_eng.startAnimation(buttonPress)
            // Do nothing.
        }

        btn_back_option.setOnClickListener{
            var intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

    }

    /**
     * Loads and stores the animations.
     */
    private fun loadAnimations() {
        buttonPress = AnimationUtils.loadAnimation(this, R.anim.button_press)
    }

    /**
     * Executes code for swiping between screens.
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (gestureDetector.onTouchEvent(event)) {
            true
        }
        else{
            super.onTouchEvent(event)
        }
    }

    /**
     * Checks if the touch is a left or right swipe - is executed from onTouchEvent
     */
    inner class GestureListener : GestureDetector.SimpleOnGestureListener()
    {

        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(downEvent: MotionEvent?, moveEvent: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            var diffX = moveEvent?.x?.minus(downEvent!!.x) ?: 0.0F
            var diffY = moveEvent?.y?.minus(downEvent!!.y) ?: 0.0F

            return if(Math.abs(diffX) > Math.abs(diffY)) {
                // this is a left or right swipe
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0 ){
                        // right swipe
                        this@QuizOptions.onSwipeRight()
                    } else {
                        // left swipe
                    }
                    true
                } else {
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            } else {
                // this is a top or bottom swipe
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        // swipe top
                    } else {
                        // swipe bottom
                    }
                    true
                } else {
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }
        }
    }

    /**
     * Executes code for a right gesture going right to enter infographics.
     */
    private fun onSwipeRight() {
        var intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }

}