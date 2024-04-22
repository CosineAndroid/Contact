package kr.camp.contact.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val profileImageDrawableId: Int,
    val name: String,
    val phoneNumber: String,
    val website: String,
    val memo: String
) : Parcelable