package kr.camp.contact.data

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val profileImageDrawableId: Int?,
    val uriImage: Uri?,
    val name: String,
    val phoneNumber: String,
    val website: String,
    val memo: String,
    val viewType: Int
) : Parcelable {

    companion object {
        const val VIEW_TYPE1 = 0
        const val VIEW_TYPE2 = 1
    }
}

