package kr.camp.contact

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import kr.camp.contact.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPager()
        setContentView(binding.root)
    }

    private fun viewPager() {

        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(ContactListFragment())
        fragmentList.add(MyPageFragment())

        binding.viewPager.adapter = ViewPagerAdapter(fragmentList, this)

        // tabLayout
        val tabTitle = listOf(getString(R.string.taplayout_contact), getString(R.string.taplayout_mypage))
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    fun showTabLayout() = with(binding) {
        tabLayout.visibility = View.VISIBLE
        viewPager.setUserInputEnabled(true)
    }

    fun hideTabLayout() = with(binding) {
        tabLayout.visibility = View.GONE
        viewPager.setUserInputEnabled(false)
    }
}










