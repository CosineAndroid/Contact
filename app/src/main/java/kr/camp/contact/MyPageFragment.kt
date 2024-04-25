package kr.camp.contact

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kr.camp.contact.databinding.FragmentMypageBinding

class MyPageFragment : Fragment() {

    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
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
                    memo: String,
                    image: Drawable
                ) {
                    with(binding) {
                        nameTextView.text = name
                        mypagePhoneCardView.descriptionTextView.text = mobile
                        mypagHomepageCardView.descriptionTextView.text = homepage
                        mypageMemoCardView.descriptionTextView.text = memo
                        cardviewImageView.setImageDrawable(image)
                    }

                }
            })
            dialog.show(parentFragmentManager, "custom")
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}