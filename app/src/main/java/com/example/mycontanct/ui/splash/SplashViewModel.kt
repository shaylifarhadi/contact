package com.example.mycontanct.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() :ViewModel(){

    private val _isTimerFinished = MutableLiveData(false)
    val isTimerFinished: MutableLiveData<Boolean>
        get() = _isTimerFinished

    init {
        startTimer()
    }

    private fun startTimer(){

        viewModelScope.launch {
            delay(2000)

            _isTimerFinished.value = true
        }
    }
}