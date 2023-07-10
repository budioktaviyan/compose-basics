package id.android.basics.compose.ui

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.android.basics.compose.data.AppContainer
import id.android.basics.compose.ui.MainDestinations.ARTICLE_ID_KEY
import id.android.basics.compose.ui.article.ArticleScreen
import id.android.basics.compose.ui.home.HomeScreen
import id.android.basics.compose.ui.interests.InterestsScreen
import kotlinx.coroutines.launch

/**
 * Destinations used in the ([ComposerApp])
 */
object MainDestinations {
  const val HOME_ROUTE = "home"
  const val INTERESTS_ROUTE = "interests"
  const val ARTICLE_ROUTE = "post"
  const val ARTICLE_ID_KEY = "postId"
}

/**
 * Models the navigation actions in the app
 */
class MainActions(navController: NavHostController) {
  val navigateToArticle: (String) -> Unit = { postId: String ->
    navController.navigate("${MainDestinations.ARTICLE_ROUTE}/$postId")
  }
  val upPress: () -> Unit = {
    navController.navigateUp()
  }
}

@Composable
fun ComposerNavGraph(
  appContainer: AppContainer,
  navController: NavHostController = rememberNavController(),
  scaffoldState: ScaffoldState = rememberScaffoldState(),
  startDestination: String = MainDestinations.HOME_ROUTE) {
  val actions = remember(navController) { MainActions(navController) }
  val coroutineScope = rememberCoroutineScope()
  val openDrawer: () -> Unit = { coroutineScope.launch { scaffoldState.drawerState.open() } }
  NavHost(
    navController = navController,
    startDestination = startDestination) {
    composable(MainDestinations.HOME_ROUTE) {
      HomeScreen(
        postsRepository = appContainer.postsRepository,
        navigateToArticle = actions.navigateToArticle,
        openDrawer = openDrawer
      )
    }
    composable(MainDestinations.INTERESTS_ROUTE) {
      InterestsScreen(
        interestsRepository = appContainer.interestsRepository,
        openDrawer = openDrawer
      )
    }
    composable("${MainDestinations.ARTICLE_ROUTE}/{$ARTICLE_ID_KEY}") { backStackEntry ->
      ArticleScreen(
        postId = backStackEntry.arguments?.getString(ARTICLE_ID_KEY),
        onBack = actions.upPress,
        postsRepository = appContainer.postsRepository
      )
    }
  }
}