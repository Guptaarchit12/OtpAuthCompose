package com.example.otpauthcompose.analytics

import timber.log.Timber

object AnalyticsLogger {

    fun logOtpGenerated(email: String) {
        Timber.Forest.d("OTP generated for $email")
    }

    fun logOtpSuccess(email: String) {
        Timber.Forest.d("OTP validation success for $email")
    }

    fun logOtpFailure(email: String) {
        Timber.Forest.d("OTP validation failed for $email")
    }

    fun logLogout(email: String) {
        Timber.Forest.d("User logged out: $email")
    }
}