package com.example.mycontanct.ui.list

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.persistableBundleOf
import androidx.paging.filter
import com.example.mycontanct.databinding.ActivityContactBinding
import com.example.mycontanct.datamodel.Contact
import com.example.mycontanct.db.ContactDao
import com.example.mycontanct.ui.create.CreateActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactActivity : AppCompatActivity() {

    private val CALL_REQUEST_CODE = 100
    private val viewModel: ContactViewModel by viewModels()
    private lateinit var binding: ActivityContactBinding
    private lateinit var adapter: ContactAdapter
    lateinit var contactDao : ContactDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        checkPermission()

        binding.fabClick = View.OnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

        val pagingAdapter = ContactAdapter(this, ::onClickCall,::onClickDelete)
        binding.adapter = pagingAdapter
        viewModel.contactList.observe(this) {
            pagingAdapter.submitData(lifecycle, it)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CALL_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Call Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(
                this,
                "Please give permission to access the application",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            getPermission()
        } else {
            //Toast.makeText(this, "permission is granted", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.CALL_PHONE),
            CALL_REQUEST_CODE
        )
    }

    private fun onClickCall(phoneNumber:String) {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${phoneNumber}"))
        this.startActivity(intent)
    }

    private fun onClickDelete(contact:Contact) {
        viewModel.contactDao.deleteContact(contact)
    }
}


