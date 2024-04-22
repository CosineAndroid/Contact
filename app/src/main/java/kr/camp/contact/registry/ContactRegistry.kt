package kr.camp.contact.registry

import kr.camp.contact.data.Contact

object ContactRegistry {

    private val _contacts = mutableListOf<Contact>()
    val contacts: List<Contact> get() = _contacts

    fun addContact(contact: Contact) {
        _contacts.add(contact)
    }
}