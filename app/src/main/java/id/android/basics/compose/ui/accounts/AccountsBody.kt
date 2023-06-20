package id.android.basics.compose.ui.accounts

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import id.android.basics.compose.R
import id.android.basics.compose.data.Account
import id.android.basics.compose.ui.components.AccountRow
import id.android.basics.compose.ui.components.StatementBody

/**
 * The Accounts screen
 */
@Composable
fun AccountsBody(accounts: List<Account>) {
  StatementBody(
    items = accounts,
    amounts = { account -> account.balance },
    colors = { account -> account.color },
    amountsTotal = accounts.map { account -> account.balance }.sum(),
    circleLabel = stringResource(R.string.total),
    rows = { account ->
      AccountRow(
        name = account.name,
        number = account.number,
        amount = account.balance,
        color = account.color
      )
    }
  )
}