package id.android.basics.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.android.basics.compose.ui.accounts.AccountsScreen
import id.android.basics.compose.ui.accounts.SingleAccountScreen
import id.android.basics.compose.ui.bills.BillsScreen
import id.android.basics.compose.ui.components.ComposerTabRow
import id.android.basics.compose.ui.overview.OverviewScreen
import id.android.basics.compose.ui.theme.ComposerTheme

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      MainApp()
    }
  }
}

@Composable
fun MainApp() {
  ComposerTheme {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()

    // Fetch your currentDestination
    val currentDestination = currentBackStack?.destination

    // Change the variable to this and use Overview as a backup screen if this returns null
    val currentScreen = composerTabRowScreens.find { screen ->
      screen.route == currentDestination?.route
    } ?: Overview

    Scaffold(
      topBar = {
        ComposerTabRow(
          allScreens = composerTabRowScreens,
          // Pass the callback like this,
          // defining the navigation action when a tab is selecte
          onTabSelected = { newScreen ->
            navController.navigateSingleTopTo(newScreen.route)
          },
          currentScreen = currentScreen
        )
      }
    ) { innerPadding ->
      NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = Modifier.padding(innerPadding)
      ) {
        composable(route = Overview.route) {
          OverviewScreen(
            onClickSeeAllAccounts = {
              navController.navigateSingleTopTo(Accounts.route)
            },
            onClickSeeAllBills = {
              navController.navigateSingleTopTo(Bills.route)
          },
            onAccountClick = { accountType ->
              navController.navigateToSingleAccount(accountType = accountType)
            }
          )
        }
        composable(route = Accounts.route) {
          AccountsScreen(
            onAccountClick = { accountType ->
              navController.navigateToSingleAccount(accountType = accountType)
            }
          )
        }
        composable(route = Bills.route) {
          BillsScreen()
        }
        composable(
          route = SingleAccount.routeWithArgs,
          arguments = SingleAccount.arguments,
          deepLinks = SingleAccount.deepLinks
        ) { navBackStackEntry ->
          // Retrieve the passed argument
          val accountType = navBackStackEntry.arguments?.getString(SingleAccount.accountTypeArg)

          // Pass accountType to SingleAccountScreen
          SingleAccountScreen(accountType = accountType)
        }
      }
    }
  }
}

private fun NavController.navigateSingleTopTo(route: String) = this.navigate(route = route) {
  popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
    saveState = true
  }
  launchSingleTop = true
  restoreState = true
}

private fun NavHostController.navigateToSingleAccount(accountType: String) {
  this.navigateSingleTopTo("${SingleAccount.route}/$accountType")
}