package id.android.basics.compose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import id.android.basics.compose.ui.theme.ComposerDialogThemeOverlay

@Composable
fun ComposerAlertDialog(
  onDismiss: () -> Unit,
  bodyText: String,
  buttonText: String) {
  ComposerDialogThemeOverlay {
    AlertDialog(
      onDismissRequest = onDismiss,
      text = { Text(text = bodyText) },
      buttons = {
        Column {
          Divider(
            modifier = Modifier.padding(horizontal = 12.dp),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
          )
          TextButton(
            onClick = onDismiss,
            shape = RectangleShape,
            contentPadding = PaddingValues(all = 16.dp),
            modifier = Modifier.fillMaxWidth()) {
            Text(text = buttonText)
          }
        }
      }
    )
  }
}