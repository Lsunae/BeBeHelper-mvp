package com.leesunae.bebehelper_mvp.view.recommendedActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.data.model.PlayItem
import com.leesunae.bebehelper_mvp.databinding.FragmentRecomendedActivityBinding
import com.leesunae.bebehelper_mvp.util.Utils
import com.leesunae.bebehelper_mvp.view.base.BaseFragment
import com.leesunae.bebehelper_mvp.view.recommendedActivity.adapter.PlayImgAdapter
import com.leesunae.bebehelper_mvp.view.recommendedActivity.presenter.RecommendedActivityContract
import com.leesunae.bebehelper_mvp.view.recommendedActivity.presenter.RecommendedActivityPresenter

class RecommendedActivityFragment :
    BaseFragment<FragmentRecomendedActivityBinding>(R.layout.fragment_recomended_activity),
    RecommendedActivityContract.View {
    private lateinit var presenter: RecommendedActivityContract.Presenter
    private lateinit var playImgAdapter: PlayImgAdapter
    private var playList = mutableListOf<PlayItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPlayImageList()
        setAdapter()
        presenter = RecommendedActivityPresenter(this, playImgAdapter, playImgAdapter)
        presenter.addData(playList)
        setClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun getIsFirstTextVisibility(isVisibility: Boolean, items: MutableList<PlayItem>) {
        if (isVisibility && items[0].name == Utils.string(requireContext(), R.string.play_block)) {
            binding.apply {
                tvPlayTitle.text = items[0].name
                tvPlayExplanation.text =
                    Utils.string(requireContext(), R.string.play_block_explanation)
            }
        }
    }

    /** 리사이클러뷰 어댑터 셋팅 */
    private fun setAdapter() {
        playImgAdapter = PlayImgAdapter()
        binding.rvPlayImg.apply {
            // 기존 코드
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = playImgAdapter
        }
    }

    private fun setClickListener() {
        playImgAdapter.setOnItemClickListener(object : PlayImgAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, item: PlayItem) {
                binding.apply {
                    tvPlayTitle.text = item.name
                    when (position) {
                        PLAY_BLOCK -> tvPlayExplanation.text =
                            Utils.string(requireContext(), R.string.play_block_explanation)
                        PLAY_DRAWING -> binding.tvPlayExplanation.text =
                            Utils.string(requireContext(), R.string.play_drawing_explanation)
                        PLAY_COOKING -> binding.tvPlayExplanation.text =
                            Utils.string(requireContext(), R.string.play_cooking_explanation)
                        PLAY_WATER -> binding.tvPlayExplanation.text =
                            Utils.string(requireContext(), R.string.play_water_explanation)
                        PLAY_PLANTS -> binding.tvPlayExplanation.text =
                            Utils.string(requireContext(), R.string.play_plants_explanation)
                    }
                }
            }
        })
    }

    private fun setPlayImageList() {
        playList.add(
            PlayItem(
                ContextCompat.getDrawable(requireContext(), R.drawable.play_block),
                Utils.string(requireContext(), R.string.play_block)
            )
        )   // 블럭놀이
        playList.add(
            PlayItem(
                ContextCompat.getDrawable(requireContext(), R.drawable.play_drawing),
                Utils.string(requireContext(), R.string.play_drawing)
            )
        )   // 그림 그리기
        playList.add(
            PlayItem(
                ContextCompat.getDrawable(requireContext(), R.drawable.play_cooking),
                Utils.string(requireContext(), R.string.play_cooking)
            )
        )   // 요리하기
        playList.add(
            PlayItem(
                ContextCompat.getDrawable(requireContext(), R.drawable.play_water),
                Utils.string(requireContext(), R.string.play_water)
            )
        )   // 물놀이
        playList.add(
            PlayItem(
                ContextCompat.getDrawable(requireContext(), R.drawable.play_plants),
                Utils.string(requireContext(), R.string.play_plants)
            )
        )   // 식물 심기
    }

    companion object {
        private const val PLAY_BLOCK = 0
        private const val PLAY_DRAWING = 1
        private const val PLAY_COOKING = 2
        private const val PLAY_WATER = 3
        private const val PLAY_PLANTS = 4
    }
}