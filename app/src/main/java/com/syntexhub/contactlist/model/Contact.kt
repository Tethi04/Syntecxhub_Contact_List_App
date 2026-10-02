package com.syntexhub.contactlist.model

import java.io.Serializable

data class Contact(
    val id: String,
    val name: String,
    val phone: String,
    val email: String = "contact@example.com",
    val avatarBgColorHex: String = "#C084FC"
) : Serializable {
    fun getInitial(): String {
        return if (name.isNotBlank()) name.trim().first().uppercase() else "?"
    }
}
