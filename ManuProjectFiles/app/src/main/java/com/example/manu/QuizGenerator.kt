package com.example.manu

import android.content.res.Resources
import android.util.Log
import kotlin.random.Random

class QuizGenerator {

    companion object {

        fun generateQuiz(questionType: QuestionType, totalQuestions: Int): ArrayList<QuestionTemp> {
            var birdDatabase: ArrayList<BirdTemp> = BirdDatabase.getBirdDatabase()
            var quizQuestions: ArrayList<QuestionTemp> = ArrayList()

            if (questionType == QuestionType.PHOTO) {
                var birdIndex: Int = 0
                var photoBirdDatabase: ArrayList<BirdTemp> = ArrayList()

                /*
                 * Extract all birds with photos.
                 */
                while (birdIndex < birdDatabase.size) {
                    if (birdDatabase[birdIndex].getPhotoResourceId() != Resources.ID_NULL) {
                        photoBirdDatabase.add(birdDatabase[birdIndex])
                    }

                    birdIndex++
                }

                var allOptions: ArrayList<String> = ArrayList()
                for (bird: BirdTemp in photoBirdDatabase) {
                    allOptions.add(bird.getBirdName())
                }

                for (questionIndex in 1..totalQuestions) {
                    var randomBirdIndex: Int = Random.nextInt(0, photoBirdDatabase.size)
                    var options: ArrayList<String> = ArrayList()
                    val photoResourceID: Int = photoBirdDatabase[randomBirdIndex].getPhotoResourceId()

                    val correctAnswer: String = photoBirdDatabase[randomBirdIndex].getBirdName()
                    options.add(correctAnswer)
                    photoBirdDatabase.removeAt(randomBirdIndex)

                    var possibleOptions: ArrayList<String> = ArrayList()

                    for (birdName: String in allOptions) {
                        if (!birdName.equals(correctAnswer))
                        possibleOptions.add(birdName)
                    }

                    possibleOptions.shuffle()

                    options.add(possibleOptions[0])
                    options.add(possibleOptions[1])
                    options.add(possibleOptions[2])

                    options.shuffle()  // Shuffle with the correct answer.

                    quizQuestions.add(QuestionTemp(photoResourceID, options, options.indexOf(correctAnswer)))
                }
            }

            return quizQuestions
        }

    }

}