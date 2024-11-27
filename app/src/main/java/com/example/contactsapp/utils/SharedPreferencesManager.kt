package com.example.contactsapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.contactsapp.data.model.Contact
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("contacts_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveContacts(contacts: List<Contact>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(contacts)
        editor.putString("contacts", json)
        editor.apply()
    }

    fun loadContacts(): List<Contact> {
        val json = sharedPreferences.getString("contacts", null)
        val type = object : TypeToken<List<Contact>>() {}.type
        return if (json != null) {
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }
}
