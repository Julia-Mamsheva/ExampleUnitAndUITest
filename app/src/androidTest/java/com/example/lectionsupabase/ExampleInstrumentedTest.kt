package com.example.lectionsupabase

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.example.lectionsupabase.view.mainActivity.components.auth

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.lectionsupabase", appContext.packageName)
    }

    /**
     * Проверка отображения экрана регистрации
     * */
    @Test
    fun SignUpscreenisdisplayed() {
        composeTestRule.setContent {
            auth(rememberNavController())
        }
        // Проверяем, что экран регистрации отображается
        /*        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
                composeTestRule.onNodeWithText("Password").assertIsDisplayed()*/
        composeTestRule.onNodeWithText("SigUp").assertIsDisplayed()
    }
    /**
     * Проверка перехода на экран входа в систему при нажатии на кнопку SigIn
     * */
    @Test
    fun NavigatetoSignInscreenafterclickingSigInbutton() {
        composeTestRule.setContent {
            auth(rememberNavController())
        }

        composeTestRule.onNodeWithText("SigIn").performClick()

        // Проверяем, что пользователь был перенаправлен на HomeScreen (Тест провалится, потому что пользователя не залогинился)
        composeTestRule.onNodeWithText("HomeScreen").assertIsDisplayed()
    }

    /**
     * Проверка отображения сообщения об ошибке при неудачной попытке авторизации
     */
    @Test
    fun emailValidation() {
        composeTestRule.setContent {
            auth(rememberNavController())
        }
        // Проверяем, что появляется сообщение об ошибке
        composeTestRule.onNodeWithText("SigIn").performClick()
        composeTestRule.onNodeWithText("Invalid email address").assertIsDisplayed()
    }
}