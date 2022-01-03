package com.example.mycontanct.ui.create

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mycontanct.databinding.ActivityCreateBinding
import com.example.mycontanct.datamodel.Contact
import com.example.mycontanct.ui.list.ContactActivity

class CreateActivity : AppCompatActivity() {

    val viewModel : CreateViewModel by viewModels()
    lateinit var binding:ActivityCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {

            val name = binding.name.text.toString()
            val number = binding.num.text.toString()

            val contact = Contact(name,number)
            viewModel.contactDao.insertContact(contact)
            val intent = Intent(this,ContactActivity::class.java)
          /* val nameEt = Contact(binding.editName.toString(),binding.editPhone.toString())*/
            intent.putExtra("name",contact)
            startActivity(intent)
        }

    }
}