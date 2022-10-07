/**
 * @author Jackson North
 * @author Daniel Robinson
 */

package com.example.manu

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import java.io.*
import kotlin.math.roundToInt

/**
 * Handles retrieving and editing the player's quiz statistics on a non-volatile medium.
 */
class StatsAdapter {

    /**
     * Handles retrieving and editing the player's quiz statistics on a non-volatile medium.
     */
    companion object {

        private lateinit var stats: MutableList<Stats>

        /**
         * A function that checks if a file exists on the users phone, if the file doesn't exist make one and compile
         * a list from the file in our directory. If the file exists the function just calls compileStats to create a
         * list from the given file.
         *
         * @param context The app context which makes file handling possible.
         */
        fun makeFile(context: Context) {
            val path = context.filesDir
            val fileName = "stats.txt"
            var file = File(path, fileName)
            var fileExists = file.exists()

            if (fileExists) {
                compileStats(context)
            } else {
                try {
                    stats = mutableListOf<Stats>()

                    lateinit var questionType: QuestionType

                    val inputStream = context.assets.open("stats.txt")
                    val size: Int = inputStream.available()
                    val buffer = ByteArray(size)
                    inputStream.read(buffer).toString()

                    val string = String(buffer)

                    string.split("\n").forEach {
                        val line = it.split(",")

                        if (line[0] == "PHOTO") {
                            questionType = QuestionType.PHOTO
                        } else if (line[0] == "SOUND") {
                            questionType = QuestionType.SOUND
                        } else if (line[0] == "MAORI") {
                            questionType = QuestionType.MAORI
                        } else if (line[0] == "ENGLISH") {
                            questionType = QuestionType.ENGLISH
                        } else if (line[0] == "ALL") {
                            questionType = QuestionType.ALL
                        }

                        if (line[0] != "") {
                            stats.add(Stats(questionType, line[1].trim().toInt(), line[2].trim().toInt(), line[3].trim().toInt()))
                        }
                    }
                    saveToFile(context)
                } catch(e: IOException) {
                    println(e)
                }
            }
        }


        /**
         * A function to compile a list of stats from an existing file.
         *
         * @param context The app context which makes file handling possible.
         */
        private fun compileStats(context: Context) {
            stats = mutableListOf<Stats>()
            lateinit var questionType: QuestionType

            val path = context.filesDir
            val inputStream = File(path, "stats.txt")
            val reader = FileInputStream(inputStream)
            var content = ByteArray(inputStream.length().toInt())

            reader.read(content)

            var fileContent = String(content)

            fileContent.split("\n").forEach {
                val line = it.split(",")
                if (line[0] == "PHOTO") {
                    questionType = QuestionType.PHOTO
                } else if (line[0] == "SOUND") {
                    questionType = QuestionType.SOUND
                } else if (line[0] == "MAORI") {
                    questionType = QuestionType.MAORI
                } else if (line[0] == "ENGLISH") {
                    questionType = QuestionType.ENGLISH
                } else if (line[0] == "ALL") {
                    questionType = QuestionType.ALL
                }

                if (line[0] != "") {
                    stats.add(Stats(questionType, line[1].toInt(), line[2].toInt(), line[3].toInt()))
                }
            }
        }

        /**
         * Updates the information of the user stats from after a quiz was finished.
         *
         * @param context The app context to handle files.
         * @param questionType The type of question to update.
         * @param numQuestions The number to increase the number of questions played.
         * @param numCorrect The amount of correct questions to increase the numCorrect value.
         */
        fun updateValues(context: Context, questionType: QuestionType, numQuestions: Int, numCorrect: Int) {
            if (questionType == QuestionType.PHOTO){
                stats[0].updateNumRight(numCorrect)
                stats[0].updateTotalPlayed(numQuestions)
                val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                val editor = preferences.edit()
                val photoGames = preferences.getInt("photoQuizzesPlayed", 0)
                val photoCorrect = preferences.getInt("photoQuizQuestionsCorrect", 0)
                var playerStats = getPlayerStats(context)
                playerStats[0] = playerStats[0] + 1
                playerStats[1] = playerStats[1] + numCorrect
                editor.putInt("photoQuizzesPlayed", photoGames + 1).toString()
                editor.putInt("photoQuizQuestionsCorrect", photoCorrect + numCorrect).toString()
                editor.commit()
            } else if(questionType == QuestionType.SOUND) {
                stats[1].updateNumRight(numCorrect)
                stats[1].updateTotalPlayed(numQuestions)

            } else if(questionType == QuestionType.MAORI) {
                stats[2].updateNumRight(numCorrect)
                stats[2].updateTotalPlayed(numQuestions)
            } else if(questionType == QuestionType.ENGLISH) {
                stats[3].updateNumRight(numCorrect)
                stats[3].updateTotalPlayed(numQuestions)
            }

            stats[4].updateNumRight(numCorrect)
            stats[4].updateTotalPlayed(numQuestions)

            saveToFile(context)
        }

        /**
         * A function to save all the information in the stats list to a file.
         *
         * @param context The app context to handle the file management.
         */
        fun saveToFile(context: Context) {
            var path = context.filesDir

            try {
                val writer = FileOutputStream(File(path, "stats.txt"))
                stats.forEach {
                    writer.write(it.toString().toByteArray())
                }
                writer.close()
            } catch(e: IOException) {
                e.printStackTrace()
            }
        }

        /**
         * Resets the values of the user stats within the file.
         *
         * @param context The app context to handle the file management.
         */
        fun resetValues(context: Context) {
            stats.forEach {
                it.resetValues()
            }

            saveToFile(context)
        }

        /**
         * Returns the stats regarding the inputted question type.
         *
         * @param questionTypeIn the question type stats being retrieved.
         *
         * @return A Stats object containing data on the desired QuestionType (i.e., desired quiz type).
         */
        fun getStatsBasedOnType(questionTypeIn: QuestionType): Stats {
            lateinit var statsOut: Stats

            stats.forEach {
                if (it.getQuestionType() == questionTypeIn) {
                    statsOut = it
                }
            }

            return statsOut
        }

        /**
         * Retrieves and returns a list of the player's stats.
         *
         * @param context The AppCompatActivity context calling this function.
         *
         * @return A list containing the player's stats. The list contains eight entries to be read in pairs. Each pair
         * contains the number of quizzes played, and the total number of questions answered correctly. There are four
         * pairs, one for each quiz type.
         */
        fun getPlayerStats(context: Context): ArrayList<Int> {
            var playerStatsInts: ArrayList<Int> = ArrayList(8)
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)

            playerStatsInts.add(preferences.getInt("numPhotoQuizzesPlayed", 0))
            playerStatsInts.add(preferences.getInt("numPhotoQuestionsCorrect", 0))
            playerStatsInts.add(preferences.getInt("numSoundQuizzesPlayed", 0))
            playerStatsInts.add(preferences.getInt("numSoundQuestionsCorrect", 0))
            playerStatsInts.add(preferences.getInt("numEnglishQuizzesPlayed", 0))
            playerStatsInts.add(preferences.getInt("numEnglishQuestionsCorrect", 0))
            playerStatsInts.add(preferences.getInt("numMaoriQuizzesPlayed", 0))
            playerStatsInts.add(preferences.getInt("numMaoriQuestionsCorrect", 0))

            return playerStatsInts
        }

        /**
         * Call this function when you complete a quiz. It takes a quiz score and merges it with the current player
         * quiz stats.
         *
         * @param context The AppCompatActivity context calling this function.
         * @param quizType The type of quiz completed.
         * @param score The score attained during the quiz.
         */
        fun submitScore(context: Context, quizType: QuestionType, score: Int) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = preferences.edit()

            if (quizType == QuestionType.PHOTO) {
                val oldTotalPlayed = preferences.getInt("numPhotoQuizzesPlayed", 0)
                val oldTotalCorrect = preferences.getInt("numPhotoQuestionsCorrect", 0)
                editor.putInt("numPhotoQuizzesPlayed", oldTotalPlayed + 1)
                editor.putInt("numPhotoQuestionsCorrect", oldTotalCorrect + score)
            } else if (quizType == QuestionType.SOUND) {
                val oldTotalPlayed = preferences.getInt("numSoundQuizzesPlayed", 0)
                val oldTotalCorrect = preferences.getInt("numSoundQuestionsCorrect", 0)
                editor.putInt("numSoundQuizzesPlayed", oldTotalPlayed + 1)
                editor.putInt("numSoundQuestionsCorrect", oldTotalCorrect + score)
            } else if (quizType == QuestionType.ENGLISH) {
                val oldTotalPlayed = preferences.getInt("numEnglishQuizzesPlayed", 0)
                val oldTotalCorrect = preferences.getInt("numEnglishQuestionsCorrect", 0)
                editor.putInt("numEnglishQuizzesPlayed", oldTotalPlayed + 1)
                editor.putInt("numEnglishQuestionsCorrect", oldTotalCorrect + score)
            } else if (quizType == QuestionType.MAORI) {
                val oldTotalPlayed = preferences.getInt("numMaoriQuizzesPlayed", 0)
                val oldTotalCorrect = preferences.getInt("numMaoriQuestionsCorrect", 0)
                editor.putInt("numMaoriQuizzesPlayed", oldTotalPlayed + 1)
                editor.putInt("numMaoriQuestionsCorrect", oldTotalCorrect + score)
            }

            editor.commit()
        }

        /**
         * Resets all stored player stats values.
         *
         * @param context The AppCompatActivity context calling this function.
         */
        fun resetStats(context: Context) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = preferences.edit()

            editor.putInt("numPhotoQuizzesPlayed", 0)
            editor.putInt("numPhotoQuestionsCorrect", 0)
            editor.putInt("numSoundQuizzesPlayed", 0)
            editor.putInt("numSoundQuestionsCorrect", 0)
            editor.putInt("numEnglishQuizzesPlayed", 0)
            editor.putInt("numEnglishQuestionsCorrect", 0)
            editor.putInt("numMaoriQuizzesPlayed", 0)
            editor.putInt("numMaoriQuestionsCorrect", 0)

            editor.commit()
        }

        /**
         * Rounds a Float to the specified number of decimal places.
         *
         * @param float The Float to round.
         * @param decimals The number of decimals to retain.
         *
         * @return The provided Float rounded to the specified number of decimal places.
         */
        fun round(float: Float, decimals: Int): Double {
            Log.d("StatsAdapter", float.toString())
            var multiplier = 10.0

            for (i in 2..decimals) {
                multiplier *= 10.0
            }

            return (float * multiplier).roundToInt() / multiplier
        }

    }

}