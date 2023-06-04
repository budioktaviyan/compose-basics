package id.android.basics.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

// Screens to be displayed in the top ComposerTabRow
val composerTabRowScreens = listOf(
  Overview,
  Accounts,
  Bills
)

/**
 * Contract for information needed on every Composer navigation destination
 */
interface MainDestination {
  val icon: ImageVector
  val route: String
}

/**
 * Composer app navigation destinations
 */
object Overview : MainDestination {
  override val icon = Icons.Filled.PieChart
  override val route = "overview"
}

object Accounts : MainDestination {
  override val icon = Icons.Filled.AttachMoney
  override val route = "accounts"
}

object Bills : MainDestination {
  override val icon = Icons.Filled.MoneyOff
  override val route = "bills"
}

object SingleAccount : MainDestination {
  // Added for simplicity, this icon will not in fact be used, as SingleAccount isn't
  // part of the ComposerTabRow selection
  override val icon = Icons.Filled.Money
  override val route = "single_account"

  const val accountTypeArg = "account_type"

  val routeWithArgs = "${route}/{${accountTypeArg}}"
  val arguments = listOf(navArgument(accountTypeArg) { type = NavType.StringType })
}