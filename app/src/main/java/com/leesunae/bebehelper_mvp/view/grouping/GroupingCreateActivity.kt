package com.leesunae.bebehelper_mvp.view.grouping

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.leesunae.bebehelper_mvp.Injection
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.data.model.GroupingItem
import com.leesunae.bebehelper_mvp.databinding.ActivityGroupingCreateBinding
import com.leesunae.bebehelper_mvp.util.OnSingleClickListener
import com.leesunae.bebehelper_mvp.util.Utils
import com.leesunae.bebehelper_mvp.view.base.BaseActivity
import com.leesunae.bebehelper_mvp.view.grouping.presenter.GroupingCreateContract
import com.leesunae.bebehelper_mvp.view.grouping.presenter.GroupingCreatePresenter

class GroupingCreateActivity :
    BaseActivity<ActivityGroupingCreateBinding>(R.layout.activity_grouping_create),
    GroupingCreateContract.View {
    private lateinit var presenter: GroupingCreateContract.Presenter
    private var groupingItem = GroupingItem()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = GroupingCreatePresenter(Injection.groupingRepository(), this)
        setupView()
    }

    override fun createGroupingSuccess(message: Boolean) {

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

            tvCreate.setOnClickListener(object : OnSingleClickListener() {
                override fun onSingleClick(v: View) {
                    presenter.createUser(groupingItem)
                }
            })

            //note 지역 선택
            val areaArray = resources.getStringArray(R.array.area_array)
            val areaAdapter: ArrayAdapter<String> =
                ArrayAdapter(this@GroupingCreateActivity, R.layout.item_select_text, areaArray)
            tvSelectArea.setAdapter(areaAdapter)
            tvSelectArea.onItemClickListener =
                AdapterView.OnItemClickListener { adapterView, view, position, id ->
                    groupingItem.area = adapterView.getItemAtPosition(position).toString()
                }

            //note 아이 나이 선택
            val ageArray = resources.getStringArray(R.array.child_age_array)
            val ageAdapter: ArrayAdapter<String> =
                ArrayAdapter(this@GroupingCreateActivity, R.layout.item_select_text, ageArray)
            tvSelectChildAge.setAdapter(ageAdapter)
            tvSelectChildAge.onItemClickListener =
                AdapterView.OnItemClickListener { adapterView, view, position, id ->
                    groupingItem.ageLimit = adapterView.getItemAtPosition(position).toString().replace("세", "")
                }

            //note 아이 나이 선택
            val childCountArray = resources.getStringArray(R.array.child_age_array)
            val childCountAdapter: ArrayAdapter<String> =
                ArrayAdapter(this@GroupingCreateActivity, R.layout.item_select_text, childCountArray)
            tvSelectChildCount.setAdapter(childCountAdapter)
            tvSelectChildCount.onItemClickListener =
                AdapterView.OnItemClickListener { adapterView, view, position, id ->
                    groupingItem.ageLimit = adapterView.getItemAtPosition(position).toString().replace("세", "")
                }

        }
    }

    private fun checkValid() {
        binding.apply {
            val title = tvTitle.text.toString()

            val content = tvContent.text.toString()
        }
    }
}