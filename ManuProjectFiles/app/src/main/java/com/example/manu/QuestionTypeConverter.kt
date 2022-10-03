package com.example.manu

/**
 * Converts between QuestionType and Int.
 */
class QuestionTypeConverter() {

    /**
     * Converts between QuestionType and Int.
     */
    companion object {

        /**
         * Converts a QuestionType value to an Int value according to the following map:
         * QuestionType.PHOTO   0
         * QuestionType.SOUND   1
         * QuestionType.ENGLISH 2
         * QuestionType.MAORI   3
         * Other               -1
         * Other includes QuestionType.ALL because translating that value is not a purpose of this class.
         *
         * @param questionType The QuestionType value which this function will read and convert to an Int.
         *
         * @return An Int representing the QuestionType passed to this function. Please read the method description for
         * special cases.
         */
        fun questionTypeToInt(questionType: QuestionType): Int {
            if (questionType == QuestionType.PHOTO) return 0
            else if (questionType == QuestionType.SOUND) return 1
            else if (questionType == QuestionType.ENGLISH) return 2
            else if (questionType == QuestionType.MAORI) return 3
            else return -1
        }

        /**
         * Converts an Int value to a QuestionType value according to the following map:
         * 0 QuestionType.PHOTO
         * 1 QuestionType.SOUND
         * 2 QuestionType.ENGLISH
         * 3 QuestionType.MAORI
         * All Int values undefined by the previous map will return QuestionType.ALL for consistency.
         *
         * @param int The Int which this function will read and convert to a QuestionType.
         *
         * @return A QuestionType representing the Int passed to this function. Please read the method description for
         * special cases.
         */
        fun intToQuestionType(int: Int): QuestionType {
            if (int == 0) return QuestionType.PHOTO
            else if (int == 1) return QuestionType.SOUND
            else if (int == 2) return QuestionType.ENGLISH
            else if (int == 3) return QuestionType.MAORI
            else return QuestionType.ALL
        }

    }

}