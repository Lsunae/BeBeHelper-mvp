package com.leesunae.bebehelper_mvp.view

import android.os.Bundle
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.constant.MenuType
import com.leesunae.bebehelper_mvp.databinding.ActivityMainBinding
import com.leesunae.bebehelper_mvp.view.base.BaseActivity
import com.leesunae.bebehelper_mvp.view.grouping.GroupingFragment
import com.leesunae.bebehelper_mvp.view.infoSharing.InfoSharingFragment
import com.leesunae.bebehelper_mvp.view.myPage.MyPageFragment
import com.leesunae.bebehelper_mvp.view.recommendedActivity.RecommendedActivityFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private var groupingFragment: GroupingFragment? = null
    private var infoSharingFragment: InfoSharingFragment? = null
    private var recommendedActivityFragment: RecommendedActivityFragment? = null
    private var myPageFragment: MyPageFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavigationBar()
    }

    private fun initNavigationBar() {
        binding.bottomNavigationView.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.action_grouping -> {
                        changeFragment(MenuType.GROUPING)
                    }
                    R.id.action_info_sharing -> {
                        changeFragment(MenuType.INFO_SHARING)
                    }
                    R.id.action_recommended_activity -> {
                        changeFragment(MenuType.RECOMMENDED_ACTIVITY)
                    }
                    R.id.action_my_page -> {
                        changeFragment(MenuType.MY_PAGE)
                    }
                }
                true
            }
            selectMenuTabIndex(MenuType.GROUPING)
        }
    }

    /** 메뉴 탭 선택 */
    private fun selectMenuTabIndex(type: MenuType?) {
        val navigation = binding.bottomNavigationView
        runOnUiThread {
            when (type) {
                MenuType.INFO_SHARING -> navigation.selectedItemId = R.id.action_grouping
                MenuType.RECOMMENDED_ACTIVITY -> navigation.selectedItemId = R.id.action_recommended_activity
                MenuType.MY_PAGE -> navigation.selectedItemId = R.id.action_my_page
                else -> navigation.selectedItemId = R.id.action_grouping
            }
        }
    }


    /**
     * 프래그먼트 변경
     * 각 프래그먼트 화면 유지를 위해 hide, show 사용
     */
    private fun changeFragment(type: MenuType) {
        when (type) {
            MenuType.GROUPING -> {
                if (groupingFragment == null) {
                    groupingFragment = GroupingFragment()
                    supportFragmentManager.beginTransaction()
                        .add(binding.mainContainer.id, groupingFragment!!).commit()
                } else {
                    supportFragmentManager.beginTransaction().show(groupingFragment!!).commit()
                    groupingFragment!!.onResume()
                }

                if (infoSharingFragment != null) supportFragmentManager.beginTransaction()
                    .hide(infoSharingFragment!!).commit()
                if (recommendedActivityFragment != null) supportFragmentManager.beginTransaction()
                    .hide(recommendedActivityFragment!!).commit()
                if (myPageFragment != null) supportFragmentManager.beginTransaction()
                    .hide(myPageFragment!!).commit()
            }
            MenuType.INFO_SHARING -> {
                if (infoSharingFragment == null) {
                    infoSharingFragment = InfoSharingFragment()
                    supportFragmentManager.beginTransaction()
                        .add(binding.mainContainer.id, infoSharingFragment!!).commit()
                } else {
                    supportFragmentManager.beginTransaction().show(infoSharingFragment!!).commit()
                    infoSharingFragment!!.onResume()
                }

                if (groupingFragment != null) supportFragmentManager.beginTransaction()
                    .hide(groupingFragment!!).commit()
                if (recommendedActivityFragment != null) supportFragmentManager.beginTransaction()
                    .hide(recommendedActivityFragment!!).commit()
                if (myPageFragment != null) supportFragmentManager.beginTransaction()
                    .hide(myPageFragment!!).commit()
            }
            MenuType.RECOMMENDED_ACTIVITY -> {
                if (recommendedActivityFragment == null) {
                    recommendedActivityFragment = RecommendedActivityFragment()
                    supportFragmentManager.beginTransaction()
                        .add(binding.mainContainer.id, recommendedActivityFragment!!).commit()
                } else {
                    supportFragmentManager.beginTransaction().show(recommendedActivityFragment!!).commit()
                    recommendedActivityFragment!!.onResume()
                }

                if (groupingFragment != null) supportFragmentManager.beginTransaction()
                    .hide(groupingFragment!!).commit()
                if (infoSharingFragment != null) supportFragmentManager.beginTransaction()
                    .hide(infoSharingFragment!!).commit()
                if (myPageFragment != null) supportFragmentManager.beginTransaction()
                    .hide(myPageFragment!!).commit()
            }
            MenuType.MY_PAGE -> {
                if (myPageFragment == null) {
                    myPageFragment = MyPageFragment()
                    supportFragmentManager.beginTransaction()
                        .add(binding.mainContainer.id, myPageFragment!!).commit()
                } else {
                    supportFragmentManager.beginTransaction().show(myPageFragment!!).commit()
                    myPageFragment!!.onResume()
                }

                if (groupingFragment != null) supportFragmentManager.beginTransaction()
                    .hide(groupingFragment!!).commit()
                if (infoSharingFragment != null) supportFragmentManager.beginTransaction()
                    .hide(infoSharingFragment!!).commit()
                if (recommendedActivityFragment != null) supportFragmentManager.beginTransaction()
                    .hide(recommendedActivityFragment!!).commit()
            }
        }
    }

}