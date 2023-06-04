package id.android.basics.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.android.basics.compose.ui.theme.ComposerTheme
import java.util.Locale

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {}
  }
}

@Composable
private fun SearchBar(modifier: Modifier = Modifier) {
  TextField(
    value = "",
    onValueChange = {},
    leadingIcon = {
      Icon(
        imageVector = Icons.Default.Search,
        contentDescription = null
      )
    },
    colors = TextFieldDefaults.textFieldColors(
      backgroundColor = MaterialTheme.colors.surface
    ),
    placeholder = {
      Text(text = stringResource(id = R.string.placeholder_search))
    },
    modifier = modifier
      .fillMaxWidth()
      .heightIn(min = 56.dp)
  )
}

@Composable
private fun AlignYourBodyElement(
  @DrawableRes drawable: Int,
  @StringRes text: Int,
  modifier: Modifier = Modifier) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally) {
    Image(
      painter = painterResource(id = drawable),
      contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .size(88.dp)
        .clip(CircleShape)
    )
    Text(
      text = stringResource(id = text),
      style = MaterialTheme.typography.h3,
      modifier = Modifier.paddingFromBaseline(
        top = 24.dp,
        bottom = 8.dp
      )
    )
  }
}

@Composable
private fun FavoriteCollectionCard(
  @DrawableRes drawable: Int,
  @StringRes text: Int,
  modifier: Modifier = Modifier) {
  Surface(
    shape = MaterialTheme.shapes.small,
    modifier = modifier) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.width(192.dp)) {
      Image(
        painter = painterResource(id = drawable),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(56.dp)
      )
      Text(
        text = stringResource(id = text),
        style = MaterialTheme.typography.h3,
        modifier = Modifier.padding(horizontal = 16.dp)
      )
    }
  }
}

@Composable
private fun AlignYourBodyRow(modifier: Modifier = Modifier) {
  LazyRow(
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    contentPadding = PaddingValues(horizontal = 16.dp),
    modifier = modifier) {
    items(alignYourBodyData) { item ->
      AlignYourBodyElement(
        drawable = item.drawable,
        text = item.text
      )
    }
  }
}

@Composable
private fun FavoriteCollectionsGrid(modifier: Modifier = Modifier) {
  LazyHorizontalGrid(
    rows = GridCells.Fixed(2),
    contentPadding = PaddingValues(horizontal = 16.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    modifier = modifier.height(120.dp)) {
    items(favoriteCollectionsData) { item ->
      FavoriteCollectionCard(
        drawable = item.drawable,
        text = item.text,
        modifier = Modifier.height(56.dp)
      )
    }
  }
}

@Composable
private fun HomeSection(
  @StringRes title: Int,
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit) {
  Column(modifier) {
    Text(
      text = stringResource(id = title).uppercase(Locale.getDefault()),
      style = MaterialTheme.typography.h2,
      modifier = Modifier
        .paddingFromBaseline(
          top = 40.dp,
          bottom = 8.dp
        )
        .padding(horizontal = 16.dp)
    )
    content()
  }
}

@Composable
private fun HomeScreen(modifier: Modifier = Modifier) {
  Column(
    modifier
      .verticalScroll(rememberScrollState())
      .padding(vertical = 16.dp)) {
    SearchBar(modifier = Modifier.padding(horizontal = 16.dp))
    HomeSection(title = R.string.align_your_body) {
      AlignYourBodyRow()
    }
    HomeSection(title = R.string.favorite_collections) {
      FavoriteCollectionsGrid()
    }
  }
}

@Composable
private fun MenuBottomNavigation(modifier: Modifier = Modifier) {
  BottomNavigation(
    backgroundColor = MaterialTheme.colors.background,
    modifier = modifier) {
    BottomNavigationItem(
      icon = {
        Icon(
          imageVector = Icons.Default.Spa,
          contentDescription = null
        )
      },
      label = {
        Text(text = stringResource(id = R.string.bottom_navigation_home))
      },
      selected = true,
      onClick = {}
    )
    BottomNavigationItem(
      icon = {
        Icon(
          imageVector = Icons.Default.AccountCircle,
          contentDescription = null
        )
      },
      label = {
        Text(text = stringResource(id = R.string.bottom_navigation_profile))
      },
      selected = false,
      onClick = {}
    )
  }
}

@Preview(
  showBackground = true,
  backgroundColor = 0xFFF0EAE2
)
@Composable
private fun SearchBarPreview() {
  ComposerTheme { SearchBar(modifier = Modifier.padding(8.dp)) }
}

@Preview(
  showBackground = true,
  backgroundColor = 0xFFF0EAE2
)
@Composable
private fun AlignYourBodyElementPreview() {
  ComposerTheme {
    AlignYourBodyElement(
      text = R.string.ab1_inversions,
      drawable = R.drawable.ab1_inversions,
      modifier = Modifier.padding(8.dp)
    )
  }
}

@Preview(
  showBackground = true,
  backgroundColor = 0xFFF0EAE2
)
@Composable
private fun FavoriteCollectionCardPreview() {
  ComposerTheme {
    FavoriteCollectionCard(
      text = R.string.fc2_nature_meditations,
      drawable = R.drawable.fc2_nature_meditations,
      modifier = Modifier.padding(8.dp)
    )
  }
}

@Preview(
  showBackground = true,
  backgroundColor = 0xFFF0EAE2
)
@Composable
private fun AlignYourBodyRowPreview() {
  ComposerTheme { AlignYourBodyRow() }
}

@Preview(
  showBackground = true,
  backgroundColor = 0xFFF0EAE2
)
@Composable
private fun FavoriteCollectionsGridPreview() {
  ComposerTheme { FavoriteCollectionsGrid() }
}

@Preview(
  showBackground = true,
  backgroundColor = 0xFFF0EAE2
)
@Composable
private fun HomeSectionPreview() {
  ComposerTheme {
    HomeSection(title = R.string.align_your_body) {
      AlignYourBodyRow()
    }
  }
}

@Preview(
  showBackground = true,
  backgroundColor = 0xFFF0EAE2,
  heightDp = 180
)
@Composable
private fun ScreenContentPreview() {
  ComposerTheme { HomeScreen() }
}

@Preview(
  showBackground = true,
  backgroundColor = 0xFFF0EAE2
)
@Composable
private fun MenuBottomNavigationPreview() {
  ComposerTheme { MenuBottomNavigation(modifier = Modifier.padding(top = 24.dp)) }
}