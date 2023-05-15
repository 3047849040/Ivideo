package com.example.ivideo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.launcher.ARouter
import com.example.ivideo.adapter.FragmentAdapter
import com.example.ivideo.databinding.ActivityMainBinding
import com.example.ivideo.view.fragment.FourFragment
import com.example.ivideo.view.fragment.OneFragment
import com.example.ivideo.view.fragment.ThreeFragment
import com.example.ivideo.view.fragment.TwoFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var fragmentAdapter: FragmentAdapter
    var list:MutableList<Fragment> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list.add(OneFragment())
        list.add(TwoFragment())
        list.add(ThreeFragment())
        list.add(FourFragment())
        fragmentAdapter = FragmentAdapter(supportFragmentManager,FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,list)
        binding.vp.adapter = fragmentAdapter
        binding.bot.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bot_dsp -> binding.vp.currentItem = 0

                R.id.bot_fyt -> binding.vp.currentItem = 1

                R.id.bot_zb -> binding.vp.currentItem = 2

                R.id.bot_me -> binding.vp.currentItem = 3
            }
           true
        }

        binding.clSearch.setOnClickListener {
            ARouter.getInstance().build("/app/SearchActivity").navigation()
        }

        binding.vp.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }
}