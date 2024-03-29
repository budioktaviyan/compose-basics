package id.android.basics.compose.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuOpen
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import id.android.basics.compose.R
import id.android.basics.compose.ui.utils.ComposerContentType
import id.android.basics.compose.ui.utils.ComposerNavigationType
import id.android.basics.compose.ui.utils.DevicePosture
import kotlinx.coroutines.launch

@Composable
fun ComposerApp(
  composerHomeUIState: ComposerHomeUIState,
  windowSize: WindowWidthSizeClass,
  foldingDevicePosture: DevicePosture) {
  /**
   * This will help us select type of navigation and content type depending on window size and fold state of the device
   */
  val navigationType: ComposerNavigationType
  val contentType: ComposerContentType
  when (windowSize) {
    WindowWidthSizeClass.Compact -> {
      navigationType = ComposerNavigationType.BOTTOM_NAVIGATION
      contentType = ComposerContentType.LIST_ONLY
    }
    WindowWidthSizeClass.Medium -> {
      navigationType = ComposerNavigationType.NAVIGATION_RAIL
      contentType = if (foldingDevicePosture is DevicePosture.BookPosture || foldingDevicePosture is DevicePosture.Separating) {
        ComposerContentType.LIST_AND_DETAIL
      } else {
        ComposerContentType.LIST_ONLY
      }
    }
    WindowWidthSizeClass.Expanded -> {
      navigationType = if (foldingDevicePosture is DevicePosture.BookPosture) {
        ComposerNavigationType.NAVIGATION_RAIL
      } else {
        ComposerNavigationType.PERMANENT_NAVIGATION_DRAWER
      }
      contentType = ComposerContentType.LIST_AND_DETAIL
    }
    else -> {
      navigationType = ComposerNavigationType.BOTTOM_NAVIGATION
      contentType = ComposerContentType.LIST_ONLY
    }
  }
  ComposerNavigationWrapperUI(navigationType, contentType, composerHomeUIState)
}

@Composable
private fun ComposerNavigationWrapperUI(
  navigationType: ComposerNavigationType,
  contentType: ComposerContentType,
  composerHomeUIState: ComposerHomeUIState) {
  val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
  val scope = rememberCoroutineScope()
  val selectedDestination = ComposerDestinations.INBOX
  if (navigationType == ComposerNavigationType.PERMANENT_NAVIGATION_DRAWER) {
    PermanentNavigationDrawer(
      drawerContent = {
        PermanentDrawerSheet {
          NavigationDrawerContent(selectedDestination)
        }
      }
    ) { ComposerAppContent(navigationType, contentType, composerHomeUIState) }
  } else {
    ModalNavigationDrawer(
      drawerContent = {
        ModalDrawerSheet {
          NavigationDrawerContent(
            selectedDestination = selectedDestination,
            onDrawerClicked = {
              scope.launch { drawerState.close() }
            }
          )
        }
      },
      drawerState = drawerState
    ) {
      ComposerAppContent(
        navigationType,
        contentType,
        composerHomeUIState,
        onDrawerClicked = {
          scope.launch { drawerState.open() }
        }
      )
    }
  }
}

@Composable
private fun ComposerAppContent(
  navigationType: ComposerNavigationType,
  contentType: ComposerContentType,
  composerHomeUIState: ComposerHomeUIState,
  onDrawerClicked: () -> Unit = {}) {
  Row(modifier = Modifier.fillMaxSize()) {
    AnimatedVisibility(visible = navigationType == ComposerNavigationType.NAVIGATION_RAIL) {
      ComposerNavigationRail(onDrawerClicked = onDrawerClicked)
    }
    Column(modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.inverseOnSurface)
    ) {
      if (contentType == ComposerContentType.LIST_AND_DETAIL) {
        ComposerListAndDetailContent(
          composerHomeUIState = composerHomeUIState,
          modifier = Modifier.weight(1f)
        )
      } else {
        ComposerListOnlyContent(
          composerHomeUIState = composerHomeUIState,
          modifier = Modifier.weight(1f)
        )
      }
      AnimatedVisibility(visible = navigationType == ComposerNavigationType.BOTTOM_NAVIGATION) {
        ComposerBottomNavigationBar()
      }
    }
  }
}

@Composable
private fun ComposerBottomNavigationBar() {
  NavigationBar(modifier = Modifier.fillMaxWidth()) {
    NavigationBarItem(
      selected = true,
      onClick = { /* TODO */ },
      icon = {
        Icon(
          imageVector = Icons.Default.Inbox,
          contentDescription = stringResource(id = R.string.tab_inbox)
        )
      }
    )
    NavigationBarItem(
      selected = false,
      onClick = { /* TODO */ },
      icon = {
        Icon(
          imageVector = Icons.Default.Article,
          contentDescription = stringResource(id = R.string.tab_inbox)
        )
      }
    )
    NavigationBarItem(
      selected = false,
      onClick = { /* TODO */ },
      icon = {
        Icon(
          imageVector = Icons.Outlined.Chat,
          contentDescription = stringResource(id = R.string.tab_inbox)
        )
      }
    )
    NavigationBarItem(
      selected = false,
      onClick = { /* TODO */ },
      icon = {
        Icon(
          imageVector = Icons.Outlined.Videocam,
          contentDescription = stringResource(id = R.string.tab_inbox)
        )
      }
    )
  }
}

@Composable
private fun ComposerNavigationRail(onDrawerClicked: () -> Unit = {}) {
  NavigationRail(modifier = Modifier.fillMaxHeight()) {
    NavigationRailItem(
      selected = false,
      onClick = onDrawerClicked, icon = {
        Icon(
          imageVector = Icons.Default.Menu,
          contentDescription = stringResource(id = R.string.navigation_drawer)
        )
      }
    )
    NavigationRailItem(
      selected = true,
      onClick = { /* TODO */ },
      icon = {
        Icon(
          imageVector = Icons.Default.Inbox,
          contentDescription = stringResource(id = R.string.tab_inbox)
        )
      }
    )
    NavigationRailItem(
      selected = false,
      onClick = { /* TODO */ },
      icon = {
        Icon(
          imageVector = Icons.Default.Article,
          stringResource(id = R.string.tab_article)
        )
      }
    )
    NavigationRailItem(
      selected = false,
      onClick = { /* TODO */ },
      icon = {
        Icon(
          imageVector = Icons.Outlined.Chat,
          stringResource(id = R.string.tab_dm)
        )
      }
    )
    NavigationRailItem(
      selected = false,
      onClick = { /* TODO */ },
      icon = {
        Icon(
          imageVector = Icons.Outlined.People,
          stringResource(id = R.string.tab_groups)
        )
      }
    )
  }
}

@Composable
private fun NavigationDrawerContent(
  selectedDestination: String,
  modifier: Modifier = Modifier,
  onDrawerClicked: () -> Unit = {}) {
  Column(modifier
    .wrapContentWidth()
    .fillMaxHeight()
    .background(MaterialTheme.colorScheme.inverseOnSurface)
    .padding(24.dp)
  ) {
    Row(modifier = modifier
      .fillMaxWidth()
      .padding(16.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = stringResource(id = R.string.app_name).uppercase(),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary
      )
      IconButton(onClick = onDrawerClicked) {
        Icon(
          imageVector = Icons.Default.MenuOpen,
          contentDescription = stringResource(id = R.string.navigation_drawer)
        )
      }
    }
    NavigationDrawerItem(
      selected = selectedDestination == ComposerDestinations.INBOX,
      label = {
        Text(
          text = stringResource(id = R.string.tab_inbox),
          modifier = Modifier.padding(horizontal = 16.dp)
        )
      },
      icon = {
        Icon(
          imageVector = Icons.Default.Inbox,
          contentDescription = stringResource(id = R.string.tab_inbox)
        )
      },
      colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent),
      onClick = { /* TODO */ })
    NavigationDrawerItem(
      selected = selectedDestination == ComposerDestinations.ARTICLES,
      label = {
        Text(
          text = stringResource(id = R.string.tab_article),
          modifier = Modifier.padding(horizontal = 16.dp)
        )
      },
      icon = {
        Icon(
          imageVector = Icons.Default.Article,
          contentDescription = stringResource(id = R.string.tab_article)
        )
      },
      colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent),
      onClick = { /* TODO */ })
    NavigationDrawerItem(
      selected = selectedDestination == ComposerDestinations.DM,
      label = {
        Text(
          text = stringResource(id = R.string.tab_dm),
          modifier = Modifier.padding(horizontal = 16.dp)
        )
      },
      icon = {
        Icon(
          imageVector = Icons.Default.Chat,
          contentDescription = stringResource(id = R.string.tab_dm)
        )
      },
      colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent),
      onClick = { /* TODO */ })
    NavigationDrawerItem(
      selected = selectedDestination == ComposerDestinations.GROUPS,
      label = {
        Text(
          text = stringResource(id = R.string.tab_groups),
          modifier = Modifier.padding(horizontal = 16.dp)
        )
      },
      icon = {
        Icon(
          imageVector = Icons.Default.Article,
          contentDescription = stringResource(id = R.string.tab_groups)
        )
      },
      colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent),
      onClick = { /* TODO */ })
  }
}