/**
 * @author Daniel Robinson and Madeline McCane
 */

package com.example.manu

import java.util.Random

/**
 * Generates quizzes from the information in the birds database.
 */
class QuizGenerator {

    /**
     * Generates quizzes from the information in the birds database.
     */
    companion object {

        val maxShuffles: Int = 10

        /**
         * Index of the previous correct option.
         */
        var lastCorrectOptionIndex: Int = -1

        val random = Random()

        /**
         * Generates a quiz from the information in the birds database.
         *
         * @param questionType The type of resource used as a question.
         * @param numQuestions The number of questions in the quiz.
         * @param numOptions The number of options per question.
         *
         * @return A list of quiz questions.
         */
        fun generateQuiz(questionType: QuestionType, numQuestions: Int, numOptions: Int): ArrayList<Question> {
            lastCorrectOptionIndex = random.nextInt(numOptions)  // No previously correct option, hence randomise.
            var questions: ArrayList<Question> = ArrayList()
            for (shuffle in 0 until random.nextInt(maxShuffles) + 1)
                questions.shuffle()

            var birds: ArrayList<Bird> = BirdDatabase.getBirdsWithResource(questionType)
            var allNames: ArrayList<String> = ArrayList()
            for (bird: Bird in birds) {
                if (questionType == QuestionType.ENGLISH) {
                    allNames.add(bird.getMaoriName())
                } else {
                    allNames.add(bird.getBirdName())
                }
            }

            allNames.shuffle()

            /*
             * Create the questions.
             */
            for (questionIndex in 0 until numQuestions) {
                val bird: Bird = birds[random.nextInt(birds.size)]

                val answer: String
                if (questionType == QuestionType.ENGLISH) {
                    answer = bird.getMaoriName()
                } else {
                    answer = bird.getBirdName()
                }

                var options: ArrayList<String> = arrayListOf(answer)  // One option must be the answer.

                val resourceId: Int
                if (questionType == QuestionType.PHOTO) {
                    resourceId = bird.getPhotoResourceId()
                } else if (questionType == QuestionType.SOUND) {
                    resourceId = bird.getSongResourceId()
                } else if (questionType == QuestionType.ENGLISH) {
                    resourceId = bird.getEnglishNameImageResourceId()
                } else {
                    resourceId = bird.getMaoriNameImageResourceId()
                }
                birds.remove(bird)

                var possibleOptions: ArrayList<String> = ArrayList()

                for (birdName: String in allNames) {
                    if (!birdName.equals(answer))
                        possibleOptions.add(birdName)
                }

                for (shuffle in 0 until random.nextInt(maxShuffles) + 1)
                    possibleOptions.shuffle()

                /*
                 * Extract the options (additional to the answer).
                 */
                for (option in 0 until numOptions-1) {
                    options.add(possibleOptions[option])
                }

                for (shuffle in 0 until random.nextInt(maxShuffles) + 1)
                    options.shuffle()  // Shuffle with the correct answer.

                /*
                    Force the correct option to be in a different place than in the last question.
                 */
                while (options.indexOf(answer) == lastCorrectOptionIndex) {
                    options.shuffle()
                }

                lastCorrectOptionIndex = options.indexOf(answer)
                questions.add(Question(resourceId, options, options.indexOf(answer)))
            }

            return questions
        }

        /**
         * Generates quizzes and calculates the frequency at which each option is the answer.
         *
         * @param quizzes The number of quizzes to run.
         * @param questionsPerQuiz The number of questions per quiz.
         *
         * @return An array of integers where each integer represents the number of times that option was the answer.
         * The integer's index corresponds the option's index.
         */
        fun optionFrequencyTest(quizzes: Int, questionsPerQuiz: Int, optionsPerQuestion: Int): IntArray {
            var optionFrequencies = IntArray(optionsPerQuestion) {0}
            for (quizNum in 1..quizzes) {
                val questions: ArrayList<Question> = generateQuiz(QuestionType.PHOTO,
                    questionsPerQuiz, optionsPerQuestion)
                for (questionNum in 0 until questionsPerQuiz) {
                    optionFrequencies[questions[questionNum].getAnswerIndex()]++
                }
            }

            return optionFrequencies
        }

    }

}