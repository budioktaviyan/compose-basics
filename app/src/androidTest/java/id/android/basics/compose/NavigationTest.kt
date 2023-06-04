package id.android.basics.compose

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

  @get: Rule
  val composeTestRule = createComposeRule()

  private lateinit var navController: TestNavHostController

  @Before
  fun setupComposerNavHost() {
    composeTestRule.setContent {
      // Creates a TestNavHostController
      navController = TestNavHostController(context = LocalContext.current)

      // Sets a ComposeNavigator to the navController so it can navigate through composables
      navController.navigatorProvider.addNavigator(ComposeNavigator())

      ComposerNavHost(navController = navController)
    }
  }

  @Test
  fun composerNavHost_verifyOverviewStartDestination() {
    composeTestRule
      .onNodeWithContentDescription("Overview Screen")
      .assertIsDisplayed()
  }

  @Test
  fun composerNavHost_clickAllAccount_navigatesToAccounts() {
    composeTestRule
      .onNodeWithContentDescription("All Accounts")
      .performClick()

    composeTestRule
      .onNodeWithContentDescription("Accounts Screen")
      .assertIsDisplayed()
  }

  @Test
  fun composerNavHost_clickAllBills_navigateToBills() {
    composeTestRule
      .onNodeWithContentDescription("All Bills")
      .performScrollTo()
      .performClick()

    val route = navController.currentBackStackEntry?.destination?.route
    assertEquals(route, "bills")
  }
}