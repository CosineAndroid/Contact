package kr.camp.contact

import kr.camp.contact.data.Contact
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kr.camp.contact.data.Contact.Companion.VIEW_TYPE1
import kr.camp.contact.data.Contact.Companion.VIEW_TYPE2
import kr.camp.contact.databinding.ItemType1Binding
import kr.camp.contact.databinding.ItemType2Binding
import kr.camp.contact.registry.ContactRegistry

class ContactAdapter(private val onClick: (Contact) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val contactList = mutableListOf<Contact>()
    // 뷰홀더1
    inner class ContactViewHolder1(private var binding: ItemType1Binding, val onClick: (Contact) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onClick(contactList[position])
            }
        }
        fun bind1(contact: Contact) {
            // 별 아이콘 클릭 시 색깔변경 & 스낵바 표시

            binding.name.text = contact.name

            var isLiked = false
            binding.star.setImageResource(if (isLiked) {R.drawable.filled_star} else {R.drawable.empty_star})

            binding.star.setOnClickListener {
                if (!isLiked) { // false 일 경우, 눌렀을 때 true로 바뀌면서 색깔채워넣기
                    binding.star.setImageResource(R.drawable.filled_star)
                    Snackbar.make(binding.type1, "관심 기업에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                    isLiked = true
                } else {
                    binding.star.setImageResource(R.drawable.empty_star)
                    isLiked = false
                }
            }


        }
    }

    // 뷰홀더2
    inner class ContactViewHolder2(private var binding: ItemType2Binding,
        val onClick: (Contact) -> Unit
    )
        : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onClick(contactList[position])
            }
        }

        fun bind2(contact: Contact) {

            binding.name.text = contact.name

            // 별 아이콘 클릭 시 색깔변경 & 스낵바 표시
            var isLiked = false
            binding.star.setImageResource(if (isLiked) {R.drawable.filled_star} else {R.drawable.empty_star})

            binding.star.setOnClickListener {
                if (!isLiked) { // false 일 경우, 눌렀을 때 true로 바뀌면서 색깔채워넣기
                    binding.star.setImageResource(R.drawable.filled_star)
                    Snackbar.make(binding.type2, "관심 기업에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                    isLiked = true
                } else {
                    binding.star.setImageResource(R.drawable.empty_star)
                    isLiked = false
                }
            }

        }
    }


    override fun getItemViewType(position: Int): Int {
        return contactList[position].viewType // positon을 when으로 빼지않고, 각 position의 type값을 받아와서 확장성있게 사용하기.
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE1 -> {
                val binding = LayoutInflater.from(parent.context).inflate(R.layout.item_type1, parent, false)
                return ContactViewHolder1(ItemType1Binding.bind(binding), onClick)
            }
            VIEW_TYPE2 -> {
                val binding = LayoutInflater.from(parent.context).inflate(R.layout.item_type2, parent, false)
                return ContactViewHolder2(ItemType2Binding.bind(binding), onClick)
            }
            else -> throw RuntimeException("알 수 없는 뷰타입 에러")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val obj = contactList[position]
        when(obj.viewType) {
            VIEW_TYPE1 -> (holder as ContactViewHolder1).bind1(obj)
            VIEW_TYPE2 -> (holder as ContactViewHolder2).bind2(obj)

        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    // contacts추가 함수
    fun addContact(contact: Contact) {
        ContactRegistry.addContact(contact)
        contactList.add(contact)
        notifyItemInserted(contactList.size)
    }
}





