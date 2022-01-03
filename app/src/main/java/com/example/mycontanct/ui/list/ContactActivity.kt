package com.example.mycontanct.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.mycontanct.databinding.ActivityContactBinding
import com.example.mycontanct.databinding.ActivityCreateBinding
import com.example.mycontanct.datamodel.Contact
import com.example.mycontanct.ui.create.CreateActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactActivity : AppCompatActivity() {

    private val viewModel: ContactViewModel by viewModels()
    private lateinit var binding: ActivityContactBinding
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

        val user = intent.getParcelableExtra<Contact>("name")
        binding.tvEmpty.text = user?.name.toString()
        binding.tv.text = user?.number.toString()

        viewModel.allContact.observe(this) {
            createRecycler(it)
        }
    }
    private fun createRecycler(it: List<Contact>) {
        adapter = ContactAdapter(it, this)
        binding.rvContact.adapter = adapter
    }
}