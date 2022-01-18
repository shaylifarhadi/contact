package com.example.mycontanct.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagingData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mycontanct.datamodel.Contact

@Dao
interface ContactDao {

    /*  @Query("SELECT * FROM contact_table ORDER BY contact_name ASC")
      fun getContactList() : LiveData<List<Contact>>*/

    @Query("SELECT * FROM contact_table ORDER BY contact_name ASC")
    fun getContactList(): DataSource.Factory<Int, Contact>


    @Insert
    fun insertContact(contact: Contact)

    @Delete
    fun deleteContact(contact:Contact)

}