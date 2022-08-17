package com.example.manu

/**
 * A temporary question class.
 *
 * @param questionResource The name of the resource that embodies the question.
 * @param questionType The type of question (i.e., the type of resource).
 * @param options The possible answers.
 * @param answerIndex The index of the correct answer.
 */
class QuestionTemp(questionResourceName: String, questionType: QuestionType, options: ArrayList<String>, answerIndex: Int) {

    private val questionResourceName: String = questionResourceName
    private val questionType: QuestionType = questionType
    private val options: ArrayList<String> = options
    private val answerIndex: Int = answerIndex

    /**
     * Returns the name of the resource that embodies the question.
     *
     * @return The name of the resource that embodies the question.
     */
    fun getQuestionResourceName(): String {
        return questionResourceName
    }

    /**
     * Returns the type of question (i.e., the type of resource).
     *
     * @return The type of question (i.e., the type of resource).
     */
    fun getQuestionType(): QuestionType {
        return questionType
    }

    /**
     * Returns the possible answers.
     *
     * @return The possible answers.
     */
    fun getOptions(): ArrayList<String> {
        return options
    }

    /**
     * Returns the index of the correct answer.
     *
     * @return The index of the correct answer.
     */
    fun getAnswerIndex(): Int {
        return answerIndex
    }

}