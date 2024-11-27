package com.example.contactsapp.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.contactsapp.data.model.Contact
import com.example.contactsapp.ui.components.ContactList
import com.example.contactsapp.ui.theme.BackgroundBlue
import com.example.contactsapp.ui.theme.BlueTopAppBar
import com.example.contactsapp.ui.theme.TextWhite
import com.example.contactsapp.viewmodel.ContactViewModel


@Composable
fun HomeScreen(navController: NavHostController, viewModel: ContactViewModel) {

    val contacts = viewModel.contacts
    val favContacts = viewModel.favContacts

    Scaffold(
        containerColor= BackgroundBlue,
        topBar = { TolBar(navController) },
        content = { paddingValues ->  Content(favContacts, contacts, paddingValues, navController) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TolBar(navController: NavHostController){
    TopAppBar(
        title = { Text(text = "Contactos", fontSize = 26.sp, color = TextWhite) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BlueTopAppBar
        ),
        actions = {
            IconButton(onClick = {  }) {
                Icon(Icons.Default.Search, tint = TextWhite, contentDescription = "Icono buscar contacto")
            }
            IconButton(onClick = { navController.navigate("add") }) {
                Icon(Icons.Default.Add, tint = TextWhite, contentDescription = "Icono añadir contacto")
            }
        }
    )
}

@Composable
fun Content(favContacts: List<Contact>, contacts: List<Contact>, paddingValues : PaddingValues, navController : NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 12.dp)
            .padding(horizontal = 12.dp)
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        ContactList(
            title = "Favoritos",
            contactList = favContacts,
            navController = navController,
            height = 275.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        ContactList(
            title = "Todos",
            contactList = contacts,
            navController = navController
        )


    }
}




