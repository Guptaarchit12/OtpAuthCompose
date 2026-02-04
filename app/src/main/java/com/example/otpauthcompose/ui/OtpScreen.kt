package com.example.otpauthcompose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.delay

@Composable
fun OtpScreen(
    email: String,
    errorMessage: String?,
    onVerify: (String) -> Unit,
    onResend: () -> Unit
) {
    var otpCode by remember { mutableStateOf("") }

    // Industrial Countdown Logic
    var ticks by remember { mutableIntStateOf(60) }
    LaunchedEffect(Unit) {
        while (ticks > 0) {
            delay(1000)
            ticks--
        }
    }

    // --- Lottie Configuration ---
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.otp_anim))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Professional Secure Animation
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(160.dp)
        )

        Text(
            text = "Verify Identity",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Enter the 6-digit code sent to\n$email",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )

        // Error Message Display
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        OutlinedTextField(
            value = otpCode,
            onValueChange = { if (it.length <= 6) otpCode = it },
            label = { Text("6-Digit OTP") },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("000000") },
            isError = errorMessage != null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            shape = MaterialTheme.shapes.medium,
            singleLine = true
        )

        // Countdown Timer
        Text(
            text = if (ticks > 0) "Code expires in ${ticks}s" else "Code expired",
            style = MaterialTheme.typography.labelLarge,
            color = if (ticks > 10) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onVerify(otpCode) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = otpCode.length == 6 && ticks > 0,
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Verify & Login", fontWeight = FontWeight.SemiBold)
        }

        TextButton(
            onClick = {
                ticks = 60
                onResend()
            },
            modifier = Modifier.padding(top = 8.dp),
            enabled = ticks < 50
        ) {
            Text("Resend Code")
        }
    }
}