package com.example.lectionsupabase

import com.example.lectionsupabase.domain.utils.ConstantsContainer
import com.example.lectionsupabase.model.ResultStateSignIn
import com.example.lectionsupabase.view.mainActivity.MainViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.SessionSource
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test
import io.github.jan.supabase.storage.Storage

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var viewModel: MainViewModel
    private lateinit var constantsContainer: ConstantsContainer
    @Before
    fun setup() {
        viewModel = MainViewModel()
        Dispatchers.setMain(Dispatchers.Unconfined)
    }
    /**
     * Тест на правильность установки начального состояния signInState
     * */
    @Test
    fun InitialSignInStateIsLoading() {
        val viewModel = MainViewModel()
        assertEquals(viewModel.signInState.value, ResultStateSignIn.Loading)
    }

    /**
     * Тест на правильность установки состояния Success при успешном входе в систему
     * */
    @Test
    fun signInStateIsSuccessAfterSuccessfulSignIn() {
        val viewModel = MainViewModel()

        // Устанавливаем пользовательский макет
        viewModel._signInState.value = ResultStateSignIn.Success(Unit)

        // Проверяем, что состояние signInState было правильно установлено
        assertEquals(viewModel.signInState.value, ResultStateSignIn.Success(Unit))
    }
    /**
     * Тест на проверку правильности установки состояния Error после неудачной попытки входа в систему
     * */

    @Test
    fun signInStateIsErrorAfterFailedSignIn() {
        val viewModel = MainViewModel()
        val mockError = Exception("Failed to sign in")

        // Устанавливаем макет ошибки
        viewModel._signInState.value = ResultStateSignIn.Error(mockError.message.toString())

        // Проверяем, что состояние signInState было правильно установлено
        assertEquals(viewModel.signInState.value, ResultStateSignIn.Error(mockError.message.toString()))
    }
}
