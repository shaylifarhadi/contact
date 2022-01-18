package com.example.mycontanct.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mycontanct.databinding.ActivitySplashBinding
import com.example.mycontanct.ui.list.ContactActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    val viewModel:SplashViewModel by viewModels()
    lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.isTimerFinished.observe(this){
            if (it) openContactActivity()
        }

    }

    private fun openContactActivity() {
        val intent = Intent(this, ContactActivity::class.java)
        startActivity(intent)
        finish()
    }
}