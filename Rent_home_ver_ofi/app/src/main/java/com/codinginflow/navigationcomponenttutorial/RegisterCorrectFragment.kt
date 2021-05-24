package com.codinginflow.navigationcomponenttutorial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import kotlinx.android.synthetic.main.fragment_rejestracja_poprawna.*

class RegisterCorrectFragment : Fragment() {

    private val args: RegisterCorrectFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rejestracja_poprawna, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username.text=args.Username

        button_login.setOnClickListener {

            val action =RegisterCorrectFragmentDirections.actionRejestracjaPoprawnaFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }
}