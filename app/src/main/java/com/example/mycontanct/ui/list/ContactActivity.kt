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
import kotlinx.android.synthetic.main.activity_contact.*

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

        val pagingAdapter = ContactAdapter(this, ::onClickCall)
        binding.rvContact.adapter = pagingAdapter

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        checkPermission()

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

        viewModel.contactList.observe(this) {
            pagingAdapter.submitData(lifecycle, it)
        }
  /*      adapter = ContactAdapter(this, ::onClickCall)
        binding.rvContact.adapter = adapter
*/

        var user = intent.getParcelableExtra<Contact>("name")

    }


 private fun checkPermission() {
       if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
       != PackageManager.PERMISSION_GRANTED){
           getPermission()
       }else{

       }
    }


      private fun getPermission() {
      ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),
      CALL_REQUEST_CODE)
  }

    private fun onClickCall(position: Int) {
        var user = intent.getParcelableExtra<Contact>("name")
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${user?.number}"))
        intent.putExtra("number", position)
        this.startActivity(intent)
   }

}
