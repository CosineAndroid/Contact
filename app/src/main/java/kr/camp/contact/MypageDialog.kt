package kr.camp.contact
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import kr.camp.contact.databinding.MypageDialogBinding
import kr.camp.contact.registry.ContactRegistry
import kr.camp.contact.registry.ContactRegistry.addContact

class MypageDialog : DialogFragment() {

    private var _binding: MypageDialogBinding? = null
    private val binding get() = _binding!!
    private var imageuri: Uri? = null

    interface OnButtonClickListener {
        fun onCancelClicked()
        fun onSaveClicked(name: String, mobile : String, homepage : String, memo : String)
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
    ): View? {
        _binding = MypageDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        // 배경 투명하게
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        with(binding){

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
                buttonClickListener.onSaveClicked(
                    nameEditText.text.toString(),
                    phoneEditText.text.toString(),
                    websiteEditText.text.toString(),
                    memoEditText.text.toString())
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

            //화면에 보여주기
            Glide.with(this)
                .load(imageuri)
                .fitCenter()
                .into(binding.circleImageView)
        }
    }
}
