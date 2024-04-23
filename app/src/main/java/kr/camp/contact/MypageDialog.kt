package kr.camp.contact

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import kr.camp.contact.data.Contact
import kr.camp.contact.databinding.MypageDialogBinding
import kr.camp.contact.registry.ContactRegistry
import kr.camp.contact.registry.ContactRegistry.addContact

class MypageDialog(context: Context) : Dialog(context) {

    private val binding by lazy { MypageDialogBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        myPageDialog()
    }


    private fun myPageDialog() = with(binding){
        // 화면 터치를 통해 dialog가 종료되지 않도록
        setCancelable(false)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 버튼 클릭이벤트 처리
        cencelButton.setOnClickListener {
            dismiss()
            Toast.makeText(context, "취소", Toast.LENGTH_SHORT).show()
        }
        saveButton.setOnClickListener {
            lateinit var newContact: Contact
            var lastViewType: Int
            if(ContactRegistry.contacts.last().viewType == 0 ) {
                lastViewType = 1
            } else lastViewType=0

            newContact  =
                Contact(
                    profileImageDrawableId = R.drawable.profile_img ,
                    name = nameEditText.text.toString(),
                    phoneNumber = phoneEditText.text.toString(),
                    website = websiteEditText.text.toString(),
                    memo= memoEditText.text.toString(),
                    viewType = lastViewType)
            val contactInstance = ContactAdapter{newContact}
            contactInstance.addContact(newContact)
            Toast.makeText(context, "저장", Toast.LENGTH_SHORT).show()
        }


    }
}