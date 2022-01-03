package com.example.mycontanct.db

import androidx.lifecycle.LiveData
import com.example.mycontanct.datamodel.Contact
import javax.inject.Inject

class ContactRepository @Inject constructor(private val contactDao: ContactDao){

    val allContact: LiveData<List<Contact>> = contactDao.getContactList()

    suspend fun insert(contact: Contact){
        insert(contact)
    }

}