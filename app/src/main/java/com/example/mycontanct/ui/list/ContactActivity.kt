package com.example.mycontanct.ui.list

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mycontanct.databinding.ActivityContactBinding
import com.example.mycontanct.datamodel.Contact
import com.example.mycontanct.ui.create.CreateActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactActivity : AppCompatActivity() {

    private val CALL_REQUEST_CODE = 100

    private val viewModel: ContactViewModel by viewModels()
    private lateinit var binding: ActivityContactBinding
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        checkPermission()

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

        val user = intent.getParcelableExtra<Contact>("name")
     /*   binding.tvEmpty.text = user?.name.toString()
        binding.tv.text = user?.number.toString()*/

        viewModel.allContact.observe(this) {
            createRecycler(it)
        }
    }

   private fun checkPermission() {
       if (ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)
       != PackageManager.PERMISSION_GRANTED){
           getPermission()
       }else{
       /*    onCallClick(position = viewModel.allContact.)*/
       }
    }

    private fun getPermission() {

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),
        CALL_REQUEST_CODE)
    }

    private fun createRecycler(it: List<Contact>) {
        adapter = ContactAdapter(it, this)
        binding.rvContact.adapter = adapter
    }

  /*  private fun onCallClick(position: Int) {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:1122334455"))
        intent.putExtra("number",position)
        startActivity(intent)
    }*/
}