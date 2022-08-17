/**
 * @author Daniel Robinson
 */

package com.example.manu

/**
 * A temporary question class.
 *
 * @param questionResourceId The question's resource ID.
 * @param options The possible answers.
 * @param answerIndex The index of the correct answer.
 */
class QuestionTemp(questionResourceId: Int, options: ArrayList<String>, answerIndex: Int) {

    private val questionResourceId: Int = questionResourceId
    private val options: ArrayList<String> = options
    private val answerIndex: Int = answerIndex

    /**
     * Returns the question's resource ID.
     *
     * @return The question's resource ID.
     */
    fun getQuestionResourceId(): Int {
        return questionResourceId
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