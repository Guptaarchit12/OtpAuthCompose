package com.example.otpauthcompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import timber.log.Timber

sealed interface AuthUiState {
    object Login : AuthUiState
    object OtpVerify : AuthUiState
    object Success : AuthUiState
}

class AuthViewModel : ViewModel() {
    var uiState by mutableStateOf<AuthUiState>(AuthUiState.Login)
        private set

    var userEmail by mutableStateOf("")
        private set

    var generatedOtp by mutableStateOf("")
        private set

    var sessionStartTime by mutableStateOf(0L)
        private set

    // ✅ NEW: OTP Logic States
    var otpExpiryTime by mutableStateOf(0L)
        private set

    var attemptsLeft by mutableIntStateOf(3)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun sendOtp(email: String) {
        userEmail = email
        errorMessage = null
        attemptsLeft = 3 // ✅ Requirement: Resets attempt count

        generatedOtp = ""
        generatedOtp = (100000..999999).random().toString()

        // ✅ Requirement: OTP expiry 60 seconds
        otpExpiryTime = System.currentTimeMillis() + 60_000

        uiState = AuthUiState.OtpVerify
        Timber.d("OTP generated for $email: $generatedOtp")
    }

    fun verifyOtp(otp: String) {
        val currentTime = System.currentTimeMillis()

        // 1. Check Expiry
        if (currentTime > otpExpiryTime) {
            errorMessage = "OTP has expired. Please resend."
            Timber.e("OTP validation failure: Expired")
            return
        }

        // 2. Check Attempts
        if (attemptsLeft <= 0) {
            errorMessage = "Max attempts reached. Please resend."
            return
        }

        // 3. Validate
        if (otp == generatedOtp) {
            sessionStartTime = System.currentTimeMillis()
            uiState = AuthUiState.Success
            errorMessage = null
            Timber.i("OTP validation success for $userEmail")
        } else {
            attemptsLeft-- // ✅ Requirement: Track attempts
            errorMessage = if (attemptsLeft > 0) {
                "Incorrect OTP. $attemptsLeft attempts remaining."
            } else {
                "Incorrect OTP. Max attempts reached."
            }
            Timber.w("OTP validation failure: Incorrect code. Attempts left: $attemptsLeft")
        }
    }

    fun logout() {
        uiState = AuthUiState.Login
        sessionStartTime = 0L
        generatedOtp = ""
        attemptsLeft = 3
        errorMessage = null
        Timber.i("Logout event recorded")
    }
}