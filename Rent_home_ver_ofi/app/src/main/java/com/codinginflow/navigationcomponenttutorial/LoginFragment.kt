package com.codinginflow.navigationcomponenttutorial

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {
    private val emailLiveData = MutableLiveData<String>()
    private val passwordLiveData = MutableLiveData<String>()
    private val RegisterButton=textViewSignUpLogin

    // Zmienna na podstawie której będzie odbywało się logowanie domyslnie bedzie na fals
    var LoginFlag: Boolean = true;


    private val isValidLiveData = MediatorLiveData<Boolean>().apply {
        this.value = false
        addSource(emailLiveData) { email ->
            val password = passwordLiveData.value
            this.value = validateForm(email, password)

        }
        addSource(passwordLiveData) { password ->
            val email = emailLiveData.value
            this.value = validateForm(email, password)
        }
    }

    private fun validateForm(email: String?, password: String?): Boolean {
        val isValidEmail = email != null && email.isNotBlank() && email.contains("@")
        val isValidPassword = password != null && password.isNotBlank() && password.length >= 6
        return isValidEmail && isValidPassword

    }


    // tutaj do dopisane czy lohin i hasło jest w bazie danych
    fun changeLoginFlagTrue() {
        LoginFlag = true;
    }

    fun changeLoginFlagFalse() {
        LoginFlag = false;
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_login, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val emailLayout = inputEmailLogin
        val passwordLayout = inputPasswordLogin
        val loginButton = btnloginLogowanie


        emailLayout.doOnTextChanged { text, _, _, _ ->
            emailLiveData.value = text?.toString()
        }

        passwordLayout.doOnTextChanged { text, _, _, _ ->
            passwordLiveData.value = text?.toString()
        }

        isValidLiveData.observe(viewLifecycleOwner) { isValid ->
            loginButton.isEnabled = isValid
            if (loginButton.isEnabled == true) {
                loginButton.setBackgroundColor(R.drawable.btn_acitiv)
            }
        }

        btnloginLogowanie.setOnClickListener {


            if (LoginFlag == true) {
                emailLayout.setText("")
                passwordLayout.setText("")
//                changeLoginFlagFalse()
                startActivity(Intent(getActivity(), MenuActivity::class.java))


            } else {
                Toast.makeText(
                    context,
                    getString(R.string.logowanieNieprawidłowe),
                    Toast.LENGTH_SHORT
                ).show()
            }


        }
        textViewSignUpLogin.setOnClickListener(){
            val action = LoginFragmentDirections.actionLoginFragmentToRejestracjaFragment2()
            findNavController().navigate(action)

        }


        btnGoogle.setOnClickListener {

            startActivity(Intent(getActivity(), MenuActivity::class.java))
        }
    }


}
