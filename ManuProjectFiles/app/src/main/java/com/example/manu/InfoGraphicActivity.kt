/* Will Rushton 12/08/2022 */

package com.example.manu

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.GestureDetector
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.PopupWindow
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import kotlinx.android.synthetic.main.activity_menu.*


/* Honestly I don't know yet...
 */
class InfoGraphicActivity : AppCompatActivity() {

    private lateinit var gestureDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_graphic_activity)

        gestureDetector = GestureDetectorCompat(this, GestureListener())

        val allButtons: ArrayList<View>
        allButtons = (findViewById<View>(R.id.scrollView1) as ScrollView).touchables // Size is 48 (birds 47 + 1)
        val size = allButtons.size // Checking how many items there are.
        //Log.d("Layout Check", size.toString())

        for(i in 0 until size){
            // Get bird image and add it to button
            allButtons[i].setBackgroundColor(Color.RED) // Sets the backgroundColor
            allButtons[i].setOnClickListener {
                // Create a pop up window and pass it the text information
                allButtons[i].setBackgroundColor(Color.BLUE)

                val popupWindow = PopupWindow(this)
                val popupView = layoutInflater.inflate(R.layout.info_graphic_popup, null)
                popupWindow.contentView = popupView
                popupWindow.isOutsideTouchable = true
                popupWindow.isFocusable = true
                popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0)
            }
        }
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        var handled = super.dispatchTouchEvent(ev)
        handled = gestureDetector.onTouchEvent(ev!!)
        return handled
    }

    /**
     * Disables any effects of the Android system's back button.
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
     * Disables any effects of the Android system's back button.
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
                        this@InfoGraphicActivity.onSwipeRight()
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
     * Disables any effects of the Android system's back button.
     */
    private fun onSwipeRight() {
        var intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }
}