package com.leesunae.bebehelper_mvp.view.grouping

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.leesunae.bebehelper_mvp.Injection
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.Session
import com.leesunae.bebehelper_mvp.data.model.GroupingItem
import com.leesunae.bebehelper_mvp.databinding.ActivityGroupingCreateBinding
import com.leesunae.bebehelper_mvp.util.CustomDialog
import com.leesunae.bebehelper_mvp.util.OnSingleClickListener
import com.leesunae.bebehelper_mvp.util.Utils
import com.leesunae.bebehelper_mvp.view.base.BaseActivity
import com.leesunae.bebehelper_mvp.view.grouping.presenter.GroupingCreateContract
import com.leesunae.bebehelper_mvp.view.grouping.presenter.GroupingCreatePresenter

class GroupingCreateActivity :
    BaseActivity<ActivityGroupingCreateBinding>(R.layout.activity_grouping_create),
    GroupingCreateContract.View {
    private lateinit var presenter: GroupingCreateContract.Presenter
    private lateinit var dialog: CustomDialog
    private var groupingItem = GroupingItem()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dialog = CustomDialog(this)
        presenter = GroupingCreatePresenter(Injection.groupingRepository(), this)
        setupView()
    }

    override fun createGroupingSuccess(message: Boolean) {
        runOnUiThread {
            dialog.apply {
                showDialog(
                    "",
                    Utils.string(this@GroupingCreateActivity, R.string.group_create_success),
                    true
                )
                setOkClickListener(object : CustomDialog.OkClickListener {
                    override fun okClick() {
                        finish()
                    }
                })
            }
        }
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
                    checkValid()
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
                    groupingItem.ageLimit =
                        adapterView.getItemAtPosition(position).toString().replace("세", "")
                }

            //note 아이 인원 선택
            val childCountArray = resources.getStringArray(R.array.child_count_array)
            val childCountAdapter: ArrayAdapter<String> =
                ArrayAdapter(
                    this@GroupingCreateActivity,
                    R.layout.item_select_text,
                    childCountArray
                )
            tvSelectChildCount.setAdapter(childCountAdapter)
            tvSelectChildCount.onItemClickListener =
                AdapterView.OnItemClickListener { adapterView, view, position, id ->
                    groupingItem.childCount =
                        adapterView.getItemAtPosition(position).toString().replace("명", "").toInt()
                }
        }
    }

    private fun checkValid() {
        binding.apply {
            val title = inputTitle.text.toString()
            val content = inputContent.text.toString()
            groupingItem.title = title
            groupingItem.content = content
            groupingItem.writerId = Session.getUser()?.id ?: 0
            groupingItem.writerNickname = Session.getUser()?.nickname ?: ""


            if (groupingItem.title.isEmpty()) tvTitle.requestFocus()
            else if (groupingItem.area.isEmpty()) tvArea.requestFocus()
            else if (groupingItem.ageLimit.isEmpty()) tvChildAge.requestFocus()
            else if (groupingItem.childCount == 0) tvChildCount.requestFocus()
            else if (groupingItem.content.isEmpty()) tvContent.requestFocus()
            else if (groupingItem.writerId == 0 || groupingItem.writerNickname.isEmpty()) dialog.showDialog(
                "",
                Utils.string(this@GroupingCreateActivity, R.string.need_to_login),
                false
            )
            else {
                presenter.createGrouping(groupingItem)
            }
        }
    }
}