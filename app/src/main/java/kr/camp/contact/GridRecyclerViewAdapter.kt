package kr.camp.contact

import kr.camp.contact.data.Contact
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kr.camp.contact.data.Contact.Companion.VIEW_TYPE1
import kr.camp.contact.data.Contact.Companion.VIEW_TYPE2
import kr.camp.contact.databinding.GridlayoutItemBinding
import kr.camp.contact.databinding.ItemType1Binding
import kr.camp.contact.databinding.ItemType2Binding
import kr.camp.contact.registry.ContactRegistry

class GridRecyclerViewAdapter(
    private val onClick: (Contact) -> Unit
) : RecyclerView.Adapter<GridRecyclerViewAdapter.ContactViewHolder>() {

    var contactList = mutableListOf<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gridlayout_item, parent, false)
        return ContactViewHolder(GridlayoutItemBinding.bind(view), onClick)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val obj = contactList[position]

        holder.bind(obj)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    // contacts추가 함수
    fun addContact(contact: Contact) {
        ContactRegistry.addContact(contact)
        notifyItemInserted(contactList.size)
    }


    class ContactViewHolder(private var binding: GridlayoutItemBinding, val onClick: (Contact) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        var contactList = mutableListOf<Contact>()

        init {
            itemView.setOnClickListener {
                onClick(contactList[position])
            }
        }

        fun bind(contact: Contact) = with(binding) {
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
                    isLiked = true
                } else {
                    star.setImageResource(R.drawable.empty_star)
                    isLiked = false
                }
            }
        }
    }
}





