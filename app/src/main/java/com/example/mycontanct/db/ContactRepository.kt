package com.example.mycontanct.db

import androidx.paging.*
import com.example.mycontanct.datamodel.Contact
import javax.inject.Inject

class ContactRepository @Inject constructor(
    val contactDao: ContactDao,
    val db: ContactDatabase
) {

    val allContact: DataSource.Factory<Int, Contact> = contactDao.getContactList()

    fun insert(contact: Contact) {
        insert(contact)
    }
    fun delete(contact:Contact){
        delete(contact)
    }
}