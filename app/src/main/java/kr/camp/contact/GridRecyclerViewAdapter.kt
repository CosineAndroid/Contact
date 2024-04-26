package kr.camp.contact
import kr.camp.contact.data.Contact
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kr.camp.contact.databinding.GridlayoutItemBinding
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

        // 아이템 클릭 리스너 설정
        holder.itemView.setOnClickListener {
            onClick(obj)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }


    class ContactViewHolder(private var binding: GridlayoutItemBinding, val onClick: (Contact) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

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
                    Snackbar.make(binding.root, "관심 기업에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                    isLiked = true
                } else {
                    star.setImageResource(R.drawable.empty_star)
                    Snackbar.make(binding.root, "관심 기업에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                    isLiked = false
                }
            }
        }
    }
}






