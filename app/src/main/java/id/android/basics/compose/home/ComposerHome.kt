package id.android.basics.compose.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import id.android.basics.compose.base.ComposerDrawer
import id.android.basics.compose.base.ComposerTabBar
import id.android.basics.compose.base.ComposerTabs
import id.android.basics.compose.base.ExploreSection
import id.android.basics.compose.data.ExploreModel

typealias OnExploreItemClicked = (ExploreModel) -> Unit

enum class ComposerScreen {
  Fly,
  Sleep,
  Eat
}

@Composable
fun ComposerHome(
  onExploreItemClicked: OnExploreItemClicked,
  modifier: Modifier = Modifier) {
  val scaffoldState = rememberScaffoldState()

  Scaffold(
    scaffoldState = scaffoldState,
    modifier = Modifier.statusBarsPadding(),
    drawerContent = { ComposerDrawer() }
  ) { padding ->
    ComposerHomeContent(
      modifier = modifier.padding(padding),
      onExploreItemClicked = onExploreItemClicked,
      openDrawer = {
        // TODO Codelab: rememberCoroutineScope step - open the navigation drawer scaffoldState.drawerState.open()
      }
    )
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComposerHomeContent(
  onExploreItemClicked: OnExploreItemClicked,
  openDrawer: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: MainViewModel = viewModel()) {
  // TODO Codelab: collectAsState step - consume stream of data from the ViewModel
  val suggestedDestinations: List<ExploreModel> = remember { emptyList() }
  val onPeopleChanged: (Int) -> Unit = { viewModel.updatePeople(it) }
  var tabSelected by remember { mutableStateOf(ComposerScreen.Fly) }

  BackdropScaffold(
    modifier = modifier,
    scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed),
    frontLayerScrimColor = Color.Unspecified,
    appBar = {
      HomeTabBar(
        openDrawer,
        tabSelected,
        onTabSelected = { tabSelected = it }
      )
    },
    backLayerContent = {
      SearchContent(
        tabSelected,
        viewModel,
        onPeopleChanged
      )
    },
    frontLayerContent = {
      when (tabSelected) {
        ComposerScreen.Fly -> {
          ExploreSection(
            title = "Explore Flights by Destination",
            exploreList = suggestedDestinations,
            onItemClicked = onExploreItemClicked
          )
        }
        ComposerScreen.Sleep -> {
          ExploreSection(
            title = "Explore Properties by Destination",
            exploreList = viewModel.hotels,
            onItemClicked = onExploreItemClicked
          )
        }
        ComposerScreen.Eat -> {
          ExploreSection(
            title = "Explore Restaurants by Destination",
            exploreList = viewModel.restaurants,
            onItemClicked = onExploreItemClicked
          )
        }
      }
    })
}

@Composable
private fun HomeTabBar(
  openDrawer: () -> Unit,
  tabSelected: ComposerScreen,
  onTabSelected: (ComposerScreen) -> Unit,
  modifier: Modifier = Modifier) {
  ComposerTabBar(
    modifier = modifier,
    onMenuClicked = openDrawer
  ) { tabBarModifier ->
    ComposerTabs(
      modifier = tabBarModifier,
      titles = ComposerScreen.values().map { it.name },
      tabSelected = tabSelected,
      onTabSelected = { newTab ->
        onTabSelected(ComposerScreen.values()[newTab.ordinal])
      }
    )
  }
}

@Composable
private fun SearchContent(
  tabSelected: ComposerScreen,
  viewModel: MainViewModel,
  onPeopleChanged: (Int) -> Unit) {
  when (tabSelected) {
    ComposerScreen.Fly -> FlySearchContent(
      onPeopleChanged = onPeopleChanged,
      onToDestinationChanged = { viewModel.toDestinationChanged(it) }
    )
    ComposerScreen.Sleep -> SleepSearchContent(onPeopleChanged = onPeopleChanged)
    ComposerScreen.Eat -> EatSearchContent(onPeopleChanged = onPeopleChanged)
  }
}