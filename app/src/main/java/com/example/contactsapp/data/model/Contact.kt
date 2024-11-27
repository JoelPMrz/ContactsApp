package com.example.contactsapp.data.model

import com.example.contactsapp.R

data class Contact (
    val id: Int = 0,
    var name: String,
    var number: String,
    val image: Int = R.drawable.contact,
    var mail: String,
    var isFavorite: Boolean = false
)
