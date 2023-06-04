package id.android.basics.compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
private fun MyApp(
  modifier: Modifier = Modifier,
  names: List<String> = listOf("World", "Compose")) {
  Surface(color = MaterialTheme.colors.background) {
    Column(modifier = modifier.padding(vertical = 4.dp)) {
      for (name in names) {
        Greeting(name = name)
      }
    }
  }
}

@Composable
private fun Greeting(name: String) {
  Surface(
    color = MaterialTheme.colors.primary,
    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {

    Row(modifier = Modifier.padding(24.dp)) {
      Column(modifier = Modifier.weight(1f)) {
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
        onClick = {},
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)) {

        Text(text = "Show more")
      }
    }
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
    MyApp()
  }
}