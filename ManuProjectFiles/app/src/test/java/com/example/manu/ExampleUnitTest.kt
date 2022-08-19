package com.example.manu

import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val numberQuizzes: Int = 100

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

        for (quiz in 1..numberQuizzes) {
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

        for (quiz in 1..numberQuizzes) {
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

        assertEquals(true, duplicateOptions)
    }

    /**
     * Checks the answer to a question is always in the option list, and that the index of the answer in that list is
     * correct.
     */
    @Test
    fun answerInOptions() {
        BirdDatabase.compileDatabase()

        for (quiz in 1..numberQuizzes) {
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

    /**
     * Checks if the isValid function is working correctly, by parsing a bird that is valid into the function
     * for question type 1.
     */
    @Test
    fun isValid_isCorrect(){
        var qList= mutableListOf<Question>()
        var ba = BirdAdapter(qList)
        val bird = Bird(1)
        assertEquals(true, ba.isValid(1, bird))
    }

    /**
     * Checking if the isValid function is working correctly when parsing in an invalid bird for question type 1.
     */
    @Test
    fun isValid_isIncorrect(){
        var qList= mutableListOf<Question>()
        var ba = BirdAdapter(qList)
        val bird = Bird(2)
        bird.setPicFileName("null")
        assertEquals(false, ba.isValid(1, bird))
    }

    /**
     * Checks if the isValid function is working correctly, by parsing a bird that is valid into the function
     * for question type 2.
     */
    @Test
    fun isValid_isCorrect2(){
        var qList= mutableListOf<Question>()
        var ba = BirdAdapter(qList)
        val bird = Bird(1)
        assertEquals(true, ba.isValid(2, bird))
    }

    /**
     * Checking if the isValid function is working correctly when parsing in an invalid bird for question type 2.
     */
    @Test
    fun isValid_isIncorrect2(){
        var qList= mutableListOf<Question>()
        var ba = BirdAdapter(qList)
        val bird = Bird(2)
        bird.setSongFileName("null")
        assertEquals(false, ba.isValid(2, bird))
    }

    /**
     * Checks if an incorrect question type is given if the code fails properly
     */
    @Test
    fun isValid_isIncorrect3(){
        var qList= mutableListOf<Question>()
        var ba = BirdAdapter(qList)
        val bird = Bird(3)

        assertEquals(false, ba.isValid(3, bird))
    }

    /**
     * Tests Daniel's question class' toString() function.
     */
    @Test
    fun questionTempToString() {
        BirdDatabase.compileDatabase()
        val questions: ArrayList<QuestionTemp> = QuizGenerator.generateQuiz(QuestionType.PHOTO, 5, 4)
        val toString: String = questions[0].toString()
        val makeString: String = questions[0].getQuestionResourceId().toString() + ", " +
                questions[0].getOptions().toString() + ", " + questions[0].getAnswerIndex().toString()
        assertEquals(true, toString == makeString)
    }

    /**
     * A test to check the getBirdName() function.
     */
    @Test
    fun getBirdName_isCorrect(){
        val bird = BirdTemp("Bellbird", R.drawable.bird_bellbird, "")

        assertEquals("Bellbird", bird.getBirdName())
    }

    /**
     * A test to check if the getPhotoResourceID function is working correctly.
     */
    @Test
    fun getPhotoResourceID_isCorrect(){
        val bird = BirdTemp("Bellbird", R.drawable.bird_bellbird, "")

        assertEquals(R.drawable.bird_bellbird, bird.getPhotoResourceId())
    }

    /**
     * A test to check if the getFunFact function is working properly.
     */
    @Test
    fun getFunFact_isCorrect(){
        val bird = BirdTemp("Bellbird", R.drawable.bird_bellbird, "")

        assertEquals("", bird.getFunFact())
    }

    /**
     * A test to check if the toString method for birdTemp is working.
     */
    @Test
    fun birdTempToString_isCorrect(){
        val bird = BirdTemp("Bellbird", R.drawable.bird_bellbird, "")
        val birdName = "Bellbird"
        val photoResourceID = R.drawable.bird_bellbird

        assertEquals("$birdName, $photoResourceID", bird.toString())
    }


    /* I am not sure how to do this sorry Daniel
    /**
     * A test to check the fail condition for getNameUsingResourceId().
     */
    @Test
    fun getNameUsingResourceId_fails(){
        val questions: ArrayList<QuestionTemp> = QuizGenerator.generateQuiz(QuestionType.PHOTO, 5, 4)
        for(question in questions){
            val answer: String = BirdDatabase.getNameUsingResourceId(question.getQuestionResourceId())
        }


    }
     */
    
}