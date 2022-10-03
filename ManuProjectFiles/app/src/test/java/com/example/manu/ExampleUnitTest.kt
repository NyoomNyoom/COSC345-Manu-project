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
            val questions: ArrayList<Question> = QuizGenerator.generateQuiz(QuestionType.PHOTO, 5, 4)

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
            val questions: ArrayList<Question> = QuizGenerator.generateQuiz(QuestionType.PHOTO, 5, 4)

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
            val questions: ArrayList<Question> = QuizGenerator.generateQuiz(QuestionType.PHOTO, 5, 4)

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
        val questions: ArrayList<Question> = QuizGenerator.generateQuiz(QuestionType.PHOTO, 5, 4)
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
        val bird = Bird("Bellbird", R.drawable.bird_bellbird, Resources.ID_NULL, Resources.ID_NULL, Resources.ID_NULL, "", "", "")

        assertEquals("Bellbird", bird.getBirdName())
    }

    /**
     * A test to check if the getPhotoResourceID function is working correctly.
     */
    @Test
    fun getPhotoResourceID_isCorrect(){
        val bird = Bird("Bellbird", R.drawable.bird_bellbird, Resources.ID_NULL, Resources.ID_NULL, Resources.ID_NULL, "", "", "")

        assertEquals(R.drawable.bird_bellbird, bird.getPhotoResourceId())
    }

    /**
     * A test to check if the getFunFact function is working properly.
     */
    @Test
    fun getFunFact_isCorrect(){
        val bird = Bird("Bellbird", R.drawable.bird_bellbird, Resources.ID_NULL, Resources.ID_NULL, Resources.ID_NULL, "", "", "")

        assertEquals("", bird.getFunFact())
    }

    /**
     * A test to check if the toString method for birdTemp is working.
     */
    @Test
    fun birdTempToString_isCorrect(){
        val bird = Bird("Bellbird", R.drawable.bird_bellbird, Resources.ID_NULL, Resources.ID_NULL, Resources.ID_NULL, "", "", "")
        val birdName = "Bellbird"
        val photoResourceID = R.drawable.bird_bellbird
        val soundResourceId = Resources.ID_NULL
        val maoriName = bird.getMaoriName()

        assertEquals("($birdName, $photoResourceID, $soundResourceId, $maoriName)", bird.toString())
    }

    /**
     * A test to check if the getMaoriName is working properly.
     */
    @Test
    fun isMaoriNameCorrect(){
        val bird = Bird("Auckland Island Teal", R.drawable.bird_auckland_island_teal, R.raw.aucklandislandteal, Resources.ID_NULL, Resources.ID_NULL, "Tētē kākāriki", "", "")
        val maoriName = "Tētē kākāriki"

        assertEquals(maoriName, bird.getMaoriName())
    }

    /**
     * A test to check if getSongResourceID.
     */
    @Test
    fun isgetSongResourceCorrect(){
        val bird = Bird("Auckland Island Teal", R.drawable.bird_auckland_island_teal, R.raw.aucklandislandteal, Resources.ID_NULL, Resources.ID_NULL, "Tētē kākāriki", "", "")

        assertEquals(R.raw.aucklandislandteal, bird.getSongResourceId())
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


    /**
     * Ensures there are no duplicate birds in the database.
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

    /*@Test
    fun getBirdsWithResourceIncorrect() {
        val questions = QuizGenerator.generateQuiz(QuestionType.SOUND,1, 4)

        val wantedAnswer: ArrayList<QuestionTemp> = arrayListOf()



        assertEquals(wantedAnswer, questions)
    }*/

    /**
     * Tests whether the database returns all birds with both Māori and English names.
     */
    @Test
    fun birdsHaveMaoriAndEnglishNames() {
        BirdDatabase.compileDatabase()

        // Check the birds have Maori names (exclusive of their primary name) using the Maori query.
        var birdsQueryMaori = BirdDatabase.getBirdsWithResource(QuestionType.MAORI)
        for (bird in birdsQueryMaori) {
            assertEquals(false, bird.getMaoriName() == "")
        }

        // Check the birds have Maori names (exclusive of their primary name) using the English query.
        var birdsQueryEnglish = BirdDatabase.getBirdsWithResource(QuestionType.ENGLISH)
        for (bird in birdsQueryEnglish) {
            assertEquals(false, bird.getMaoriName() == "")
        }

        // Take all the birds and refine it to the birds with Maori names.
        var allBirds = BirdDatabase.getBirdList()
        var birdsWithMaoriName = ArrayList<Bird>()
        for (bird in allBirds) {
            if (bird.getMaoriName() != "")
                birdsWithMaoriName.add(bird)
        }

        // Compare this refined list to the ones presented by the database in response to the Maori and English queries.
        for (bird in birdsWithMaoriName) {
            assertEquals(true, bird in birdsQueryMaori && bird in birdsQueryEnglish)
        }

        // Finally, test their sizes (if one is a subset of the other, and they are the same size, they are the same).
        assertEquals(true, birdsQueryMaori.size == birdsWithMaoriName.size)
        assertEquals(true, birdsQueryEnglish.size == birdsWithMaoriName.size)
    }

    /**
     * Tests whether the database returns all birds that have a sound resource.
     */
    @Test
    fun birdsHaveSoundResource() {
        BirdDatabase.compileDatabase()

        // Validate the database's query result.
        val queryResult = BirdDatabase.getBirdsWithResource(QuestionType.SOUND)
        for (bird in queryResult) {
            assertEquals(true, bird.getSongResourceId() != Resources.ID_NULL)
        }

        // Get all birds and narrow them down to the ones with sounds. This is the "answer".
        val allBirds = BirdDatabase.getBirdList()
        var birdsWithSongs = ArrayList<Bird>()
        for (bird in allBirds) {
            if (bird.getSongResourceId() != Resources.ID_NULL)
                birdsWithSongs.add(bird)
        }

        // Compare the "answer" to the query result.
        for (bird in birdsWithSongs) {
            assertEquals(true, bird in queryResult)
        }

        // Finally, test their sizes (if one is a subset of the other, and they are the same size, they are the same).
        assertEquals(true, queryResult.size == birdsWithSongs.size)
    }

    /**
     * Query the database for all birds with photos and check the query isn't empty.
     */
    @Test
    fun getBirdsWithPhotos() {
        BirdDatabase.compileDatabase()

        // Validate the database's query result.
        for (i in 1..1000) {
            val queryResult = BirdDatabase.getBirdsWithResource(QuestionType.PHOTO)
            assertEquals(true, queryResult.size > 10)
        }
    }

    /**
     * Generate quizzes and check the resulting question list has the correct number of questions.
     */
    @Test
    fun generateQuizzes() {
        BirdDatabase.compileDatabase()
        val quizLength = 30

        for (i in 1..1000) {
            val questions = QuizGenerator.generateQuiz(QuestionType.PHOTO, quizLength, 4)
            assertEquals(true, questions.size == quizLength)
        }
    }

    /**
     * Tests whether the bird endangerment status can be passed through (in and out) the bird consistently.
     */
    @Test
    fun birdEndangermentStatus() {
        val bird = (Bird("Morepork", R.drawable.bird_morepork, R.raw.morepork, R.drawable.english_morepork,
            R.drawable.maori_morepork, "Ruru", "Not Threatened", ""))
        if (bird.getEndangerment() != "Not Threatened") {
            assertEquals(false, true)
        } else {
            assertEquals(true, true)
        }
    }

    /**
     * Tests whether the bird's Māori name can be passed through (in and out) the bird consistently.
     */
    @Test
    fun birdMaoriName() {
        val bird = (Bird("Morepork", R.drawable.bird_morepork, R.raw.morepork, R.drawable.english_morepork,
            R.drawable.maori_morepork, "Ruru", "Not Threatened", ""))
        if (bird.getMaoriName() != "Ruru") {
            assertEquals(false, true)
        } else {
            assertEquals(true, true)
        }
    }

    /**
     * Generates a bird song quiz and checks whether the question resource is not null, if there are four options, and
     * if the answer index is in the correct range.
     */
    @Test
    fun generateBirdSongQuiz() {
        BirdDatabase.compileDatabase()
        val questions = QuizGenerator.generateQuiz(QuestionType.SOUND, 10, 4)

        for (question in questions) {
            if (question.getQuestionResourceId() == Resources.ID_NULL) {
                assertEquals(false, true)
            }

            if (question.getOptions().size != 4) {
                assertEquals(false, true)
            }

            val answerIndex = question.getAnswerIndex()
            if (!(answerIndex == 0 || answerIndex == 1 || answerIndex == 2 || answerIndex == 3)) {
                assertEquals(false, true)
            }
        }
    }

    /**
     * Generates an English to Māori quiz and checks whether the question resource is not null, if there are four
     * options, and if the answer index is in the correct range.
     */
    @Test
    fun generateEnglishQuiz() {
        BirdDatabase.compileDatabase()
        val questions = QuizGenerator.generateQuiz(QuestionType.ENGLISH, 10, 4)

        for (question in questions) {
            if (question.getQuestionResourceId() == Resources.ID_NULL) {
                assertEquals(false, true)
            }

            if (question.getOptions().size != 4) {
                assertEquals(false, true)
            }

            val answerIndex = question.getAnswerIndex()
            if (!(answerIndex == 0 || answerIndex == 1 || answerIndex == 2 || answerIndex == 3)) {
                assertEquals(false, true)
            }
        }
    }

    /**
    * Generates an Māori to English quiz and checks whether the question resource is not null, if there are four
    * options, and if the answer index is in the correct range.
    */
    @Test
    fun generateMaoriQuiz() {
        BirdDatabase.compileDatabase()
        val questions = QuizGenerator.generateQuiz(QuestionType.MAORI, 10, 4)

        for (question in questions) {
            if (question.getQuestionResourceId() == Resources.ID_NULL) {
                assertEquals(false, true)
            }

            if (question.getOptions().size != 4) {
                assertEquals(false, true)
            }

            val answerIndex = question.getAnswerIndex()
            for (index in 0 until 4) {
                // If the answer index is within the valid bounds.
                if (answerIndex == index) {
                    assertEquals(true, true)
                    return
                }
            }
            assertEquals(false, true)  // If the answer index is not within the valid bounds.
        }
    }

    //Stats.kt file tests
    /**
     * Checking if getNumRight is correct.
     */
    @Test
    fun getNumRightCorrect(){
        val statsObj = Stats(QuestionType.PHOTO, 10, 9, 10)

        assertEquals(9, statsObj.getNumRight())
    }

    /**
     * Checking if updateNumRight is correct.
     */
    @Test
    fun updateNumRightIsCorrect(){
        val statsObj = Stats(QuestionType.PHOTO, 10, 9, 10)

        statsObj.updateNumRight(4)

        assertEquals(13, statsObj.getNumRight())
    }

    /**
     * Checking if total played is correct.
     */
    @Test
    fun getTotalPlayedCorrect(){
        val statsObj = Stats(QuestionType.PHOTO, 10, 9, 10)

        assertEquals(10, statsObj.getTotalPlayed())
    }

    /**
     * Checking if updateTotalPlayed is correct.
     */
    @Test
    fun updateTotalPlayedCorrect(){
        val statsObj = Stats(QuestionType.PHOTO, 10, 9, 10)

        statsObj.updateTotalPlayed(10)

        assertEquals(20, statsObj.getTotalPlayed())
    }

    /**
     * checking if getQuestionType works.
     */
    @Test
    fun getQuestionTypeCorrect(){
        val statsObj = Stats(QuestionType.PHOTO, 10, 9, 10)

        assertEquals(QuestionType.PHOTO, statsObj.getQuestionType())
    }

    /**
     * Checking if getQuestionLength works.
     */
    @Test
    fun getQuestionLengthCorrect(){
        val statsObj = Stats(QuestionType.PHOTO, 10, 9, 10)

        assertEquals(10, statsObj.getQuestionLength())
    }

    /**
     * Checking if getAverage is correct.
     */
    @Test
    fun getAverageCorrect(){
        val statsObj = Stats(QuestionType.PHOTO, 10, 9, 10)
        statsObj.updateTotalPlayed(30)

        assertEquals(9/40, statsObj.getAverage())
    }

    /**
     * Tests average if totalPlayed = 0.
     */
    @Test
    fun getAverageNumZeroCorrect(){
        val statsObj = Stats(QuestionType.PHOTO, 10, 9, 0)

        assertEquals(0, statsObj.getAverage())
    }

    /**
     * Tests getQuizzes if numQuestions = 0.
     */
    @Test
    fun getTotalQuizzesPlayedZero(){
        val statsObj = Stats(QuestionType.PHOTO, 0, 9, 10)

        assertEquals(1, statsObj.getTotalQuizzesPlayed())
    }

    /**
     * Checking if resetValues is working.
     */
    @Test
    fun resetValuesCorrect(){
        val statsObj = Stats(QuestionType.PHOTO, 10, 9, 10)

        statsObj.resetValues()

        assertEquals(0, statsObj.getTotalPlayed())
    }

    /**
     * Checking if getTotalQuizzesPlayed is working.
     */
    @Test
    fun getTotalQuizzesPlayedCorrect(){
        val statsObj = Stats(QuestionType.PHOTO, 10, 9, 10)

        statsObj.updateTotalPlayed(30)

        assertEquals(40/10, statsObj.getTotalQuizzesPlayed())
    }

    /**
     * Tests encryption for various inputs.
     */
    @Test
    fun encryption() {
        var plaintext = "123456789"
        assertEquals(true, StatsAdapter.decrypt(StatsAdapter.encrypt(plaintext)) == plaintext)
        plaintext = "ajsbd8327g4r23u"
        assertEquals(true, StatsAdapter.decrypt(StatsAdapter.encrypt(plaintext)) == plaintext)
        plaintext = "abcdefghijklmnopqrstuvwxyz"
        assertEquals(true, StatsAdapter.decrypt(StatsAdapter.encrypt(plaintext)) == plaintext)
        plaintext = "HI"
        assertEquals(true, StatsAdapter.decrypt(StatsAdapter.encrypt(plaintext)) == plaintext)
        plaintext = "\n2,3,4,\n"
        assertEquals(true, StatsAdapter.decrypt(StatsAdapter.encrypt(plaintext)) == plaintext)
    }

    /**
     * Try and retrieve a bird that does not exist.
     */
    @Test
    fun nonExistentBird() {
        BirdDatabase.compileDatabase()
        val emptyBird = BirdDatabase.getBirdUsingPhotoResourceId(Resources.ID_NULL)
        assertEquals(true, emptyBird.getBirdName() == "EMPTY_NAME")
    }

    /**
     * Tests whether you can retrieve a bird's name using its raw sound file.
     */
    @Test
    fun getBirdUsingSoundResource() {
        BirdDatabase.compileDatabase()
        val birdName = BirdDatabase.getNameUsingResourceId(R.raw.aucklandislandteal)
        assertEquals(true, birdName == "Auckland Island Teal")
    }

    /**
     * Tests the behaviour of the database if you ask for the bird that has a non-existent raw sound file and
     * non-existent photo file (which none do).
     */
    @Test
    fun getBirdWithEmptySound() {
        BirdDatabase.compileDatabase()
        val birdName = BirdDatabase.getNameUsingResourceId(-1)  // Resource IDs are always positive.
        assertEquals(true, birdName == "")
    }

    /**
     * Ensures QuestionType.ALL is not accepted by the birds database (since this value is for storing stats not
     * querying birds).
     */
    @Test
    fun getBirdsWithResourceAll() {
        BirdDatabase.compileDatabase()
        val birds = BirdDatabase.getBirdsWithResource(QuestionType.ALL)
        val emptyBirdList: ArrayList<Bird> = ArrayList()
        assertEquals(true, birds == emptyBirdList)
    }

    /**
     * Ensures the bird database returns the same result each time when it requests a list of all birds.
     */
    @Test
    fun checkAllBirdsQuery() {
        BirdDatabase.compileDatabase()
        val birdList1 = BirdDatabase.getBirdList()
        val birdList2 = BirdDatabase.getBirdList()
        assertEquals(true, birdList1 == birdList2)
    }

    /**
     * Purely for code coverage. I wanted to see if you could instantiate a class that contained a companion object
     * because such objects behave like static classes (and aren't meant to be instantiated.
     */
    @Test
    fun instantiateStaticBirdDatabase() {
        BirdDatabase.compileDatabase()
        val database = BirdDatabase()
        // Does not assertEquals because this is not a proper test.
    }

}