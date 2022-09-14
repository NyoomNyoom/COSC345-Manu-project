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
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.manu", appContext.packageName)
    }

    //Bird.kt's tests
    @Test
    fun isUpdateValuesCorrect(){
        val bird1 = Bird(0)
        bird1.updateValues(InstrumentationRegistry.getInstrumentation().targetContext)

        assertEquals("Auckland Island Teal", bird1.getBirdName())
    }
}