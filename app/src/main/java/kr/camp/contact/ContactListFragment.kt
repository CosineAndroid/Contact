package kr.camp.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import kr.camp.contact.databinding.FragmentContactlistBinding

class ContactListFragment : Fragment() {

    private val binding by lazy { FragmentContactlistBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
}

