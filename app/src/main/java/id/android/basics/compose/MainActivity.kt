package id.android.basics.compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.android.basics.compose.ui.theme.ComposerTheme

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      ComposerTheme {
        MyApp(modifier = Modifier.fillMaxSize())
      }
    }
  }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
  var shouldShowOnboarding by remember { mutableStateOf(true) }

  Surface(
    modifier,
    color = MaterialTheme.colors.background) {

    if (shouldShowOnboarding) {
      OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    } else {
      Greetings()
    }
  }
}

@Composable
private fun OnboardingScreen(onContinueClicked: () -> Unit, modifier: Modifier = Modifier) {
  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
    Text(
      text = "Welcome to the Basics Codelab!",
      color = MaterialTheme.colors.secondary
    )
    Button(
      modifier = Modifier.padding(vertical = 24.dp),
      onClick = onContinueClicked,
      colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)) {

      Text(
        text = "Continue",
        color = MaterialTheme.colors.background
      )
    }
  }
}

@Composable
private fun Greetings(
  modifier: Modifier = Modifier,
  names: List<String> = listOf("World", "Compose")) {
  Column(modifier = modifier.padding(vertical = 4.dp)) {
    for (name in names) {
      Greeting(name = name)
    }
  }
}

@Composable
private fun Greeting(name: String) {
  var isExpanded by remember { mutableStateOf(false) }

  val extraPadding = if (isExpanded) 48.dp else 0.dp

  Surface(
    color = MaterialTheme.colors.primary,
    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {

    Row(modifier = Modifier.padding(24.dp)) {
      Column(
        modifier = Modifier
          .weight(1f)
          .padding(bottom = extraPadding)) {
        Text(
          text = "Hello,",
          color = MaterialTheme.colors.secondary
        )
        Text(
          text = name,
          color = MaterialTheme.colors.secondary
        )
      }
      Button(
        onClick = { isExpanded = !isExpanded },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)) {

        Text(text = if (isExpanded) "Show less" else "Show more")
      }
    }
  }
}

@Preview(
  name = "Dark Mode",
  uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
  name = "Light Mode",
  uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun MyAppPreview() {
  ComposerTheme {
    MyApp(Modifier.fillMaxSize())
  }
}

@Preview(
  name = "Dark Mode",
  uiMode = Configuration.UI_MODE_NIGHT_YES,
  showBackground = true,
  widthDp = 320,
  heightDp = 320
)
@Preview(
  name = "Light Mode",
  uiMode = Configuration.UI_MODE_NIGHT_NO,
  showBackground = true,
  widthDp = 320,
  heightDp = 320
)
@Composable
fun OnboardingPreview() {
  ComposerTheme {
    OnboardingScreen(onContinueClicked = {})
  }
}

@Preview(
  name = "Dark Mode",
  uiMode = Configuration.UI_MODE_NIGHT_YES,
  widthDp = 320
)
@Preview(
  name = "Light Mode",
  uiMode = Configuration.UI_MODE_NIGHT_NO,
  widthDp = 320
)
@Composable
fun DefaultPreview() {
  ComposerTheme {
    Greetings()
  }
}