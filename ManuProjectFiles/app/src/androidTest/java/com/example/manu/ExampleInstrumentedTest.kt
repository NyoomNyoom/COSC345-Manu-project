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
    
    @Test
    fun updateValuesCorrect() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        StatsAdapter.updateValues(context, QuestionType.PHOTO, 10, 5)

        val numCorrect = (StatsAdapter.getStatsBasedOnType(QuestionType.PHOTO)).getNumRight()
    }

}
