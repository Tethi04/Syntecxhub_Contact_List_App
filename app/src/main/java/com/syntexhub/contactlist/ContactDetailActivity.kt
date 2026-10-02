package com.syntexhub.contactlist

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syntexhub.contactlist.databinding.ActivityContactDetailBinding
import com.syntexhub.contactlist.model.Contact

class ContactDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDetailBinding
    private var currentContact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentContact = intent.getSerializableExtra("EXTRA_CONTACT") as? Contact
        currentContact?.let { displayContactDetails(it) }
    }

    private fun displayContactDetails(contact: Contact) {
        binding.tvDetailName.text = contact.name
        binding.tvDetailPhone.text = contact.phone
        binding.tvDetailEmail.text = contact.email
        binding.tvDetailAvatar.text = contact.getInitial()

        try {
            val drawable = binding.tvDetailAvatar.background as? GradientDrawable
            drawable?.setColor(Color.parseColor(contact.avatarBgColorHex))
        } catch (_: Exception) {}

        binding.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${contact.phone}"))
            startActivity(intent)
        }

        binding.btnSms.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:${contact.phone}"))
            startActivity(intent)
        }

        binding.btnDeleteContact.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("DELETED_CONTACT_ID", contact.id)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
