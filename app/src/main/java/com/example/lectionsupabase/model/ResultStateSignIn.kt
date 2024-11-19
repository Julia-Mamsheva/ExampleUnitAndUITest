package com.example.lectionsupabase.model


sealed class ResultStateSignIn {
    data object Loading : ResultStateSignIn()
    data class Success(val data: Unit) : ResultStateSignIn()
    data class Error(val message: String) : ResultStateSignIn()
}