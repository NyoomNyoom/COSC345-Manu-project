/**
 * @author Daniel Robinson
 */

package com.example.manu

import kotlin.random.Random

/**
 * Generates quizzes from the information in the birds database.
 */
class QuizGenerator {

    companion object {

        /**
         * Generates a quiz from the information in the birds database.
         *
         * @param questionType The type of resource used as a question.
         * @param numQuestions The number of questions in the quiz.
         * @param numOptions The number of options per question.
         *
         * @return A list of quiz questions.
         */
        fun generateQuiz(questionType: QuestionType, numQuestions: Int, numOptions: Int): ArrayList<QuestionTemp> {
            var questions: ArrayList<QuestionTemp> = ArrayList()

            if (questionType == QuestionType.PHOTO) {
                var birds: ArrayList<BirdTemp> = BirdDatabase.getBirdsWithResource(QuestionType.PHOTO)

                var allNames: ArrayList<String> = ArrayList()
                for (bird: BirdTemp in birds) {
                    allNames.add(bird.getBirdName())
                }

                for (questionIndex in 0 until numQuestions) {
                    var randomBirdIndex: Int = Random.nextInt(0, birds.size)
                    val answer: String = birds[randomBirdIndex].getBirdName()
                    var options: ArrayList<String> = arrayListOf(answer)  // One option must be the answer.
                    val photoResourceId: Int = birds[randomBirdIndex].getPhotoResourceId()
                    birds.removeAt(randomBirdIndex)

                    var possibleOptions: ArrayList<String> = ArrayList()

                    for (birdName: String in allNames) {
                        if (!birdName.equals(answer))
                            possibleOptions.add(birdName)
                    }

                    possibleOptions.shuffle()

                    for (option in 0 until numOptions-1) {
                        options.add(possibleOptions[option])
                    }

                    options.shuffle()  // Shuffle with the correct answer.

                    questions.add(QuestionTemp(photoResourceId, options, options.indexOf(answer)))
                }
            }

            return questions
        }

    }

}