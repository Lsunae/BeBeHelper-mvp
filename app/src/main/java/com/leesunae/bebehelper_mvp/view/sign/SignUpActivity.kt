package com.leesunae.bebehelper_mvp.view.sign

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.leesunae.bebehelper_mvp.Injection
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.constant.Regex
import com.leesunae.bebehelper_mvp.data.model.UserItem
import com.leesunae.bebehelper_mvp.databinding.ActivitySignUpBinding
import com.leesunae.bebehelper_mvp.util.CustomDialog
import com.leesunae.bebehelper_mvp.util.OnSingleClickListener
import com.leesunae.bebehelper_mvp.util.Utils
import com.leesunae.bebehelper_mvp.view.base.BaseActivity
import com.leesunae.bebehelper_mvp.view.sign.presenter.SignUpContract
import com.leesunae.bebehelper_mvp.view.sign.presenter.SignUpPresenter
import java.util.regex.Pattern

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up),
    SignUpContract.View {
    private lateinit var presenter: SignUpContract.Presenter
    private lateinit var dialog: CustomDialog
    private var user: UserItem = UserItem(
        email = "",
        password = "",
        passwordConfirm = "",
        nickname = "",
        gender = "",
        childGender = "",
        ageOfChildren = "",
        area = "",
        image = ""
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dialog = CustomDialog(this)
        presenter = SignUpPresenter(Injection.userRepository(), this)
        setUpView()
        presenter.getUserAll()

    }

    override fun showMessage(message: Boolean) {
        println("signUp_show_message_ $message")
        if (message) {
            presenter.getUserAll()
        }
    }

    override fun checkedEmail(email: String, isChecked: Boolean) {
        if (isChecked) {
            user = user.copy(email = email)
        }
    }

    override fun checkedNickname(nickname: String, isChecked: Boolean) {
        if (isChecked) {
            user = user.copy(nickname = nickname)
        }
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
                ArrayAdapter(this@SignUpActivity, R.layout.item_select_text, ageArray)
            tvAgeItem.setAdapter(ageAdapter)
            tvAgeItem.onItemClickListener =
                AdapterView.OnItemClickListener { adapterView, view, position, id ->
                    tvShowAgeItem.text = adapterView.getItemAtPosition(position).toString()
                }

            // 지역 선택
            val areaArray = resources.getStringArray(R.array.area_array)
            val areaAdapter: ArrayAdapter<String> =
                ArrayAdapter(this@SignUpActivity, R.layout.item_select_text, areaArray)
            tvAreaItem.setAdapter(areaAdapter)
            tvAreaItem.onItemClickListener =
                AdapterView.OnItemClickListener { adapterView, view, position, id ->
                    tvShowAreaItem.text = adapterView.getItemAtPosition(position).toString()
                }

            // 이메일 중복체크
            tvEmailCheck.setOnClickListener(object : OnSingleClickListener() {
                override fun onSingleClick(v: View) {
                    val email = inputEmail.text.toString()
                    presenter.checkEmail(email)
                }
            })

            // 닉네임 중복체크
            tvNickCheck.setOnClickListener(object : OnSingleClickListener() {
                override fun onSingleClick(v: View) {
                    val nickname = inputNickname.text.toString()
                    presenter.checkNickname(nickname)
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

    private fun checkValid() {
        if (checkEmail() && checkPassword())
    }

    /** 이메일 체크 */
    private fun checkEmail(): Boolean {
        binding.apply {
            val emailText = inputEmail.text.toString()

            presenter.checkEmail(emailText)

            if (emailText.isNotEmpty()) {   // 이메일 입력하지 않은 경우
                dialog.apply {
                    showDialog("", Utils.string(this@SignUpActivity, R.string.sign_up_email_error_empty), true)
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputEmail.requestFocus()
                        }
                    })
                }
                return false
            } else if (!checkPatternEmail(emailText)) {     // 이메일 형식 맞지 않을 경우
                dialog.apply {
                    showDialog("", Utils.string(this@SignUpActivity, R.string.email_format), true)
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputEmail.requestFocus()
                        }
                    })
                }
                return false
            } else if () {

            } else {
                return true
            }
        }
    }

    /** 비밀번호 체크 */
    private fun checkPassword(): Boolean {
        binding.apply {
            val passwordText = inputPassword.text.toString()
            val passwordConfirmText = inputPasswordConfirm.text.toString()
            if (passwordText.isNotEmpty()) {    // 비밀번호가 입력되지 않은 경우
                dialog.apply {
                    showDialog("", Utils.string(this@SignUpActivity, R.string.sign_up_password_error_empty), true)
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputPassword.requestFocus()
                        }
                    })
                }
                return false
            } else if (!checkPatternPassword(passwordText)) {    // 비밀번호 형식이 맞지 않는 경우
                dialog.apply {
                    showDialog("", Utils.string(this@SignUpActivity, R.string.sign_up_password_error_format), true)
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputPassword.requestFocus()
                        }
                    })
                }
                return false
            } else if (passwordConfirmText.isNotEmpty()) {    // 비밀번호 확인이 입력되지 않은 경우
                dialog.apply {
                    showDialog("", Utils.string(this@SignUpActivity, R.string.sign_up_password_confirm_error_empty), true)
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputPasswordConfirm.requestFocus()
                        }
                    })
                }
                return false
            } else if (!checkPatternPassword(passwordConfirmText)) {    // 비밀번호 확인 형식이 맞지 않는 경우
                dialog.apply {
                    showDialog("", Utils.string(this@SignUpActivity, R.string.sign_up_password_error_format), true)
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputPasswordConfirm.requestFocus()
                        }
                    })
                }
                return false
            } else if (passwordText != passwordConfirmText) {    // 비밀번호와 비밀번호 확인 정보가 일치하지 않는 경우
                dialog.apply {
                    showDialog("", Utils.string(this@SignUpActivity, R.string.sign_up_password_confirm_error_invalid), true)
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputPasswordConfirm.requestFocus()
                        }
                    })
                }
                return false
            } else {
                return true
            }
        }
    }

    /** 닉네임 체크 */
    private fun checkNickname(): Boolean {
        binding.apply {
            val nicknameText = inputNickname.text.toString()
            if (nicknameText.isNotEmpty()) {   // 닉네임 입력하지 않은 경우
                dialog.apply {
                    showDialog("", Utils.string(this@SignUpActivity, R.string.sign_up_nickname_error_empty), true)
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputNickname.requestFocus()
                        }
                    })
                }
                return false
            } else if (!checkPatternNickname(nicknameText)) {     // 닉네임 형식 맞지 않을 경우
                dialog.apply {
                    showDialog("", Utils.string(this@SignUpActivity, R.string.sign_up_name_error_format), true)
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputNickname.requestFocus()
                        }
                    })
                }
                return false
            } else {
                return true
            }
        }
    }

    /** 이메일 형식 체크 */
    fun checkPatternEmail(email: String): Boolean {
        val isCheckedEmail = Pattern.matches(Regex.EMAIL, email)
        user = user.copy(email = email, isCheckedEmail = isCheckedEmail)
        return isCheckedEmail
    }

    /** 비밀번호 형식 체크 */
    fun checkPatternPassword(password: String): Boolean {
        val isCheckedPassword = Pattern.matches(Regex.PASSWORD, password)
        user = user.copy(password = password, isCheckedPassword = isCheckedPassword)
        return isCheckedPassword
    }

    /** 닉네임 형식 체크 */
    fun checkPatternNickname(nickname: String): Boolean {
        val isCheckedNickname = Pattern.matches(Regex.NICKNAME, nickname)
        user = user.copy(nickname = nickname, isCheckedNickname = isCheckedNickname)
        return isCheckedNickname
    }
}