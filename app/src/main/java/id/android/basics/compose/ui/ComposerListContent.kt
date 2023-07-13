package id.android.basics.compose.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import id.android.basics.compose.R
import id.android.basics.compose.data.Email

@Composable
fun ComposerListOnlyContent(
  composerHomeUIState: ComposerHomeUIState,
  modifier: Modifier = Modifier) {
  LazyColumn(modifier = modifier) {
    item {
      ComposerSearchBar(modifier = Modifier.fillMaxWidth())
    }
    items(composerHomeUIState.emails) { email ->
      ComposerEmailListItem(email = email)
    }
  }
}

@Composable
fun ComposerListAndDetailContent(
  composerHomeUIState: ComposerHomeUIState,
  modifier: Modifier = Modifier,
  selectedItemIndex: Int = 0) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(12.dp)) {
    LazyColumn(modifier = modifier.weight(1f)) {
      items(composerHomeUIState.emails) { email ->
        ComposerEmailListItem(email = email)
      }
    }
    LazyColumn(modifier = modifier.weight(1f)) {
      items(composerHomeUIState.emails[selectedItemIndex].threads) { email ->
        ComposerEmailThreadItem(email = email)
      }
    }
  }
}

@SuppressLint("ModifierParameter")
@Composable
private fun ComposerEmailListItem(
  email: Email,
  modifier: Modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
  Card(modifier = modifier) {
    Column(modifier = Modifier
      .fillMaxWidth()
      .padding(20.dp)
    ) {
      Row(modifier = Modifier.fillMaxWidth()) {
        ComposerProfileImage(
          drawableResource = email.sender.avatar,
          description = email.sender.fullName,
        )
        Column(modifier = Modifier
          .weight(1f)
          .padding(horizontal = 12.dp, vertical = 4.dp),
          verticalArrangement = Arrangement.Center
        ) {
          Text(
            text = email.sender.firstName,
            style = MaterialTheme.typography.labelMedium
          )
          Text(
            text = email.createAt,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.outline
          )
        }
        IconButton(
          onClick = { /* TODO */ },
          modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surface)
        ) {
          Icon(
            imageVector = Icons.Default.StarBorder,
            contentDescription = "Favorite",
            tint = MaterialTheme.colorScheme.outline
          )
        }
      }
      Text(
        text = email.subject,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
      )
      Text(
        text = email.body,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 2,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        overflow = TextOverflow.Ellipsis
      )
    }
  }
}

@SuppressLint("ModifierParameter")
@Composable
private fun ComposerEmailThreadItem(
  email: Email,
  modifier: Modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
  Card(
    modifier = modifier,
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
  ) {
    Column(modifier = Modifier
      .fillMaxWidth()
      .padding(20.dp)
    ) {
      Row(modifier = Modifier.fillMaxWidth()) {
        ComposerProfileImage(
          drawableResource = email.sender.avatar,
          description = email.sender.fullName,
        )
        Column(modifier = Modifier
          .weight(1f)
          .padding(horizontal = 12.dp, vertical = 4.dp),
          verticalArrangement = Arrangement.Center
        ) {
          Text(
            text = email.sender.firstName,
            style = MaterialTheme.typography.labelMedium
          )
          Text(
            text = email.createAt,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.outline
          )
        }
        IconButton(
          onClick = { /* TODO */ },
          modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surface)
        ) {
          Icon(
            imageVector = Icons.Default.StarBorder,
            contentDescription = "Favorite",
            tint = MaterialTheme.colorScheme.outline
          )
        }
      }
      Text(
        text = email.subject,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.outline,
        modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
      )
      Text(
        text = email.body,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
      Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
      ) {
        Button(
          onClick = { /* TODO */ },
          modifier = Modifier.weight(1f),
          colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inverseOnSurface)
        ) {
          Text(
            text = stringResource(id = R.string.reply),
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
        Button(
          onClick = { /* TODO */ },
          modifier = Modifier.weight(1f),
          colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inverseOnSurface)
        ) {
          Text(
            text = stringResource(id = R.string.reply_all),
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }
  }
}

@Composable
private fun ComposerSearchBar(modifier: Modifier = Modifier) {
  Row(modifier = modifier
    .fillMaxWidth()
    .padding(16.dp)
    .background(MaterialTheme.colorScheme.surface, CircleShape),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      imageVector = Icons.Default.Search,
      contentDescription = stringResource(id = R.string.search),
      modifier = Modifier.padding(start = 16.dp),
      tint = MaterialTheme.colorScheme.outline
    )
    Text(
      text = stringResource(id = R.string.search_replies),
      modifier = Modifier
        .weight(1f)
        .padding(16.dp),
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.outline
    )
    ComposerProfileImage(
      drawableResource = R.drawable.avatar_6,
      description = stringResource(id = R.string.profile),
      modifier = Modifier
        .padding(12.dp)
        .size(32.dp)
    )
  }
}

@SuppressLint("ModifierParameter")
@Composable
private fun ComposerProfileImage(
  drawableResource: Int,
  description: String,
  modifier: Modifier = Modifier.size(40.dp)) {
  Image(
    modifier = modifier.clip(CircleShape),
    painter = painterResource(id = drawableResource),
    contentDescription = description,
  )
}