package com.example.contactsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactsapp.R
import com.example.contactsapp.ui.theme.WhiteItem

@Composable
fun SocialOptions(){
    SocialOption(text = "Llamar", R.drawable.ic_phone, padding = 5.dp)

    Spacer(modifier = Modifier.height(6.dp))

    SocialOption(text = "WhastApp", R.drawable.ic_whatsapp, padding = 0.dp)

    Spacer(modifier = Modifier.height(6.dp))

    SocialOption(text = "Enviar correo", R.drawable.ic_mail, padding = 0.dp)
}

@Composable
fun SocialOption(text :String, icon : Int, padding: Dp){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {  }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(WhiteItem)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "icono",
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .padding(padding)
            )
            Spacer(modifier = Modifier.width(18.dp))
            Text(text = text, fontSize = 18.sp )
        }
    }
}