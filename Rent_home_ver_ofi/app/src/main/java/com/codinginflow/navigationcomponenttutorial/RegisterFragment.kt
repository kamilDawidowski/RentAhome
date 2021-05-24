package com.codinginflow.navigationcomponenttutorial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_rejestracja.*

class RegisterFragment : Fragment() {

    private val ToLoginLayoutButton = alreadyHaveAccount

    private val emailLiveData = MutableLiveData<String>()
    private val passwordLiveData = MutableLiveData<String>()
    private val passwordConfirmLiveData = MutableLiveData<String>()
    private val usernameLiveData = MutableLiveData<String>()

    private val RegisterButton = btnRegister

    // Zmienna na podstawie której będzie odbywało się logowanie domyslnie bedzie na fals
    var RegisterFlag: Boolean = true;

    fun changeRegisterFlagTrue() {
        RegisterFlag = true;
    }

    fun changeRegisterFlagFalse() {
        RegisterFlag = false;
    }


    private val isValidLiveData = MediatorLiveData<Boolean>().apply {
        this.value = false

        addSource(emailLiveData) { email ->
            val password = passwordLiveData.value
            val passwordConfirm = passwordConfirmLiveData.value
            val username =usernameLiveData.value
            this.value = validateForm(email, password,passwordConfirm,username)

        }
        addSource(passwordLiveData) { password ->
            val email = emailLiveData.value
            val passwordConfirm = passwordConfirmLiveData.value
            val username =usernameLiveData.value
            this.value = validateForm(email, password,passwordConfirm,username)
        }
        addSource(passwordConfirmLiveData) { passwordConfirm ->
            val email = emailLiveData.value
            val password = passwordLiveData.value
            val username =usernameLiveData.value
            this.value = validateForm(email,password, passwordConfirm,username)
        }
        addSource(usernameLiveData) { username ->
            val email = emailLiveData.value
            val password = passwordLiveData.value
            val passwordConfirm = passwordConfirmLiveData.value
            this.value = validateForm(email,password, passwordConfirm,username)
        }
    }


    private fun validateForm(email: String?, password: String?,passwordConfirm:String?,username:String?): Boolean {
        val isValidEmail = email != null && email.isNotBlank() && email.contains("@")
        val isUsernameValid=username !=null && username.isNotBlank()&& username.length>=5
        val isValidPassword = password != null && password.isNotBlank() && password.length >= 6
        val isValidConfirmPassword = password != null && password.isNotBlank() && password.length >= 6 && password==passwordConfirm

        return isValidEmail && isValidPassword && isUsernameValid && isValidConfirmPassword

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_rejestracja, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        inputEmail.doOnTextChanged { text, _, _, _ ->
            emailLiveData.value = text?.toString()
        }

        inputPasswordRegister.doOnTextChanged { text, _, _, _ ->
            passwordLiveData.value = text?.toString()
        }
        inputConformPasswordRegister.doOnTextChanged { text, _, _, _ ->
            passwordConfirmLiveData.value = text?.toString()
        }

        inputUsername.doOnTextChanged { text, _, _, _ ->
            usernameLiveData.value = text?.toString()
        }
        isValidLiveData.observe(viewLifecycleOwner) { isValid ->
            btnRegister.isEnabled = isValid
            if (btnRegister.isEnabled == true) {
                btnRegister.setBackgroundColor(R.drawable.btn_acitiv)
            }
        }




        btnRegister.setOnClickListener {
            val username = inputUsername.text.toString()
            val action =
                RegisterFragmentDirections.actionRejestracjaFragment2ToRejestracjaPoprawnaFragment2(
                    username
                )
            inputUsername.setText("")
            inputConformPasswordRegister.setText("")
            inputPasswordRegister.setText("")
            inputEmail.setText("")


            findNavController().navigate(action)
        }

        alreadyHaveAccount.setOnClickListener {
            val action = RegisterFragmentDirections.actionRejestracjaFragment2ToLoginFragment()
            findNavController().navigate(action)
        }
    }


//
//    Login.setOnClickListener {
//        if (Username.text.isNullOrBlank() && Password.text.isNullOrBlank()) {
//            Toast.makeText(this, "Prosze wypełnić wymagane pola", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, "${Username.text} został zalogowany ", Toast.LENGTH_LONG)
//                .show()
//
//        }
//
//
//    }
//


//
//    override fun onUserInteraction() {
//        val Username: EditText = findViewById(R.id.username)
//        val Password: EditText = findViewById(R.id.password)
//        val Login: Button = findViewById(R.id.login)
//        val HelpText: TextView =findViewById(R.id.helpText)
//
//        super.onUserInteraction()
//        if (Password.isFocused) {
//            if (Password.length() < 6) {
//                HelpText.setText(getString(R.string.Info_haslo_za_krótkie))
//            }
//            else {
//                HelpText.setText("")
//            }
//
//        }
//
//    }
//


}