package id.android.basics.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import id.android.basics.compose.ui.accounts.AccountsScreen
import id.android.basics.compose.ui.accounts.SingleAccountScreen
import id.android.basics.compose.ui.bills.BillsScreen
import id.android.basics.compose.ui.overview.OverviewScreen

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
  val screen: @Composable () -> Unit
}

/**
 * Composer app navigation destinations
 */
object Overview : MainDestination {
  override val icon = Icons.Filled.PieChart
  override val route = "overview"
  override val screen: @Composable () -> Unit = { OverviewScreen() }
}

object Accounts : MainDestination {
  override val icon = Icons.Filled.AttachMoney
  override val route = "accounts"
  override val screen: @Composable () -> Unit = { AccountsScreen() }
}

object Bills : MainDestination {
  override val icon = Icons.Filled.MoneyOff
  override val route = "bills"
  override val screen: @Composable () -> Unit = { BillsScreen() }
}

object SingleAccount : MainDestination {
  // Added for simplicity, this icon will not in fact be used, as SingleAccount isn't
  // part of the ComposerTabRow selection
  override val icon = Icons.Filled.Money
  override val route = "single_account"
  override val screen: @Composable () -> Unit = { SingleAccountScreen() }

  const val accountTypeArg = "account_type"
}