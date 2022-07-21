package com.leesunae.bebehelper_mvp.view.grouping

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.databinding.FragmentGroupingBinding
import com.leesunae.bebehelper_mvp.util.OnSingleClickListener
import com.leesunae.bebehelper_mvp.view.base.BaseFragment

class GroupingFragment : BaseFragment<FragmentGroupingBinding>(R.layout.fragment_grouping) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
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
}