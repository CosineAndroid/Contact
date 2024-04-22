package kr.camp.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kr.camp.contact.databinding.ActivityMainBinding
import kr.camp.contact.databinding.FragmentContactlistBinding

class ContactListFragment : Fragment() {

    private lateinit var binding: FragmentContactlistBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListContact.inflate(inflater, container,false)
        return binding.root
    }
}



