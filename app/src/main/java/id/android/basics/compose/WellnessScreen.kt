package id.android.basics.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
  Column(modifier = modifier) {
    StatefulCounter()

    val list = remember {
      mutableStateListOf<WellnessTask>().apply {
        addAll(getWellnessTasks())
      }
    }
    WellnessTasksList(
      list = list,
      onCloseTask = { task -> list.remove(task) }
    )
  }
}

private fun getWellnessTasks() = List(30) { index ->
  WellnessTask(
    id = index,
    label = "Task #$index"
  )
}