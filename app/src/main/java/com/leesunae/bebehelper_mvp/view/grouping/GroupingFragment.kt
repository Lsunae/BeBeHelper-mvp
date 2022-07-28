package com.leesunae.bebehelper_mvp.view.grouping

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.leesunae.bebehelper_mvp.Injection
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.data.room.entity.Grouping
import com.leesunae.bebehelper_mvp.databinding.FragmentGroupingBinding
import com.leesunae.bebehelper_mvp.databinding.ItemGroupingBinding
import com.leesunae.bebehelper_mvp.util.OnSingleClickListener
import com.leesunae.bebehelper_mvp.view.base.BaseFragment
import com.leesunae.bebehelper_mvp.view.grouping.presenter.GroupingContract
import com.leesunae.bebehelper_mvp.view.grouping.presenter.GroupingPresenter
import java.lang.ref.WeakReference

class GroupingFragment : BaseFragment<FragmentGroupingBinding>(R.layout.fragment_grouping), GroupingContract.View {
    private lateinit var presenter: GroupingContract.Presenter
    private lateinit var groupingAdapter: GroupingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        groupingAdapter.groupingFragment = WeakReference(this)

        presenter = GroupingPresenter(Injection.groupingRepository(), this, groupingAdapter, groupingAdapter)
        presenter.getGroupingList(requireContext())
        setUpView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun getGroupingListData(groupings: MutableList<Grouping>) {
        println("groupings_ $groupings")
    }

    /** 리사이클러뷰 어댑터 셋팅 */
    private fun setAdapter() {
        groupingAdapter = GroupingAdapter()
        binding.rvGrouping.apply {
            // 기존 코드
            layoutManager = LinearLayoutManager(requireContext())
            adapter = groupingAdapter
        }
    }

    /** 뷰 셋팅 */
    private fun setUpView() {
        binding.apply {
            // 리스트 새로고침
            swipeLayout.setOnRefreshListener {
                presenter.getGroupingList(requireContext())
                swipeLayout.isRefreshing = false
            }

            setClickListener()
        }
    }

    private fun setClickListener() {
        binding.apply {
            ivCreate.setOnClickListener(object : OnSingleClickListener() {
                override fun onSingleClick(v: View) {
                    val intent = Intent(requireContext(), GroupingCreateActivity::class.java)
                    startActivity(intent)
                }
            })
        }
    }

    /** 리스트 더보기 */
    fun apiListMore() {
        activity?.runOnUiThread {
            presenter.getGroupingList(requireContext())
        }
    }
}