package id.android.basics.compose

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import id.android.basics.compose.ui.components.ComposerTopAppBar
import id.android.basics.compose.ui.theme.ComposerTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Before
  fun setup() {
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
  }

  @Test
  fun composerTopAppBarTest() {
    composeTestRule
      .onNodeWithContentDescription(ComposerScreen.Accounts.name)
      .assertIsSelected()
  }

  @Test
  fun composerTopAppBarTest_currentLabelExists() {
    composeTestRule
      .onRoot(useUnmergedTree = true)
      .printToLog("currentLabelExists")

    composeTestRule.onNode(
      hasText(ComposerScreen.Accounts.name.uppercase()) and
      hasParent(hasContentDescription(ComposerScreen.Accounts.name)),
      useUnmergedTree = true
    ).assertExists()
  }
}