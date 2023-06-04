package id.android.basics.compose.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.SolidColor
import id.android.basics.compose.ui.captionTextStyle

@Composable
fun ComposerEditableUserInput(
  hint: String,
  caption: String? = null,
  @DrawableRes vectorImageId: Int? = null,
  onInputChanged: (String) -> Unit) {
  // TODO Codelab: Encapsulate this state in a state holder
  var textState by remember { mutableStateOf(hint) }
  val isHint = { textState == hint }

  ComposerBaseUserInput(
    caption = caption,
    tintIcon = { !isHint() },
    showCaption = { !isHint() },
    vectorImageId = vectorImageId) {
    BasicTextField(
      value = textState,
      onValueChange = {
        textState = it
        if (!isHint()) onInputChanged(textState)
      },
      textStyle = if (isHint()) {
        captionTextStyle.copy(color = LocalContentColor.current)
      } else {
        MaterialTheme.typography.body1.copy(color = LocalContentColor.current)
      },
      cursorBrush = SolidColor(LocalContentColor.current)
    )
  }
}