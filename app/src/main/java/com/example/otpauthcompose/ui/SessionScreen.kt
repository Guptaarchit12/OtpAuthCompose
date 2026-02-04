import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SessionScreen(startTime: Long, onLogout: () -> Unit) {
    // We calculate elapsed time based on the difference between "now" and "start"
    var elapsedSeconds by remember { mutableStateOf(0L) }

    LaunchedEffect(Unit) {
        val startTimestamp = System.currentTimeMillis()
        while (true) {
            // Calculate actual seconds passed since the screen opened
            elapsedSeconds = (System.currentTimeMillis() - startTimestamp) / 1000
            delay(1000)
        }
    }

    // Convert raw seconds into mm:ss
    val minutes = elapsedSeconds / 60
    val seconds = elapsedSeconds % 60
    val timeDisplay = String.format("%02d:%02d", minutes, seconds)

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Session Duration: $timeDisplay", style = MaterialTheme.typography.bodyLarge)
        Button(onClick = onLogout) {
            Text("Logout")
        }
    }
}