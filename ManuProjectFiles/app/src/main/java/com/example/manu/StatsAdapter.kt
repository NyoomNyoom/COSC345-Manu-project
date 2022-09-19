/* Jackson North 16/09/2022 */

package com.example.manu

import android.content.Context
import android.util.Log
import java.io.*
import java.nio.file.Paths


class StatsAdapter {

    companion object{
        private lateinit var stats: MutableList<Stats>

        private val shortQuiz = 5
        private val mediumQuiz = 10
        private val longQuiz = 20

        fun compileStats(context: Context){
            stats = mutableListOf<Stats>()
            lateinit var questionType: QuestionType


            val inputStream = context.assets.open("stats.txt")
            val input = InputStreamReader(inputStream, "UTF-8")
            val reader = BufferedReader(input)

            reader.forEachLine {
                val i = 0
                val line = it.split(",")

                if(line[0] == "0"){
                    questionType = QuestionType.PHOTO
                }else if(line[0] == "1"){
                    questionType = QuestionType.SOUND
                }else if(line[0] == "2"){
                    questionType = QuestionType.MAORI
                }else if(line[0] == "3"){
                    questionType = QuestionType.ENGLISH
                }else if(line[0] == "4"){
                    questionType = QuestionType.ALL
                }

                stats.add(Stats(questionType, line[1].toInt(), line[2].toInt(), line[3].toInt()))
            }
            //Log.d("StatsAdapter", "Stats: "+ stats[0].toString())
        }

        fun updateValues(context: Context, numQuestions: Int, numCorrect: Int, questionType: QuestionType){
            if(questionType == QuestionType.PHOTO){
                if (numQuestions == shortQuiz){
                    stats[0].updateNumRight(numCorrect)
                    stats[0].updateTotalPlayed(numQuestions)
                }else if(numQuestions == mediumQuiz){
                    stats[1].updateNumRight(numCorrect)
                    stats[1].updateTotalPlayed(numQuestions)
                }else if(numQuestions == longQuiz){
                    stats[2].updateNumRight(numCorrect)
                    stats[2].updateTotalPlayed(numQuestions)
                }
            }else if(questionType == QuestionType.SOUND){
                if (numQuestions == shortQuiz){
                    stats[3].updateNumRight(numCorrect)
                    stats[3].updateTotalPlayed(numQuestions)
                }else if(numQuestions == mediumQuiz){
                    stats[4].updateNumRight(numCorrect)
                    stats[4].updateTotalPlayed(numQuestions)
                }else if(numQuestions == longQuiz){
                    stats[5].updateNumRight(numCorrect)
                    stats[5].updateTotalPlayed(numQuestions)
                }
            }else if(questionType == QuestionType.MAORI){
                if (numQuestions == shortQuiz){
                    stats[6].updateNumRight(numCorrect)
                    stats[6].updateTotalPlayed(numQuestions)
                }else if(numQuestions == mediumQuiz){
                    stats[7].updateNumRight(numCorrect)
                    stats[7].updateTotalPlayed(numQuestions)
                }else if(numQuestions == longQuiz){
                    stats[8].updateNumRight(numCorrect)
                    stats[8].updateTotalPlayed(numQuestions)
                }
            }else if(questionType == QuestionType.ENGLISH){
                if (numQuestions == shortQuiz){
                    stats[9].updateNumRight(numCorrect)
                    stats[9].updateTotalPlayed(numQuestions)
                }else if(numQuestions == mediumQuiz){
                    stats[10].updateNumRight(numCorrect)
                    stats[10].updateTotalPlayed(numQuestions)
                }else if(numQuestions == longQuiz){
                    stats[11].updateNumRight(numCorrect)
                    stats[11].updateTotalPlayed(numQuestions)
                }
            }else if(questionType == QuestionType.ALL){
                if (numQuestions == shortQuiz){
                    stats[12].updateNumRight(numCorrect)
                    stats[12].updateTotalPlayed(numQuestions)
                }else if(numQuestions == mediumQuiz){
                    stats[13].updateNumRight(numCorrect)
                    stats[13].updateTotalPlayed(numQuestions)
                }else if(numQuestions == longQuiz){
                    stats[14].updateNumRight(numCorrect)
                    stats[14].updateTotalPlayed(numQuestions)
                }
            }

            saveToFile(context)
        }

        fun saveToFile(context: Context){
            try{
                Log.d("StatsAdapter", "Finding file.")
                val writer = OutputStreamWriter(context.openFileOutput("stats.txt", Context.MODE_PRIVATE))
                Log.d("StatsAdapter", "Writing.")
                writer.write("Hello")
                writer.flush()
                writer.close()
            } catch (e: IOException) {
                Log.d("StatsAdapter", "" + e)
            }
        }
    }
}