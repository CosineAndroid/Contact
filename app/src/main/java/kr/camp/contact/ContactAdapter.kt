package kr.camp.contact

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import kr.camp.contact.data.Contact
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kr.camp.contact.data.Contact.Companion.VIEW_TYPE1
import kr.camp.contact.data.Contact.Companion.VIEW_TYPE2
import kr.camp.contact.databinding.ItemType1Binding
import kr.camp.contact.databinding.ItemType2Binding
import kr.camp.contact.registry.ContactRegistry
import java.util.Collections

class ContactAdapter(
    private val context: Context?,
    private val onClick: (Contact) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    ItemTouchHelperCallback.OnItemMoveListener {

    var contactList = mutableListOf<Contact>()

    fun startActivity(intent: Intent) {
        context?.startActivity(intent)
    }

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

            if (contact.showStar) {
                star.visibility = View.VISIBLE
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
            } else {
                star.visibility = View.GONE
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

            if (contact.showStar) {
                star.visibility = View.VISIBLE
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
            } else {
                star.visibility = View.GONE
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

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(contactList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    // swipe시 전화 연결
    override fun onItemSwiped(position: Int) {
        val contactNumber = contactList[position].phoneNumber
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${contactNumber}"))
        startActivity(intent) //  생성자로 context 가져오기
        notifyItemChanged(position)
    }

    // contacts추가 함수
    fun addContact(contact: Contact) {
        ContactRegistry.addContact(contact)
        notifyItemInserted(contactList.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setStarVisibility(value: Boolean) {
        contactList.forEach { it.showStar = value }
        notifyDataSetChanged()
    }
}