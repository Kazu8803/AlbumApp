package com.example.albumapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorLong
import androidx.fragment.app.FragmentManager
import com.example.albumapp.fragments.CatalogFragment
import com.example.albumapp.fragments.CreateAccountFragment
import com.example.albumapp.fragments.LoginFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.catalog_activity_main.*
import org.w3c.dom.Text
import android.R.color
import android.graphics.*
import android.text.Layout
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.core.graphics.drawable.DrawableCompat


class CatalogActivity : AppCompatActivity() {
        val catalogFragment = CatalogFragment.newInstance()
        lateinit var toolbar: Toolbar

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.catalog_activity_main)
            toolbar = findViewById(R.id.appToolbar)
            setSupportActionBar(toolbar)

            toolbar.setBackgroundColor(getColor(R.color.colorPrimary))

            changeTitleAndColor()
            createNavigationIconAndFunctionality()
            changeColorOverflowIcon()

            supportFragmentManager.beginTransaction()
                .replace(R.id.catalogFrameContainer, catalogFragment , "catalog_fragment_container")
                .commit()

        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logoutMenu -> logoutSession()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun changeColorOverflowIcon(){
        val drawable = toolbar.overflowIcon
        drawable?.mutate()?.let { DrawableCompat.setTint(it, ContextCompat.getColor(this@CatalogActivity, R.color.colorWhite)) }
    }

    private fun changeTitleAndColor(){
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setTitle("")
    }

    private fun createNavigationIconAndFunctionality(){
        toolbar.navigationIcon = getDrawable(R.drawable.ic_left_arrow)
        toolbar.setNavigationOnClickListener(
            { returnPreviousLayout() })
    }

    private fun returnPreviousLayout(){
        supportFragmentManager.popBackStackImmediate()
    }

    private fun logoutSession(){
        val loginIntent = Intent(applicationContext, LoginActivity::class.java)
        deleteSharedPreferences("user_logged")
        startActivity(loginIntent)
        finish()
    }

    private fun receiveDataFromCatalogFragment(){
//        window
    }

}