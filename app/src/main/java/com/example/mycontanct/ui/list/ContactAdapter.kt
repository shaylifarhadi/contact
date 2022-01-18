package com.example.mycontanct.ui.list

import android.content.Context
import android.content.Intent
import android.icu.text.Transliterator
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontanct.datamodel.Contact
import androidx.recyclerview.widget.DiffUtil
import com.example.mycontanct.databinding.ItemContactBinding

class ContactAdapter(
    val context : Context, val onCallClick : (String) -> Unit,
    val onClickDelete : (Contact) -> Unit
) :
    PagingDataAdapter<Contact, ContactAdapter.ContactViewHolder>(
        ContactDiffCallback()
    ) {
    override fun onBindViewHolder(holder : ContactViewHolder, position : Int) {
        val data = getItem(position)

        if (data != null) {
            holder.bind(data)
        } else {
            holder.clear()
        }

    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ContactViewHolder {
        return ContactViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ContactViewHolder(private val binding : ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data : Contact) {
            binding.let {
                it.tvContactName.text = data.name
                it.tvNumber.text = data.number
            }

            binding.imgCall.setOnClickListener {
                onCallClick.invoke(data.number)
            }

            binding.imgDelete.setOnClickListener {
                onClickDelete.invoke(data)
            }
        }

        fun clear() {
            binding.tvContactName.text = ""
            binding.tvNumber.text = ""
        }
    }
}

class ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem : Contact, newItem : Contact) : Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem : Contact, newItem : Contact) : Boolean {
        return oldItem == newItem
    }

}

