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

        val maxShuffles: Int = 10

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
            for (shuffle in 0 until Random.nextInt(1, maxShuffles))
                questions.shuffle()

            /*
             * Generate a "photo to name" quiz.
             */
            if (questionType == QuestionType.PHOTO) {
                var birds: ArrayList<BirdTemp> = BirdDatabase.getBirdsWithResource(QuestionType.PHOTO)

                var allNames: ArrayList<String> = ArrayList()
                for (bird: BirdTemp in birds) {
                    allNames.add(bird.getBirdName())
                }

                /*
                 * Create the questions.
                 */
                for (questionIndex in 0 until numQuestions) {
                    val bird: BirdTemp = birds[Random.nextInt(0, birds.size)]
                    val answer: String = bird.getBirdName()
                    var options: ArrayList<String> = arrayListOf(answer)  // One option must be the answer.
                    val photoResourceId: Int = bird.getPhotoResourceId()
                    birds.remove(bird)

                    var possibleOptions: ArrayList<String> = ArrayList()

                    for (birdName: String in allNames) {
                        if (!birdName.equals(answer))
                            possibleOptions.add(birdName)
                    }

                    for (shuffle in 0 until Random.nextInt(1, maxShuffles))
                        possibleOptions.shuffle()

                    /*
                     * Extract the options (additional to the answer).
                     */
                    for (option in 0 until numOptions-1) {
                        options.add(possibleOptions[option])
                    }

                    for (shuffle in 0 until Random.nextInt(1, maxShuffles))
                        options.shuffle()  // Shuffle with the correct answer.

                    questions.add(QuestionTemp(photoResourceId, options, options.indexOf(answer)))
                }
            }

            return questions
        }

    }

}