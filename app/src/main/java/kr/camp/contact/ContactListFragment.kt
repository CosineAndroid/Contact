package kr.camp.contact

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kr.camp.contact.data.Contact
import kr.camp.contact.databinding.FragmentContactlistBinding
import kr.camp.contact.registry.ContactRegistry

class ContactListFragment : Fragment() {

    private val binding by lazy { FragmentContactlistBinding.inflate(layoutInflater) }

    private val contactAdapter: ContactAdapter by lazy {
        ContactAdapter { item ->
            adapterOnClick(item)
        }
    }

    // 온클릭 메소드
    private fun adapterOnClick(contact: Contact) {
        val contactDetailFragment = ContactDetailFragment.newInstance(contact)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.contact_list, contactDetailFragment)
            ?.setReorderingAllowed(true)
            ?.addToBackStack(null)
            ?.commit()
    }

    // detail fragment에 값 주기
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        // 리스트 연결
        contactAdapter.contactList = ContactRegistry.contacts as MutableList<Contact>
        Log.d("contactList", ContactRegistry.contacts.toString())
        Log.d("contactList", contactAdapter.contactList.toString())

        // 리싸이클러뷰- 어뎁터 연결
        with(binding.recyclerView) {
            this.adapter = contactAdapter
        }
        // 레이아웃 메니저
        binding.recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        ) // this를 쓸경우 mainAcrtivity의 context를 참조하게 돼서 안됨.

        // add 버튼 클릭
        binding.addContact.setOnClickListener {
            val dialog = AddContactDialog()

            dialog.setButtonClickListener(object : AddContactDialog.OnButtonClickListener {
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
                    Toast.makeText(
                        context,
                        getString(R.string.contact_list_add),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

            dialog.show(parentFragmentManager, "custom")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 툴바 이미지
        val activity = requireActivity() as AppCompatActivity

        activity.supportActionBar?.hide()
        activity.setSupportActionBar(binding.contactlistToolbar)
        activity.supportActionBar?.setDisplayShowTitleEnabled(false) // 제목표시X 아래에서 추가
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼
        binding.contactlistToolbar.title = "Contact"

    }
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        activity?.menuInflater?.inflate(R.menu.menu_toolbar, menu)
//        return true
//    }

    // 툴바 메뉴 클릭이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { // 뒤로가기 버튼
                activity?.finish()
            }
            R.id.toolbar_info -> { // 레이아웃 타입 메뉴 띄우기
                when(item.itemId) {
                    R.id.first -> {

                    }
                    R.id.second-> {

                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun setContactButtonVisibility(visibility: Int) {
        binding.addContact.visibility = visibility
    }
}

