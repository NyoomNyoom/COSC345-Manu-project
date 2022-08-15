/* Will Rushton 12/08/2022 */

package com.example.manu

import android.graphics.Color
import android.util.Log
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*

/* Honestly I don't know yet...
 */
class InfoGraphicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_graphic_activity)

        val allButtons: ArrayList<View>
        allButtons = (findViewById<View>(R.id.scrollView1) as ScrollView).touchables // Size is 48 (birds 47 + 1)
        val size = allButtons.size // Checking how many items there are.
        //Log.d("Layout Check", size.toString())

        for(i in 0 until size){
            //Get bird image and add it to button
            allButtons[i].setBackgroundColor(Color.RED) // Sets the backgroundC
            allButtons[i].setOnClickListener{
                // Create a pop up window and pass it the text information
                finish()
            }
        }
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }
}