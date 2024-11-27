package com.example.contactsapp.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.contactsapp.viewmodel.ContactViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.contactsapp.R
import com.example.contactsapp.data.model.Contact
import com.example.contactsapp.ui.components.BottomOptions
import com.example.contactsapp.ui.components.SocialOptions
import com.example.contactsapp.ui.theme.BackgroundBlue
import com.example.contactsapp.ui.theme.BlueTopAppBar
import com.example.contactsapp.ui.theme.TextWhite
import com.example.contactsapp.ui.theme.WhiteItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(navController: NavHostController,contactId: Int?,viewModel: ContactViewModel) {

    val contact = contactId?.let { id ->
        viewModel.contacts.find { it.id == id }
    }

    val context = LocalContext.current

    val yellowFavorite = Color(0xFFF3A812)

    if (contact == null) {
        NullContact(navController)
    } else {

        var name by remember { mutableStateOf(contact.name) }
        var number by remember { mutableStateOf(contact.number) }
        var mail by remember { mutableStateOf(contact.mail) }
        var isFavorite by remember { mutableStateOf(contact.isFavorite) }


        Scaffold(
            containerColor= BackgroundBlue,
            topBar = {
                TopAppBar(
                    title = { Text("Datos del contacto", color = TextWhite)},
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor= BlueTopAppBar,
                    ),
                    actions = {
                        IconButton(onClick = {
                            viewModel.toggleFavorite(contactId)
                        }) {
                            Icon(
                                painter = painterResource(id = if (isFavorite) R.drawable.ic_star else R.drawable.ic_star_border),
                                contentDescription = "Icono de Favorito",
                                modifier = Modifier
                                    .clickable {
                                        isFavorite = !isFavorite
                                    }
                                    .size(28.dp),
                                tint = if (isFavorite) yellowFavorite else TextWhite
                            )
                        }
                        var show by rememberSaveable { mutableStateOf(false)  }
                        IconButton(onClick = {
                            show = true
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_bin),
                                contentDescription = "Icono Eliminar contacto",
                                modifier = Modifier
                                    .size(28.dp),
                                tint = TextWhite
                            )
                        }
                        DeleteContactDialog(
                            show = show,
                            onConfirm = { viewModel.deleteContact(contact); navController.popBackStack()},
                            onDismiss = { show = false},
                            contact = contact,
                            context = context)
                    },
                )
            },

            bottomBar = {
                BottomOptions(
                    navController = navController,
                    contact = Contact(0, name, number, mail = mail, isFavorite = isFavorite),
                    negative = "Salir",
                    positive = "Guardar",

                    onConfirm = {
                        val updatedContact = Contact(
                            id = contact.id,
                            name = name,
                            number = number,
                            mail = mail,
                            isFavorite = isFavorite
                        )

                        if (updatedContact.name.isNotEmpty() && updatedContact.number.isNotEmpty() && updatedContact.mail.isNotEmpty()) {
                            viewModel.editContact(updatedContact)
                            Toast.makeText(context,"Contacto ${contact.name} actualizado", Toast.LENGTH_SHORT).show()
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
                        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = WhiteItem,
                        )
                    )

                    TextField(
                        value = number,
                        onValueChange = { number = it },
                        label = { Text("Número de teléfono") },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = WhiteItem,
                        )
                    )

                    TextField(
                        value = mail,
                        onValueChange = { mail = it },
                        label = { Text("Correo electrónico") },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = WhiteItem,
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    SocialOptions()
                }
            }
        )
    }
}


@Composable
fun NullContact(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "El contacto no existe", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Volver",
            modifier = Modifier.clickable { navController.popBackStack() },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun DeleteContactDialog(
    show : Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    contact : Contact,
    context: Context
){

    if(show){
        AlertDialog(
            onDismissRequest = { onDismiss() },
            dismissButton = { TextButton(onClick = {
                onDismiss()
            }) {
                Text(text = "Cancelar")
            } },
            confirmButton = { TextButton(onClick = {
                if (contact.isFavorite) {
                    Toast.makeText(
                        context,
                        "No se puede eliminar un contacto favorito",
                        Toast.LENGTH_LONG
                    ).show()

                } else {
                    onConfirm()
                    Toast.makeText(context,"Contacto ${contact.name} eliminado", Toast.LENGTH_SHORT).show()
                }

            }) {
                Text(text = "Eliminar")
            } },
            title = { Text( text= "Eliminar a ${contact.name}")},
            text = { Text( text = "El conctacto se eliminará de forma permanente") }
        )
    }
}



