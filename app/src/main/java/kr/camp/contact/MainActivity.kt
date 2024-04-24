package kr.camp.contact

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼
        binding.toolbar.title = "Contact"

    }

    // 툴바 메뉴
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    // 툴바 메뉴 클릭이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { // 뒤로가기 버튼
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun viewPager() {

        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(ContactListFragment())
        fragmentList.add(MyPageFragment())

        binding.viewPager.adapter = ViewPagerAdapter(fragmentList, this)

        // tabLayout
        val tabTitle =
            listOf(getString(R.string.taplayout_contact), getString(R.string.taplayout_mypage))
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










