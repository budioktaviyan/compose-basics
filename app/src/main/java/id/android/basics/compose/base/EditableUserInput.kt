package id.android.basics.compose.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.SolidColor
import id.android.basics.compose.ui.captionTextStyle

class EditableUserInputState(
  private val hint: String,
  initialText: String) {

  var text by mutableStateOf(initialText)

  val isHint: Boolean
    get() = text == hint

  companion object {
    val Saver: Saver<EditableUserInputState, *> = listSaver(
      save = { listOf(it.hint, it.text) },
      restore = { EditableUserInputState(
        hint = it[0],
        initialText = it[1])
      }
    )
  }
}

@Composable
fun ComposerEditableUserInput(
  state: EditableUserInputState = rememberEditableUserInputState(hint = ""),
  caption: String? = null,
  @DrawableRes vectorImageId: Int? = null) {
  ComposerBaseUserInput(
    caption = caption,
    tintIcon = { !state.isHint },
    showCaption = { !state.isHint },
    vectorImageId = vectorImageId) {
    BasicTextField(
      value = state.text,
      onValueChange = { state.text = it },
      textStyle = if (state.isHint) {
        captionTextStyle.copy(color = LocalContentColor.current)
      } else {
        MaterialTheme.typography.body1.copy(color = LocalContentColor.current)
      },
      cursorBrush = SolidColor(LocalContentColor.current)
    )
  }
}

@Composable
fun rememberEditableUserInputState(hint: String): EditableUserInputState =
  rememberSaveable(
    hint,
    saver = EditableUserInputState.Saver
  ) { EditableUserInputState(hint, hint) }