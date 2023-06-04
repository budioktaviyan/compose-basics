package id.android.basics.compose.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.android.basics.compose.R
import id.android.basics.compose.base.SimpleUserInput

@Composable
fun FlySearchContent(
  onPeopleChanged: (Int) -> Unit,
  onToDestinationChanged: (String) -> Unit) {
  ComposerSearch {
    PeopleUserInput(
      onPeopleChanged = onPeopleChanged,
      titleSuffix = ", Economy"
    )
    Spacer(Modifier.height(8.dp))
    FromDestination()
    Spacer(Modifier.height(8.dp))
    ToDestinationUserInput(onToDestinationChanged = onToDestinationChanged)
    Spacer(Modifier.height(8.dp))
    DatesUserInput()
  }
}

@Composable
fun SleepSearchContent(onPeopleChanged: (Int) -> Unit) {
  ComposerSearch {
    PeopleUserInput(onPeopleChanged = onPeopleChanged)
    Spacer(Modifier.height(8.dp))
    DatesUserInput()
    Spacer(Modifier.height(8.dp))
    SimpleUserInput(
      caption = "Select Location",
      vectorImageId = R.drawable.ic_hotel
    )
  }
}

@Composable
fun EatSearchContent(onPeopleChanged: (Int) -> Unit) {
  ComposerSearch {
    PeopleUserInput(onPeopleChanged = onPeopleChanged)
    Spacer(Modifier.height(8.dp))
    DatesUserInput()
    Spacer(Modifier.height(8.dp))
    SimpleUserInput(
      caption = "Select Time",
      vectorImageId = R.drawable.ic_time
    )
    Spacer(Modifier.height(8.dp))
    SimpleUserInput(
      caption = "Select Location",
      vectorImageId = R.drawable.ic_restaurant
    )
  }
}

@Composable
private fun ComposerSearch(content: @Composable () -> Unit) {
  Column(
    Modifier.padding(
      start = 24.dp,
      top = 0.dp,
      end = 24.dp,
      bottom = 12.dp
    )
  ) {
    content()
  }
}