package com.team.storeappassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.*
import com.team.storeappassignment.ui.screens.HomeScreenController
import com.team.storeappassignment.ui.screens.ProfileNavigator
import com.team.storeappassignment.ui.screens.ProfileScreen
import com.team.storeappassignment.ui.theme.StoreAppAssignmentTheme
import com.team.storeappassignment.util.Constants
import com.team.storeappassignment.util.ShoppingViewModal

class MainActivity : ComponentActivity() {
    private val viewModel:ShoppingViewModal by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoreAppAssignmentTheme {

                   ScreenNavigator(viewModel = viewModel)
            }
        }
    }

}
@Composable
fun ScreenNavigator(modifier: Modifier=Modifier, viewModel:ShoppingViewModal) {
    val navController = rememberNavController()
    viewModel.navigateTo = {
        navController.navigate(it)
    }
    NavHost(navController = navController, startDestination =Constants.HOME_SCREEN) {
        composable(Constants.PROFILE_SCREEN) {
          ProfileNavigator(viewModal = viewModel)
        }

        composable(Constants.HOME_SCREEN) {
            HomeScreenController(viewModel = viewModel)
        }
        composable("session") {

        }
        composable("splash"){

        }
    }
}


