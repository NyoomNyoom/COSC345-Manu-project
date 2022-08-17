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
    fun addition_isCorrect(){
        assertEquals(4, 2+2)
    }

    /*
    @Test
    fun isValid_isCorrect(){
        var qList= mutableListOf<Question>()
        var ba = BirdAdapter(qList)
        val bird = Bird()
        bird.chosenBird(1)
        assertEquals(true, ba.isValid(1, bird))
    }

    @Test
    fun createQuiz_isCorrect(){
        var qList = mutableListOf<Question>()
        var birdAdapt = BirdAdapter(qList)
        val question = Question(Bird(), 1)
        question.correctBirdObject.chosenBird(1)

        val birdNum = 1

        question.addOption(Bird(), birdNum)
        question.addOption(Bird(), birdNum)
        question.addOption(Bird(), birdNum)

        qList.add(question)
        val bird = Bird()
        bird.chosenBird(1)

        assertEquals(qList, birdAdapt.createQuizForced(1,1,1))

    }

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
}