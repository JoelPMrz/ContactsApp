package com.example.contactsapp.ui.screens


import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.contactsapp.navegation.Navigator
import com.example.contactsapp.viewmodel.ContactViewModel

import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MyApp(viewModel: ContactViewModel){
    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color(0xFFFFFFFF)

    systemUiController.setStatusBarColor(
        color = statusBarColor,
        darkIcons = true
    )

    Navigator(viewModel)

}