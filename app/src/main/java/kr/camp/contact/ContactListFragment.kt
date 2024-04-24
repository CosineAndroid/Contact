package kr.camp.contact

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.manager.Lifecycle
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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // fragment에서 mainActivity의 툴바 커스텀하는 코드(contactlist에서만 메뉴바 선택할 수 있게 )
        val menuHost: MenuHost = requireActivity() // fragment가 속한 activity 반환
        menuHost.addMenuProvider(object : MenuProvider {
            // 툴바 메뉴
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.contactlist_menu_toolbar,menu)
            }

            // 툴바 클릭이벤트
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
            android.R.id.home -> { // 뒤로가기 버튼
                activity?.finish()
            }
            R.id.toolbar_info -> { // 레이아웃 타입 메뉴 띄우기
                when(menuItem.itemId) {
                    R.id.first -> {
                        binding.recyclerView.layoutManager = LinearLayoutManager(
                            requireContext(), LinearLayoutManager.VERTICAL, false
                        )
                    }
                    R.id.second-> {
//                        binding.recyclerView.layoutManager = GridLayoutManager(
//                            requireContext(),  GridLayoutManager.DEFAULT_SPAN_COUNT, false
//                        )
                    }
                }
            }
        }
        return true
            }
        }, viewLifecycleOwner, androidx.lifecycle.Lifecycle.State.RESUMED)

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


    fun setContactButtonVisibility(visibility: Int) {
        binding.addContact.visibility = visibility
    }
}

