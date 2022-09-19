/* Jackson North 16/09/2022 */

package com.example.manu

import android.content.Context
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.file.Paths


class StatsAdapter {

    companion object{
        private lateinit var stats: MutableList<Stats>

        private val shortQuiz = 5
        private val mediumQuiz = 10
        private val longQuiz = 20

        fun compileStats(questionType: QuestionType, numQuestions: Int, numRight: Int, totalPlayed: Int, context: Context){
            stats = mutableListOf<Stats>()


            val inputStream = context.assets.open("bird-data.csv")
            val input = InputStreamReader(inputStream, "UTF-8")
            val reader = BufferedReader(input)

            reader.forEachLine {
                val i = 0
                val line = it.split(",")
            }
        }

        fun updateValues(context: Context, numQuestions: Int, numCorrect: Int, questionType: QuestionType){

        }


    }
}