package kr.camp.contact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.camp.contact.data.Contact
import kr.camp.contact.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val fragment = ContactDetailFragment.newInstance(Contact(0, "", "", "", ""))
        supportFragmentManager.beginTransaction().replace(R.id.testLayout, fragment).commit()
    }
}