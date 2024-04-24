package kr.camp.contact

import kr.camp.contact.data.Contact
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kr.camp.contact.data.Contact.Companion.VIEW_TYPE1
import kr.camp.contact.data.Contact.Companion.VIEW_TYPE2
import kr.camp.contact.databinding.ItemType1Binding
import kr.camp.contact.databinding.ItemType2Binding
import kr.camp.contact.registry.ContactRegistry

class ContactAdapter(
    private val onClick: (Contact) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var contactList = mutableListOf<Contact>()

    // 뷰홀더1
    inner class ContactViewHolder1(
        private var binding: ItemType1Binding,
        val onClick: (Contact) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClick(contactList[position])
            }
        }

        fun bind1(contact: Contact) = with(binding) {
            // 별 아이콘 클릭 시 색깔변경 & 스낵바 표시
            name.text = contact.name
            if (contact.profileImageDrawableId != null) {
                circleImage.setImageResource(contact.profileImageDrawableId)
            } else {
                circleImage.setImageURI(contact.uriImage)
            }

            var isLiked = false
            star.setImageResource(
                if (isLiked) {
                    R.drawable.filled_star
                } else {
                    R.drawable.empty_star
                }
            )
            star.setOnClickListener {
                if (!isLiked) { // false 일 경우, 눌렀을 때 true로 바뀌면서 색깔채워넣기
                    star.setImageResource(R.drawable.filled_star)
                    Snackbar.make(type1, "관심 기업에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                    isLiked = true
                } else {
                    star.setImageResource(R.drawable.empty_star)
                    Snackbar.make(type1, "관심 기업에서 해제되었습니다.", Snackbar.LENGTH_SHORT).show()
                    isLiked = false
                }
            }
        }
    }

    // 뷰홀더2
    inner class ContactViewHolder2(
        private var binding: ItemType2Binding,
        val onClick: (Contact) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClick(contactList[position])
            }
        }

        fun bind2(contact: Contact) = with(binding) {
            name.text = contact.name
            if (contact.profileImageDrawableId != null) {
                circleImage.setImageResource(contact.profileImageDrawableId)
            } else {
                circleImage.setImageURI(contact.uriImage)
            }

            // 별 아이콘 클릭 시 색깔변경 & 스낵바 표시
            var isLiked = false
            star.setImageResource(
                if (isLiked) {
                    R.drawable.filled_star
                } else {
                    R.drawable.empty_star
                }
            )
            star.setOnClickListener {
                if (!isLiked) { // false 일 경우, 눌렀을 때 true로 바뀌면서 색깔채워넣기
                    star.setImageResource(R.drawable.filled_star)
                    Snackbar.make(type2, "관심 기업에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                    isLiked = true
                } else {
                    star.setImageResource(R.drawable.empty_star)
                    Snackbar.make(type2, "관심 기업에서 해제되었습니다.", Snackbar.LENGTH_SHORT).show()
                    isLiked = false
                }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return contactList[position].viewType // positon을 when으로 빼지않고, 각 position의 type값을 받아와서 확장성있게 사용하기.
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE1 -> {
                val binding = ItemType1Binding.inflate(layoutInflater, parent, false)
                ContactViewHolder1(binding, onClick)
            }

            VIEW_TYPE2 -> {
                val binding = ItemType2Binding.inflate(layoutInflater, parent, false)
                ContactViewHolder2(binding, onClick)
            }

            else -> throw RuntimeException("알 수 없는 뷰타입 에러")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val obj = contactList[position]
        when (obj.viewType) {
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
        notifyItemInserted(contactList.size)
    }
}





