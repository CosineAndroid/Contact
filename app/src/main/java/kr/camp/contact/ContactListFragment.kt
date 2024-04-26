package kr.camp.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kr.camp.contact.data.Contact
import kr.camp.contact.databinding.FragmentContactlistBinding
import kr.camp.contact.registry.ContactRegistry

class ContactListFragment : Fragment() {

    private var _binding: FragmentContactlistBinding? = null
    private val binding get() = _binding!!

    private val contactAdapter: ContactAdapter by lazy {
        ContactAdapter(requireContext()) { item ->
            adapterOnClick(item)
        }
    }

    private val gridAdapter: GridRecyclerViewAdapter by lazy {
        GridRecyclerViewAdapter{ item ->
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
        _binding = FragmentContactlistBinding.inflate(inflater, container, false)
        // fragment에서 mainActivity의 툴바 커스텀하는 코드(contactlist에서만 메뉴바 선택할 수 있게 )
        val menuHost: MenuHost = requireActivity() // fragment가 속한 activity 반환
        menuHost.addMenuProvider(object : MenuProvider {
            // 툴바 메뉴
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.contactlist_menu_toolbar, menu)
            }

            // 툴바 클릭이벤트
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.first -> {
                        binding.recyclerView.adapter = contactAdapter
                        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                        (binding.recyclerView.adapter as ContactAdapter).setStarVisibility(true)
                    }

                    R.id.second -> {
                        binding.recyclerView.adapter = gridAdapter
                        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                    }
                }
                return true
            }
        }, viewLifecycleOwner, androidx.lifecycle.Lifecycle.State.RESUMED)

        // 리스트 연결
        contactAdapter.contactList = ContactRegistry.contacts as MutableList<Contact>
        gridAdapter.contactList = ContactRegistry.contacts as MutableList<Contact>

        // 리싸이클러뷰- 어뎁터 연결
        with(binding.recyclerView) {
            this.adapter = contactAdapter
        }

        // 레이아웃 메니저
        binding.recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        ) // this를 쓸경우 mainAcrtivity의 context를 참조하게 돼서 안됨.

        // 리싸이클러뷰 - 아이템터치헬퍼 - 아이템터치헬퍼콜백   ⇒ 연결
        val itemTouchHelperCallback = ItemTouchHelperCallback(contactAdapter)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.recyclerView)


        // add 버튼 클릭
        binding.addContact.setOnClickListener {
            val dialog = AddContactDialog {
                binding.recyclerView.layoutManager !is GridLayoutManager
            }
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
                    memo: String,
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

