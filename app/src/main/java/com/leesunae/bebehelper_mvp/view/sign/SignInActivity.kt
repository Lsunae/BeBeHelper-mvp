package com.leesunae.bebehelper_mvp.view.sign

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.databinding.ActivitySignInBinding
import com.leesunae.bebehelper_mvp.util.OnSingleClickListener
import com.leesunae.bebehelper_mvp.view.MainActivity
import com.leesunae.bebehelper_mvp.view.base.BaseActivity

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpView()
    }

    private fun setUpView() {
        binding.apply {
            incInputEmail.etInput.hint = resources.getString(R.string.sign_in_email)
            incInputPassword.etInput.hint = resources.getString(R.string.sign_in_password)

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