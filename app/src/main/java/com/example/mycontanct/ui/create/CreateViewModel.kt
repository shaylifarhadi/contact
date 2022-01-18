package com.example.mycontanct.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycontanct.datamodel.Contact
import com.example.mycontanct.db.ContactDao
import com.example.mycontanct.db.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val repository: ContactRepository,
    val contactDao: ContactDao
) : ViewModel() {


    fun insertContact(contact: Contact) {
        viewModelScope.launch {
            repository.insert(contact)
        }
    }
}


