package com.example.mycontanct.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mycontanct.datamodel.Contact


@Database(entities = [ Contact::class], version = 4)

abstract class ContactDatabase : RoomDatabase() {

    abstract fun getContactDao(): ContactDao
}