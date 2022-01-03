package com.example.mycontanct.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycontanct.datamodel.Contact
import com.example.mycontanct.db.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(private val repository: ContactRepository) :
    ViewModel() {

    val allContact: LiveData<List<Contact>> = repository.allContact

    fun insertContact(contact: Contact) {
        viewModelScope.launch {
             repository.insert(contact)
        }
    }
}

