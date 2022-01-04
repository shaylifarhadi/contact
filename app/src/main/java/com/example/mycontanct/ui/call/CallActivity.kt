package com.example.mycontanct.ui.call

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.mycontanct.R
import com.example.mycontanct.databinding.ActivityCallBinding
import com.example.mycontanct.ui.list.ContactAdapter

class CallActivity : AppCompatActivity() {

    lateinit var adapter: ContactAdapter
    lateinit var binding: ActivityCallBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCallBinding.inflate(layoutInflater)
        setContentView(binding.root)
/*
        intent.getIntExtra("number",adapter.onClickCall(position))*/
    }
}