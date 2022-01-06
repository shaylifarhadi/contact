package com.example.mycontanct.db

import androidx.paging.*
import com.example.mycontanct.datamodel.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class ContactRepository @Inject constructor(
    private val contactDao: ContactDao,
    val db: ContactDatabase
) {

   val allContact: DataSource.Factory<Int, Contact> = contactDao.getContactList()

    suspend fun insert(contact: Contact) {
          withContext(Dispatchers.IO){
              insert(contact)
          }
    }
}