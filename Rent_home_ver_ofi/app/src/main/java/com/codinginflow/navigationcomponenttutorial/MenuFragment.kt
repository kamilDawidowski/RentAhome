package com.codinginflow.navigationcomponenttutorial

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_menu.*


class MenuFragment : Fragment() {
//    private lateinit var linearLayoutManager: LinearLayoutManager
    // Baza danych przechowanie do wyswietlenie w recycleview
    var myDB: DatabaseHelper? = null
//    var ogloszenie_id: ArrayList<String>? = null;
//    var ogloszenie_title: ArrayList<kotlin.String?>? = null;
//    var ogloszenie_description: ArrayList<kotlin.String?>? = null;
//    var ogloszenie_price: ArrayList<kotlin.String?>? = null;
//    var customAdapter: CustomAdapter? = null;


//    var customAdapter: CustomAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_menu, container, false)

    }

    val myD = DatabaseHelper(context)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        addbutton.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToDodajOgloszenieFragment()
            findNavController().navigate(action)

        }
//        storeDataInArrays()
//        customAdapter = CustomAdapter(
//            context, ogloszenie_id, ogloszenie_title, ogloszenie_price, ogloszenie_description)
//        recyclerview.setAdapter(customAdapter);
//        recyclerview.setLayoutManager(LinearLayoutManager(getActivity()));

    }

//    fun storeDataInArrays() {
//        val cursor: Cursor = myDB!!.readAllData()
//        if (cursor.getCount() === 0) {
////            empty_imageview.setVisibility(View.VISIBLE)
////            no_data.setVisibility(View.VISIBLE)
//        } else {
//            while (cursor.moveToNext()) {
//                ogloszenie_id?.add(cursor.getString(0))
//                ogloszenie_title?.add(cursor.getString(1))
//                ogloszenie_description?.add(cursor.getString(2))
//                ogloszenie_price?.add(cursor.getString(3))
//            }
////            empty_imageview.setVisibility(View.GONE)
////            no_data.setVisibility(View.GONE)
//        }
//    }
//
}