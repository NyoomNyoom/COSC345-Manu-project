/* Jackson North 03/08/2022 */
package com.example.manu

import  kotlin.random.Random

/* Class takes an empty list in of type bird, and then the function create quiz to be called which
will create the quiz. */
class BirdAdapter(val questionList : MutableList<Bird>) {

    //Creates a quiz using the amount of questions and type that the creator wants.
    fun createQuiz(questionAmount: Int, questionType: Int){
        for(i in 1..questionAmount){
            questionList[i] = randomBird(questionType)
        }
    }

    //function that finds a random bird for the question type.
    private fun randomBird(questionType : Int): Bird {
        var birdNum = Random.nextInt(0,47)
        var bird = createBird(1,birdNum)

        if(isValid(questionType,bird)){
            return bird
        }else{
            randomBird(questionType)
        }
        return bird //only here to stop kotlin from throwing an error, SHOUlD never reach this.
    }

    //function checks if the bird that is given is a valid bird for the question.
    private fun isValid(questionType : Int, bird : Bird): Boolean{
        if(questionType == 1){

        }
    }

    //creates a random bird from the given bird number and question type.
    private fun createBird(questionType: Int, birdNumber : Int): Bird{
        var bird = Bird("Kiwi", "birdTest.txt", "null", "birdTest.txt")
        return bird
    }
}