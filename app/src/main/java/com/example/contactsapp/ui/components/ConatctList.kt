package com.example.contactsapp.ui.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.contactsapp.data.model.Contact
import com.example.contactsapp.ui.theme.TextWhite

@Composable
fun ContactList(title: String, contactList : List<Contact>, navController: NavHostController, height: Dp? = null){
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight= FontWeight.Bold,
            color = TextWhite,
            modifier = Modifier.padding(start = 12.dp, top = 16.dp, bottom = 4.dp)
        )
    if(contactList.isNotEmpty()){
        LazyColumn(modifier = Modifier.fillMaxWidth().then(
            if (height != null) Modifier.heightIn(max = height)
            else Modifier
        )) {
            items(contactList) { contact ->
                ContactItem(
                    contact = contact,
                    onClick = { navController.navigate("details/${contact.id}") }
                )
            }
        }
    }
    else{
        NotContactItem(navController)
    }
}

