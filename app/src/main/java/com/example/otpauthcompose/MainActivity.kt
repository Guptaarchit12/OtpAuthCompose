package com.example.otpauthcompose

import SessionScreen
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.otpauthcompose.ui.theme.OtpAuthComposeTheme
import com.example.otpauthcompose.viewmodel.AuthViewModel
import com.example.otpauthcompose.viewmodel.AuthUiState
import timber.log.Timber

class MainActivity : ComponentActivity() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Timber.treeCount == 0) {
            Timber.plant(Timber.DebugTree())
        }

        setContent {
            val context = LocalContext.current

            LaunchedEffect(viewModel.generatedOtp) {
                if (viewModel.generatedOtp.isNotEmpty()) {
                    Toast.makeText(
                        context,
                        "Simulated Email: Your OTP is ${viewModel.generatedOtp}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            OtpAuthComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (viewModel.uiState) {
                        is AuthUiState.Login -> {
                            LoginScreen(
                                // ✅ Explicitly defined type to fix the inference error
                                onSendOtp = { email: String -> viewModel.sendOtp(email) }
                            )
                        }
                        is AuthUiState.OtpVerify -> {
                            OtpScreen(
                                email = viewModel.userEmail,
                                errorMessage = viewModel.errorMessage,
                                onVerify = { otp -> viewModel.verifyOtp(otp) },
                                onResend = { viewModel.sendOtp(viewModel.userEmail) }
                            )
                        }
                        is AuthUiState.Success -> {
                            SessionScreen(
                                startTime = viewModel.sessionStartTime,
                                onLogout = { viewModel.logout() }
                            )
                        }
                    }
                }
            }
        }
    }
}