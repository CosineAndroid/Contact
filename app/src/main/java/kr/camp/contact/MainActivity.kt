package kr.camp.contact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kr.camp.contact.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ViewPager()

        setContentView(binding.root)
    }

    private fun ViewPager(){
        val fragmentList = listOf(MyPageFragment(), MyPageFragment())
    }
}