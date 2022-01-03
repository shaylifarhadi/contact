package com.example.mycontanct.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mycontanct.datamodel.Contact
import java.util.concurrent.Flow

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact_table ORDER BY contact_name ASC")
    fun getContactList() : LiveData<List<Contact>>

    @Insert
    fun insertContact(contact: Contact)

}