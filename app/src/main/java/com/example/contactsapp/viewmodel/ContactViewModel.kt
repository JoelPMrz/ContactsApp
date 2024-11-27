package com.example.contactsapp.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import com.example.contactsapp.data.model.Contact
import com.example.contactsapp.utils.SharedPreferencesManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ContactViewModel(context: Context) : ViewModel() {

    private val sharedPreferencesManager = SharedPreferencesManager(context)

    private var nextId: Int = 0

    private val _contacts = mutableStateListOf<Contact>().apply {
        addAll(sharedPreferencesManager.loadContacts())
    }

    val contacts: List<Contact> get() = _contacts.sortedBy { it.name }
    val favContacts: List<Contact> get() = contacts.filter { it.isFavorite }


    fun editContact(contact: Contact) {
        val updatedContact = _contacts.find { it.id == contact.id }
        updatedContact?.let {
            it.name = contact.name
            it.number = contact.number
            it.mail = contact.mail
            it.isFavorite = contact.isFavorite
            sharedPreferencesManager.saveContacts(_contacts)
        }
    }

    fun deleteContact(contact: Contact) {
        _contacts.removeIf { it.id == contact.id }
        sharedPreferencesManager.saveContacts(_contacts)
    }

    fun addContact(contact: Contact) {
        _contacts.add(contact)
        nextId++
        sharedPreferencesManager.saveContacts(_contacts)
    }

    fun getNextId(): Int {
        return nextId
    }

    fun toggleFavorite(contactId: Int) {
        val contact = _contacts.find { it.id == contactId }
        contact?.let {
            it.isFavorite = !it.isFavorite
            sharedPreferencesManager.saveContacts(_contacts)
        }
    }
}

class ContactViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            return ContactViewModel(context) as T
        }
        throw IllegalArgumentException("No existe el viewModel")
    }
}
