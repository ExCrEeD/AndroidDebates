package com.example.debates.Actividades

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.TextView
import com.example.debates.PoliDebates
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.debates.R


class Menu : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        showMenuByRol(navView)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.MenuScroll, R.id.MenuCreateDebate, R.id.MenuReport,
                R.id.MenuRegisterUser, R.id.MenuRegisterUser,R.id.MenuLogout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        setUserInfo()
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setUserInfo(){
        var txt_username : TextView = findViewById(R.id.txt_userName) as TextView
        txt_username.setText(PoliDebates.localStorage.getName()+" "+ PoliDebates.localStorage.getSecondName())
        var txt_userEmail : TextView = findViewById(R.id.txt_userEmail) as TextView
        txt_userEmail.setText(PoliDebates.localStorage.getEmail())
    }

    private fun showMenuByRol(navView: NavigationView)
    {
        navView.getMenu().findItem(R.id.MenuReport).setVisible(PoliDebates.localStorage.getMenuReport())
        navView.getMenu().findItem(R.id.MenuCreateDebate).setVisible(PoliDebates.localStorage.getMenuCreateDebate())
        navView.getMenu().findItem(R.id.MenuRegisterUser).setVisible(PoliDebates.localStorage.getMenuRegisterUser())
    }
}
