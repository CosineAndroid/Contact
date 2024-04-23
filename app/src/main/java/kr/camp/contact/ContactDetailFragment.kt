package kr.camp.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import kr.camp.contact.data.Contact
import kr.camp.contact.databinding.ContactDetailCardBinding
import kr.camp.contact.databinding.FragmentContactDetailBinding
import kr.camp.contact.intent.IntentKey

class ContactDetailFragment : Fragment() {

    private var _binding: FragmentContactDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var contact: Contact

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactDetailBinding.inflate(inflater, container, false)
        arguments?.let { bundle ->
            (bundle.getParcelable(IntentKey.CONTACT) as? Contact)?.let { contact ->
                this.contact = contact
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as? MainActivity
        mainActivity?.hideNavigationBar()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() = with(binding) {
        profileImageView.setImageResource(contact.profileImageDrawableId)

        nameTextView.text = contact.name
        messageButton.root.text = getString(R.string.contact_detail_message_button)
        callButton.root.text = getString(R.string.contact_detail_call_button)

        phoneNumberCardView.init(R.string.contact_detail_phone_number_title, contact.phoneNumber)
        websiteCardView.init(R.string.contact_detail_website_title, contact.website)
        memoCardView.init(R.string.contact_detail_memo_title, contact.memo)
    }

    private fun ContactDetailCardBinding.init(stringId: Int, description: String) {
        titleTextView.text = getString(stringId)
        descriptionTextView.text = description
    }

    companion object {
        fun newInstance(contact: Contact): ContactDetailFragment {
            val bundle = Bundle().apply {
                putParcelable(IntentKey.CONTACT, contact)
            }
            return ContactDetailFragment().apply {
                arguments = bundle
            }
        }
    }
}





