package com.example.manu

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun isValid_isCorrect(){
        var qList= mutableListOf<Question>()
        var ba = BirdAdapter(qList)
        val bird = Bird(1)
        assertEquals(true, ba.isValid(1, bird))
    }

    @Test
    fun createQuiz_isCorrect(){
        var qList = mutableListOf<Question>()
        var birdAdapt = BirdAdapter(qList)
        val question = Question(Bird(1), 1)
        question.addOption(Bird(1))
        question.addOption(Bird(1))
        question.addOption(Bird(1))

        qList.add(question)
        val bird = Bird(1)

        assertEquals(qList, birdAdapt.createQuizForced(1,1,1))

    }
}