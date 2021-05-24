package com.codinginflow.navigationcomponenttutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_dodaj_ogloszenie.*


class AddAdvertisment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_dodaj_ogloszenie, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        button_dodaj_ogloszenie.setOnClickListener {
            if (checkBoxDodanieogloszenia.isChecked) {
//                (String title, String data, int price,int activity,String img,String description,String owner,int lat,int LONG)
                val myDB = DatabaseHelper(context)
                myDB.addOgloszenie(
                    addTytulOgloszenia.getText().toString().trim(),"2020",Integer.valueOf(addCenaOgloszenia.getText().toString().trim()),Integer.valueOf(1),"img",addOpisOgloszenia.getText().toString().trim(),"img",1,11)


                val action =AddAdvertismentDirections.actionDodajOgloszenieFragmentToMenuFragment()
                findNavController().navigate(action)

            } else {
                Toast.makeText(activity, "Zatwierdż ogłoszenie!", Toast.LENGTH_SHORT).show();

            }


        }
    }

}