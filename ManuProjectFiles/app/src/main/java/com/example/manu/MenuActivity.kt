/**
 * @author Madeline McCane
 */

package com.example.manu

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_menu.*
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.WindowCompat
import kotlinx.android.synthetic.main.activity_quiz.*

/**
 * Runs and displays the main menu.
 */
class MenuActivity : AppCompatActivity() {

    private lateinit var gestureDetector: GestureDetectorCompat
    private lateinit var buttonPress: Animation
    private var soundFlag: Boolean = false

    /**
     * This is run when the class is instantiated. Hands control to either the infographic screen
     * or the quiz menu on a button press.
     * Button names need to be changed.
     *
     * @param Bundle Saves information between separate loads of this activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)  // Set the layout to the menu layout.

        // Hide the navigation and status bars.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowCompat.setDecorFitsSystemWindows(window, true)  // Places the layout outside the navbar and status bar.

        gestureDetector = GestureDetectorCompat(this, GestureListener())
        loadAnimations()

        soundFlag = intent.getBooleanExtra("soundFlag", false)
        if (soundFlag == false) {
            AudioManager.playAudio(this, R.raw.menu_ambience)
        }

        btn_play.setOnClickListener {
            btn_play.startAnimation(buttonPress)
            var intent = Intent(this, QuizOptionsActivity::class.java)
            intent.putExtra("soundFlag", true)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }

        btn_infographics.setOnClickListener {
            btn_infographics.startAnimation(buttonPress)
            val intent = Intent(this, InfoGraphicActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            finish()
        }

        btn_statistics.setOnClickListener {
            btn_statistics.startAnimation(buttonPress)
            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }

        btn_credits.setOnClickListener{
            btn_credits.startAnimation(buttonPress)
            val intent = Intent(this, CreditActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }

        btn_help.setOnClickListener{
            btn_help.isClickable = false

            btn_help.startAnimation(buttonPress)
            val intent = Intent(this, HintPopupActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

            object : CountDownTimer(300, 100) {

                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    btn_help.isClickable = true
                }
            }.start()
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
     *
     * @param event The MotionEvent generated when the user touches/swipes the screen.
     *
     * @return True if the touch event was a gesture, or the value of the super function otherwise.
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (gestureDetector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    /**
     * Checks if the touch is a left or right swipe - is executed from onTouchEvent.
     */
    inner class GestureListener : GestureDetector.SimpleOnGestureListener()
    {
        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        /**
         * Called when a fling is detected. This performs the calculations to decide whether the fling is an acceptable
         * gesture to change screens.
         *
         * @param downEvent Used to calculate the x and y movements of the swipe.
         * @param moveEvent Reports object movement. Hold either absolute or relative movements and other data,
         * depending on the type of device.
         * @param velocityX The velocity in the left and right direction of the screen (in portrait mode).
         * @param velocityY The velocity in the up and down direction of the screen (in portrait mode).
         *
         * @return True if the fling was accepted and acted on as a gesture, or false if it was not.
         */
        override fun onFling(downEvent: MotionEvent?, moveEvent: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            var diffX = moveEvent?.x?.minus(downEvent!!.x) ?: 0.0F
            var diffY = moveEvent?.y?.minus(downEvent!!.y) ?: 0.0F

            return if(Math.abs(diffX) > Math.abs(diffY)) {
                // this is a left or right swipe
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0 ){
                        // right swipe
                        this@MenuActivity.onSwipeRight()
                    } else {
                        // left swipe
                        this@MenuActivity.onSwipeLeft()
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
     * Go to Infographics when screen is swiped right
     */
    private fun onSwipeRight() {
        var intent = Intent(this, InfoGraphicActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        finish()
    }

    /**
     * Go to the quiz options when a left swiping gesture occurs.
     */
    private fun onSwipeLeft() {
        var intent = Intent(this, QuizOptionsActivity::class.java)
        intent.putExtra("soundFlag", true)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        finish()
    }

    /**
     * Pauses the audio when the app is quit or the screen closes.
     */
    override fun onPause() {
        super.onPause()
        AudioManager.pauseAudio()
    }

    /**
     * Resumes audio when the app is opened again.
     */
    override fun onResume() {
        super.onResume()
        AudioManager.resumeAudio()
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }

}
