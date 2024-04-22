package kr.camp.contact

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kr.camp.contact.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewPager()
    }

    private fun viewPager(){

        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(ContactListFragment())
        fragmentList.add(MyPageFragment())

        binding.viewpager.adapter = ViewPagerAdapter(fragmentList, this)

        val tabTitle = listOf(getString(R.string.taplayout_contact),getString(R.string.taplayout_mypage))

        TabLayoutMediator(binding.tabLayout, binding.viewpager){ tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }
}