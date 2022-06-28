package com.leesunae.bebehelper_mvp.view.grouping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.databinding.FragmentGroupingBinding
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

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}