package kr.camp.contact

import android.os.Bundle
import android.view.Menu
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

        // 툴바 이미지
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // 제목표시X 24번 라인에서 동적으로 추가
        binding.toolbar.title = getString(R.string.tablayout_contact)
    }

    // 툴바 메뉴
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    private fun viewPager() {
        val fragmentList = ArrayList<Fragment>().apply {
            add(ContactListFragment())
            add(MyPageFragment())
        }
        binding.viewPager.adapter = ViewPagerAdapter(fragmentList, this)

        // tabLayout
        val tabTitle = listOf(getString(R.string.tablayout_contact), getString(R.string.tablayout_mypage))
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    fun showBar() = with(binding) {
        toolbar.visibility = View.VISIBLE
        tabLayout.visibility = View.VISIBLE
        viewPager.setUserInputEnabled(true)
    }

    fun hideBar() = with(binding) {
        toolbar.visibility = View.GONE
        tabLayout.visibility = View.GONE
        viewPager.setUserInputEnabled(false)
    }
}