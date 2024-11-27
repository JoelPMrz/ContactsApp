package com.example.contactsapp.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.contactsapp.ui.screens.ContactAddScreen
import com.example.contactsapp.ui.screens.ContactDetailScreen
import com.example.contactsapp.ui.screens.HomeScreen
import com.example.contactsapp.viewmodel.ContactViewModel


@Composable
fun Navigator(viewModel: ContactViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController, viewModel)
        }

        composable("add"){
            ContactAddScreen(navController,viewModel)
        }

        composable(
            route = "details/{contactId}",
            arguments = listOf(navArgument("contactId") { type = NavType.IntType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getInt("contactId")
            ContactDetailScreen(navController, contactId, viewModel)
        }
    }
}


