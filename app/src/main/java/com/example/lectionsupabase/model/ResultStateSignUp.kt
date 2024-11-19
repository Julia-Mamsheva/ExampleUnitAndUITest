package com.example.lectionsupabase.model

import io.github.jan.supabase.gotrue.user.UserInfo

sealed class ResultStateSignUp {
    data object Loading : ResultStateSignUp()
    data class Success(val data: UserInfo?) : ResultStateSignUp()
    data class Error(val message: String) : ResultStateSignUp()
}