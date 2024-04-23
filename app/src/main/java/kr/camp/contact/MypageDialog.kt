package kr.camp.contact
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kr.camp.contact.databinding.MypageDialogBinding

class MypageDialog : DialogFragment() {

    private var _binding: MypageDialogBinding? = null
    private val binding get() = _binding!!

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

        binding.cencelButton.setOnClickListener {
            buttonClickListener.onCancelClicked()
            dismiss()
        }

        binding.saveButton.setOnClickListener {

            buttonClickListener.onSaveClicked(
                binding.nameEditText.text.toString(),
                binding.phoneEditText.text.toString(),
                binding.websiteEditText.text.toString(),
                binding.memoEditText.text.toString())

            dismiss()
        }

        return view
    }


}
