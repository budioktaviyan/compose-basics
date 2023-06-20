package id.android.basics.compose

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.MainTestClock
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.dp
import androidx.test.filters.SdkSuppress
import id.android.basics.compose.ui.components.AnimatedCircle
import id.android.basics.compose.ui.theme.ComposerTheme
import org.junit.Rule
import org.junit.Test

/**
 * Test to showcase [MainTestClock] present in [ComposeTestRule]. It allows for animation
 * testing at specific points in time
 *
 * For assertions, a simple screenshot testing framework is used. It requires SDK 30+ and to
 * be run on a device with 420dpi, as that the density used to generate the golden images
 * present in androidTest/assets. It runs bitmap comparisons on device
 *
 * Note that different systems can produce slightly different screenshots making the test fail
 */
@ExperimentalTestApi
@SdkSuppress(minSdkVersion = Build.VERSION_CODES.R)
class AnimatingCircleTests {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun circleAnimation_idle_screenshot() {
    composeTestRule.mainClock.autoAdvance = true
    showAnimatedCircle()
    assertScreenshotMatchesGolden("circle_done", composeTestRule.onRoot())
  }

  @Test
  fun circleAnimation_initial_screenshot() {
    compareTimeScreenshot(0, "circle_initial")
  }

  @Test
  fun circleAnimation_beforeDelay_screenshot() {
    compareTimeScreenshot(499, "circle_initial")
  }

  @Test
  fun circleAnimation_midAnimation_screenshot() {
    compareTimeScreenshot(600, "circle_100")
  }

  @Test
  fun circleAnimation_animationDone_screenshot() {
    compareTimeScreenshot(1500, "circle_done")
  }

  private fun compareTimeScreenshot(timeMs: Long, goldenName: String) {
    // Start with a paused clock
    composeTestRule.mainClock.autoAdvance = false

    // Start the unit under test
    showAnimatedCircle()

    // Advance clock (keeping it paused)
    composeTestRule.mainClock.advanceTimeBy(timeMs)

    // Take screenshot and compare with golden image in androidTest/assets
    assertScreenshotMatchesGolden(goldenName, composeTestRule.onRoot())
  }

  private fun showAnimatedCircle() {
    composeTestRule.setContent {
      ComposerTheme {
        AnimatedCircle(
          modifier = Modifier
            .background(Color.White)
            .size(320.dp),
          proportions = listOf(0.25f, 0.5f, 0.25f),
          colors = listOf(Color.Red, Color.DarkGray, Color.Black)
        )
      }
    }
  }
}