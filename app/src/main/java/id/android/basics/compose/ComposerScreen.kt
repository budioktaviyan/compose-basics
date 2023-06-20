package id.android.basics.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import id.android.basics.compose.data.UserData
import id.android.basics.compose.ui.accounts.AccountsBody
import id.android.basics.compose.ui.bills.BillsBody
import id.android.basics.compose.ui.overview.OverviewBody

/**
 * Screen state for Composer. Navigation is kept simple until a proper mechanism is available. Back
 * navigation is not supported
 */
enum class ComposerScreen(val icon: ImageVector, private val body: @Composable ((ComposerScreen) -> Unit) -> Unit) {
  Overview(
    icon = Icons.Filled.PieChart,
    body = { onScreenChange -> OverviewBody(onScreenChange) }
  ),
  Accounts(
    icon = Icons.Filled.AttachMoney,
    body = { AccountsBody(UserData.accounts) }
  ),
  Bills(
    icon = Icons.Filled.MoneyOff,
    body = { BillsBody(UserData.bills) }
  );

  @Composable
  fun Content(onScreenChange: (ComposerScreen) -> Unit) {
    body(onScreenChange)
  }
}