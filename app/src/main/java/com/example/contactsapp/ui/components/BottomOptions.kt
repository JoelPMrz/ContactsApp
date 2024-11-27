package com.example.contactsapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contactsapp.data.model.Contact
import com.example.contactsapp.ui.theme.BlueTopAppBar
import com.example.contactsapp.ui.theme.TextWhite
import com.example.contactsapp.ui.theme.WhiteItem

@Composable
fun BottomOptions(navController: NavController, contact : Contact, onConfirm: () -> Unit, negative : String, positive : String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlueTopAppBar)
            .padding(18.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = negative,
            fontSize= 20.sp,
            color = TextWhite,
            modifier = Modifier.clickable {
                navController.popBackStack()
            }
        )
        Text(
            text = positive,
            color = TextWhite,
            fontSize= 20.sp,
            modifier = Modifier.clickable {
                onConfirm()
            }
        )
    }
}