package id.android.basics.compose.ui.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import id.android.basics.compose.R
import id.android.basics.compose.data.UserData
import id.android.basics.compose.ui.components.AccountRow
import id.android.basics.compose.ui.components.BillRow
import id.android.basics.compose.ui.components.ComposerAlertDialog
import id.android.basics.compose.ui.components.ComposerDivider
import id.android.basics.compose.ui.components.formatAmount
import java.util.Locale

private const val SHOWN_ITEMS = 3

private val ComposerDefaultPadding = 12.dp

@Composable
fun OverviewScreen(
  onClickSeeAllAccounts: () -> Unit = {},
  onClickSeeAllBills: () -> Unit = {},
  onAccountClick: (String) -> Unit = {}) {
  Column(
    modifier = Modifier
    .padding(16.dp)
    .verticalScroll(rememberScrollState())
    .semantics {
      contentDescription = "Overview Screen"
    }
  ) {
    AlertCard()
    Spacer(Modifier.height(ComposerDefaultPadding))
    AccountsCard(
      onClickSeeAll = onClickSeeAllAccounts,
      onAccountClick = onAccountClick
    )
    Spacer(Modifier.height(ComposerDefaultPadding))
    BillsCard(onClickSeeAll = onClickSeeAllBills)
  }
}

/**
 * The Alerts card within the Composer Overview screen
 */
@Composable
private fun AlertCard() {
  var showDialog by remember { mutableStateOf(false) }
  val alertMessage = "Heads up, you've used up 90% of your Shopping budget for this month"

  if (showDialog) {
    ComposerAlertDialog(
      onDismiss = {
        showDialog = false
      },
      bodyText = alertMessage,
      buttonText = "Dismiss".uppercase(Locale.getDefault())
    )
  }
  Card {
    Column {
      AlertHeader {
        showDialog = true
      }
      ComposerDivider(
        modifier = Modifier.padding(
          start = ComposerDefaultPadding,
          end = ComposerDefaultPadding
        )
      )
      AlertItem(alertMessage)
    }
  }
}

@Composable
private fun AlertHeader(onClickSeeAll: () -> Unit) {
  Row(
    modifier = Modifier
      .padding(ComposerDefaultPadding)
      .fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      text = "Alerts",
      style = MaterialTheme.typography.subtitle2,
      modifier = Modifier.align(Alignment.CenterVertically)
    )
    TextButton(
      onClick = onClickSeeAll,
      contentPadding = PaddingValues(0.dp),
      modifier = Modifier.align(Alignment.CenterVertically)
    ) {
      Text(
        text = "SEE ALL",
        style = MaterialTheme.typography.button
      )
    }
  }
}

@Composable
private fun AlertItem(message: String) {
  Row(
    modifier = Modifier
      .padding(ComposerDefaultPadding)
      /**
       * Regard the whole row as one semantics node. This way each row will receive focus as
       * a whole and the focus bounds will be around the whole row content. The semantics
       * properties of the descendants will be merged. If we'd use clearAndSetSemantics instead,
       * we'd have to define the semantics properties explicitly
       */
      .semantics(mergeDescendants = true) {},
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      style = MaterialTheme.typography.body2,
      modifier = Modifier.weight(1f),
      text = message
    )
    IconButton(
      onClick = {},
      modifier = Modifier
      .align(Alignment.Top)
      .clearAndSetSemantics {}) {
      Icon(
        Icons.Filled.Sort,
        contentDescription = null
      )
    }
  }
}

/**
 * The Accounts card within the Composer Overview screen
 */
@Composable
private fun AccountsCard(
  onClickSeeAll: () -> Unit,
  onAccountClick: (String) -> Unit) {
  val amount = UserData.accounts.map { account -> account.balance }.sum()

  OverviewScreenCard(
    title = stringResource(R.string.accounts),
    amount = amount,
    onClickSeeAll = onClickSeeAll,
    data = UserData.accounts,
    colors = { it.color },
    values = { it.balance }
  ) { account ->
    AccountRow(
      modifier = Modifier.clickable { onAccountClick(account.name) },
      name = account.name,
      number = account.number,
      amount = account.balance,
      color = account.color
    )
  }
}

/**
 * The Bills card within the Composer Overview screen
 */
@Composable
private fun BillsCard(onClickSeeAll: () -> Unit) {
  val amount = UserData.bills.map { bill -> bill.amount }.sum()

  OverviewScreenCard(
    title = stringResource(R.string.bills),
    amount = amount,
    onClickSeeAll = onClickSeeAll,
    data = UserData.bills,
    colors = { it.color },
    values = { it.amount }) { bill ->
    BillRow(
      name = bill.name,
      due = bill.due,
      amount = bill.amount,
      color = bill.color
    )
  }
}

/**
 * Base structure for cards in the Overview screen
 */
@Composable
private fun <T> OverviewScreenCard(
  title: String,
  amount: Float,
  onClickSeeAll: () -> Unit,
  values: (T) -> Float,
  colors: (T) -> Color,
  data: List<T>,
  row: @Composable (T) -> Unit) {
  Card {
    Column {
      Column(Modifier.padding(ComposerDefaultPadding)) {
        Text(
          text = title,
          style = MaterialTheme.typography.subtitle2
        )

        val amountText = "$${formatAmount(amount)}"
        Text(
          text = amountText,
          style = MaterialTheme.typography.h2
        )
      }
      OverViewDivider(
        data,
        values,
        colors
      )
      Column(
        Modifier.padding(
          start = 16.dp,
          top = 4.dp,
          end = 8.dp
        )
      ) {
        data.take(SHOWN_ITEMS).forEach { row(it) }
        SeeAllButton(
          modifier = Modifier.clearAndSetSemantics {
            contentDescription = "All $title"
          },
          onClick = onClickSeeAll
        )
      }
    }
  }
}

@Composable
private fun <T> OverViewDivider(
  data: List<T>,
  values: (T) -> Float,
  colors: (T) -> Color) {
  Row(Modifier.fillMaxWidth()) {
    data.forEach { item: T ->
      Spacer(
        modifier = Modifier
          .weight(values(item))
          .height(1.dp)
          .background(colors(item))
      )
    }
  }
}

@Composable
private fun SeeAllButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit) {
  TextButton(
    onClick = onClick,
    modifier = modifier
      .height(44.dp)
      .fillMaxWidth()
  ) {
    Text(text = stringResource(R.string.see_all))
  }
}