/**
 * This script is an exact copy of a view controlling Kotlin class (like the default MainActivity.kt
 * script class). The lines with comments saying "ADDED: " are in addition to the default code.
 */

package com.example.manu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log  // ADDED: Allows you to print debugging statements.
import kotlinx.android.synthetic.main.test.*
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader


/**
 * An example script for how to start programming a content view. Disregarding the code with a comment saying
 * "ADDED:...", such a script must do the following: be in the specified package, import the specified modules, extend
 * the specified class, and contain the specified function with its specified instructions.
 * specified
 */
class HelloWorld : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // Calls the superclass' creation method to inherit its functionality.
        setContentView(R.layout.test)  // Change "activity_main" to your desired view.
        Log.d("HelloWorld", "Well, that worked!")  // ADDED: Print text to prove it works.



        createBirdBtn.setOnClickListener {
            var questions = mutableListOf<Question>()
            var birdadapter = BirdAdapter(questions)

            birdadapter.createQuiz(this,1,1)
            ListToStringTV.setText(birdadapter.toString())

            IVbirdpics.setImageResource(questions[0].correctBirdObject.getPhotoResourceID())
        }
    }
}