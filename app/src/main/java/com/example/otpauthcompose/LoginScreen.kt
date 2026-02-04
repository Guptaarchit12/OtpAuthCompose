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
import com.airbnb.lottie.compose.* // Ensure dependency is in build.gradle

@Composable
fun LoginScreen(onSendOtp: (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    // --- Lottie Configuration ---
    // Make sure your file is in res/raw/login_anim.json
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.login_anim))
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
        // Professional Animation Header
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .size(180.dp) // Professional sizing
                .padding(bottom = 16.dp)
        )

        Text(
            text = "Authentication",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Enter your corporate email to receive a secure OTP",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            placeholder = { Text("name@example.com") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = MaterialTheme.shapes.medium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onSendOtp(email) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = isEmailValid,
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Get Started",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}