## OTPAuthCompose — Passwordless Authentication (Jetpack Compose)

A production-ready Android application built using Jetpack Compose and Material 3, demonstrating a secure passwordless authentication flow using Email + OTP.
The project focuses on clean architecture, reactive state management, and modern Android development best practices.

## Project Walkthrough & Setup

Demo Video (Google Drive):
Includes repository cloning, architecture overview, and a live application walkthrough.
(Drive access set to “Anyone with the link can view”)

Link: Add your public Google Drive link here

## Technical Highlights
Modern UI / UX

Fully implemented using Jetpack Compose and Material 3

Responsive layout supporting multiple screen sizes

Lottie animations integrated to provide visual feedback during:

Authentication

OTP verification

Session transitions

Secure Authentication Logic
Email Validation

Regex-based email validation implemented at the UI layer

Prevents invalid input before OTP generation

OTP Lifecycle Management

6-digit OTP generated locally

60-second expiry timer implemented using LaunchedEffect and ViewModel state

Real-time countdown that survives recompositions

Attempt Throttling

Maximum of 3 OTP verification attempts

Generating a new OTP:

Invalidates the previous OTP

Resets the attempt counter

Simulated OTP Delivery

OTP delivery simulated using the Snackbar API

Custom duration to mimic real-world delivery behavior during testing

## Architecture & Best Practices
MVVM Architecture

Clear separation of concerns between:

UI (Compose)

Business logic (ViewModel)

Data handling

Unidirectional Data Flow (UDF)

UI reacts to immutable state updates

Authentication flow managed using sealed UI states

Predictable and testable state transitions

Professional Logging

Timber SDK integrated for centralized, structured logging

Logs key events including:

OTP generation

OTP validation success and failure

Logout events

## Project Structure
app/
├── analytics/
│   └── AnalyticsLogger.kt
├── data/
│   └── OtpManager.kt
├── ui/
│   ├── LoginScreen.kt
│   ├── OtpScreen.kt
│   └── SessionScreen.kt
├── ui.theme/
│   └── Theme.kt
├── viewmodel/
│   ├── AuthViewModel.kt
│   └── AuthUiState.kt
└── MainActivity.kt

## Key Components

AuthViewModel.kt
Handles OTP generation, expiry logic, attempt tracking, and session state.

LoginScreen.kt / OtpScreen.kt
Declarative Compose UI with state-driven rendering and animations.

SessionScreen.kt
Displays session start time and live session duration (mm:ss).

MainActivity.kt
Application entry point and navigation host.

## How to Run the Project
Clone the Repository
git clone https://github.com/Guptaarchit12/OtpAuthCompose.git

Environment Setup

Open the project in Android Studio (Ladybug or later)

Ensure JDK 17 is configured in Gradle settings

Dependency Sync

Sync the Gradle files

Lottie animation files are located at:

app/src/main/res/raw/

Launch :

Run on an emulator or physical device

Minimum SDK: 24

Notes for Reviewers

No backend is used; all logic is implemented locally as per assignment requirements

No global mutable state

No UI logic inside the ViewModel

No blocking calls on the main thread

Focused on demonstrating understanding rather than template usage

## Author

Archit Gupta
B.Tech Computer Science and Engineering

