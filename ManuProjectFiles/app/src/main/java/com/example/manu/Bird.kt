/* Jackson North 03/08/2022 */
package com.example.manu

import java.io.File

/*
* Need to change the constructor to be a constructor with only a number inputed which refers to a
* number from a csv file that has all the information currently in the folder name and then saves
* the information to a string which will then be able to be called out by the functions in
* birdAdapter.
*
* NEED TO HAVE A LIST THAT HAS 4 BIRDS (1 CORRECT 3 WRONG) WITH THE CORRECT INDEX AS A SECOND NUMBER
* RETURNED.
*/

data class Bird(val Name: String, val fileName: String, val altName: String, val infoFile: String, val birdNumIn: Int) {
    private var nameOfBird = Name
    private var birdFile = fileName
    private var alternativeName = altName
    private var birdInfoFile = infoFile
    private var birdNum = birdNumIn

    fun birdName(){

    }

    //returns the file name for referencing in the question.
    fun getFile(questionType: Int, birdnumIn: Int): String{
        val fileName = "src/main/assets/bird-data.csv"
        val lines: List<String> = File(fileName).readLines()
        var birdNumber = birdNumIn
        birdNumber -= 1
        var birdNumString = birdNumber.toString()

        var output = ""

        lines.forEach{
            if(it[0] == birdNumString.first()){
                output = it
            }
        }

        //check output for the file of either jpg or mp3.

        return output
    }

    fun getAltName(){

    }

    fun getInfoFile(){

    }

    fun getBirdNum(){

    }
}