A robust, production-ready Android application built with Jetpack Compose and Material 3. This project demonstrates a secure passwordless authentication flow, featuring reactive state management and modern UI/UX principles.

 Project Walkthrough & Setup
Watch the Demo Video (Google Drive) > Includes: Git cloning guide, technical architecture overview, and live app demonstration.

 Technical Highlights
 Modern UI/UX with Lottie
Integrated Airbnb’s Lottie for vector-based animations to provide high-end visual feedback during the Auth and Verification stages.

Fully Responsive Design using Material 3 design tokens, ensuring compatibility across different screen sizes (including foldables).

 Secure Authentication Logic
Regex Validation: Industrial-grade email validation that prevents invalid data submission at the UI layer.

OTP Lifecycle Management: - 60-Second Expiry: Implemented a real-time countdown timer using LaunchedEffect and ViewModel state.

Attempt Throttling: Security guardrail that locks or resets the session after 3 failed verification attempts.

Simulated Delivery: Integrated Snackbar API with custom durations to simulate secure OTP delivery for testing.

 Architecture & Best Practices
MVVM Architecture: Strict separation of concerns to ensure code maintainability.

Unidirectional Data Flow (UDF): UI states are managed via Sealed Interfaces (AuthUiState), making the app logic predictable and easy to test.

Professional Logging: Integrated the Timber SDK for centralized, tree-based logging, essential for production debugging.

 Project Structure
viewmodel/AuthViewModel.kt: The "Brain" of the app. Handles OTP generation, timer logic, and attempt counting.

ui/LoginScreen.kt & OtpScreen.kt: Declarative UI components featuring Lottie animations.

MainActivity.kt: The entry point managing high-level navigation and system UI.

 How to Run the Project
Clone the Repository:

Bash
git clone https://github.com/Guptaarchit12/OtpAuthCompose.git
Environment Setup:

Open the project in Android Studio (Ladybug or later).

Ensure JDK 17 is configured in your Gradle settings.

Dependency Sync:

Click the Elephant Icon to sync the build.gradle.kts files.

Resources like Lottie JSONs are located in app/src/main/res/raw.

Launch:

Run on an Emulator or Physical Device (API 24+).
