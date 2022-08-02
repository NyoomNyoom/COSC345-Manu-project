/* Jackson North 03/08/2022 */
package com.example.manu

/* Class takes an empty list in of type bird, and then the function create quiz to be called which
will create the quiz. */
class BirdAdapter(val questionList : MutableList<bird>) {

    //Creates a quiz using the amount of questions and type that the creator wants.
    fun createQuiz(questionAmount: Int, questionType: Int){
        for(i in 1..questionAmount){
            questionList[i] = randomBird(questionType)
        }
    }

    //function that finds a random bird for the question type.
    fun randomBird(questionType : Int){

    }
}