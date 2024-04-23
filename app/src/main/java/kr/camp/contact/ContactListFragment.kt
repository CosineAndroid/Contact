package kr.camp.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kr.camp.contact.data.Contact
import kr.camp.contact.databinding.FragmentContactlistBinding
import kr.camp.contact.intent.IntentKey

class ContactListFragment : Fragment() {

    private val binding by lazy { FragmentContactlistBinding.inflate(layoutInflater) }

    private val contactAdapter: ContactAdapter by lazy {
        ContactAdapter{
            item ->
            adapterOnClick(item)
        }
    }
    // 온클릭 메소드
    private fun adapterOnClick(contact: Contact) {
        val bundle = Bundle().apply { putParcelable(IntentKey.CONTACT, contact) }
        val contactDetailFragment = ContactDetailFragment.newInstance(bundle)
        childFragmentManager.beginTransaction()
            .replace(R.id.main,contactDetailFragment)
            .setReorderingAllowed(true)
            .addToBackStack("")
            .commit()
    }

    // detail fragment에 값 주기
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {


        // 리싸이클러뷰- 어뎁터 연결
        with(binding.recyclerView) {
            this.adapter = contactAdapter
        }

        // 레이아웃 메니저
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false) // this를 쓸경우 mainAcrtivity의 context를 참조하게 돼서 안됨.


        return binding.root
    }
}

