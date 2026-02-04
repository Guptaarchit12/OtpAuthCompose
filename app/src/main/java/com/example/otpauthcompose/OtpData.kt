package com.example.data

data class OtpData(
    val otp: String,
    val createdAt: Long,
    var attemptsLeft: Int = 3
)
