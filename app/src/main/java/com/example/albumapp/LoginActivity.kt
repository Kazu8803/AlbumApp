package com.example.albumapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.albumapp.fragments.CreateAccountFragment
import com.example.albumapp.fragments.LoginFragment
import com.example.albumapp.utils.SharedPreferencesControl
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {
    private val PRIVATE_MODE = 0
    private val PREF_NAME = "user_logged"

    val loginFragment: LoginFragment = LoginFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity_main)

        var sharedPref = SharedPreferencesControl()
        sharedPref.instanceSharedPreferences(this)
        if(sharedPref.isUserLogged()) {
            val catalogIntent = Intent(applicationContext, CatalogActivity::class.java)
            startActivity(catalogIntent)
            finish()
        }
        else {
            loginFragment.addSharedPreferences(sharedPref)
            supportFragmentManager.beginTransaction()
                .add(R.id.loginFrameContainer, loginFragment, "login_fragment_container")
                .commit()
        }


    }
}
