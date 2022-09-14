package com.example.manu

import android.content.res.Resources
import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val numberQuizzes: Int = 100

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


        assertEquals(false, duplicateOptions)
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
        val bird = BirdTemp("Bellbird", R.drawable.bird_bellbird,Resources.ID_NULL,"", "")

        assertEquals("Bellbird", bird.getBirdName())
    }

    /**
     * A test to check if the getPhotoResourceID function is working correctly.
     */
    @Test
    fun getPhotoResourceID_isCorrect(){
        val bird = BirdTemp("Bellbird", R.drawable.bird_bellbird,Resources.ID_NULL,"", "")

        assertEquals(R.drawable.bird_bellbird, bird.getPhotoResourceId())
    }

    /**
     * A test to check if the getFunFact function is working properly.
     */
    @Test
    fun getFunFact_isCorrect(){
        val bird = BirdTemp("Bellbird", R.drawable.bird_bellbird,Resources.ID_NULL,"", "")

        assertEquals("", bird.getFunFact())
    }

    /**
     * A test to check if the toString method for birdTemp is working.
     */
    @Test
    fun birdTempToString_isCorrect(){
        val bird = BirdTemp("Bellbird", R.drawable.bird_bellbird,Resources.ID_NULL,"", "")
        val birdName = "Bellbird"
        val photoResourceID = R.drawable.bird_bellbird
        val soundResourceId = Resources.ID_NULL
        val maoriName = bird.getmaoriName()

        assertEquals("($birdName, $photoResourceID, $soundResourceId, $maoriName)", bird.toString())
    }

    /**
     * A test to check if the getMaoriName is working properly.
     */
    @Test
    fun isMaoriNameCorrect(){
        val bird = BirdTemp("Auckland Island Teal", R.drawable.bird_auckland_island_teal, R.raw.aucklandislandteal, "Tētē kākāriki", "")
        val maoriName = "Tētē kākāriki"

        assertEquals(maoriName, bird.getmaoriName())
    }

    /**
     * A test to check if getSongResourceID.
     */
    @Test
    fun isgetSongResourceCorrect(){
        val bird = BirdTemp("Auckland Island Teal", R.drawable.bird_auckland_island_teal, R.raw.aucklandislandteal, "Tētē kākāriki", "")

        assertEquals(R.raw.aucklandislandteal, bird.getSongResourceID())
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



    @Test
    fun noDuplicatesInBirdDatabase(){
        var stillValid = true
        BirdDatabase.compileDatabase()

        val birds = BirdDatabase.getBirdList()
        val birds2 = BirdDatabase.getBirdList()

        for ((birdTrackerList1, bird) in birds.withIndex()){
            for ((birdTrackerList2, secondBird) in birds2.withIndex()){
                if(birdTrackerList1 != birdTrackerList2) {
                    if (bird.getBirdName() == secondBird.getBirdName()) {
                        stillValid = false
                    }
                }
            }
        }

        assertEquals(true, stillValid)
    }

    /**
     * Runs the photo quiz a number of times to check whether the birds are selected at random. This should result in a
     * uniform distribution.
     */
    @Test
    fun uniformBirdSelectionForQuiz() {
        val quizzes = 100000
        val questionsPerQuiz = 5
        val marginOfErrorPercentage = 0.05

        BirdDatabase.compileDatabase()
        val birdFrequencies = BirdDatabase.birdFrequencyTest(quizzes, questionsPerQuiz)
        val averageSelectionsPerBird = quizzes * questionsPerQuiz / BirdDatabase.getBirdList().size
        val marginOfError = averageSelectionsPerBird * marginOfErrorPercentage

        /*
            Check all birds appear at a relatively uniform frequency.
         */
        for (birdFrequency in birdFrequencies) {
            if (birdFrequency < averageSelectionsPerBird - marginOfError || birdFrequency > averageSelectionsPerBird
                + marginOfError) {
                assertEquals(true, false)
            }
        }

        assertEquals(true, true)
    }

    /**
     * Runs the photo quiz a number of times to check whether the option index for the correct answer is selected at
     * random. This should result in a uniform distribution.
     */
    @Test
    fun uniformCorrectOption() {
        val quizzes = 100000
        val questionsPerQuiz = 5
        val optionsPerQuestion = 4
        val marginOfErrorPercentage = 0.05

        val optionFrequencies = QuizGenerator.optionFrequencyTest(quizzes, questionsPerQuiz, optionsPerQuestion)
        val averageSelectionsPerOption = quizzes * questionsPerQuiz / optionsPerQuestion
        val marginOfError = averageSelectionsPerOption * marginOfErrorPercentage

        /*
            Check all options contain the correct answer at a relatively uniform frequency.
         */
        for (optionFrequency in optionFrequencies) {
            if (optionFrequency < averageSelectionsPerOption - marginOfError || optionFrequency >
                averageSelectionsPerOption + marginOfError) {
                assertEquals(true, false)
            }
        }

        assertEquals(true, true)
    }

    /**
     * Checks whether the answer is in a different option for any two consecutive questions.
     */
    @Test
    fun consecutiveQuestionDifferentCorrectOption() {
        val quizzes = 1000
        val questionsPerQuiz = 5
        val optionsPerQuestion = 4

        for (quiz in 1..quizzes) {
            val questions = QuizGenerator.generateQuiz(QuestionType.PHOTO, questionsPerQuiz, optionsPerQuestion)
            var previousCorrectOption = -1

            for (question in questions) {
                if (question.getAnswerIndex() == previousCorrectOption)
                    assertEquals(true, false)
                previousCorrectOption = question.getAnswerIndex()
            }
        }

        assertEquals(true, true)
    }



}