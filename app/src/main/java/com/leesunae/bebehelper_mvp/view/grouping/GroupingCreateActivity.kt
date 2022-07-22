package com.leesunae.bebehelper_mvp.view.grouping

import android.os.Bundle
import android.view.View
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.databinding.ActivityGroupingCreateBinding
import com.leesunae.bebehelper_mvp.util.OnSingleClickListener
import com.leesunae.bebehelper_mvp.util.Utils
import com.leesunae.bebehelper_mvp.view.base.BaseActivity

class GroupingCreateActivity :
    BaseActivity<ActivityGroupingCreateBinding>(R.layout.activity_grouping_create) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
    }

    private fun setupView() {
        binding.apply {
            incActionbar.apply {
                tvTitle.text = Utils.string(this@GroupingCreateActivity, R.string.group_write_title)
                ivBack.setOnClickListener(object : OnSingleClickListener() {
                    override fun onSingleClick(v: View) {
                        finish()
                    }
                })
            }
        }
    }
}