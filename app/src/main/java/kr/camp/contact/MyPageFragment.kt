package kr.camp.contact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.camp.contact.databinding.FragmentMypageBinding

class MyPageFragment : Fragment() {

    private val binding by lazy { FragmentMypageBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.floatingButton.setOnClickListener {
            showDialog()
        }

        return binding.root

    }

    private fun showDialog(){
        MypageDialog(requireContext()).show()

    }


}