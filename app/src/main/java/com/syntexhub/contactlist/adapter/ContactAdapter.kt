package com.syntexhub.contactlist.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syntexhub.contactlist.databinding.ItemContactBinding
import com.syntexhub.contactlist.model.Contact

class ContactAdapter(
    private var contactList: MutableList<Contact>,
    private val onItemClick: (Contact) -> Unit,
    private val onDeleteClick: (Contact) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private var filteredList: MutableList<Contact> = contactList.toMutableList()

    inner class ContactViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = filteredList[position]
        holder.binding.tvName.text = contact.name
        holder.binding.tvPhone.text = contact.phone
        holder.binding.tvAvatarText.text = contact.getInitial()

        try {
            val drawable = holder.binding.tvAvatarText.background as? GradientDrawable
            drawable?.setColor(Color.parseColor(contact.avatarBgColorHex))
        } catch (_: Exception) {}

        holder.itemView.setOnClickListener {
            onItemClick(contact)
        }

        holder.binding.btnQuickDelete.setOnClickListener {
            onDeleteClick(contact)
        }
    }

    override fun getItemCount(): Int = filteredList.size

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            contactList.toMutableList()
        } else {
            contactList.filter {
                it.name.contains(query, ignoreCase = true) || it.phone.contains(query)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }

    fun updateData(newList: MutableList<Contact>) {
        this.contactList = newList
        this.filteredList = newList.toMutableList()
        notifyDataSetChanged()
    }
}
