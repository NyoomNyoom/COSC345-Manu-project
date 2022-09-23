package com.example.manu

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    
    /**
    * The default test that came with android studio :).
    */
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.manu", appContext.packageName)
    }
     
     //StatsAdapter.kt tests
     
    /**
    * A test to check if the app can compile a database.
    */
    @Test
    fun testFunctionCompiles(){
        StatsAdapter.makeFile(InstrumentationRegistry.getInstrumentation().targetContext)

        assertEquals(true, true)
    }

    /**
     * A test to check if the app updates values.
     */
    @Test
    fun updateValuesCorrect() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        StatsAdapter.makeFile(context)
        StatsAdapter.resetValues(context)
        StatsAdapter.updateValues(context, QuestionType.PHOTO, 10, 5)


        val numCorrect = (StatsAdapter.getStatsBasedOnType(QuestionType.PHOTO)).getNumRight()


        assertEquals(5, numCorrect)
    }

    /**
     * A test to check if reset values works.
     */
    @Test
    fun resetValuesCorrect(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        StatsAdapter.makeFile(context)
        StatsAdapter.updateValues(context, QuestionType.PHOTO, 10, 5)
        StatsAdapter.updateValues(context, QuestionType.SOUND, 10, 5)
        StatsAdapter.updateValues(context, QuestionType.MAORI, 10, 5)
        StatsAdapter.updateValues(context, QuestionType.ENGLISH, 10, 5)

        StatsAdapter.resetValues(context)

        assertEquals(0, StatsAdapter.getStatsBasedOnType(QuestionType.PHOTO).getNumRight())
    }

    /**
     * A test to check getStatsBasedOn.
     */
    @Test
    fun getStatsBasedOnCorrect(){
        var isRight = false
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        StatsAdapter.makeFile(context)
        StatsAdapter.resetValues(context)

        StatsAdapter.updateValues(context, QuestionType.PHOTO, 10, 1)
        StatsAdapter.updateValues(context, QuestionType.SOUND, 10, 2)
        StatsAdapter.updateValues(context, QuestionType.MAORI, 10, 3)
        StatsAdapter.updateValues(context, QuestionType.ENGLISH, 10, 4)

        if(StatsAdapter.getStatsBasedOnType(QuestionType.PHOTO).getNumRight() == 1){
            isRight = true
        }else if(StatsAdapter.getStatsBasedOnType(QuestionType.SOUND).getNumRight() == 2){
            isRight = true
        }else if(StatsAdapter.getStatsBasedOnType(QuestionType.MAORI).getNumRight() == 3){
            isRight = true
        }else if(StatsAdapter.getStatsBasedOnType(QuestionType.ENGLISH).getNumRight() == 4){
            isRight = true
        }

        assertEquals(true, isRight)
    }
}
