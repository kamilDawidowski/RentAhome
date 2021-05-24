package com.codinginflow.navigationcomponenttutorial

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*
import java.util.*

class MenuActivity : AppCompatActivity()
{
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navigation_menu_for_fragment) as NavHostFragment
        navController = navHostFragment.
        findNavController()


        // Do braku powrotu w toolbar
        appBarConfiguration= AppBarConfiguration(setOf(R.id.menuFragment,R.id.mapaFragment,R.id.mojeKontoFragment))



//             Pasek gorny
        setSupportActionBar(toolbar2)
        setupActionBarWithNavController(navController,appBarConfiguration)

        // Nwaigacja dolna
        bottomNavigationView.setupWithNavController(navController)














        loadLocate()
        val LanguageSwitch: Switch = findViewById(R.id.SwitchJezyk)
        val DarkMode: Switch = findViewById(R.id.SwitchTryb)

        LanguageSwitch.setOnClickListener {
            if (LanguageSwitch.isChecked) {
                setLocate("en")
                recreate()


            } else {
                setLocate("pl")
                recreate()
            }
        }



        DarkMode.setOnClickListener {


            if (DarkMode.isChecked) {


                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()

            }


        }




    }


    private fun loadLocate() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        if (language != null) {
            setLocate(language)
        }
    }

    private fun setLocate(Lang: String) {

        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

}


