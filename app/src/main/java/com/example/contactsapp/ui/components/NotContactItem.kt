package com.example.contactsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.contactsapp.R
import com.example.contactsapp.ui.theme.WhiteItem

@Composable
fun NotContactItem(navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clip(CircleShape)
            .clickable { navController.navigate("add") }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(WhiteItem)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.contact),
                contentDescription = "Imagen del contacto",
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape) // Mantén la imagen redonda
            )
            Spacer(modifier = Modifier.width(18.dp))
            Text(text = "Añade un nuevo contacto", fontSize = 20.sp)
        }
    }
}
