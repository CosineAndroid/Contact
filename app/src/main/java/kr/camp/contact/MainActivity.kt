package kr.camp.contact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import kr.camp.contact.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPager()
        setContentView(binding.root)

//        // navigaton 버튼 클릭
//        binding.apply{
//            fragmentBtn1.setOnclickListner {
//                setFragment(ContactListFragment())
//            }
//            fragmentBtn2.setOnclickListner{
//                setFragment(MyPageFragment)
//            }
//        }
        setFragment(ContactListFragment())
    }

        private fun setFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.viewpager,fragment)
            setReorderingAllowed(true)
            addToBackStack("")
        }

        }




    private fun viewPager(){
        val fragmentList = listOf(MyPageFragment(), MyPageFragment())
    }
}