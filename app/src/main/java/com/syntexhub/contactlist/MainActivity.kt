package com.syntexhub.contactlist

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.syntexhub.contactlist.adapter.ContactAdapter
import com.syntexhub.contactlist.databinding.ActivityMainBinding
import com.syntexhub.contactlist.databinding.DialogAddContactBinding
import com.syntexhub.contactlist.model.Contact

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactAdapter: ContactAdapter
    private var contactsList = mutableListOf<Contact>()

    private val detailActivityResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val deletedId = result.data?.getStringExtra("DELETED_CONTACT_ID")
            if (deletedId != null) {
                contactsList.removeAll { it.id == deletedId }
                contactAdapter.updateData(contactsList)
                Toast.makeText(this, "Contact deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                loadDeviceContacts()
            } else {
                Toast.makeText(this, "Permission denied. Showing saved contacts.", Toast.LENGTH_SHORT).show()
                loadSampleContacts()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSearchFilter()
        setupAddContactButton()
        checkContactPermission()
    }

    private fun setupRecyclerView() {
        contactAdapter = ContactAdapter(
            contactsList,
            onItemClick = { contact ->
                val intent = Intent(this, ContactDetailActivity::class.java).apply {
                    putExtra("EXTRA_CONTACT", contact)
                }
                detailActivityResult.launch(intent)
            },
            onDeleteClick = { contact ->
                contactsList.remove(contact)
                contactAdapter.updateData(contactsList)
                Toast.makeText(this, "${contact.name} deleted", Toast.LENGTH_SHORT).show()
            }
        )
        binding.rvContacts.adapter = contactAdapter
    }

    private fun setupSearchFilter() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                contactAdapter.filter(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupAddContactButton() {
        binding.fabAddContact.setOnClickListener {
            showAddContactDialog()
        }
    }

    private fun showAddContactDialog() {
        val dialogBinding = DialogAddContactBinding.inflate(LayoutInflater.from(this))
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialogBinding.btnSaveContact.setOnClickListener {
            val name = dialogBinding.etAddName.text.toString().trim()
            val phone = dialogBinding.etAddPhone.text.toString().trim()
            val email = dialogBinding.etAddEmail.text.toString().trim()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                val newContact = Contact(
                    id = System.currentTimeMillis().toString(),
                    name = name,
                    phone = phone,
                    email = if (email.isNotEmpty()) email else "contact@example.com",
                    avatarBgColorHex = listOf("#C084FC", "#F472B6", "#A5F3FC", "#818CF8").random()
                )
                contactsList.add(0, newContact)
                contactAdapter.updateData(contactsList)
                dialog.dismiss()
                Toast.makeText(this, "New Contact Saved! 🌸", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter name and phone number", Toast.LENGTH_SHORT).show()
            }
        }

        dialogBinding.btnCancelAdd.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun checkContactPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            == PackageManager.PERMISSION_GRANTED
        ) {
            loadDeviceContacts()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
        }
    }

    private fun loadDeviceContacts() {
        val deviceContacts = mutableListOf<Contact>()
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            ),
            null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )

        cursor?.use {
            val idIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
            val nameIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val numberIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

            val colorPalette = listOf("#C084FC", "#F472B6", "#A5F3FC", "#818CF8", "#FBBF24")

            while (it.moveToNext()) {
                val id = it.getString(idIndex)
                val name = it.getString(nameIndex) ?: "Unknown"
                val phone = it.getString(numberIndex) ?: ""
                val randomColor = colorPalette[id.hashCode().coerceAtLeast(0) % colorPalette.size]

                deviceContacts.add(Contact(id, name, phone, "contact@syntexhub.com", randomColor))
            }
        }

        if (deviceContacts.isNotEmpty()) {
            contactsList = deviceContacts
            contactAdapter.updateData(contactsList)
        } else {
            loadSampleContacts()
        }
    }

    private fun loadSampleContacts() {
        contactsList = mutableListOf(
            Contact("1", "Anjali Kumari Singh", "+91 98123 45678", "anjali@example.com", "#C084FC"),
            Contact("2", "Kajal Yadav", "+91 98234 56789", "kajal@example.com", "#F472B6"),
            Contact("3", "Tanushree Das", "+91 98345 67890", "tanushree@example.com", "#A5F3FC")
        )
        contactAdapter.updateData(contactsList)
    }
}
