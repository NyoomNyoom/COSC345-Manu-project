/**
 * @author Daniel Robinson
 */

package com.example.manu

/**
 * A temporary question class.
 *
 * @param questionResourceID The question's resource ID.
 * @param questionType The type of question (i.e., the type of resource).
 * @param options The possible answers.
 * @param answerIndex The index of the correct answer.
 */
class QuestionTemp(questionResourceID: Int, questionType: QuestionType, options: ArrayList<String>, answerIndex: Int) {

    private val questionResourceID: Int = questionResourceID
    private val questionType: QuestionType = questionType
    private val options: ArrayList<String> = options
    private val answerIndex: Int = answerIndex

    /**
     * Returns the question's resource ID.
     *
     * @return The question's resource ID.
     */
    fun getQuestionResourceID(): Int {
        return questionResourceID
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