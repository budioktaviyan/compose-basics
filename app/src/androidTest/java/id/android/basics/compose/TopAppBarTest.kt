package id.android.basics.compose

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import id.android.basics.compose.ui.components.ComposerTopAppBar
import id.android.basics.compose.ui.theme.ComposerTheme
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun composerTopAppBarTest() {
    val allScreens = ComposerScreen.values().toList()
    composeTestRule.setContent {
      ComposerTheme {
        ComposerTopAppBar(
          allScreens = allScreens,
          onTabSelected = {},
          currentScreen = ComposerScreen.Accounts
        )
      }
    }

    composeTestRule
      .onNodeWithContentDescription(ComposerScreen.Accounts.name)
      .assertIsSelected()
  }
}