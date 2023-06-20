package id.android.basics.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import id.android.basics.compose.ui.overview.OverviewBody
import id.android.basics.compose.ui.theme.ComposerTheme
import org.junit.Rule
import org.junit.Test

class OverviewScreenTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun overviewScreen_alertsDisplayed() {
    composeTestRule.setContent {
      ComposerTheme {
        OverviewBody()
      }
    }

    composeTestRule
      .onNodeWithText("Alerts")
      .assertIsDisplayed()
  }
}