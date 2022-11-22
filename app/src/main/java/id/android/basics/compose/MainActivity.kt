package id.android.basics.compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.android.basics.compose.ui.theme.ComposerTheme

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      ComposerTheme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background) {
          People(profiles = peopleSamples())
        }
      }
    }
  }
}

data class Profile(
  val name: String,
  val title: String
)

@Composable
fun Greeting(profile: Profile) {
  Row(modifier = Modifier.padding(all = 8.dp)) {
    Image(
      painter = painterResource(R.drawable.profile_picture),
      contentDescription = "Contact Profile Picture",
      modifier = Modifier
        .size(40.dp)
        .clip(CircleShape)
        .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
    )

    Spacer(modifier = Modifier.width(8.dp))

    var isExpanded by remember { mutableStateOf(false) }

    val surfaceColor by animateColorAsState(
      if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    )

    Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
      Text(
        text = "Hi ${profile.name}!",
        color = MaterialTheme.colors.secondaryVariant,
        style = MaterialTheme.typography.subtitle2
      )

      Spacer(modifier = Modifier.height(4.dp))

      Surface(
        shape = MaterialTheme.shapes.medium,
        elevation = 1.dp,
        color = surfaceColor,
        modifier = Modifier
          .animateContentSize()
          .padding(1.dp)) {
        Text(
          text = profile.title,
          modifier = Modifier.padding(all = 4.dp),
          maxLines = if (isExpanded) Int.MAX_VALUE else 1,
          style = MaterialTheme.typography.body2
        )
      }
    }
  }
}

fun peopleSamples(): List<Profile> = listOf(
  Profile("Sidiq Permana", "GDE Android 1"),
  Profile("Andrew Kurniadi", "GDE Android 2"),
  Profile("Budi Oktaviyan", "GDE Android 3")
)

@Composable
fun People(profiles: List<Profile>) {
  LazyColumn {
    profiles.map { profile ->
      item {
        Greeting(profile)
      }
    }
  }
}

@Preview(name = "Light Mode")
@Preview(
  name = "Dark Mode",
  uiMode = Configuration.UI_MODE_NIGHT_YES,
  showBackground = true
)
@Composable
fun DefaultPreview() {
  ComposerTheme {
    Greeting(
      profile = Profile(
        "Budi Oktaviyan",
        "GDE Android"
      )
    )
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewPeople() {
  ComposerTheme {
    People(profiles = peopleSamples())
  }
}