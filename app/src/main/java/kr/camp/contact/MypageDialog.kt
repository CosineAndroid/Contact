package kr.camp.contact

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import kr.camp.contact.databinding.MypageDialogBinding

class MypageDialog(context: Context) : Dialog(context) {

    private val binding by lazy { MypageDialogBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        myPageDialog()
    }


    private fun myPageDialog() = with(binding){
        // 화면 터치를 통해 dialog가 종료되지 않도록
        setCancelable(false)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 버튼 클릭이벤트 처리
        cencelButton.setOnClickListener {
            dismiss()
            Toast.makeText(context, "취소", Toast.LENGTH_SHORT).show()
        }
        saveButton.setOnClickListener {

//            Toast.makeText(context, "저장", Toast.LENGTH_SHORT).show()
        }
    }
}