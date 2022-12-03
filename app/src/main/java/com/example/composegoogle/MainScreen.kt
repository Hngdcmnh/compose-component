package com.example.composegoogle

import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composegoogle.data.DataSource.flavors
import com.example.composegoogle.data.DataSource.quantityOptions

enum class CupCakeScreen(val title: Int) {
    Start(title = R.string.app_name1),
    Flavor(title = R.string.choose_flavor),
    Pickup(title = R.string.choose_pickup_date),
    Summary(title = R.string.order_summary)
}

@Composable
fun CupcakeAppBar(
    currentScreen: CupCakeScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                            id = R.string.back_button
                        )
                    )

                }
            }
        }

    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        CupCakeScreen.valueOf(backStackEntry?.destination?.route ?: CupCakeScreen.Start.name)

    Scaffold(topBar = {
        CupcakeAppBar(
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp() })

    }) { paddingValues ->

//        val uiState by viewModel.uiState

        NavHost(
            navController = navController,
            startDestination = CupCakeScreen.Start.name,
            modifier = modifier.padding(paddingValues)
        ) {
            composable(route = CupCakeScreen.Start.name) {
                StartOrderScreen(quantityOptions = quantityOptions, onNextButtonClicked = {
                    navController.navigate(CupCakeScreen.Flavor.name)
                })
            }
            composable(route = CupCakeScreen.Flavor.name) {
                val context = LocalContext.current
                SelectOptionScreen(
                    options = flavors.map { id -> context.resources.getString(id) },
                    onNextButtonClicked = {
                        navController.navigate(CupCakeScreen.Start.name)
                    },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(navController)
                    })
            }
        }

    }
}

private fun cancelOrderAndNavigateToStart(
    navController: NavHostController
) {
    navController.popBackStack(CupCakeScreen.Start.name, inclusive = false)
}