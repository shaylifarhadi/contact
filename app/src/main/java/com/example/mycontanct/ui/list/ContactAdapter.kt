package com.example.mycontanct.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontanct.databinding.ItemContactBinding
import com.example.mycontanct.datamodel.Contact
import android.content.Intent
import android.net.Uri


class ContactAdapter(
    private val contactList: List<Contact>,
    private val context: Context,
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = contactList[position]
        holder.binding.apply {
            tvContactName.text = item.name
            tvNumber.text = item.number
        }

        holder.bind(position)
    }
    override fun getItemCount(): Int {
        return contactList.size
    }

    inner class ContactViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(position: Int){
            binding.imgCall.setOnClickListener {
                val position = contactList[adapterPosition]

                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${position.number}"))
                intent.putExtra("number",position)
                context.startActivity(intent)
            }
        }


    }
}