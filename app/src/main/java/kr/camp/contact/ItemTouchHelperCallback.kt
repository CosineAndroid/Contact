package kr.camp.contact

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kr.camp.contact.data.Contact

class ItemTouchHelperCallback(
    private val itemMoveListener: OnItemMoveListener
) : ItemTouchHelper.Callback() { // ItemTouchHelper  상속받음

    interface OnItemMoveListener { // 리싸이클러뷰 어뎁터와 통신
        // Drag할 때
        fun onItemMoved(fromPosition: Int, toPosition: Int)

        // Swipe할 때
        fun onItemSwiped(position: Int)
    }


    // 어떤 이벤트 허용할 것인가(SWIPE or DRAG) ※ swipe: 짧은 접촉 / drag: 긴 접촉
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return if (recyclerView.layoutManager is GridLayoutManager) {
            // GridLayout 형식
            val dragFlags =
                ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            val swipeFlags = ItemTouchHelper.END
            makeMovementFlags(dragFlags, swipeFlags)
        } else {
            // 일반 LinearLayout 형식
            val dragFlags =
                ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            val swipeFlags = ItemTouchHelper.END
            makeMovementFlags(dragFlags, swipeFlags)
        }
    }

    // Drag & DROP 할 때 호출
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        // 리싸이클러뷰에서 현재 선택된 데이터와 드래그한 위치에 있는 데이터를 실시간으로 교환
        val fromPositon: Int = viewHolder.adapterPosition // 기존 포지션
        val toPosition: Int = target.adapterPosition      // 드래그했을 때 포지션
        itemMoveListener.onItemMoved(fromPositon, toPosition)
        return true
    }

    //  Swipe할 때 호출": 전체 swipe해서 삭제하는 경우(반만 swipe하고 싶으면 onChildDrqw override 후, item.xml도 새롭게 바꿔야 함.)
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        itemMoveListener.onItemSwiped(viewHolder.adapterPosition)
    }

    // swipe 백그라운드 설정
    override fun onChildDraw(
        canvas: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val viewItem = viewHolder.itemView
            // 양수이면 오른쪽 스와이프
            if (dX > 0) {
                SwipeBackgroundHelper.paintDrawCommandToStart(
                    canvas,
                    viewItem,
                    R.drawable.contact_detail_button,
                    R.color.darkblue,
                    dX
                )
            }
        }
        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    override fun isLongPressDragEnabled(): Boolean = false
}