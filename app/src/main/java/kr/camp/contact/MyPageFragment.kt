package kr.camp.contact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kr.camp.contact.databinding.FragmentMypageBinding

class MyPageFragment : Fragment() {

    private val binding by lazy { FragmentMypageBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.floatingButton.setOnClickListener {
            val dialog = MyPageDialog()

            dialog.setButtonClickListener(object : MyPageDialog.OnButtonClickListener {
                override fun onCancelClicked() {
                    Toast.makeText(
                        context,
                        getString(R.string.mypage_toast_cancel),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onSaveClicked(
                    name: String,
                    mobile: String,
                    homepage: String,
                    memo: String
                ) {
                    with(binding) {
                        nameTextView.text = name
                        mobileContextTextView.text = mobile
                        homepageContextTextView.text = homepage
                        memoContextTextView.text = memo
                    }

                }
            })
            dialog.show(parentFragmentManager, "custom")
        }
        return binding.root
    }
}