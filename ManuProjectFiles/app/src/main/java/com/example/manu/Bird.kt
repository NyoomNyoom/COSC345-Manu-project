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

data class Bird(val birdNumIn: Int) {
    private var nameOfBird = ""
    private var birdFilePic = ""
    private var birdFileSong = ""
    private var alternativeName = ""
    private var birdInfoFile = ""
    private var birdNum = birdNumIn

    fun updateValues(){
        val fileName = "src/main/assets/bird-data.csv"
        val lines: List<String> = File(fileName).readLines()
        var birdNumber = birdNum
        var birdInfo: MutableList<String> = mutableListOf()

        birdNumber -= 1
        var birdNumString = birdNumber.toString()

        //finds the correct line that was asked for and adds the info into a variable
        lines.forEach{ iter ->
            if(iter[0] == birdNumString.first()){
                iter.split(",").forEach{
                    birdInfo.add(it)
                }
            }
        }

        nameOfBird = birdInfo[1]
        birdFilePic = birdInfo[2]
        birdFileSong = birdInfo[3]
        alternativeName = birdInfo[4]
        birdInfoFile = birdInfo[5]
    }

    fun getBirdName(): String{
        return nameOfBird
    }

    //returns the file name for referencing in the question.
    fun getFile(questionType: Int, birdNumIn: Int): String{
        var output = ""

        //adds the filetype to the end of the string
        if(questionType == 1){
            output = "$birdFilePic.jpg" //only using jpg because I do not know what filetype we will use for pictures.
        }else if(questionType == 2){
            output = "$birdFileSong.mp3"
        }

        return output
    }

    fun getAltName(): String{
        return alternativeName
    }

    fun getInfoFile(): String{
        return birdInfoFile
    }

    fun getBirdNum(): Int{
        return birdNum
    }
}