package com.example.lectionsupabase.view.mainActivity


import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.lectionsupabase.domain.utils.ConstantsContainer
import com.example.lectionsupabase.model.ResultStateSignUp
import com.example.lectionsupabase.model.ResultStateSignIn
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class MainViewModel() : ViewModel() {
    val _signInState = MutableStateFlow<ResultStateSignIn>(ResultStateSignIn.Loading)
    val signInState: StateFlow<ResultStateSignIn> = _signInState.asStateFlow()

    private val _signUpState = MutableStateFlow<ResultStateSignUp>(ResultStateSignUp.Loading)
    val signUpState: StateFlow<ResultStateSignUp> = _signUpState.asStateFlow()

    fun onSignInEmailPassword(emailUser: String, passwordUser: String) {
        viewModelScope.launch {
            try {
                val user = ConstantsContainer.Constants.supabase.auth.signInWith(Email) {
                    email = emailUser
                    password = passwordUser
                }
                _signInState.value = ResultStateSignIn.Success(user)
            } catch (e: Exception) {
                println(e.message)
                _signInState.value = ResultStateSignIn.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun onSignUpEmail(emailUser: String, passwordUser: String) {
        viewModelScope.launch {
            try {
                val user = ConstantsContainer.Constants.supabase.auth.signUpWith(Email) {
                    email = emailUser
                    password = passwordUser
                }
                _signUpState.value = ResultStateSignUp.Success(user)
            } catch (e: Exception) {
                _signUpState.value = ResultStateSignUp.Error(e.message ?: "Unknown error")
            }
        }
    }
}