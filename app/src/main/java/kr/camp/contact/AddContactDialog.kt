package kr.camp.contact

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import kr.camp.contact.data.Contact
import kr.camp.contact.databinding.MypageDialogBinding
import kr.camp.contact.registry.ContactRegistry
import java.util.regex.Pattern

class AddContactDialog(
    private val starValue: () -> Boolean
) : DialogFragment() {

    private var _binding: MypageDialogBinding? = null
    private val binding get() = _binding!!
    private var imageuri: Uri? = null

    interface OnButtonClickListener {
        fun onCancelClicked()
        fun onSaveClicked(name: String, mobile: String, homepage: String, memo: String)
    }

    // 클릭 이벤트 설정
    fun setButtonClickListener(buttonClickListener: OnButtonClickListener) {
        this.buttonClickListener = buttonClickListener
    }

    private lateinit var buttonClickListener: OnButtonClickListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MypageDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        // 유효성 검사
        val name = binding.nameEditText.text
        val phoneNumber = binding.phoneEditText.text
        val website = binding.websiteEditText.text
        binding.saveButton.isEnabled = false

        binding.nameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isRegularName(name.toString())) {
                    binding.nameEditText.setBackgroundResource(R.drawable.dialog_edittext2)


                } else {
                    binding.nameEditText.setBackgroundResource(R.drawable.dialog_edittext3)
                    binding.saveButton.setBackgroundResource(R.drawable.darkgray_corner_button)
                    binding.saveButton.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.phoneEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isRegularPhoneNumber(phoneNumber.toString())) {
                    binding.phoneEditText.setBackgroundResource(R.drawable.dialog_edittext2)
                    binding.saveButton.setBackgroundResource(R.drawable.darkblue_corner_button)
                    binding.saveButton.isEnabled = true

                } else {
                    binding.phoneEditText.setBackgroundResource(R.drawable.dialog_edittext3)
                    binding.saveButton.setBackgroundResource(R.drawable.darkgray_corner_button)
                    binding.saveButton.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.websiteEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isRegularWebsite(website.toString())) {
                    binding.websiteEditText.setBackgroundResource(R.drawable.dialog_edittext2)
                } else {
                    binding.websiteEditText.setBackgroundResource(R.drawable.dialog_edittext3)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // 배경 투명하게
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 화면터치해도 다이얼로그 사라지지 않게
        isCancelable = false

        with(binding) {
            circleImageView.setOnClickListener {
                // 갤러리 실행
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                activityResult.launch(intent)
            }
            cencelButton.setOnClickListener {
                buttonClickListener.onCancelClicked()
                dismiss()
            }
            saveButton.setOnClickListener {
                val lastViewType: Int = if (ContactRegistry.contacts.last().viewType == 0) 1 else 0
                val starValue = starValue()
                val newContact = Contact(
                    profileImageDrawableId = null,
                    uriImage = imageuri,
                    name = nameEditText.text.toString(),
                    phoneNumber = phoneEditText.text.toString(),
                    website = websiteEditText.text.toString(),
                    memo = memoEditText.text.toString(),
                    viewType = lastViewType,
                    showStar = starValue
                )
                val contactInstance = ContactAdapter(context) { newContact }
                contactInstance.addContact(newContact)
                dismiss()
            }
        }
        return view
    }

    //사진 갖고오기
    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        //결과 코드 OK , 결가값 null 아니면
        if (it.resultCode == AppCompatActivity.RESULT_OK && it.data != null) {
            //값 담기
            imageuri = it.data!!.data
            Log.d("image", imageuri.toString())

            //화면에 보여주기
            Glide.with(this)
                .load(imageuri)
                .fitCenter()
                .into(binding.circleImageView)
        }
    }

    // 패턴
    private fun isRegularName(name: String): Boolean {
        val namePattern = "^[가-힣]{1,5}$"
        return Pattern.matches(namePattern, name)
    }

    private fun isRegularPhoneNumber(phoneNumber: String): Boolean {
        val phoneNumberPattern = "^([0-9]{3})-?([0-9]{3,4})-?([0-9]{4})$"
        return Pattern.matches(phoneNumberPattern, phoneNumber)
    }

    private fun isRegularWebsite(website: String): Boolean {
        val websitePattern = "^https?://(?:www\\.)?[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$"

        return Pattern.matches(websitePattern, website)
    }
}
