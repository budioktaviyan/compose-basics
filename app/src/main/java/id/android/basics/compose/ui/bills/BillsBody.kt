package id.android.basics.compose.ui.bills

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import id.android.basics.compose.R
import id.android.basics.compose.data.Bill
import id.android.basics.compose.ui.components.BillRow
import id.android.basics.compose.ui.components.StatementBody

/**
 * The Bills screen
 */
@Composable
fun BillsBody(bills: List<Bill>) {
  StatementBody(
    items = bills,
    amounts = { bill -> bill.amount },
    colors = { bill -> bill.color },
    amountsTotal = bills.map { bill -> bill.amount }.sum(),
    circleLabel = stringResource(R.string.due),
    rows = { bill ->
      BillRow(
        bill.name,
        bill.due,
        bill.amount,
        bill.color
      )
    }
  )
}