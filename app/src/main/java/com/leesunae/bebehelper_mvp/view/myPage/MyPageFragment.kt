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
        setUpView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun setUpView() {
        val user = Session.getUser()
        binding.apply {
            incActionbar.tvTitle.text = Utils.string(requireContext(), R.string.my_page)

            tvUserName.text = String.format(
                Utils.string(requireContext(), R.string.nickname_text),
                user?.nickname
            )
            tvChildAge.text = String.format(
                Utils.string(requireContext(), R.string.child_age_text),
                user?.ageOfChildren
            )
            tvChildGender.text = String.format(
                Utils.string(requireContext(), R.string.child_gender_text),
                user?.childGender
            )
            tvUserArea.text =
                String.format(Utils.string(requireContext(), R.string.area_text), user?.area)
        }
        setSignOut()
    }

    private fun setSignOut() {
        binding.tvSignOut.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {

                // 로그아웃 다시 한 번 체크하는 다이얼로그
                dialog.apply {
                    showProposalDialog(
                        "",
                        Utils.string(requireContext(), R.string.sign_out_check),
                        Utils.string(requireContext(), R.string.sign_out),
                        Utils.string(requireContext(), R.string.cancel),
                        true
                    )
                    // 로그아웃 클릭 시
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            Session.logout {
                                if (it) {
                                    // 로그아웃 성공 다이얼로그
                                    goToSignIn()
                                } else {
                                    // 로그아웃 실패 다이얼로그
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
            }
        })
    }

    /** 로그인 화면 이동 */
    private fun goToSignIn() {
        val intent = Intent(requireContext(), SignInActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}