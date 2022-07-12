package com.leesunae.bebehelper_mvp.view.sign

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.leesunae.bebehelper_mvp.Injection
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.data.room.entity.User
import com.leesunae.bebehelper_mvp.databinding.ActivitySignUpBinding
import com.leesunae.bebehelper_mvp.util.OnSingleClickListener
import com.leesunae.bebehelper_mvp.view.base.BaseActivity
import com.leesunae.bebehelper_mvp.view.sign.presenter.SignUpContract
import com.leesunae.bebehelper_mvp.view.sign.presenter.SignUpPresenter

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up),
    SignUpContract.View {
    private lateinit var presenter: SignUpContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SignUpPresenter(Injection.userRepository(), this)
        setUpView()

    }

    override fun showMessage(message: Boolean) {
        println("signUp_show_message_ $message")
        if (message) {
            presenter.getUserAll()
        }
    }

    override fun checkedEmail(isChecked: Boolean) {

    }

    override fun checkedNickname(isChecked: Boolean) {

    }

    private fun setUpView() {
        binding.apply {
            incActionbar.apply {
                tvTitle.text = resources.getString(R.string.sign_up)
                ivBack.setOnClickListener(object : OnSingleClickListener() {
                    override fun onSingleClick(v: View) {
                        finish()
                    }
                })
            }

            // 회원가입 클릭
            tvRegister.setOnClickListener(object : OnSingleClickListener() {
                override fun onSingleClick(v: View) {
                    clickRegister()
                }
            })

            // 아이 나이 선택
            val ageArray = resources.getStringArray(R.array.child_age_array)
            val ageAdapter: ArrayAdapter<String> =
                ArrayAdapter(this@SignUpActivity, R.layout.item_age, ageArray)
            tvAgeItem.setAdapter(ageAdapter)

            tvAgeItem.onItemClickListener =
                AdapterView.OnItemClickListener { adapterView, view, position, id ->
                    tvShowItem.text = adapterView.getItemAtPosition(position).toString()
                }

            // 이메일 중복체크
            tvEmailCheck.setOnClickListener(object : OnSingleClickListener() {
                override fun onSingleClick(v: View) {

                }
            })

            // 닉네임 중복체크
            tvEmailCheck.setOnClickListener(object : OnSingleClickListener() {
                override fun onSingleClick(v: View) {

                }
            })
        }
    }

    /** 회원가입 버튼 클릭 */
    private fun clickRegister() {
        binding.apply {
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()
            val nickname = inputNickname.text.toString()
            println("click_register_email_ $email")
            println("click_register_password_ $password")
            println("click_register_nickname_ $nickname")
            presenter.createUser(email, password, nickname)
        }
    }
}