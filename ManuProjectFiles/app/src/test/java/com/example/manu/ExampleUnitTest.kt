package com.example.manu

import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    /*
    @Test
    fun createQuiz_isCorrect(){
        var qList = mutableListOf<Question>()
        var birdAdapt = BirdAdapter(qList)
        val question = Question(Bird(), 1)
        question.correctBirdObject.chosenBird(1)

        val birdNum = 1

        question.addOption(Bird(birdNum))
        question.addOption(Bird(birdNum))
        question.addOption(Bird(birdNum))

        qList.add(question)
        val bird = Bird(birdNum)

        assertEquals(qList, birdAdapt.createQuizForced(1,1,1))

    }
    */

    /*
    @Test
    fun updateValues_isCorrect(){
        val birdNumber = 0
        val ExpectedOutput = "Kiwi, bird, 0, kiwi, kiwi, no fact"

        val bird = Bird()
        bird.chosenBird(birdNumber)
        bird.updateValues()

        assertEquals(ExpectedOutput, bird.toString())
    }
    */

    /**
     * Checks no individual quiz contains a duplicate question.
     */
    @Test
    fun noDuplicateQuestions() {
        BirdDatabase.compileDatabase()
        var duplicateQuestions: Boolean = false

        for (quiz in 1..10) {
            val questions: ArrayList<QuestionTemp> = QuizGenerator.generateQuiz(QuestionType.PHOTO, 5, 4)

            for (question in questions) {
                var occurrences: Int = 0
                for (compareQuestion in questions) {
                    if (compareQuestion == question)
                        occurrences++
                }
                if (occurrences != 1) {
                    duplicateQuestions = true
                    break
                }
            }

            if (duplicateQuestions)
                break
        }

        assertEquals(false, duplicateQuestions)
    }

    /**
     * Checks no individual question contains a duplicate option.
     */
    @Test
    fun noDuplicateOptions() {
        BirdDatabase.compileDatabase()
        var duplicateOptions: Boolean = false

        for (quiz in 1..10) {
            val questions: ArrayList<QuestionTemp> = QuizGenerator.generateQuiz(QuestionType.PHOTO, 5, 4)

            for (question in questions) {
                for (option in question.getOptions()) {
                    var occurrences: Int = 0
                    for (compareOption in question.getOptions()) {
                        if (compareOption == option)
                            occurrences++
                    }
                    if (occurrences != 1) {
                        duplicateOptions = true
                        break
                    }
                }

                if (duplicateOptions)
                    break
            }

            if (duplicateOptions)
                break
        }

        assertEquals(false, duplicateOptions)
    }

    /**
     * Checks the answer to a question is always in the option list, and that the index of the answer in that list is
     * correct.
     */
    @Test
    fun answerInOptions() {
        BirdDatabase.compileDatabase()

        for (quiz in 1..10) {
            val questions: ArrayList<QuestionTemp> = QuizGenerator.generateQuiz(QuestionType.PHOTO, 5, 4)

            for (question in questions) {
                val answer: String = BirdDatabase.getNameUsingResourceId(question.getQuestionResourceId())
                val answerIndex: Int = question.getAnswerIndex()
                if (question.getOptions()[answerIndex] != answer) {
                    assertEquals(true, false)  // Force a failed test.
                    return
                }
            }
        }

        assertEquals(true, true)  // Since no tests gave any problems, force a successful test.
    }
}