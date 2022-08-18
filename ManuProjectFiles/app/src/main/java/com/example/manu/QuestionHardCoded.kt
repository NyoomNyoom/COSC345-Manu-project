package com.example.manu

import kotlin.random.Random

data class QuestionHardCoded(val questionNum: Int){
    private val questionList: MutableList<Question> = mutableListOf()

    fun createQuiz(): MutableList<Question>{
        var i = 0
        while(i <= questionNum-1 && questionNum <= 10){
            questionList.add(Question(Bird(i),1))
        }

        if(questionNum == 5){

        }else if(questionNum == 10){

        }else {

        }

        return questionList
    }

}
