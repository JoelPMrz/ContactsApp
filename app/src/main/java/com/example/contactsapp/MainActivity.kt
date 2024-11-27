package com.example.contactsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.contactsapp.ui.screens.MyApp
import com.example.contactsapp.ui.theme.ContactsAppTheme
import com.example.contactsapp.viewmodel.ContactViewModel
import com.example.contactsapp.viewmodel.ContactViewModelFactory

class MainActivity : ComponentActivity() {

    private val contactViewModel: ContactViewModel by viewModels {
        ContactViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ContactsAppTheme {
                MyApp(viewModel = contactViewModel)
            }
        }
    }
}
