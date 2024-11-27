package com.example.contactsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contactsapp.R
import com.example.contactsapp.data.model.Contact
import com.example.contactsapp.ui.components.BottomOptions
import com.example.contactsapp.viewmodel.ContactViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.contactsapp.ui.theme.BackgroundBlue
import com.example.contactsapp.ui.theme.BlueTopAppBar
import com.example.contactsapp.ui.theme.TextWhite
import com.example.contactsapp.ui.theme.WhiteItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactAddScreen( navController: NavController, viewModel: ContactViewModel){

    val yellowFavorite = Color(0xFFF3A812)
    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var mail by remember { mutableStateOf("") }
    var isFavorite by remember { mutableStateOf(false) }

    Scaffold(
        containerColor= BackgroundBlue,
        topBar = {
            TopAppBar(
                title = { Text("Nuevo contacto", color = TextWhite) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor= BlueTopAppBar,
                )
            )
        },
        bottomBar = {
            BottomOptions(
                navController = navController,
                contact = Contact(0, name, number, mail = mail),
                negative = "Cancelar",
                positive = "Confirmar",

                onConfirm = {

                    val newContact = Contact(
                        id = viewModel.getNextId(),
                        name = name,
                        number = number,
                        mail = mail,
                        isFavorite = isFavorite
                    )

                    if (newContact.name.isNotEmpty() && newContact.number.isNotEmpty() && newContact.mail.isNotEmpty()) {
                        viewModel.addContact(newContact)
                        navController.popBackStack()
                    }
                }

            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(
                    modifier = Modifier
                        .size(200.dp)
                        .border(5.dp, WhiteItem, CircleShape)
                        .padding(10.dp),
                    shape = CircleShape
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.contact),
                        contentDescription = "Imagen del usuario",
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth().clip(CircleShape),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = WhiteItem,
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    value = number,
                    onValueChange = { number = it },
                    label = { Text("Número de teléfono") },
                    modifier = Modifier.fillMaxWidth().clip(CircleShape),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = WhiteItem,
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    value = mail,
                    onValueChange = { mail = it },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth().clip(CircleShape),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = WhiteItem,
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(text = "Marcar como favorito", color = TextWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = if (isFavorite) R.drawable.ic_star else R.drawable.ic_star_border),
                        contentDescription = "Favorito",
                        modifier = Modifier
                            .clickable {
                                isFavorite = !isFavorite
                            }
                            .size(28.dp),
                        tint = if (isFavorite) yellowFavorite else TextWhite
                    )
                }
            }
        }
    )
}



