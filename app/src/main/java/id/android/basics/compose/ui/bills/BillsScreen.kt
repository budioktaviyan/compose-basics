package id.android.basics.compose.ui.bills

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import id.android.basics.compose.R
import id.android.basics.compose.data.Bill
import id.android.basics.compose.data.UserData
import id.android.basics.compose.ui.components.BillRow
import id.android.basics.compose.ui.components.StatementBody

/**
 * The Bills screen
 */
@Composable
fun BillsScreen(bills: List<Bill> = remember { UserData.bills } ) {
  StatementBody(
    modifier = Modifier.clearAndSetSemantics {
      contentDescription = "Bills"
    },
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