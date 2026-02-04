package com.example.data

import java.util.concurrent.ConcurrentHashMap
import kotlin.random.Random

class OtpManager {

    private val otpStore = ConcurrentHashMap<String, OtpData>()
    private val expiryMillis = 60_000L

    fun generateOtp(email: String): String {
        val otp = Random.nextInt(100000, 999999).toString()
        otpStore[email] = OtpData(otp, System.currentTimeMillis())
        return otp
    }

    fun validateOtp(email: String, inputOtp: String): OtpResult {
        val data = otpStore[email] ?: return OtpResult.Invalid

        if (System.currentTimeMillis() - data.createdAt > expiryMillis) {
            otpStore.remove(email)
            return OtpResult.Expired
        }

        if (data.attemptsLeft <= 0) {
            otpStore.remove(email)
            return OtpResult.AttemptsExceeded
        }

        return if (data.otp == inputOtp) {
            otpStore.remove(email)
            OtpResult.Success
        } else {
            data.attemptsLeft--
            OtpResult.Invalid
        }
    }
}

sealed class OtpResult {
    object Success : OtpResult()
    object Invalid : OtpResult()
    object Expired : OtpResult()
    object AttemptsExceeded : OtpResult()
}
