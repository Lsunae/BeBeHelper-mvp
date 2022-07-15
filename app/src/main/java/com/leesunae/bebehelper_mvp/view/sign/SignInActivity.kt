package com.leesunae.bebehelper_mvp.view.sign

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.leesunae.bebehelper_mvp.Injection
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.Session
import com.leesunae.bebehelper_mvp.data.room.entity.User
import com.leesunae.bebehelper_mvp.databinding.ActivitySignInBinding
import com.leesunae.bebehelper_mvp.util.CustomDialog
import com.leesunae.bebehelper_mvp.util.OnSingleClickListener
import com.leesunae.bebehelper_mvp.util.Utils
import com.leesunae.bebehelper_mvp.view.MainActivity
import com.leesunae.bebehelper_mvp.view.base.BaseActivity
import com.leesunae.bebehelper_mvp.view.sign.presenter.SignInContract
import com.leesunae.bebehelper_mvp.view.sign.presenter.SignInPresenter

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in),
    SignInContract.View {
    private lateinit var presenter: SignInContract.Presenter
    private lateinit var dialog: CustomDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("signIn_save_user_data_ ${Session.getUser()}")

        dialog = CustomDialog(this)

        Session.checkLogin(listener = { isLogin ->
            println("isLogin__ $isLogin")
            if (isLogin) {
                println("isLogin_true_ ")
                goToMain()
            } else {
                println("isLogin_false_ ")
            }
        })

        presenter = SignInPresenter(Injection.userRepository(), this)
        setUpView()
    }

    override fun loginSuccess(isLogin: Boolean, user: User?) {
        runOnUiThread {
            if (isLogin) {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            } else {
                showDialog(R.string.sign_in_fail, binding.inputEmail, true)
            }
        }
    }

    /** 메인 화면 이동(로그인) */
    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    private fun setUpView() {
        binding.apply {
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

            // 로그인 클릭
            tvLogin.setOnClickListener(object : OnSingleClickListener() {
                override fun onSingleClick(v: View) {
                    clickLogin()
                }
            })
        }
    }

    private fun clickLogin() {
        binding.apply {
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()
            println("signIn_email_ $email")
            println("signIn_password_ $password")
            if (email.isEmpty()) {
                showDialog(R.string.email_error_empty, inputEmail, true)
            } else if (password.isEmpty()) {
                showDialog(R.string.password_error_empty, inputPassword, true)
            } else {
                presenter.login(email, password)
            }
        }
    }

    private fun showDialog(resourceId: Int, inputView: TextInputEditText, isOkClick: Boolean) {
        dialog.apply {
            showDialog("", Utils.string(this@SignInActivity, resourceId), isOkClick)
            if (isOkClick) {
                setOkClickListener(object : CustomDialog.OkClickListener {
                    override fun okClick() {
                        inputView.requestFocus()
                    }
                })
            }
        }
    }
}