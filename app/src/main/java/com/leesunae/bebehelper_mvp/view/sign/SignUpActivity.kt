package com.leesunae.bebehelper_mvp.view.sign

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
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

    override fun createUserSuccess(message: Boolean) {
        println("signUp_show_message_ $message")
        if (message) {
            presenter.getUserAll()
            finish()
        }
    }

    override fun checkedEmail(email: String, isChecked: Boolean) {
        println("check_email_ $email")
        println("check_email_isChecked_ $isChecked")
        if (isChecked) user.email = email
        user.isCheckedEmail = isChecked
        if (user.isCheckedEmail && user.isCheckedNickname) {
            println("check_email_isChecked_11 $isChecked")
            checkValid()
        }
    }

    override fun checkedNickname(nickname: String, isChecked: Boolean) {
        println("check_nickname_ $nickname")
        println("check_nickname_isChecked_ $isChecked")
        if (isChecked) user.nickname = nickname
        user.isCheckedNickname = isChecked
        if (user.isCheckedEmail && user.isCheckedNickname) {
            println("check_nickname_isChecked_11 $isChecked")
            checkValid()
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
                    user.ageOfChildren = adapterView.getItemAtPosition(position).toString().replace("세", "")
                }

            // 지역 선택
            val areaArray = resources.getStringArray(R.array.area_array)
            val areaAdapter: ArrayAdapter<String> =
                ArrayAdapter(this@SignUpActivity, R.layout.item_select_text, areaArray)
            tvAreaItem.setAdapter(areaAdapter)
            tvAreaItem.onItemClickListener =
                AdapterView.OnItemClickListener { adapterView, view, position, id ->
                    user.area = adapterView.getItemAtPosition(position).toString()
                }

            /* note 현재 미사용
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

             */
        }
    }

    /** 회원가입 버튼 클릭 */
    private fun clickRegister() {
        binding.apply {
            user.email = inputEmail.text.toString()
            user.password = inputPassword.text.toString()
            user.nickname = inputNickname.text.toString()
            println("click_register_email_ ${user.email}")
            println("click_register_password_ ${user.password}")
            println("click_register_nickname_ ${user.nickname}")
//            presenter.createUser(user)

//            presenter.checkEmail(email)
//            presenter.checkNickname(nickname)

            checkEmail()
            checkNickname()
        }
    }

    /** 입력 정보 유효성 확인 */
    private fun checkValid() {
        binding.apply {
            user.gender = findViewById<RadioButton>(rgParentGender.checkedRadioButtonId).text.toString()  // 부모(유저) 성별
            user.childGender = findViewById<RadioButton>(rgChildGender.checkedRadioButtonId).text.toString()  // 아이 성별

            rgParentGender.setOnCheckedChangeListener { group, i ->
                val radioBtn = group.findViewById<RadioButton>(i)
                user.gender = radioBtn.text.toString()
                println("parent_selected_radioBtn_ ${radioBtn.text}")
            }

            rgChildGender.setOnCheckedChangeListener { group, i ->
                val radioBtn = group.findViewById<RadioButton>(i)
                user.childGender = radioBtn.text.toString()
                println("child_selected_radioBtn_ ${radioBtn.text}")
            }

//            val age = if (user.ageOfChildren.isNotEmpty()) user.ageOfChildren else ""
//            user.ageOfChildren = age
            user.area = user.area
        }
        user.isCheckedPassword = checkPassword()
        if (user.isCheckedEmail && user.isCheckedNickname && user.isCheckedPassword && user.gender.isNotEmpty() && user.childGender.isNotEmpty() && user.ageOfChildren.isNotEmpty() && user.area.isNotEmpty()) {
            dialog.apply {
                showDialog("", Utils.string(this@SignUpActivity, R.string.success), true)
                setOkClickListener(object : CustomDialog.OkClickListener {
                    override fun okClick() {
                        //note 로그인 처리 필요
                        presenter.createUser(user)
                    }
                })
            }
        }
    }

    /** 이메일 입력 데이터 및 형식 체크 */
    private fun checkEmail() {
        binding.apply {
            if (user.email.isEmpty()) {   // 이메일 입력하지 않은 경우
                dialog.apply {
                    showDialog(
                        "",
                        Utils.string(this@SignUpActivity, R.string.email_error_empty),
                        true
                    )
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputEmail.requestFocus()
                        }
                    })
                }
            } else if (!checkPatternEmail(user.email)) {     // 이메일 형식 맞지 않을 경우
                dialog.apply {
                    showDialog("", Utils.string(this@SignUpActivity, R.string.email_format), true)
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputEmail.requestFocus()
                        }
                    })
                }
            } else {
                presenter.checkEmail(user.email)
            }
        }
    }

    /** 비밀번호 입력 데이터 및 형식 체크 */
    private fun checkPassword(): Boolean {
        binding.apply {
            val passwordText = inputPassword.text.toString()
            val passwordConfirmText = inputPasswordConfirm.text.toString()
            if (passwordText.isEmpty()) {    // 비밀번호가 입력되지 않은 경우
                dialog.apply {
                    showDialog(
                        "",
                        Utils.string(this@SignUpActivity, R.string.password_error_empty),
                        true
                    )
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputPassword.requestFocus()
                        }
                    })
                }
                return false
            } else if (!checkPatternPassword(passwordText)) {    // 비밀번호 형식이 맞지 않는 경우
                dialog.apply {
                    showDialog(
                        "",
                        Utils.string(this@SignUpActivity, R.string.password_error_format),
                        true
                    )
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputPassword.requestFocus()
                        }
                    })
                }
                return false
            } else if (passwordConfirmText.isEmpty()) {    // 비밀번호 확인이 입력되지 않은 경우
                dialog.apply {
                    showDialog(
                        "",
                        Utils.string(
                            this@SignUpActivity,
                            R.string.password_confirm_error_empty
                        ),
                        true
                    )
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputPasswordConfirm.requestFocus()
                        }
                    })
                }
                return false
            } else if (!checkPatternPassword(passwordConfirmText)) {    // 비밀번호 확인 형식이 맞지 않는 경우
                dialog.apply {
                    showDialog(
                        "",
                        Utils.string(this@SignUpActivity, R.string.password_error_format),
                        true
                    )
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputPasswordConfirm.requestFocus()
                        }
                    })
                }
                return false
            } else if (passwordText != passwordConfirmText) {    // 비밀번호와 비밀번호 확인 정보가 일치하지 않는 경우
                dialog.apply {
                    showDialog(
                        "",
                        Utils.string(
                            this@SignUpActivity,
                            R.string.password_confirm_error_invalid
                        ),
                        true
                    )
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputPasswordConfirm.requestFocus()
                        }
                    })
                }
                return false
            } else {
                user.password = passwordText
                user.passwordConfirm = passwordConfirmText
                return true
            }
        }
    }

    /** 닉네임 입력 데이터 및 형식 체크 */
    private fun checkNickname() {
        binding.apply {
            println("checkNickname_ ${user.nickname}")
            if (user.nickname.isEmpty()) {   // 닉네임 입력하지 않은 경우
                println("checkNickname_1 ${user.nickname}")
                dialog.apply {
                    showDialog(
                        "",
                        Utils.string(this@SignUpActivity, R.string.nickname_error_empty),
                        true
                    )
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputNickname.requestFocus()
                        }
                    })
                }
            } else if (!checkPatternNickname(user.nickname)) {     // 닉네임 형식 맞지 않을 경우
                dialog.apply {
                    showDialog(
                        "",
                        Utils.string(this@SignUpActivity, R.string.name_error_format),
                        true
                    )
                    setOkClickListener(object : CustomDialog.OkClickListener {
                        override fun okClick() {
                            inputNickname.requestFocus()
                        }
                    })
                }
            } else {
                presenter.checkNickname(user.nickname)
            }
        }
    }

    /** 이메일 형식 체크 */
    private fun checkPatternEmail(email: String): Boolean {
        val isCheckedEmail = Pattern.matches(Regex.EMAIL, email)
        user = user.copy(email = email, isCheckedEmail = isCheckedEmail)
        return isCheckedEmail
    }

    /** 비밀번호 형식 체크 */
    private fun checkPatternPassword(password: String): Boolean {
        val isCheckedPassword = Pattern.matches(Regex.PASSWORD, password)
        user = user.copy(password = password, isCheckedPassword = isCheckedPassword)
        return isCheckedPassword
    }

    /** 닉네임 형식 체크 */
    private fun checkPatternNickname(nickname: String): Boolean {
        val isCheckedNickname = Pattern.matches(Regex.NICKNAME, nickname)
        user = user.copy(nickname = nickname, isCheckedNickname = isCheckedNickname)
        return isCheckedNickname
    }
}