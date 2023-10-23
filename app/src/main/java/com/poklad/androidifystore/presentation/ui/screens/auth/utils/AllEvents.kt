package com.poklad.androidifystore.presentation.ui.screens.auth.utils

sealed class AllEvents {
    data class Message(val message: String) : AllEvents()
    data class ErrorCode(val code: Int) : AllEvents()
    data class Error(val error: String) : AllEvents()
}