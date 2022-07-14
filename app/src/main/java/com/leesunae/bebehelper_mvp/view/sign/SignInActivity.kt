package com.leesunae.bebehelper_mvp.view.sign

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.leesunae.bebehelper_mvp.App
import com.leesunae.bebehelper_mvp.Injection
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.databinding.ActivitySignInBinding
import com.leesunae.bebehelper_mvp.util.OnSingleClickListener
import com.leesunae.bebehelper_mvp.view.MainActivity
import com.leesunae.bebehelper_mvp.view.base.BaseActivity
import com.leesunae.bebehelper_mvp.view.sign.presenter.SignInContract
import com.leesunae.bebehelper_mvp.view.sign.presenter.SignInPresenter

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in),
    SignInContract.View {
    private lateinit var presenter: SignInContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SignInPresenter(Injection.userRepository(), this)
        setUpView()
    }

    override fun loginSuccess(message: Boolean) {

    }

    private fun setUpView() {
        binding.apply {
            incInputEmail.etInput.hint = resources.getString(R.string.email)
            incInputPassword.etInput.hint = resources.getString(R.string.password)

            // 회원가입 텍스트 컬러 셋팅
            val span = SpannableString(tvSignUp.text)
            val spanStartLength = tvSignUp.text.length - 4
            span.setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(
                        this@SignInActivity,
                        R.color.pink
                    )
                ), spanStartLength, tvSignUp.text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            tvSignUp.text = span
            tvSignUp.setOnClickListener(object : OnSingleClickListener() {
                override fun onSingleClick(v: View) {

                    val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
                    startActivity(intent)
                }
            })

            // testCode_ 메인화면 이동
            tvMain.setOnClickListener(object : OnSingleClickListener() {
                override fun onSingleClick(v: View) {
                    val intent = Intent(this@SignInActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    startActivity(intent)
                }
            })
        }
    }
}