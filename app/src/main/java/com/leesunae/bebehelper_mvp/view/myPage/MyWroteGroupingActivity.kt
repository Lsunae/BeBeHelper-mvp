package com.leesunae.bebehelper_mvp.view.myPage

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.leesunae.bebehelper_mvp.Injection
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.data.room.entity.Grouping
import com.leesunae.bebehelper_mvp.databinding.ActivityMyWroteGroupingBinding
import com.leesunae.bebehelper_mvp.util.OnSingleClickListener
import com.leesunae.bebehelper_mvp.util.Utils
import com.leesunae.bebehelper_mvp.view.base.BaseActivity
import com.leesunae.bebehelper_mvp.view.myPage.adapter.MyWroteGroupingRvAdapter
import com.leesunae.bebehelper_mvp.view.myPage.presenter.MyWroteGroupingContract
import com.leesunae.bebehelper_mvp.view.myPage.presenter.MyWroteGroupingPresenter
import kotlinx.android.synthetic.main.layout_actionbar_detail.*

class MyWroteGroupingActivity :
    BaseActivity<ActivityMyWroteGroupingBinding>(R.layout.activity_my_wrote_grouping), MyWroteGroupingContract.View {
    private lateinit var presenter: MyWroteGroupingContract.Presenter
    private lateinit var myGroupingAdapter: MyWroteGroupingRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpView()
        setRvAdapter()
        presenter = MyWroteGroupingPresenter(Injection.groupingRepository(),this, myGroupingAdapter, myGroupingAdapter)
        presenter.getGroupingList()
    }

    override fun getGroupingListData(groupingList: MutableList<Grouping>) {
        println("my_grouping_groupingList_ $groupingList")
    }

    private fun setUpView() {
        binding.apply {
            incActionbar.tvTitle.text = Utils.string(this@MyWroteGroupingActivity, R.string.my_wrote_grouping)
            iv_back.setOnClickListener(object : OnSingleClickListener() {
                override fun onSingleClick(v: View) {
                    finish()
                }
            })
        }
    }

    private fun setRvAdapter() {
        myGroupingAdapter = MyWroteGroupingRvAdapter()
        binding.rvMyWroteGrouping.apply {
            // 기존 코드
            layoutManager = LinearLayoutManager(this@MyWroteGroupingActivity)
            adapter = myGroupingAdapter
        }
    }

}