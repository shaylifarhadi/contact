package com.example.mycontanct.ui.list

import android.annotation.SuppressLint
import android.provider.ContactsContract
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.mycontanct.datamodel.Contact
import com.example.mycontanct.db.ContactDao
import com.example.mycontanct.db.ContactDatabase
import com.example.mycontanct.db.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.asCoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    val contactDao: ContactDao,
) :
    ViewModel() {



    @SuppressLint("RestrictedApi")
    val contactList: LiveData<PagingData<Contact>> =
        Pager(
            PagingConfig(pageSize = 50),
            null,
            contactDao.getContactList()
                .asPagingSourceFactory(
                    ArchTaskExecutor.getIOThreadExecutor().asCoroutineDispatcher())
        ).liveData

}