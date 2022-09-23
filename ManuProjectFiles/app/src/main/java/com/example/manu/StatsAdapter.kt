/* Jackson North 16/09/2022 */

package com.example.manu

import android.content.Context
import android.util.Log
import java.io.*


class StatsAdapter {

    companion object{
        private lateinit var stats: MutableList<Stats>

        fun makeFile(context: Context){
            val path = context.filesDir
            val fileName = "stats.txt"
            var file = File(path, fileName)
            var fileExists = file.exists()

            if(fileExists){
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
                }catch(e: IOException){

                }
            }
        }

        private fun compileStats(context: Context){
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


        fun updateValues(context: Context, questionType: QuestionType, numQuestions: Int, numCorrect: Int){


            if(questionType == QuestionType.PHOTO){
                stats[0].updateNumRight(numCorrect)
                stats[0].updateTotalPlayed(numQuestions)
            }else if(questionType == QuestionType.SOUND){
                stats[1].updateNumRight(numCorrect)
                stats[1].updateTotalPlayed(numQuestions)

            }else if(questionType == QuestionType.MAORI){
                stats[2].updateNumRight(numCorrect)
                stats[2].updateTotalPlayed(numQuestions)
            }else if(questionType == QuestionType.ENGLISH){
                stats[3].updateNumRight(numCorrect)
                stats[3].updateTotalPlayed(numQuestions)
            }

            stats[4].updateNumRight(numCorrect)
            stats[4].updateTotalPlayed(numQuestions)

            saveToFile(context)
        }

        fun saveToFile(context: Context){
            var path = context.filesDir

            try {
                val writer = FileOutputStream(File(path, "stats.txt"))
                stats.forEach {
                    writer.write(it.toString().toByteArray())
                }
                writer.close()
            }catch(e: Exception){
                e.printStackTrace()
            }
        }

        fun resetValues(context: Context){
            stats.forEach {
                it.resetValues()
            }

            saveToFile(context)
        }

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
         * Encrypts or decrypts an input string according to a key held in the function.
         *
         * @param input The string to encrypt/decrypt.
         * @param direction 1 to encrypt, or -1 to decrypt.
         *
         * @return The encrypted or decrypted input.
         */
        private fun cipher(input: String, direction: Int): String {
            // Only accept the encryption or decryption direction because no other directions exist.
            if (!(direction == 1 || direction == -1)) {
                throw IllegalArgumentException()
            }

            val key = "345"
            val strength = 3  // The amount to multiply digits in the key by when altering characters.
            var output = ""

            /*
                For each character in the input, find the corresponding character in the key, turn the key's character
                into a digit, multiply that digit by the strength, and add this value to the input's character. This is
                the new character for the ciphertext. The direction can be 1 (to perform the operation) or -1 (to undo
                the operation).
             */
            for (index in input.indices) {
                output += input[index] + (key[index % key.length].digitToInt() * direction * strength)
            }

            return output
        }

        /**
         * Encrypts a string.
         *
         * @param input The string to encrypt.
         *
         * @return The encrypted string.
         */
        fun encrypt(input: String): String {
            return cipher(input, 1)
        }

        /**
         * Decrypts a string.
         *
         * @param input The string to decrypt.
         *
         * @return The decrypted string.
         */
        fun decrypt(input: String): String {
            return cipher(input, -1)
        }
    }
}