/* Jackson North 03/08/2022 */
package com.example.manu

import android.util.Log
import  kotlin.random.Random

/* Class takes an empty list in of type bird, and then the function create quiz to be called which
will create the quiz. */
class BirdAdapter(val questionList : MutableList<Question>) {

    //Creates a quiz using the amount of questions and type that the creator wants.
    fun createQuiz(questionAmount: Int, questionType: Int): MutableList<Question>{
        if(questionType !in 1..4){
            Log.d("Question Error", "Please enter a valid question number(1-4).")
        }
        for(i in 1..questionAmount){
            questionList[i] = Question(randomBird(questionType), questionType)
            questionList[i].addOption(randomBird(0))
        }

        return questionList
    }

    //function that finds a random bird for the question type.
    private fun randomBird(questionType : Int): Bird {
        var birdNum = Random.nextInt(0,47)
        var tempBird = createBird(birdNum)
        lateinit var birdOut: Bird

        if (questionType == 0){
            return createBird(birdNum)
        }

        if(isValid(questionType,tempBird)){
            birdOut = tempBird
        }else{
            return randomBird(questionType)
        }

        birdOut.updateValues()

        return birdOut
    }

    //function checks if the bird that is given is a valid bird for the question.
    private fun isValid(questionType: Int, bird: Bird): Boolean{
        var validBird= false
        if(questionType == 1){
            if(bird.getFile(questionType) != "null"){
                validBird = true
            }
        }else if(questionType == 2){
            if(bird.getFile(questionType) != "null"){
                validBird = true
            }
        }else {
            validBird = false
        }
            //this is a SICK comment
        return validBird
    }

    //creates a random bird from the given bird number and question type.
    private fun createBird(birdNumber : Int): Bird{
        return Bird(birdNumber)
    }
}