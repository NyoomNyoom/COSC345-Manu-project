package com.example.manu

data class QuestionData(
    var question:String,
    var option0:String,
    var option1:String,
    var option2:String,
    var option3:String,
    var correctOptionIndex:Int
) {
    override fun toString(): String {
        return "'$question' ? [$option0, $option1, $option2, $option3][$correctOptionIndex]"
    }
}
