package com.leesunae.bebehelper_mvp.view.recommendedActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.databinding.FragmentRecomendedActivityBinding
import com.leesunae.bebehelper_mvp.view.base.BaseFragment

class RecommendedActivityFragment :
    BaseFragment<FragmentRecomendedActivityBinding>(R.layout.fragment_recomended_activity) {

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