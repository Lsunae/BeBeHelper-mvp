package com.leesunae.bebehelper_mvp.view.myPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.Session
import com.leesunae.bebehelper_mvp.databinding.FragmentMyPageBinding
import com.leesunae.bebehelper_mvp.util.CustomDialog
import com.leesunae.bebehelper_mvp.util.OnSingleClickListener
import com.leesunae.bebehelper_mvp.util.Utils
import com.leesunae.bebehelper_mvp.view.base.BaseFragment
import com.leesunae.bebehelper_mvp.view.sign.SignInActivity

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private lateinit var dialog: CustomDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog = CustomDialog(requireContext())

        binding.tvSignOut.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {
                Session.logout {
                    if (it) {
                        dialog.apply {
                            showDialog(
                                "",
                                Utils.string(requireContext(), R.string.sign_out_success),
                                true
                            )
                            setOkClickListener(object : CustomDialog.OkClickListener {
                                override fun okClick() {
                                    goToSignIn()
                                }
                            })
                        }
                    } else {
                        dialog.showDialog(
                            "",
                            Utils.string(requireContext(), R.string.sign_out_fail),
                            false
                        )
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    /** 로그인 화면 이동 */
    private fun goToSignIn() {
        val intent = Intent(requireContext(), SignInActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}