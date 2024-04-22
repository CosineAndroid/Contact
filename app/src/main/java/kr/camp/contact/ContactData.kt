package kr.camp.contact

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.net.Inet4Address

@Parcelize
class ContactData (
    val profileImageDrawbleId : Int,
    val name : String,
    val phoneNumber: String,
    val website: String,
    val memo: String
) : Parcelable {

    companion object {
        const val VIEW_TYPE1 = 0
        const val VIEW_TYPE2 = 1

    }
}