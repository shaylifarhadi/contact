package com.example.mycontanct.module

import android.content.Context
import androidx.room.Room
import com.example.mycontanct.datamodel.Contact
import com.example.mycontanct.db.ContactDao
import com.example.mycontanct.db.ContactDatabase
import com.example.mycontanct.db.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationComponent
    @Provides
    fun provideContactDatabase(@ApplicationContext app : Context) =
        Room.databaseBuilder(
            app, ContactDatabase::class.java,
            "contact_database"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    fun provideContactDao(contactDb : ContactDatabase) : ContactDao {
        return contactDb.getContactDao()
    }

}